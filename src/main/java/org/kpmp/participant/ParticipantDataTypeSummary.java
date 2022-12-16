package org.kpmp.participant;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ParticipantDataTypeSummary {
	private Long akiCount;
	private Long ckdCount;
	private Long hrtCount;
	private List<ParticipantDataTypeInformation> spatialViewerDataTypes;
	private List<ParticipantDataTypeInformation> explorerDataTypes;

	public ParticipantDataTypeSummary(Long akiCount, Long ckdCount, Long hrtCount) {
		this.akiCount = akiCount;
		this.ckdCount = ckdCount;
		this.hrtCount = hrtCount;
}

	public List<ParticipantDataTypeInformation> getSpatialViewerDataTypes() {
		return spatialViewerDataTypes;
	}

	public void setSpatialViewerDataTypes(List<ParticipantDataTypeInformation> spatialViewerDataTypes) {
		this.spatialViewerDataTypes = spatialViewerDataTypes;
	}

	public List<ParticipantDataTypeInformation> getExplorerDataTypes() {
		return explorerDataTypes;
	}

	public void setExplorerDataTypes(List<ParticipantDataTypeInformation> explorerDataTypes) {
		this.explorerDataTypes = explorerDataTypes;
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
}
