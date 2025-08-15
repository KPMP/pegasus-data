package org.kpmp.geneExpressionSummary.singleCell;

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
public class SCRNAParticipantValueNewData implements Serializable {

	private static final long serialVersionUID = 8746341469788157316L;

	@Column(name = "enrollment_category")
	private String enrollmentCategory;

	@Column(name = "redcap_id")
	private String redcapId;

	@Id
	public String getRedcapId() {
		return redcapId;
	}

	public void setRedcapId(String redcapId) {
		this.redcapId = redcapId;
	}

	public String getEnrollmentCategory() {
		return enrollmentCategory;
	}

	public void setEnrollmentCategory(String enrollmentCategory) {
		this.enrollmentCategory = enrollmentCategory;
	}
}
