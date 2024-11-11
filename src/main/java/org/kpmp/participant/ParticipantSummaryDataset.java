package org.kpmp.participant;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "participant")
public class ParticipantSummaryDataset implements Serializable {
	private static final long serialVersionUID = -6616360991151079273L;
	@Id
	@Column(name = "participant_id")
	private int participantId;
	@Column(name = "old_participant_id")
	private String oldparticipantId;
	@Column(name = "redcap_id")
	private String redcapId;
	@Column(name = "age_binned")
	private String ageBinned;
	private String sex;
	@Column(name = "tissue_source")
	private String tissueSource;
	private String protocol;
	@Column(name = "sample_type")
	private String sampleType;
	@Column(name = "enrollment_category")
	private String enrollmentCategory;

	public int getParticipantId() {
		return participantId;
	}

	public void setParticipantId(int participantId) {
		this.participantId = participantId;
	}

	public String getOldParticipantId() {
		return oldparticipantId;
	}

	public void setOldParticipantId(String oldparticipantId) {
		this.oldparticipantId = oldparticipantId;
	}

	public String getRedcapId() {
		return redcapId;
	}

	public void setRedcapId(String redcapId) {
		this.redcapId = redcapId;
	}

	public String getAgeBinned() {
		return ageBinned;
	}

	public void setAgeBinned(String ageBinned) {
		this.ageBinned = ageBinned;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getTissueSource() {
		return tissueSource;
	}

	public void setTissueSource(String tissueSource) {
		this.tissueSource = tissueSource;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getSampleType() {
		return sampleType;
	}

	public void setSampleType(String sampleType) {
		this.sampleType = sampleType;
	}

	public String getEnrollmentCategory() {
		return enrollmentCategory;
	}

	public void setEnrollmentCategory(String enrollmentCategory) {
		this.enrollmentCategory = enrollmentCategory;
	}

}
