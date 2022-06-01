package org.kpmp.datasetSummary;

import java.util.ArrayList;
import java.util.List;
import org.kpmp.OmicsTypeEnum;
import org.kpmp.DataTypeEnum;
import org.kpmp.FullDataTypeEnum;
import org.kpmp.TissueTypeEnum;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class DatasetSummaryService {
	private DatasetSummaryRepository datasetSummaryRepository;

	@Autowired
	public DatasetSummaryService(DatasetSummaryRepository datasetSummaryRepository) {
		this.datasetSummaryRepository = datasetSummaryRepository;
	}

	public List<DatasetSummary> getSummaryData() {
		ArrayList<DatasetSummary> summaryData = new ArrayList<DatasetSummary>();
		summaryData.add(new DatasetSummary(
			OmicsTypeEnum.TRANSCRIPTOMICS.getEnum(),
			FullDataTypeEnum.SPATIAL_TRANSCRIPTOMICS_FULL.getFull(),
			DataTypeEnum.SPATIAL_TRANSCRIPTOMICS.getAbbreviation(),
			datasetSummaryRepository.getDataSummaryCount(TissueTypeEnum.AKI.getParticipantTissueType(), FullDataTypeEnum.SPATIAL_TRANSCRIPTOMICS_FULL.getFull()),
			datasetSummaryRepository.getDataSummaryCount(TissueTypeEnum.CKD.getParticipantTissueType(), FullDataTypeEnum.SPATIAL_TRANSCRIPTOMICS_FULL.getFull()),
			datasetSummaryRepository.getDataSummaryCount(TissueTypeEnum.HEALTHY_REFERENCE.getParticipantTissueType(), FullDataTypeEnum.SPATIAL_TRANSCRIPTOMICS_FULL.getFull()),
			datasetSummaryRepository.getParticipantSummaryCount(FullDataTypeEnum.SPATIAL_TRANSCRIPTOMICS_FULL.getFull())));

		summaryData.add(new DatasetSummary(
			OmicsTypeEnum.TRANSCRIPTOMICS.getEnum(),
			FullDataTypeEnum.TISSUE_IMAGING_AND_CYTOMETRY_3D_FULL.getFull(),
			DataTypeEnum.TISSUE_IMAGING_AND_CYTOMETRY_3D.getAbbreviation(),
			datasetSummaryRepository.getDataSummaryCount(TissueTypeEnum.AKI.getParticipantTissueType(), FullDataTypeEnum.TISSUE_IMAGING_AND_CYTOMETRY_3D_FULL.getFull()),
			datasetSummaryRepository.getDataSummaryCount(TissueTypeEnum.CKD.getParticipantTissueType(), FullDataTypeEnum.TISSUE_IMAGING_AND_CYTOMETRY_3D_FULL.getFull()),
			datasetSummaryRepository.getDataSummaryCount(TissueTypeEnum.HEALTHY_REFERENCE.getParticipantTissueType(), FullDataTypeEnum.TISSUE_IMAGING_AND_CYTOMETRY_3D_FULL.getFull()),
			datasetSummaryRepository.getParticipantSummaryCount(FullDataTypeEnum.TISSUE_IMAGING_AND_CYTOMETRY_3D_FULL.getFull())));

		summaryData.add(new DatasetSummary(
			OmicsTypeEnum.TRANSCRIPTOMICS.getEnum(),
			FullDataTypeEnum.LIGHT_MICROSCOPIC_WHOLE_SLIDE_IMAGES_FULL.getFull(),
			DataTypeEnum.LIGHT_MICROSCOPIC_WHOLE_SLIDE_IMAGES.getAbbreviation(),
			datasetSummaryRepository.getDataSummaryCount(TissueTypeEnum.AKI.getParticipantTissueType(), FullDataTypeEnum.LIGHT_MICROSCOPIC_WHOLE_SLIDE_IMAGES_FULL.getFull()),
			datasetSummaryRepository.getDataSummaryCount(TissueTypeEnum.CKD.getParticipantTissueType(), FullDataTypeEnum.LIGHT_MICROSCOPIC_WHOLE_SLIDE_IMAGES_FULL.getFull()),
			datasetSummaryRepository.getDataSummaryCount(TissueTypeEnum.HEALTHY_REFERENCE.getParticipantTissueType(), FullDataTypeEnum.LIGHT_MICROSCOPIC_WHOLE_SLIDE_IMAGES_FULL.getFull()),
			datasetSummaryRepository.getParticipantSummaryCount(FullDataTypeEnum.LIGHT_MICROSCOPIC_WHOLE_SLIDE_IMAGES_FULL.getFull())));
		return summaryData;
	}
}
