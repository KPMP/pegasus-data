package org.kpmp.dataSummary;

import java.util.ArrayList;
import java.util.List;

import org.kpmp.DataTypeEnum;
import org.kpmp.FullDataTypeEnum;
import org.kpmp.OmicsTypeEnum;
import org.kpmp.TissueTypeEnum;
import org.kpmp.repositorySummary.TissueTypeSummaryByDataType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataSummaryService {
	private DataSummaryRepository dataSummaryRepository;

	@Autowired
	public DataSummaryService(DataSummaryRepository dataSummaryRepository) {
		this.dataSummaryRepository = dataSummaryRepository;
	}

	public List<TissueTypeSummaryByDataType> getSummaryData() {
		List<TissueTypeSummaryByDataType> summaryData = new ArrayList<>();

		summaryData.add(new TissueTypeSummaryByDataType(
			OmicsTypeEnum.TRANSCRIPTOMICS.getEnum(),
			FullDataTypeEnum.SPATIAL_TRANSCRIPTOMICS_FULL.getFullName(),
			DataTypeEnum.SPATIAL_TRANSCRIPTOMICS.getAbbreviation(),
			dataSummaryRepository.getDataSummaryCount(TissueTypeEnum.AKI.getParticipantTissueType(), FullDataTypeEnum.SPATIAL_TRANSCRIPTOMICS_FULL.getFullName()),
			dataSummaryRepository.getDataSummaryCount(TissueTypeEnum.CKD.getParticipantTissueType(), FullDataTypeEnum.SPATIAL_TRANSCRIPTOMICS_FULL.getFullName()),
			dataSummaryRepository.getDataSummaryCount(TissueTypeEnum.HEALTHY_REFERENCE.getParticipantTissueType(), FullDataTypeEnum.SPATIAL_TRANSCRIPTOMICS_FULL.getFullName()),
			dataSummaryRepository.getParticipantSummaryCount(FullDataTypeEnum.SPATIAL_TRANSCRIPTOMICS_FULL.getFullName())));

		summaryData.add(new TissueTypeSummaryByDataType(
			OmicsTypeEnum.TRANSCRIPTOMICS.getEnum(),
			FullDataTypeEnum.TISSUE_IMAGING_AND_CYTOMETRY_3D_FULL.getFullName(),
			DataTypeEnum.TISSUE_IMAGING_AND_CYTOMETRY_3D.getAbbreviation(),
			dataSummaryRepository.getDataSummaryCount(TissueTypeEnum.AKI.getParticipantTissueType(), FullDataTypeEnum.TISSUE_IMAGING_AND_CYTOMETRY_3D_FULL.getFullName()),
			dataSummaryRepository.getDataSummaryCount(TissueTypeEnum.CKD.getParticipantTissueType(), FullDataTypeEnum.TISSUE_IMAGING_AND_CYTOMETRY_3D_FULL.getFullName()),
			dataSummaryRepository.getDataSummaryCount(TissueTypeEnum.HEALTHY_REFERENCE.getParticipantTissueType(), FullDataTypeEnum.TISSUE_IMAGING_AND_CYTOMETRY_3D_FULL.getFullName()),
			dataSummaryRepository.getParticipantSummaryCount(FullDataTypeEnum.TISSUE_IMAGING_AND_CYTOMETRY_3D_FULL.getFullName())));

		summaryData.add(new TissueTypeSummaryByDataType(
			OmicsTypeEnum.TRANSCRIPTOMICS.getEnum(),
			FullDataTypeEnum.LIGHT_MICROSCOPIC_WHOLE_SLIDE_IMAGES_FULL.getFullName(),
			DataTypeEnum.LIGHT_MICROSCOPIC_WHOLE_SLIDE_IMAGES.getAbbreviation(),
			dataSummaryRepository.getDataSummaryCount(TissueTypeEnum.AKI.getParticipantTissueType(), FullDataTypeEnum.LIGHT_MICROSCOPIC_WHOLE_SLIDE_IMAGES_FULL.getFullName()),
			dataSummaryRepository.getDataSummaryCount(TissueTypeEnum.CKD.getParticipantTissueType(), FullDataTypeEnum.LIGHT_MICROSCOPIC_WHOLE_SLIDE_IMAGES_FULL.getFullName()),
			dataSummaryRepository.getDataSummaryCount(TissueTypeEnum.HEALTHY_REFERENCE.getParticipantTissueType(), FullDataTypeEnum.LIGHT_MICROSCOPIC_WHOLE_SLIDE_IMAGES_FULL.getFullName()),
			dataSummaryRepository.getParticipantSummaryCount(FullDataTypeEnum.LIGHT_MICROSCOPIC_WHOLE_SLIDE_IMAGES_FULL.getFullName())));

		summaryData.add(new TissueTypeSummaryByDataType(
			OmicsTypeEnum.TRANSCRIPTOMICS.getEnum(),
			FullDataTypeEnum.CODEX_FULL.getFullName(),
			DataTypeEnum.CODEX.getAbbreviation(),
			dataSummaryRepository.getDataSummaryCount(TissueTypeEnum.AKI.getParticipantTissueType(), FullDataTypeEnum.CODEX_FULL.getFullName()),
			dataSummaryRepository.getDataSummaryCount(TissueTypeEnum.CKD.getParticipantTissueType(), FullDataTypeEnum.CODEX_FULL.getFullName()),
			dataSummaryRepository.getDataSummaryCount(TissueTypeEnum.HEALTHY_REFERENCE.getParticipantTissueType(), FullDataTypeEnum.CODEX_FULL.getFullName()),
			dataSummaryRepository.getParticipantSummaryCount(FullDataTypeEnum.CODEX_FULL.getFullName())));
			
		summaryData.add(new TissueTypeSummaryByDataType(
			OmicsTypeEnum.TRANSCRIPTOMICS.getEnum(),
			FullDataTypeEnum.SPATIAL_LIPIDOMICS_FULL.getFullName(),
			DataTypeEnum.SPATIAL_LIPIDOMICS.getAbbreviation(),
			dataSummaryRepository.getDataSummaryLinkCount(TissueTypeEnum.AKI.getParticipantTissueType(), FullDataTypeEnum.SPATIAL_LIPIDOMICS_FULL.getFullName()),
			dataSummaryRepository.getDataSummaryLinkCount(TissueTypeEnum.CKD.getParticipantTissueType(), FullDataTypeEnum.SPATIAL_LIPIDOMICS_FULL.getFullName()),
			dataSummaryRepository.getDataSummaryLinkCount(TissueTypeEnum.HEALTHY_REFERENCE.getParticipantTissueType(), FullDataTypeEnum.SPATIAL_LIPIDOMICS_FULL.getFullName()),
			dataSummaryRepository.getParticipantSummaryLinkCount(FullDataTypeEnum.SPATIAL_LIPIDOMICS_FULL.getFullName())));
		
			summaryData.add(new TissueTypeSummaryByDataType(
			OmicsTypeEnum.TRANSCRIPTOMICS.getEnum(),
			FullDataTypeEnum.SPATIAL_METABOLOMICS_FULL.getFullName(),
			DataTypeEnum.SPATIAL_METABOLOMICS.getAbbreviation(),
			dataSummaryRepository.getDataSummaryLinkCount(TissueTypeEnum.AKI.getParticipantTissueType(), FullDataTypeEnum.SPATIAL_METABOLOMICS_FULL.getFullName()),
			dataSummaryRepository.getDataSummaryLinkCount(TissueTypeEnum.CKD.getParticipantTissueType(), FullDataTypeEnum.SPATIAL_METABOLOMICS_FULL.getFullName()),
			dataSummaryRepository.getDataSummaryLinkCount(TissueTypeEnum.HEALTHY_REFERENCE.getParticipantTissueType(), FullDataTypeEnum.SPATIAL_METABOLOMICS_FULL.getFullName()),
			dataSummaryRepository.getParticipantSummaryLinkCount(FullDataTypeEnum.SPATIAL_METABOLOMICS_FULL.getFullName())));

		summaryData.add(new TissueTypeSummaryByDataType(
			OmicsTypeEnum.TRANSCRIPTOMICS.getEnum(),
			FullDataTypeEnum.SPATIAL_NGLYCOMICS_FULL.getFullName(),
			DataTypeEnum.SPATIAL_NGLYCOMICS.getAbbreviation(),
			dataSummaryRepository.getDataSummaryLinkCount(TissueTypeEnum.AKI.getParticipantTissueType(), FullDataTypeEnum.SPATIAL_NGLYCOMICS_FULL.getFullName()),
			dataSummaryRepository.getDataSummaryLinkCount(TissueTypeEnum.CKD.getParticipantTissueType(), FullDataTypeEnum.SPATIAL_NGLYCOMICS_FULL.getFullName()),
			dataSummaryRepository.getDataSummaryLinkCount(TissueTypeEnum.HEALTHY_REFERENCE.getParticipantTissueType(), FullDataTypeEnum.SPATIAL_NGLYCOMICS_FULL.getFullName()),
			dataSummaryRepository.getParticipantSummaryLinkCount(FullDataTypeEnum.SPATIAL_NGLYCOMICS_FULL.getFullName())));
		return summaryData;
	}
}
