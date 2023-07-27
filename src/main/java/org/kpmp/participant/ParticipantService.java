package org.kpmp.participant;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.kpmp.FullDataTypeEnum;
import org.kpmp.TissueTypeEnum;
import org.kpmp.dataSummary.DataSummaryRepository;
import org.kpmp.geneExpressionSummary.RTParticipantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParticipantService {
	private ParticipantSummaryDatasetRepository participantSummaryDatasetRepository;
	private DataSummaryRepository dataSummaryRepo;
	private SpatialViewerTypeRepository svTypeRepo;
	private SingleCellMetadataRepository scMetadataRepo;
	private Logger logger = LoggerFactory.getLogger(ParticipantService.class);

	private final String SPATIAL_VIEWER_FILE_VIEW = "sv_file_v";
	private final String SPATIAL_VIEWER_LINK_VIEW = "sv_link_v";
	private SingleNucleusMetadataRepository snMetadataRepo;
	private RTParticipantRepository rtParticipantRepo;

	@Autowired
	public ParticipantService(DataSummaryRepository dataSummaryRepo, SpatialViewerTypeRepository svTypeRepo,
			SingleCellMetadataRepository scMetadataRepo, SingleNucleusMetadataRepository snMetadataRepo,
			RTParticipantRepository rtParticipantRepo,
			ParticipantSummaryDatasetRepository participantSummaryDatasetRepository) {
		this.dataSummaryRepo = dataSummaryRepo;
		this.svTypeRepo = svTypeRepo;
		this.scMetadataRepo = scMetadataRepo;
		this.snMetadataRepo = snMetadataRepo;
		this.rtParticipantRepo = rtParticipantRepo;
		this.participantSummaryDatasetRepository = participantSummaryDatasetRepository;
	}
  
	public ParticipantSummaryDataset getParticipantSummaryDataset(String redcapId) {
		return participantSummaryDatasetRepository.findByRedcapId(redcapId);
	}

	public List<ParticipantTissueTypeSummary> getTissueData(){
		List<ParticipantTissueTypeSummary> tissueData = new ArrayList<>();

		tissueData.add(new ParticipantTissueTypeSummary(
			participantSummaryDatasetRepository.getDataSummaryCount(TissueTypeEnum.AKI.getParticipantTissueType()), 
			participantSummaryDatasetRepository.getDataSummaryCount(TissueTypeEnum.CKD.getParticipantTissueType()), 
			participantSummaryDatasetRepository.getDataSummaryCount(TissueTypeEnum.HEALTHY_REFERENCE.getParticipantTissueType()),
		    participantSummaryDatasetRepository.getDataSummaryCount(TissueTypeEnum.DMR.getParticipantTissueType())));
		return tissueData;
	}

	public ParticipantDataTypeSummary getDataTypeCounts(String redcapId) {
		ParticipantDataTypeSummary summaryData = new ParticipantDataTypeSummary();
		summaryData.setRepositoryDataTypes(getRepositoryCounts(redcapId));
		return summaryData;
	}

	public ParticipantDataTypeSummary getExperimentCounts(String redcapId) {
		ParticipantDataTypeSummary summaryData = new ParticipantDataTypeSummary();
		summaryData.setSpatialViewerDataTypes(getSpatialViewerCounts(redcapId));
		summaryData.setExplorerDataTypes(getExplorerCounts(redcapId));

		return summaryData;
	}

	private List<ParticipantDataTypeInformation> getExplorerCounts(String redcapId) {
		List<ParticipantDataTypeInformation> explorerExperiments = new ArrayList<>();
		int scCount = 0;
		if (scMetadataRepo.existsByRedcapId(redcapId)) {
			scCount = 1;
		}

		ParticipantDataTypeInformation singleCellData = new ParticipantDataTypeInformation(
				FullDataTypeEnum.SINGLE_CELL_FULL.getFullName(), scCount, true);
		explorerExperiments.add(singleCellData);

		int snCount = 0;
		if (snMetadataRepo.existsByRedcapId(redcapId)) {
			snCount = 1;
		}
		ParticipantDataTypeInformation singleNucData = new ParticipantDataTypeInformation(
				FullDataTypeEnum.SINGLE_NUCLEUS_FULL.getFullName(), snCount, true);
		explorerExperiments.add(singleNucData);

		int regionalTranscriptomicsCount = 0;
		if (rtParticipantRepo.existsByRedcapId(redcapId)) {
			regionalTranscriptomicsCount = 1;
		}
		ParticipantDataTypeInformation regionalTranscriptomicsData = new ParticipantDataTypeInformation(
				FullDataTypeEnum.REGIONAL_TRANSCRIPTOMICS_FULL.getFullName(), regionalTranscriptomicsCount, true);
		explorerExperiments.add(regionalTranscriptomicsData);

		return explorerExperiments;
	}

	private List<ParticipantDataTypeInformation> getSpatialViewerCounts(String redcapId) {
		List<ParticipantDataTypeInformation> spatialViewerExperiments = new ArrayList<>();

		List<SpatialViewerDataType> spatialViewerDataTypes = svTypeRepo.findAll();
		for (SpatialViewerDataType spatialViewerDataType : spatialViewerDataTypes) {
			String dataType = spatialViewerDataType.getDataType();
			if (spatialViewerDataType.getTableName().equals(SPATIAL_VIEWER_FILE_VIEW)) {

				Integer count = dataSummaryRepo.getParticipantSvFileDataTypeCount(redcapId, dataType);
				ParticipantDataTypeInformation dataTypeInfo = new ParticipantDataTypeInformation(dataType, count,
						false);
				spatialViewerExperiments.add(dataTypeInfo);
			} else if (spatialViewerDataType.getTableName().equals(SPATIAL_VIEWER_LINK_VIEW)) {
				Integer count = dataSummaryRepo.getParticipantSvLinkDataTypeCount(redcapId, dataType);
				ParticipantDataTypeInformation dataTypeInfo = new ParticipantDataTypeInformation(dataType, count,
						false);
				spatialViewerExperiments.add(dataTypeInfo);
			} else {
				logger.error("Unable to query for data type: " + dataType + ". Need to change code to handle.");
			}
		}
		return spatialViewerExperiments;
	}
	
	private List<ParticipantDataTypeInformation> getRepositoryCounts(String redcapId) {
		List<ParticipantDataTypeInformation> repoCounts = new ArrayList<>();
		
		List<String> repoDataTypes = dataSummaryRepo.getRepoDataTypes();
		for (String repoDataType : repoDataTypes) {
			Integer count = dataSummaryRepo.getParticipantRepoFileDataTypeCount(redcapId, repoDataType);
			ParticipantDataTypeInformation dataTypeInfo = new ParticipantDataTypeInformation(repoDataType, count, false);
			repoCounts.add(dataTypeInfo);
		}
		return repoCounts;
	}
}