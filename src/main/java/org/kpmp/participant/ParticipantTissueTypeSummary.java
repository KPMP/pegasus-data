package org.kpmp.participant;
import org.springframework.stereotype.Component;

@Component
public class ParticipantTissueTypeSummary {
	private Long akiCount;
	private Long ckdCount;
	private Long hrtCount;

	public ParticipantTissueTypeSummary(Long akiCount, Long ckdCount, Long hrtCount) {
		this.akiCount = akiCount;
		this.ckdCount = ckdCount;
		this.hrtCount = hrtCount;
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
