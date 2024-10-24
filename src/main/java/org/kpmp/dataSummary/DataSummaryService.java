package org.kpmp.dataSummary;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kpmp.DataTypeEnum;
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

		summaryData.add(new DataTypeSummary(OmicsTypeEnum.TRANSCRIPTOMICS.getEnum(),
				FullDataTypeEnum.SPATIAL_TRANSCRIPTOMICS_FULL.getFullName(),
				DataTypeEnum.SPATIAL_TRANSCRIPTOMICS.getAbbreviation(),
				dataSummaryRepository.getDataSummaryCount(EnrollmentCategoryEnum.AKI.getParticipantEnrollmentCategory(),
						FullDataTypeEnum.SPATIAL_TRANSCRIPTOMICS_FULL.getFullName()),
				dataSummaryRepository.getDataSummaryCount(EnrollmentCategoryEnum.CKD.getParticipantEnrollmentCategory(),
						FullDataTypeEnum.SPATIAL_TRANSCRIPTOMICS_FULL.getFullName()),
				dataSummaryRepository.getDataSummaryCount(EnrollmentCategoryEnum.HEALTHY_REFERENCE.getParticipantEnrollmentCategory(),
						FullDataTypeEnum.SPATIAL_TRANSCRIPTOMICS_FULL.getFullName()),
				dataSummaryRepository.getDataSummaryCount(EnrollmentCategoryEnum.DMR.getParticipantEnrollmentCategory(),
						FullDataTypeEnum.SPATIAL_TRANSCRIPTOMICS_FULL.getFullName()),
				dataSummaryRepository.getDataSummaryTotal(FullDataTypeEnum.SPATIAL_TRANSCRIPTOMICS_FULL.getFullName()),
				dataSummaryRepository
						.getParticipantSummaryCount(FullDataTypeEnum.SPATIAL_TRANSCRIPTOMICS_FULL.getFullName())));

		summaryData.add(new DataTypeSummary(OmicsTypeEnum.TRANSCRIPTOMICS.getEnum(),
				FullDataTypeEnum.TISSUE_IMAGING_AND_CYTOMETRY_3D_FULL.getFullName(),
				DataTypeEnum.TISSUE_IMAGING_AND_CYTOMETRY_3D.getAbbreviation(),
				dataSummaryRepository.getDataSummaryCount(EnrollmentCategoryEnum.AKI.getParticipantEnrollmentCategory(),
						FullDataTypeEnum.TISSUE_IMAGING_AND_CYTOMETRY_3D_FULL.getFullName()),
				dataSummaryRepository.getDataSummaryCount(EnrollmentCategoryEnum.CKD.getParticipantEnrollmentCategory(),
						FullDataTypeEnum.TISSUE_IMAGING_AND_CYTOMETRY_3D_FULL.getFullName()),
				dataSummaryRepository.getDataSummaryCount(EnrollmentCategoryEnum.HEALTHY_REFERENCE.getParticipantEnrollmentCategory(),
						FullDataTypeEnum.TISSUE_IMAGING_AND_CYTOMETRY_3D_FULL.getFullName()),
				dataSummaryRepository.getDataSummaryCount(EnrollmentCategoryEnum.DMR.getParticipantEnrollmentCategory(),
						FullDataTypeEnum.TISSUE_IMAGING_AND_CYTOMETRY_3D_FULL.getFullName()),
				dataSummaryRepository.getDataSummaryTotal(FullDataTypeEnum.TISSUE_IMAGING_AND_CYTOMETRY_3D_FULL.getFullName()),
				dataSummaryRepository.getParticipantSummaryCount(
						FullDataTypeEnum.TISSUE_IMAGING_AND_CYTOMETRY_3D_FULL.getFullName())));

		summaryData.add(new DataTypeSummary(OmicsTypeEnum.TRANSCRIPTOMICS.getEnum(),
				FullDataTypeEnum.LIGHT_MICROSCOPIC_WHOLE_SLIDE_IMAGES_FULL.getFullName(),
				DataTypeEnum.LIGHT_MICROSCOPIC_WHOLE_SLIDE_IMAGES.getAbbreviation(),
				dataSummaryRepository.getDataSummaryCount(EnrollmentCategoryEnum.AKI.getParticipantEnrollmentCategory(),
						FullDataTypeEnum.LIGHT_MICROSCOPIC_WHOLE_SLIDE_IMAGES_FULL.getFullName()),
				dataSummaryRepository.getDataSummaryCount(EnrollmentCategoryEnum.CKD.getParticipantEnrollmentCategory(),
						FullDataTypeEnum.LIGHT_MICROSCOPIC_WHOLE_SLIDE_IMAGES_FULL.getFullName()),
				dataSummaryRepository.getDataSummaryCount(EnrollmentCategoryEnum.HEALTHY_REFERENCE.getParticipantEnrollmentCategory(),
						FullDataTypeEnum.LIGHT_MICROSCOPIC_WHOLE_SLIDE_IMAGES_FULL.getFullName()),
				dataSummaryRepository.getDataSummaryCount(EnrollmentCategoryEnum.DMR.getParticipantEnrollmentCategory(),
						FullDataTypeEnum.LIGHT_MICROSCOPIC_WHOLE_SLIDE_IMAGES_FULL.getFullName()),
				dataSummaryRepository.getDataSummaryTotal(
						FullDataTypeEnum.LIGHT_MICROSCOPIC_WHOLE_SLIDE_IMAGES_FULL.getFullName()),
				dataSummaryRepository.getParticipantSummaryCount(
						FullDataTypeEnum.LIGHT_MICROSCOPIC_WHOLE_SLIDE_IMAGES_FULL.getFullName())));

		summaryData.add(new DataTypeSummary(OmicsTypeEnum.TRANSCRIPTOMICS.getEnum(),
				FullDataTypeEnum.CODEX_FULL.getFullName(), DataTypeEnum.CODEX.getAbbreviation(),
				dataSummaryRepository.getDataSummaryCount(EnrollmentCategoryEnum.AKI.getParticipantEnrollmentCategory(),
						FullDataTypeEnum.CODEX_FULL.getFullName()),
				dataSummaryRepository.getDataSummaryCount(EnrollmentCategoryEnum.CKD.getParticipantEnrollmentCategory(),
						FullDataTypeEnum.CODEX_FULL.getFullName()),
				dataSummaryRepository.getDataSummaryCount(EnrollmentCategoryEnum.HEALTHY_REFERENCE.getParticipantEnrollmentCategory(),
						FullDataTypeEnum.CODEX_FULL.getFullName()),
				dataSummaryRepository.getDataSummaryCount(EnrollmentCategoryEnum.DMR.getParticipantEnrollmentCategory(),
						FullDataTypeEnum.CODEX_FULL.getFullName()),
				dataSummaryRepository.getDataSummaryTotal(FullDataTypeEnum.CODEX_FULL.getFullName()),
				dataSummaryRepository.getParticipantSummaryCount(FullDataTypeEnum.CODEX_FULL.getFullName())));

		summaryData.add(new DataTypeSummary(OmicsTypeEnum.TRANSCRIPTOMICS.getEnum(),
				FullDataTypeEnum.SPATIAL_LIPIDOMICS_FULL.getFullName(),
				DataTypeEnum.SPATIAL_LIPIDOMICS.getAbbreviation(),
				dataSummaryRepository.getDataSummaryLinkCount(EnrollmentCategoryEnum.AKI.getParticipantEnrollmentCategory(),
						FullDataTypeEnum.SPATIAL_LIPIDOMICS_FULL.getFullName()),
				dataSummaryRepository.getDataSummaryLinkCount(EnrollmentCategoryEnum.CKD.getParticipantEnrollmentCategory(),
						FullDataTypeEnum.SPATIAL_LIPIDOMICS_FULL.getFullName()),
				dataSummaryRepository.getDataSummaryLinkCount(
						EnrollmentCategoryEnum.HEALTHY_REFERENCE.getParticipantEnrollmentCategory(),
						FullDataTypeEnum.SPATIAL_LIPIDOMICS_FULL.getFullName()),
				dataSummaryRepository.getDataSummaryLinkCount(EnrollmentCategoryEnum.DMR.getParticipantEnrollmentCategory(),
						FullDataTypeEnum.SPATIAL_LIPIDOMICS_FULL.getFullName()),
				dataSummaryRepository.getDataSummaryLinkTotal(FullDataTypeEnum.SPATIAL_LIPIDOMICS_FULL.getFullName()),
				dataSummaryRepository
						.getParticipantSummaryLinkCount(FullDataTypeEnum.SPATIAL_LIPIDOMICS_FULL.getFullName())));

		summaryData.add(new DataTypeSummary(OmicsTypeEnum.TRANSCRIPTOMICS.getEnum(),
				FullDataTypeEnum.SPATIAL_METABOLOMICS_FULL.getFullName(),
				DataTypeEnum.SPATIAL_METABOLOMICS.getAbbreviation(),
				dataSummaryRepository.getDataSummaryLinkCount(EnrollmentCategoryEnum.AKI.getParticipantEnrollmentCategory(),
						FullDataTypeEnum.SPATIAL_METABOLOMICS_FULL.getFullName()),
				dataSummaryRepository.getDataSummaryLinkCount(EnrollmentCategoryEnum.CKD.getParticipantEnrollmentCategory(),
						FullDataTypeEnum.SPATIAL_METABOLOMICS_FULL.getFullName()),
				dataSummaryRepository.getDataSummaryLinkCount(
						EnrollmentCategoryEnum.HEALTHY_REFERENCE.getParticipantEnrollmentCategory(),
						FullDataTypeEnum.SPATIAL_METABOLOMICS_FULL.getFullName()),
				dataSummaryRepository.getDataSummaryLinkCount(EnrollmentCategoryEnum.DMR.getParticipantEnrollmentCategory(),
						FullDataTypeEnum.SPATIAL_METABOLOMICS_FULL.getFullName()),
				dataSummaryRepository.getDataSummaryLinkTotal(FullDataTypeEnum.SPATIAL_METABOLOMICS_FULL.getFullName()),
				dataSummaryRepository
						.getParticipantSummaryLinkCount(FullDataTypeEnum.SPATIAL_METABOLOMICS_FULL.getFullName())));

		summaryData.add(new DataTypeSummary(OmicsTypeEnum.TRANSCRIPTOMICS.getEnum(),
				FullDataTypeEnum.SPATIAL_NGLYCOMICS_FULL.getFullName(),
				DataTypeEnum.SPATIAL_NGLYCOMICS.getAbbreviation(),
				dataSummaryRepository.getDataSummaryLinkCount(EnrollmentCategoryEnum.AKI.getParticipantEnrollmentCategory(),
						FullDataTypeEnum.SPATIAL_NGLYCOMICS_FULL.getFullName()),
				dataSummaryRepository.getDataSummaryLinkCount(EnrollmentCategoryEnum.CKD.getParticipantEnrollmentCategory(),
						FullDataTypeEnum.SPATIAL_NGLYCOMICS_FULL.getFullName()),
				dataSummaryRepository.getDataSummaryLinkCount(
						EnrollmentCategoryEnum.HEALTHY_REFERENCE.getParticipantEnrollmentCategory(),
						FullDataTypeEnum.SPATIAL_NGLYCOMICS_FULL.getFullName()),
				dataSummaryRepository.getDataSummaryLinkCount(EnrollmentCategoryEnum.DMR.getParticipantEnrollmentCategory(),
						FullDataTypeEnum.SPATIAL_NGLYCOMICS_FULL.getFullName()),
				dataSummaryRepository.getDataSummaryLinkTotal(FullDataTypeEnum.SPATIAL_NGLYCOMICS_FULL.getFullName()),
				dataSummaryRepository
						.getParticipantSummaryLinkCount(FullDataTypeEnum.SPATIAL_NGLYCOMICS_FULL.getFullName())));
        
        summaryData.add(new DataTypeSummary(OmicsTypeEnum.TRANSCRIPTOMICS.getEnum(),
                FullDataTypeEnum.IMAGING_MASS_CYTOMETRY_FULL.getFullName(),
                DataTypeEnum.IMAGING_MASS_CYTOMETRY.getAbbreviation(),
                dataSummaryRepository.getDataSummaryCount(EnrollmentCategoryEnum.AKI.getParticipantEnrollmentCategory(),
                        FullDataTypeEnum.IMAGING_MASS_CYTOMETRY_FULL.getFullName()),
                dataSummaryRepository.getDataSummaryCount(EnrollmentCategoryEnum.CKD.getParticipantEnrollmentCategory(),
                        FullDataTypeEnum.IMAGING_MASS_CYTOMETRY_FULL.getFullName()),
                dataSummaryRepository.getDataSummaryCount(
                        EnrollmentCategoryEnum.HEALTHY_REFERENCE.getParticipantEnrollmentCategory(),
                        FullDataTypeEnum.IMAGING_MASS_CYTOMETRY_FULL.getFullName()),
                dataSummaryRepository.getDataSummaryCount(EnrollmentCategoryEnum.DMR.getParticipantEnrollmentCategory(),
                        FullDataTypeEnum.IMAGING_MASS_CYTOMETRY_FULL.getFullName()),
				dataSummaryRepository.getDataSummaryTotal(FullDataTypeEnum.IMAGING_MASS_CYTOMETRY_FULL.getFullName()),
                dataSummaryRepository
                        .getParticipantSummaryCount(FullDataTypeEnum.IMAGING_MASS_CYTOMETRY_FULL.getFullName())));
		return summaryData;
	}
}
