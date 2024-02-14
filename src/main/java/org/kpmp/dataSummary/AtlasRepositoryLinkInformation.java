package org.kpmp.dataSummary;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class AtlasRepositoryLinkInformation {

	public static final String EXPERIMENTAL_STRATEGY = "experimental_strategy";
	public static final String DATA_CATEGORY = "data_category";
	
	private String linkType;
	private String linkValue;

	public AtlasRepositoryLinkInformation(String linkType, String linkValue) {
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
		if (obj instanceof AtlasRepositoryLinkInformation) {
			final AtlasRepositoryLinkInformation other = (AtlasRepositoryLinkInformation) obj;
			return new EqualsBuilder().append(linkType, other.getLinkType()).append(linkValue, other.getLinkValue()).isEquals();
		}
		else {
			return false;
		}
	}

}
