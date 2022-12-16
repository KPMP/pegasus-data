package org.kpmp.participant;
import org.springframework.stereotype.Component;

@Component
public class ParticipantTissueTypeSummary {
	private Integer akiCount;
	private Integer ckdCount;
	private Integer hrtCount;

	public ParticipantTissueTypeSummary(Integer akiCount, Integer ckdCount, Integer hrtCount) {
		this.akiCount = akiCount;
		this.ckdCount = ckdCount;
		this.hrtCount = hrtCount;
	}

	public Integer getAkiCount() {
    return this.akiCount;
  }

  public void setAkiCount(Integer akiCount) {
    this.akiCount = akiCount;
  }

  public Integer getCkdCount() {
    return this.ckdCount;
  }

  public void setCkdCount(Integer ckdCount) {
    this.ckdCount = ckdCount;
  }

  public Integer getHrtCount() {
    return this.hrtCount;
  }

  public void setHrtCount(Integer hrtCount) {
    this.hrtCount = hrtCount;
  }
}
