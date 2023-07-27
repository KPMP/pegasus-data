package org.kpmp.participant;
import org.kpmp.dataSummary.AtlasRepoSummaryLinkInformation;
import org.springframework.stereotype.Component;

@Component
public class ParticipantRepoDataTypeInformation {
    
	private String dataType;
	private Integer count;
    private AtlasRepoSummaryLinkInformation linkInformation;

	public ParticipantRepoDataTypeInformation() {
		// default constructor so spring does not complain
	}

	public ParticipantRepoDataTypeInformation(String dataType, Integer count, AtlasRepoSummaryLinkInformation linkInformation) {
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

	public AtlasRepoSummaryLinkInformation getLinkInformation() {
		return linkInformation;
	}

	public void setLinkInformation(AtlasRepoSummaryLinkInformation linkInformation) {
		this.linkInformation = linkInformation;
	}
}
