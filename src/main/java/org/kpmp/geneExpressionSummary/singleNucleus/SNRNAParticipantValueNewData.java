package org.kpmp.geneExpressionSummary.singleNucleus;

import org.kpmp.geneExpressionSummary.Participant;
import org.kpmp.geneExpressionSummary.ParticipantId;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name = "sn_participant_tissue_2025_v")
@IdClass(ParticipantId.class)
public class SNRNAParticipantValueNewData implements Participant, Serializable {

	private static final long serialVersionUID = -2404700991479961255L;

	@Column(name = "enrollment_category")
	private String enrollmentCategory;

	@Column(name = "redcap_id")
	private String redcapId;

	@Override
	@Id
	public String getRedcapId() {
		return redcapId;
	}

	@Override
	public void setRedcapId(String redcapId) {
		this.redcapId = redcapId;
	}

	@Override
	public String getEnrollmentCategory() {
		return enrollmentCategory;
	}

	@Override
	public void setEnrollmentCategory(String enrollmentCategory) {
		this.enrollmentCategory = enrollmentCategory;
	}
}
