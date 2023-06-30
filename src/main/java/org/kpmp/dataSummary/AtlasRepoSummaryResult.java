package org.kpmp.dataSummary;

import java.util.ArrayList;
import java.util.List;

public class AtlasRepoSummaryResult {

	private List<AtlasRepoSummaryRow> summaryRows;
	private Long totalFiles;

	public AtlasRepoSummaryResult(List<AtlasRepoSummaryRow> summaryRows, Long totalFiles) {
		this.summaryRows = summaryRows;
		this.totalFiles = totalFiles;

	}

	public AtlasRepoSummaryResult() {
		summaryRows = new ArrayList<>();
	}

	public List<AtlasRepoSummaryRow> getSummaryRows() {
		return summaryRows;
	}

	public void setSummaryRows(List<AtlasRepoSummaryRow> summaryRows) {
		this.summaryRows = summaryRows;
	}

	public Long getTotalFiles() {
		return totalFiles;
	}

	public void setTotalFiles(Long totalFiles) {
		this.totalFiles = totalFiles;
	}

}
