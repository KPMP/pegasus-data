package org.kpmp.geneExpressionSummary;

import java.io.Serializable;

public class ParticipantId implements Serializable {
	private static final long serialVersionUID = 8123478456751869304L;
	private String tissueType;
	private String redcapId;

	public void SNRNAParticipantId(String tissueType, String redcapId) {
		this.tissueType = tissueType;
		this.redcapId = redcapId;
	}

	public String getTissueType() {
		return tissueType;
	}

	public void setTissueType(String tissueType) {
		this.tissueType = tissueType;
	}

	public String getRedcapId() {
		return redcapId;
	}

	public void setRedcapId(String redcapId) {
		this.redcapId = redcapId;
	}
}
