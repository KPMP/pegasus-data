package org.kpmp.dataSummary;

public class AtlasRepoSummaryLinkInformation {

	private String linkType;
	private String linkValue;

	public AtlasRepoSummaryLinkInformation(String linkType, String linkValue) {
		this.setLinkType(linkType);
		this.setLinkValue(linkValue);
	}

	public String getLinkType() {
		return linkType;
	}

	public void setLinkType(String linkType) {
		this.linkType = linkType;
	}

	public String getLinkValue() {
		return linkValue;
	}

	public void setLinkValue(String linkValue) {
		this.linkValue = linkValue;
	}

}
