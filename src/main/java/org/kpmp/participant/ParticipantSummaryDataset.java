package org.kpmp.participant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "participant")
public class ParticipantSummaryDataset  {
    @Id
    @Column(name = "participant_id")
    private int participant_id;
    @Column(name = "old_participant_id")
    private String oldparticipant_id;
    @Column(name = "redcap_id")
    private String redcap_id;
    @Column(name = "age_binned")
    private String age_binned;
    private String sex;
    @Column(name = "tissue_source")
    private String tissue_source;
    private String protocol;
    @Column(name = "sample_type")
    private String sample_type;
    @Column(name = "tissue_type")
    private String tissue_type;
    @Column(name = "clinical_data")
    private String clinical_data;
    
    public int getParticipantId() {
        return participant_id;
    }

    public void setParticipantId(int participantId) {
        this.participant_id = participantId;
    }

    public String getOldParticipantId() {
        return oldparticipant_id;
    }

    public void setOldParticipantId(String oldparticipant_id) {
        this.oldparticipant_id = oldparticipant_id;
    }

    public String getRedcapId() {
        return redcap_id;
    }

    public void setRedcapId(String redcap_id) {
        this.redcap_id = redcap_id;
    }

    public String getAgeBinned() {
        return age_binned;
    }

    public void setAgeBinned(String age_binned) {
        this.age_binned = age_binned;
    }

    public String getSex() {
      return sex;
    }

    public void setSex(String sex) {
      this.sex = sex;
    }

    public String getTissueSource() {
        return tissue_source;
    }

    public void setTissueSource(String tissue_source) {
        this.tissue_source = tissue_source;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getSampleType() {
        return sample_type;
    }

    public void setSampleType(String sample_type) {
        this.sample_type = sample_type;
    }

    public String getTissueType() {
        return tissue_type;
    }

    public void setTissueType(String tissue_type) {
        this.tissue_type = tissue_type;
    }

    public String getClinicalData() {
        return clinical_data;
    }

    public void setClinicalData(String clinical_data) {
        this.clinical_data = clinical_data;
    }

}
