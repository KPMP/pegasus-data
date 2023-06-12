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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataSummaryService {
	private static final String EXPERIMENTAL_STRATEGY = "experimental_strategy";
	private static final String DATA_CATEGORY = "data_category";
	private static final String CONTROLLED_ACCESS = "controlled";
	private static final String OPEN_ACCESS = "open";
	private static final String BIOMARKERS = "Biomarkers";
	private static final String BIOMARKER = "Biomarker";
	private static final String CLINICAL_STUDY_DATA = "Clinical Study Data";

	private DataSummaryRepository dataSummaryRepository;
	private AtlasRepoSummaryRepository repoSummaryRepository;

	@Autowired
	public DataSummaryService(DataSummaryRepository dataSummaryRepository,
			AtlasRepoSummaryRepository repoSummaryRepository) {
		this.dataSummaryRepository = dataSummaryRepository;
		this.repoSummaryRepository = repoSummaryRepository;
	}

	public AtlasRepoSummaryResult getAtlasRepoSummary() throws Exception {
		List<ExperimentalStrategyValue> experimentalStrategies = repoSummaryRepository.findAll();
		Map<String, AtlasRepoSummaryRow> summaryMap = new HashMap<>();
		int totalFiles = 0;
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
				totalFiles += experimentalStrategyValue.getCount();
			} else {
				AtlasRepoSummaryRow row = new AtlasRepoSummaryRow(experimentalStrategy,
						getLinkInformation(experimentalStrategyValue));
				setCounts(experimentalStrategyValue, row);
				summaryMap.put(experimentalStrategy, row);
				totalFiles += experimentalStrategyValue.getCount();
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

		return new AtlasRepoSummaryResult(results, totalFiles);
	}

	private AtlasRepoSummaryLinkInformation getLinkInformation(ExperimentalStrategyValue experimentalStrategy) {
		if (experimentalStrategy.getDataCategory().equalsIgnoreCase(BIOMARKER)
				|| experimentalStrategy.getDataType().equalsIgnoreCase(CLINICAL_STUDY_DATA)) {
			return new AtlasRepoSummaryLinkInformation(DATA_CATEGORY, experimentalStrategy.getDataCategory());
		} else {
			return new AtlasRepoSummaryLinkInformation(EXPERIMENTAL_STRATEGY,
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
