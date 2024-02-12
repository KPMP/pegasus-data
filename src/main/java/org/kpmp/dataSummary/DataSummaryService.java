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
import org.kpmp.TissueTypeEnum;
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
	
	private static final String CONTROLLED_ACCESS = "controlled";
	private static final String OPEN_ACCESS = "open";

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
			if (experimentalStrategy.isEmpty()
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
		if (experimentalStrategyValue.getAccess().equalsIgnoreCase(OPEN_ACCESS)) {
			atlasRepoSummaryRow.addToOpenCount(experimentalStrategyValue.getCount());
		} else if (experimentalStrategyValue.getAccess().equalsIgnoreCase(CONTROLLED_ACCESS)) {
			atlasRepoSummaryRow.addToControlledCount(experimentalStrategyValue.getCount());
		} else {
			throw new Exception(
					"Unexpected access value while getting summary counts: " + experimentalStrategyValue.getAccess());
		}
	}

	public List<DataTypeSummary> getSummaryData() {
		List<DataTypeSummary> summaryData = new ArrayList<>();

		summaryData.add(new DataTypeSummary(OmicsTypeEnum.TRANSCRIPTOMICS.getEnum(),
				FullDataTypeEnum.SPATIAL_TRANSCRIPTOMICS_FULL.getFullName(),
				DataTypeEnum.SPATIAL_TRANSCRIPTOMICS.getAbbreviation(),
				dataSummaryRepository.getDataSummaryCount(TissueTypeEnum.AKI.getParticipantTissueType(),
						FullDataTypeEnum.SPATIAL_TRANSCRIPTOMICS_FULL.getFullName()),
				dataSummaryRepository.getDataSummaryCount(TissueTypeEnum.CKD.getParticipantTissueType(),
						FullDataTypeEnum.SPATIAL_TRANSCRIPTOMICS_FULL.getFullName()),
				dataSummaryRepository.getDataSummaryCount(TissueTypeEnum.HEALTHY_REFERENCE.getParticipantTissueType(),
						FullDataTypeEnum.SPATIAL_TRANSCRIPTOMICS_FULL.getFullName()),
				dataSummaryRepository.getDataSummaryCount(TissueTypeEnum.DMR.getParticipantTissueType(),
						FullDataTypeEnum.SPATIAL_TRANSCRIPTOMICS_FULL.getFullName()),
				dataSummaryRepository
						.getParticipantSummaryCount(FullDataTypeEnum.SPATIAL_TRANSCRIPTOMICS_FULL.getFullName())));

		summaryData.add(new DataTypeSummary(OmicsTypeEnum.TRANSCRIPTOMICS.getEnum(),
				FullDataTypeEnum.TISSUE_IMAGING_AND_CYTOMETRY_3D_FULL.getFullName(),
				DataTypeEnum.TISSUE_IMAGING_AND_CYTOMETRY_3D.getAbbreviation(),
				dataSummaryRepository.getDataSummaryCount(TissueTypeEnum.AKI.getParticipantTissueType(),
						FullDataTypeEnum.TISSUE_IMAGING_AND_CYTOMETRY_3D_FULL.getFullName()),
				dataSummaryRepository.getDataSummaryCount(TissueTypeEnum.CKD.getParticipantTissueType(),
						FullDataTypeEnum.TISSUE_IMAGING_AND_CYTOMETRY_3D_FULL.getFullName()),
				dataSummaryRepository.getDataSummaryCount(TissueTypeEnum.HEALTHY_REFERENCE.getParticipantTissueType(),
						FullDataTypeEnum.TISSUE_IMAGING_AND_CYTOMETRY_3D_FULL.getFullName()),
				dataSummaryRepository.getDataSummaryCount(TissueTypeEnum.DMR.getParticipantTissueType(),
						FullDataTypeEnum.TISSUE_IMAGING_AND_CYTOMETRY_3D_FULL.getFullName()),
				dataSummaryRepository.getParticipantSummaryCount(
						FullDataTypeEnum.TISSUE_IMAGING_AND_CYTOMETRY_3D_FULL.getFullName())));

		summaryData.add(new DataTypeSummary(OmicsTypeEnum.TRANSCRIPTOMICS.getEnum(),
				FullDataTypeEnum.LIGHT_MICROSCOPIC_WHOLE_SLIDE_IMAGES_FULL.getFullName(),
				DataTypeEnum.LIGHT_MICROSCOPIC_WHOLE_SLIDE_IMAGES.getAbbreviation(),
				dataSummaryRepository.getDataSummaryCount(TissueTypeEnum.AKI.getParticipantTissueType(),
						FullDataTypeEnum.LIGHT_MICROSCOPIC_WHOLE_SLIDE_IMAGES_FULL.getFullName()),
				dataSummaryRepository.getDataSummaryCount(TissueTypeEnum.CKD.getParticipantTissueType(),
						FullDataTypeEnum.LIGHT_MICROSCOPIC_WHOLE_SLIDE_IMAGES_FULL.getFullName()),
				dataSummaryRepository.getDataSummaryCount(TissueTypeEnum.HEALTHY_REFERENCE.getParticipantTissueType(),
						FullDataTypeEnum.LIGHT_MICROSCOPIC_WHOLE_SLIDE_IMAGES_FULL.getFullName()),
				dataSummaryRepository.getDataSummaryCount(TissueTypeEnum.DMR.getParticipantTissueType(),
						FullDataTypeEnum.LIGHT_MICROSCOPIC_WHOLE_SLIDE_IMAGES_FULL.getFullName()),
				dataSummaryRepository.getParticipantSummaryCount(
						FullDataTypeEnum.LIGHT_MICROSCOPIC_WHOLE_SLIDE_IMAGES_FULL.getFullName())));

		summaryData.add(new DataTypeSummary(OmicsTypeEnum.TRANSCRIPTOMICS.getEnum(),
				FullDataTypeEnum.CODEX_FULL.getFullName(), DataTypeEnum.CODEX.getAbbreviation(),
				dataSummaryRepository.getDataSummaryCount(TissueTypeEnum.AKI.getParticipantTissueType(),
						FullDataTypeEnum.CODEX_FULL.getFullName()),
				dataSummaryRepository.getDataSummaryCount(TissueTypeEnum.CKD.getParticipantTissueType(),
						FullDataTypeEnum.CODEX_FULL.getFullName()),
				dataSummaryRepository.getDataSummaryCount(TissueTypeEnum.HEALTHY_REFERENCE.getParticipantTissueType(),
						FullDataTypeEnum.CODEX_FULL.getFullName()),
				dataSummaryRepository.getDataSummaryCount(TissueTypeEnum.DMR.getParticipantTissueType(),
						FullDataTypeEnum.CODEX_FULL.getFullName()),
				dataSummaryRepository.getParticipantSummaryCount(FullDataTypeEnum.CODEX_FULL.getFullName())));

		summaryData.add(new DataTypeSummary(OmicsTypeEnum.TRANSCRIPTOMICS.getEnum(),
				FullDataTypeEnum.SPATIAL_LIPIDOMICS_FULL.getFullName(),
				DataTypeEnum.SPATIAL_LIPIDOMICS.getAbbreviation(),
				dataSummaryRepository.getDataSummaryLinkCount(TissueTypeEnum.AKI.getParticipantTissueType(),
						FullDataTypeEnum.SPATIAL_LIPIDOMICS_FULL.getFullName()),
				dataSummaryRepository.getDataSummaryLinkCount(TissueTypeEnum.CKD.getParticipantTissueType(),
						FullDataTypeEnum.SPATIAL_LIPIDOMICS_FULL.getFullName()),
				dataSummaryRepository.getDataSummaryLinkCount(
						TissueTypeEnum.HEALTHY_REFERENCE.getParticipantTissueType(),
						FullDataTypeEnum.SPATIAL_LIPIDOMICS_FULL.getFullName()),
				dataSummaryRepository.getDataSummaryLinkCount(TissueTypeEnum.DMR.getParticipantTissueType(),
						FullDataTypeEnum.SPATIAL_LIPIDOMICS_FULL.getFullName()),
				dataSummaryRepository
						.getParticipantSummaryLinkCount(FullDataTypeEnum.SPATIAL_LIPIDOMICS_FULL.getFullName())));

		summaryData.add(new DataTypeSummary(OmicsTypeEnum.TRANSCRIPTOMICS.getEnum(),
				FullDataTypeEnum.SPATIAL_METABOLOMICS_FULL.getFullName(),
				DataTypeEnum.SPATIAL_METABOLOMICS.getAbbreviation(),
				dataSummaryRepository.getDataSummaryLinkCount(TissueTypeEnum.AKI.getParticipantTissueType(),
						FullDataTypeEnum.SPATIAL_METABOLOMICS_FULL.getFullName()),
				dataSummaryRepository.getDataSummaryLinkCount(TissueTypeEnum.CKD.getParticipantTissueType(),
						FullDataTypeEnum.SPATIAL_METABOLOMICS_FULL.getFullName()),
				dataSummaryRepository.getDataSummaryLinkCount(
						TissueTypeEnum.HEALTHY_REFERENCE.getParticipantTissueType(),
						FullDataTypeEnum.SPATIAL_METABOLOMICS_FULL.getFullName()),
				dataSummaryRepository.getDataSummaryLinkCount(TissueTypeEnum.DMR.getParticipantTissueType(),
						FullDataTypeEnum.SPATIAL_METABOLOMICS_FULL.getFullName()),
				dataSummaryRepository
						.getParticipantSummaryLinkCount(FullDataTypeEnum.SPATIAL_METABOLOMICS_FULL.getFullName())));

		summaryData.add(new DataTypeSummary(OmicsTypeEnum.TRANSCRIPTOMICS.getEnum(),
				FullDataTypeEnum.SPATIAL_NGLYCOMICS_FULL.getFullName(),
				DataTypeEnum.SPATIAL_NGLYCOMICS.getAbbreviation(),
				dataSummaryRepository.getDataSummaryLinkCount(TissueTypeEnum.AKI.getParticipantTissueType(),
						FullDataTypeEnum.SPATIAL_NGLYCOMICS_FULL.getFullName()),
				dataSummaryRepository.getDataSummaryLinkCount(TissueTypeEnum.CKD.getParticipantTissueType(),
						FullDataTypeEnum.SPATIAL_NGLYCOMICS_FULL.getFullName()),
				dataSummaryRepository.getDataSummaryLinkCount(
						TissueTypeEnum.HEALTHY_REFERENCE.getParticipantTissueType(),
						FullDataTypeEnum.SPATIAL_NGLYCOMICS_FULL.getFullName()),
				dataSummaryRepository.getDataSummaryLinkCount(TissueTypeEnum.DMR.getParticipantTissueType(),
						FullDataTypeEnum.SPATIAL_NGLYCOMICS_FULL.getFullName()),
				dataSummaryRepository
						.getParticipantSummaryLinkCount(FullDataTypeEnum.SPATIAL_NGLYCOMICS_FULL.getFullName())));
		return summaryData;
	}
}
