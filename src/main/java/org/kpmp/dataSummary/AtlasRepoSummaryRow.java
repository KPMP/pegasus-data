package org.kpmp.dataSummary;

public class AtlasRepoSummaryRow {
	private int openCount;
	private int controlledCount;
	private String omicsType;
	private AtlasRepositoryLinkInformation linkInformation;

	public AtlasRepoSummaryRow(String omicsType, AtlasRepositoryLinkInformation linkInformation) {
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

	public AtlasRepositoryLinkInformation getLinkInformation() {
		return linkInformation;
	}

	public void setLinkInformation(AtlasRepositoryLinkInformation linkInformation) {
		this.linkInformation = linkInformation;
	}
}
