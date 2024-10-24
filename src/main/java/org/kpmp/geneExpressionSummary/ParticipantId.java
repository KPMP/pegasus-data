package org.kpmp.geneExpressionSummary;

import java.io.Serializable;

public class ParticipantId implements Serializable {
	private static final long serialVersionUID = 8123478456751869304L;
	private String enrollmentCategory;
	private String redcapId;

	public void SNRNAParticipantId(String enrollmentCategory, String redcapId) {
		this.enrollmentCategory = enrollmentCategory;
		this.redcapId = redcapId;
	}

	public String getEnrollmentCategory() {
		return enrollmentCategory;
	}

	public void setEnrollmentCategory(String enrollmentCategory) {
		this.enrollmentCategory = enrollmentCategory;
	}

	public String getRedcapId() {
		return redcapId;
	}

	public void setRedcapId(String redcapId) {
		this.redcapId = redcapId;
	}
}
