package org.kpmp.dataSummary;

import java.util.ArrayList;
import java.util.List;

import org.kpmp.DataTypeEnum;
import org.kpmp.FullDataTypeEnum;
import org.kpmp.OmicsTypeEnum;
import org.kpmp.TissueTypeEnum;
import org.kpmp.datasetSummary.DatasetSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataSummaryService {
	private DataSummaryRepository dataSummaryRepository;

	@Autowired
	public DataSummaryService(DataSummaryRepository dataSummaryRepository) {
		this.dataSummaryRepository = dataSummaryRepository;
	}

	public List<DatasetSummary> getSummaryData() {
		List<DatasetSummary> summaryData = new ArrayList<>();

		summaryData.add(new DatasetSummary(
			OmicsTypeEnum.TRANSCRIPTOMICS.getEnum(),
			FullDataTypeEnum.SPATIAL_TRANSCRIPTOMICS_FULL.getFull(),
			DataTypeEnum.SPATIAL_TRANSCRIPTOMICS.getAbbreviation(),
			dataSummaryRepository.getDataSummaryCount(TissueTypeEnum.AKI.getParticipantTissueType(), FullDataTypeEnum.SPATIAL_TRANSCRIPTOMICS_FULL.getFull()),
			dataSummaryRepository.getDataSummaryCount(TissueTypeEnum.CKD.getParticipantTissueType(), FullDataTypeEnum.SPATIAL_TRANSCRIPTOMICS_FULL.getFull()),
			dataSummaryRepository.getDataSummaryCount(TissueTypeEnum.HEALTHY_REFERENCE.getParticipantTissueType(), FullDataTypeEnum.SPATIAL_TRANSCRIPTOMICS_FULL.getFull()),
			dataSummaryRepository.getParticipantSummaryCount(FullDataTypeEnum.SPATIAL_TRANSCRIPTOMICS_FULL.getFull())));

		summaryData.add(new DatasetSummary(
			OmicsTypeEnum.TRANSCRIPTOMICS.getEnum(),
			FullDataTypeEnum.TISSUE_IMAGING_AND_CYTOMETRY_3D_FULL.getFull(),
			DataTypeEnum.TISSUE_IMAGING_AND_CYTOMETRY_3D.getAbbreviation(),
			dataSummaryRepository.getDataSummaryCount(TissueTypeEnum.AKI.getParticipantTissueType(), FullDataTypeEnum.TISSUE_IMAGING_AND_CYTOMETRY_3D_FULL.getFull()),
			dataSummaryRepository.getDataSummaryCount(TissueTypeEnum.CKD.getParticipantTissueType(), FullDataTypeEnum.TISSUE_IMAGING_AND_CYTOMETRY_3D_FULL.getFull()),
			dataSummaryRepository.getDataSummaryCount(TissueTypeEnum.HEALTHY_REFERENCE.getParticipantTissueType(), FullDataTypeEnum.TISSUE_IMAGING_AND_CYTOMETRY_3D_FULL.getFull()),
			dataSummaryRepository.getParticipantSummaryCount(FullDataTypeEnum.TISSUE_IMAGING_AND_CYTOMETRY_3D_FULL.getFull())));

		summaryData.add(new DatasetSummary(
			OmicsTypeEnum.TRANSCRIPTOMICS.getEnum(),
			FullDataTypeEnum.LIGHT_MICROSCOPIC_WHOLE_SLIDE_IMAGES_FULL.getFull(),
			DataTypeEnum.LIGHT_MICROSCOPIC_WHOLE_SLIDE_IMAGES.getAbbreviation(),
			dataSummaryRepository.getDataSummaryCount(TissueTypeEnum.AKI.getParticipantTissueType(), FullDataTypeEnum.LIGHT_MICROSCOPIC_WHOLE_SLIDE_IMAGES_FULL.getFull()),
			dataSummaryRepository.getDataSummaryCount(TissueTypeEnum.CKD.getParticipantTissueType(), FullDataTypeEnum.LIGHT_MICROSCOPIC_WHOLE_SLIDE_IMAGES_FULL.getFull()),
			dataSummaryRepository.getDataSummaryCount(TissueTypeEnum.HEALTHY_REFERENCE.getParticipantTissueType(), FullDataTypeEnum.LIGHT_MICROSCOPIC_WHOLE_SLIDE_IMAGES_FULL.getFull()),
			dataSummaryRepository.getParticipantSummaryCount(FullDataTypeEnum.LIGHT_MICROSCOPIC_WHOLE_SLIDE_IMAGES_FULL.getFull())));

		summaryData.add(new DatasetSummary(
			OmicsTypeEnum.TRANSCRIPTOMICS.getEnum(),
			FullDataTypeEnum.CODEX_FULL.getFull(),
			DataTypeEnum.CODEX.getAbbreviation(),
			dataSummaryRepository.getDataSummaryCount(TissueTypeEnum.AKI.getParticipantTissueType(), FullDataTypeEnum.CODEX_FULL.getFull()),
			dataSummaryRepository.getDataSummaryCount(TissueTypeEnum.CKD.getParticipantTissueType(), FullDataTypeEnum.CODEX_FULL.getFull()),
			dataSummaryRepository.getDataSummaryCount(TissueTypeEnum.HEALTHY_REFERENCE.getParticipantTissueType(), FullDataTypeEnum.CODEX_FULL.getFull()),
			dataSummaryRepository.getParticipantSummaryCount(FullDataTypeEnum.CODEX_FULL.getFull())));
			
		summaryData.add(new DatasetSummary(
			OmicsTypeEnum.TRANSCRIPTOMICS.getEnum(),
			FullDataTypeEnum.SPATIAL_LIPIDOMICS_FULL.getFull(),
			DataTypeEnum.SPATIAL_LIPIDOMICS.getAbbreviation(),
			dataSummaryRepository.getDataSummaryCount(TissueTypeEnum.AKI.getParticipantTissueType(), FullDataTypeEnum.SPATIAL_LIPIDOMICS_FULL.getFull()),
			dataSummaryRepository.getDataSummaryCount(TissueTypeEnum.CKD.getParticipantTissueType(), FullDataTypeEnum.SPATIAL_LIPIDOMICS_FULL.getFull()),
			dataSummaryRepository.getDataSummaryCount(TissueTypeEnum.HEALTHY_REFERENCE.getParticipantTissueType(), FullDataTypeEnum.SPATIAL_LIPIDOMICS_FULL.getFull()),
			dataSummaryRepository.getParticipantSummaryCount(FullDataTypeEnum.SPATIAL_LIPIDOMICS_FULL.getFull())));
		
			summaryData.add(new DatasetSummary(
			OmicsTypeEnum.TRANSCRIPTOMICS.getEnum(),
			FullDataTypeEnum.SPATIAL_METABOLOMICS_FULL.getFull(),
			DataTypeEnum.SPATIAL_METABOLOMICS.getAbbreviation(),
			dataSummaryRepository.getDataSummaryCount(TissueTypeEnum.AKI.getParticipantTissueType(), FullDataTypeEnum.SPATIAL_METABOLOMICS_FULL.getFull()),
			dataSummaryRepository.getDataSummaryCount(TissueTypeEnum.CKD.getParticipantTissueType(), FullDataTypeEnum.SPATIAL_METABOLOMICS_FULL.getFull()),
			dataSummaryRepository.getDataSummaryCount(TissueTypeEnum.HEALTHY_REFERENCE.getParticipantTissueType(), FullDataTypeEnum.SPATIAL_METABOLOMICS_FULL.getFull()),
			dataSummaryRepository.getParticipantSummaryCount(FullDataTypeEnum.SPATIAL_METABOLOMICS_FULL.getFull())));

		summaryData.add(new DatasetSummary(
			OmicsTypeEnum.TRANSCRIPTOMICS.getEnum(),
			FullDataTypeEnum.SPATIAL_NGLYCOMICS_FULL.getFull(),
			DataTypeEnum.SPATIAL_NGLYCOMICS.getAbbreviation(),
			dataSummaryRepository.getDataSummaryCount(TissueTypeEnum.AKI.getParticipantTissueType(), FullDataTypeEnum.SPATIAL_NGLYCOMICS_FULL.getFull()),
			dataSummaryRepository.getDataSummaryCount(TissueTypeEnum.CKD.getParticipantTissueType(), FullDataTypeEnum.SPATIAL_NGLYCOMICS_FULL.getFull()),
			dataSummaryRepository.getDataSummaryCount(TissueTypeEnum.HEALTHY_REFERENCE.getParticipantTissueType(), FullDataTypeEnum.SPATIAL_NGLYCOMICS_FULL.getFull()),
			dataSummaryRepository.getParticipantSummaryCount(FullDataTypeEnum.SPATIAL_NGLYCOMICS_FULL.getFull())));
		return summaryData;
	}
}
