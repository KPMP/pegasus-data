package org.kpmp.datasetSummary;

import java.util.ArrayList;
import java.util.List;
import org.kpmp.datasetSummary.DatasetSummary;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class DatasetSummaryService {
	private DatasetSummaryRepository datasetSummaryRepository;

	@Autowired
	public DatasetSummaryService(DatasetSummaryRepository datasetSummaryRepository) {
		this.datasetSummaryRepository = datasetSummaryRepository;
	}

	public List<DatasetSummary> getSummaryData(String datatype) {
		ArrayList<DatasetSummary> summaryData = new ArrayList<DatasetSummary>();

		datasetSummaryRepository.getCountByDataType("Spatial Transcriptomics");
		
		
		summaryData.add(new DatasetSummary(
			"Spatial Transcriptomics",
			"st",
			"st",
			Long.valueOf(0),
			Long.valueOf(0),
			Long.valueOf(0),
			Long.valueOf(0)));


		return summaryData;
	}
}
