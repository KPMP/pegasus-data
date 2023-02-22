package org.kpmp.dataSummary;

public class AtlasRepoSummaryRow {
	private int openCount;
	private int controlledCount;
	private String omicsType;
	private AtlasRepoSummaryLinkInformation linkInformation;

	public AtlasRepoSummaryRow(String omicsType, AtlasRepoSummaryLinkInformation linkInformation) {
		this.omicsType = omicsType;
		this.linkInformation = linkInformation;
	}

	public int getOpenCount() {
		return openCount;
	}

	public void setOpenCount(int openCount) {
		this.openCount = openCount;
	}

	public int getControlledCount() {
		return controlledCount;
	}

	public void setControlledCount(int controlledCount) {
		this.controlledCount = controlledCount;
	}

	public String getOmicsType() {
		return omicsType;
	}

	public void setOmicsType(String omicsType) {
		this.omicsType = omicsType;
	}

	public void addToControlledCount(int count) {
		this.controlledCount = this.controlledCount + count;
	}

	public void addToOpenCount(int count) {
		this.openCount = this.openCount + count;
	}

	public AtlasRepoSummaryLinkInformation getLinkInformation() {
		return linkInformation;
	}

	public void setLinkInformation(AtlasRepoSummaryLinkInformation linkInformation) {
		this.linkInformation = linkInformation;
	}
}
