package org.kpmp.participant;

import org.kpmp.dataSummary.AtlasRepositoryLinkInformation;

public class ParticipantRepoDataTypeInformation {

	private String dataType;
	private Integer count;
	private AtlasRepositoryLinkInformation linkInformation;

	public ParticipantRepoDataTypeInformation(String dataType, Integer count, AtlasRepositoryLinkInformation linkInformation) {
		this.dataType = dataType;
		this.count = count;
		this.linkInformation = linkInformation;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public AtlasRepositoryLinkInformation getLinkInformation() {
		return linkInformation;
	}

	public void setLinkInformation(AtlasRepositoryLinkInformation linkInformation) {
		this.linkInformation = linkInformation;
	}
}
