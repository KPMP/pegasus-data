package org.kpmp.participant;

import java.io.Serializable;

public class ParticipantTissueTypeSummary implements Serializable {
	private static final long serialVersionUID = 4596059416539472482L;
	private Long akiCount;
	private Long ckdCount;
	private Long hrtCount;

	private Long resistorCount;
	public ParticipantTissueTypeSummary(Long akiCount, Long ckdCount, Long hrtCount, Long resistorCount) {
		this.akiCount = akiCount;
		this.ckdCount = ckdCount;
		this.hrtCount = hrtCount;
		this.resistorCount = resistorCount;
	}
	public Long getAkiCount() {
		return this.akiCount;
	}

	public void setAkiCount(Long akiCount) {
		this.akiCount = akiCount;
	}

	public Long getCkdCount() {
		return this.ckdCount;
	}

	public void setCkdCount(Long ckdCount) {
		this.ckdCount = ckdCount;
	}

	public Long getHrtCount() {
		return this.hrtCount;
	}

	public void setHrtCount(Long hrtCount) {
		this.hrtCount = hrtCount;
	}

	public Long getResistorCount() {
		return resistorCount;
	}

	public void setResistorCount(Long resistorCount) {
		this.resistorCount = resistorCount;
	}
}
