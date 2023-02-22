package org.kpmp.dataSummary;

import java.util.ArrayList;
import java.util.List;

public class AtlasRepoSummaryResult {

	private List<AtlasRepoSummaryRow> summaryRows;
	private int totalFiles;

	public AtlasRepoSummaryResult(List<AtlasRepoSummaryRow> summaryRows, int totalFiles) {
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

	public int getTotalFiles() {
		return totalFiles;
	}

	public void setTotalFiles(int totalFiles) {
		this.totalFiles = totalFiles;
	}

}
