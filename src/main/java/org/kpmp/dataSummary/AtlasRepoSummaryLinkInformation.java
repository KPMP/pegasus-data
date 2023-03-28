package org.kpmp.dataSummary;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(linkType).append(linkValue).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof AtlasRepoSummaryLinkInformation) {
			final AtlasRepoSummaryLinkInformation other = (AtlasRepoSummaryLinkInformation) obj;
			return new EqualsBuilder().append(linkType, other.getLinkType()).append(linkValue, other.getLinkValue())
					.isEquals();
		} else {
			return false;
		}

	}

}
