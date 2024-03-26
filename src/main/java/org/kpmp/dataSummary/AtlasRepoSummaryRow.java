package org.kpmp.dataSummary;

public class AtlasRepoSummaryRow {
	private String omicsType;
	private AtlasRepositoryLinkInformation linkInformation;
	private Long akiCount;
	private Long ckdCount;
	private Long hrtCount;
	private Long dmrCount;

	public AtlasRepoSummaryRow(String omicsType, AtlasRepositoryLinkInformation linkInformation) {
		this.omicsType = omicsType;
		this.linkInformation = linkInformation;
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

	public Long getDmrCount() {
		return this.dmrCount;
	}

	public void setDmrCount(Long dmrCount) {
		this.dmrCount = dmrCount;
	}

	public String getOmicsType() {
		return omicsType;
	}

	public void setOmicsType(String omicsType) {
		this.omicsType = omicsType;
	}

	public AtlasRepositoryLinkInformation getLinkInformation() {
		return linkInformation;
	}

	public void setLinkInformation(AtlasRepositoryLinkInformation linkInformation) {
		this.linkInformation = linkInformation;
	}
}
