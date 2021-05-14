package org.kpmp.datasetSummary;

public class DatasetSummary {
    private String omicsType;
    private String dataType;
    private String dataTypeShort;
    private Long aki;
    private Long ckd;
    private Long hrt;
    private Long participants;

    public DatasetSummary(String myOmicsType, String dataType, String dataTypeShort, Long aki, Long ckd, Long hrt, Long participants) {
        this.omicsType = myOmicsType;
        this.dataType = dataType;
        this.dataTypeShort = dataTypeShort;
        this.aki = aki;
        this.ckd = ckd;
        this.hrt = hrt;
        this.participants = participants;
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

  public Long getAki() {
    return this.aki;
  }

  public void setAki(Long aki) {
    this.aki = aki;
  }

  public Long getCkd() {
    return this.ckd;
  }

  public void setCkd(Long ckd) {
    this.ckd = ckd;
  }

  public Long getHrt() {
    return this.hrt;
  }

  public void setHrt(Long hrt) {
    this.hrt = hrt;
  }

  public Long getParticipants() {
    return this.participants;
  }

  public void setParticipants(Long participants) {
    this.participants = participants;
  }
}