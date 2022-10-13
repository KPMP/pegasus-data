package org.kpmp.participants;

import java.util.ArrayList;
import java.util.List;

import org.kpmp.dataSummary.DataSummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParticipantService {

	private DataSummaryRepository dataSummaryRepo;
	private SpatialViewerTypeRepository svTypeRepo;

	@Autowired
	public ParticipantService(DataSummaryRepository dataSummaryRepo, SpatialViewerTypeRepository svTypeRepo) {
		this.dataSummaryRepo = dataSummaryRepo;
		this.svTypeRepo = svTypeRepo;
	}

	public ParticipantDataTypeSummary getExperimentCounts(String participantId) {
		ParticipantDataTypeSummary summaryData = new ParticipantDataTypeSummary();
		List<ParticipantDataTypeInformation> spatialViewerExperiments = getSpatialViewerCounts(participantId);
		summaryData.setSpatialViewerDataTypes(spatialViewerExperiments);

		return summaryData;
	}

	private List<ParticipantDataTypeInformation> getSpatialViewerCounts(String redcapId) {
		List<ParticipantDataTypeInformation> spatialViewerExperiments = new ArrayList<>();

		List<SpatialViewerDataType> spatialViewerDataTypes = svTypeRepo.findAll();
		for (SpatialViewerDataType spatialViewerDataType : spatialViewerDataTypes) {
			String dataType = spatialViewerDataType.getDataType();
			if (spatialViewerDataType.getTableName().equals("sv_file_v")) {

				Long count = dataSummaryRepo.getParticipantSvFileDataTypeCount(redcapId, dataType);
				ParticipantDataTypeInformation dataTypeInfo = new ParticipantDataTypeInformation(dataType, count,
						false);
				spatialViewerExperiments.add(dataTypeInfo);
			} else if (spatialViewerDataType.getTableName().equals("sv_link_v")) {
				Long count = dataSummaryRepo.getParticipantSvLinkDataTypeCount(redcapId, dataType);
				ParticipantDataTypeInformation dataTypeInfo = new ParticipantDataTypeInformation(dataType, count,
						false);
				spatialViewerExperiments.add(dataTypeInfo);
			}
		}
		return spatialViewerExperiments;
	}

}

/*
 * Order of operations for spatial viewer data 1) Get all data types from
 * spatial viewer 2) loop through the results and get counts for each data type
 * for given participant and fill in summary object 3) Make sure to set all to
 * not aggregated
 */

/*
 * For Explorer data types will need to have a list of data types that we know
 * ahead of time and query those for the participant since we don't have this
 * coded anywhere
 */