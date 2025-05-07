package org.kpmp.dataSummary;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kpmp.FullDataTypeEnum;
import org.kpmp.OmicsTypeEnum;
import org.kpmp.EnrollmentCategoryEnum;
import org.kpmp.file.ARFileInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DataSummaryService {
	
	@Value("${experiment.label.clinicalStudyData}")
	private String CLINICAL_STUDY_DATA;
	@Value("${experiment.label.biomarkers}")
	private String BIOMARKERS;
	@Value("${experiment.category.biomarker}")
	private String BIOMARKER;

	private DataSummaryRepository dataSummaryRepository;
	private AtlasRepoSummaryRepository repoSummaryRepository;
	private ARFileInfoService fileInfoService;

	@Autowired
	public DataSummaryService(DataSummaryRepository dataSummaryRepository,
			AtlasRepoSummaryRepository repoSummaryRepository, ARFileInfoService fileInfoService) {
		this.dataSummaryRepository = dataSummaryRepository;
		this.repoSummaryRepository = repoSummaryRepository;
		this.fileInfoService = fileInfoService;
	}

	public AtlasRepoSummaryResult getAtlasRepoSummary() throws Exception {
		List<ExperimentalStrategyValue> experimentalStrategies = repoSummaryRepository.findAll();
		Map<String, AtlasRepoSummaryRow> summaryMap = new HashMap<>();
		
		for (ExperimentalStrategyValue experimentalStrategyValue : experimentalStrategies) {
			String experimentalStrategy = experimentalStrategyValue.getExperimentalStrategy();
			if ((experimentalStrategy == null || experimentalStrategy.isEmpty() || experimentalStrategy.equals(""))
					&& experimentalStrategyValue.getDataType().equalsIgnoreCase(CLINICAL_STUDY_DATA)) {
				experimentalStrategy = CLINICAL_STUDY_DATA;
			} else if (experimentalStrategyValue.getDataCategory().equalsIgnoreCase(BIOMARKER)) {
				experimentalStrategy = BIOMARKERS;
			}

			if (summaryMap.containsKey(experimentalStrategy)) {
				AtlasRepoSummaryRow atlasRepoSummaryRow = summaryMap.get(experimentalStrategy);
				setCounts(experimentalStrategyValue, atlasRepoSummaryRow);
			} else {
				AtlasRepoSummaryRow row = new AtlasRepoSummaryRow(experimentalStrategy,
						getLinkInformation(experimentalStrategyValue));
				setCounts(experimentalStrategyValue, row);
				summaryMap.put(experimentalStrategy, row);
			}
		}

		Collection<AtlasRepoSummaryRow> values = summaryMap.values();
		List<AtlasRepoSummaryRow> results = new ArrayList<>(values);
		results.sort(new Comparator<AtlasRepoSummaryRow>() {
			@Override
			public int compare(AtlasRepoSummaryRow one, AtlasRepoSummaryRow two) {
				return one.getOmicsType().compareToIgnoreCase(two.getOmicsType());
			}
		});

		return new AtlasRepoSummaryResult(results, fileInfoService.getRepositoryTotalFileCount());
	}

	private AtlasRepositoryLinkInformation getLinkInformation(ExperimentalStrategyValue experimentalStrategy) {
		if (experimentalStrategy.getDataCategory().equalsIgnoreCase(BIOMARKER)
				|| experimentalStrategy.getDataType().equalsIgnoreCase(CLINICAL_STUDY_DATA)) {
			return new AtlasRepositoryLinkInformation(AtlasRepositoryLinkInformation.DATA_CATEGORY, experimentalStrategy.getDataCategory());
		} else {
			return new AtlasRepositoryLinkInformation(AtlasRepositoryLinkInformation.EXPERIMENTAL_STRATEGY,
					experimentalStrategy.getExperimentalStrategy());
		}
	}

	private void setCounts(ExperimentalStrategyValue experimentalStrategyValue, AtlasRepoSummaryRow atlasRepoSummaryRow)
			throws Exception {
		if (experimentalStrategyValue.getDataCategory().equalsIgnoreCase(BIOMARKER)) {
			atlasRepoSummaryRow.setAkiCount(
				dataSummaryRepository.getRepoBiomarkerSummaryCount(
					EnrollmentCategoryEnum.AKI.getParticipantEnrollmentCategory()
				)
			);
			atlasRepoSummaryRow.setCkdCount(
				dataSummaryRepository.getRepoBiomarkerSummaryCount(
					EnrollmentCategoryEnum.CKD.getParticipantEnrollmentCategory()
				)
			);
			atlasRepoSummaryRow.setHrtCount(
				dataSummaryRepository.getRepoBiomarkerSummaryCount(
					EnrollmentCategoryEnum.HEALTHY_REFERENCE.getParticipantEnrollmentCategory()
				)
			);
			atlasRepoSummaryRow.setDmrCount(
				dataSummaryRepository.getRepoBiomarkerSummaryCount(
					EnrollmentCategoryEnum.DMR.getParticipantEnrollmentCategory()
				)
			);
			atlasRepoSummaryRow.setTotalCount(
				dataSummaryRepository.getRepoBiomarkerSummaryTotal()
			);
		}
		else {
			atlasRepoSummaryRow.setAkiCount(
				dataSummaryRepository.getRepoDataSummaryCount(
					EnrollmentCategoryEnum.AKI.getParticipantEnrollmentCategory(), 
					experimentalStrategyValue.getExperimentalStrategy()
				)
			);
			atlasRepoSummaryRow.setCkdCount(
				dataSummaryRepository.getRepoDataSummaryCount(
					EnrollmentCategoryEnum.CKD.getParticipantEnrollmentCategory(), 
					experimentalStrategyValue.getExperimentalStrategy()
				)
			);
			atlasRepoSummaryRow.setHrtCount(
				dataSummaryRepository.getRepoDataSummaryCount(
					EnrollmentCategoryEnum.HEALTHY_REFERENCE.getParticipantEnrollmentCategory(), 
					experimentalStrategyValue.getExperimentalStrategy()
				)
			);
			atlasRepoSummaryRow.setDmrCount(
				dataSummaryRepository.getRepoDataSummaryCount(
					EnrollmentCategoryEnum.DMR.getParticipantEnrollmentCategory(), 
					experimentalStrategyValue.getExperimentalStrategy()
				)
			);
			atlasRepoSummaryRow.setTotalCount(
				dataSummaryRepository.getRepoDataSummaryTotal(
					experimentalStrategyValue.getExperimentalStrategy()
				)
			);
		}
	}

	public List<DataTypeSummary> getSummaryData() {
		List<DataTypeSummary> summaryData = new ArrayList<>();
		List<String> svDataTypes = dataSummaryRepository.getSpatialViewerDataTypes();

		for ( String dataType : svDataTypes) {
			summaryData.add(new DataTypeSummary(OmicsTypeEnum.TRANSCRIPTOMICS.getEnum(),
					dataType, FullDataTypeEnum.fromLong(dataType).getAbbreviation(),
					dataSummaryRepository.getDataSummaryCount(EnrollmentCategoryEnum.AKI.getParticipantEnrollmentCategory(), dataType),
					dataSummaryRepository.getDataSummaryCount(EnrollmentCategoryEnum.CKD.getParticipantEnrollmentCategory(), dataType),
					dataSummaryRepository.getDataSummaryCount(EnrollmentCategoryEnum.HEALTHY_REFERENCE.getParticipantEnrollmentCategory(), dataType),
					dataSummaryRepository.getDataSummaryCount(EnrollmentCategoryEnum.DMR.getParticipantEnrollmentCategory(), dataType),
					dataSummaryRepository.getDataSummaryTotal(dataType),
					dataSummaryRepository.getParticipantSummaryCount(dataType)));
		}

		List<String> linkDataTypes = dataSummaryRepository.getSpatialViewerLinkDataTypes();
		for ( String dataType : linkDataTypes) {
			summaryData.add(new DataTypeSummary(OmicsTypeEnum.TRANSCRIPTOMICS.getEnum(),
					dataType, FullDataTypeEnum.fromLong(dataType).getAbbreviation(),
					dataSummaryRepository.getDataSummaryLinkCount(EnrollmentCategoryEnum.AKI.getParticipantEnrollmentCategory(), dataType),
					dataSummaryRepository.getDataSummaryLinkCount(EnrollmentCategoryEnum.CKD.getParticipantEnrollmentCategory(), dataType),
					dataSummaryRepository.getDataSummaryLinkCount(EnrollmentCategoryEnum.HEALTHY_REFERENCE.getParticipantEnrollmentCategory(), dataType),
					dataSummaryRepository.getDataSummaryLinkCount(EnrollmentCategoryEnum.DMR.getParticipantEnrollmentCategory(), dataType),
					dataSummaryRepository.getDataSummaryTotal(dataType),
					dataSummaryRepository.getParticipantSummaryCount(dataType)));
		}

		return summaryData;
	}
}
