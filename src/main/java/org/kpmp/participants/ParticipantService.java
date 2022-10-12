package org.kpmp.participants;

import org.kpmp.dataSummary.DataSummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParticipantService {

	private DataSummaryRepository dataSummaryRepo;

	@Autowired
	public ParticipantService(DataSummaryRepository dataSummaryRepo) {
		this.dataSummaryRepo = dataSummaryRepo;
		// TODO Auto-generated constructor stub
	}

	public ParticipantExperimentalStrategySummary getExperimentCounts(String participantId) {
		return new ParticipantExperimentalStrategySummary();
	}

}

// Get Spatial Viewer file type counts
//select count(*) from sv_file_v f
//join participant p on f.participant_id = p.participant_id
//where p.redcap_id = '32-10034'
//and data_type = 'xxx';

// Get Spatial Viewer link type counts
//select count(*) from sv_link_v f
//join participant p on f.participant_id = p.participant_id
//where p.redcap_id = '30-10018'
//and data_type = 'Spatial N-glycomics'

// get all of the data types in spatial viewer
//select distinct(data_type), 'sv_file_v' as table_name from sv_file_v
//union
//select distinct(data_type), 'sv_link_v' as table_name from sv_link_v;

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