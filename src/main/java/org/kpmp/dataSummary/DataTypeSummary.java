package org.kpmp.dataSummary;

import java.io.Serializable;

public class DataTypeSummary implements Serializable {
	private static final long serialVersionUID = 114808450473696153L;
	private String omicsType;
	private String dataType;
	private String dataTypeShort;
	private Long akiCount;
	private Long ckdCount;
	private Long hrtCount;
	private Long participantCount;
	private Long resistorCount;

	public DataTypeSummary(String omicsType, String dataType, String dataTypeShort, Long akiCount, Long ckdCount,
			Long hrtCount, Long resistorCount, Long participantCount) {
		this.omicsType = omicsType;
		this.dataType = dataType;
		this.dataTypeShort = dataTypeShort;
		this.akiCount = akiCount;
		this.ckdCount = ckdCount;
		this.hrtCount = hrtCount;
		this.setResistorCount(resistorCount);
		this.participantCount = participantCount;
	}

	public String getOmicsType() {
		return this.omicsType;
	}

	public void setOmicsType(String omicsType) {
		this.omicsType = omicsType;
	}

	public String getDataType() {
		return this.dataType;
	}

	public void setDataTypeType(String dataType) {
		this.dataType = dataType;
	}

	public String getDataTypeShort() {
		return this.dataTypeShort;
	}

	public void setDataTypeShort(String dataTypeShort) {
		this.dataTypeShort = dataTypeShort;
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

	public Long getParticipantCount() {
		return this.participantCount;
	}

	public void setParticipantCount(Long participantCount) {
		this.participantCount = participantCount;
	}

	public Long getResistorCount() {
		return resistorCount;
	}

	public void setResistorCount(Long resistorCount) {
		this.resistorCount = resistorCount;
	}
}