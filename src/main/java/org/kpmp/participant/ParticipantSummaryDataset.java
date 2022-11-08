package org.kpmp.participant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "participant")
public class ParticipantSummaryDataset  implements Serializable {
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
    @Column(name = "tissue_type")
    private String tissueType;
    @Column(name = "clinical_data")
    private String clinicalData;
    
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

    public String getTissueType() {
        return tissueType;
    }

    public void setTissueType(String tissueType) {
        this.tissueType = tissueType;
    }

    public String getClinicalData() {
        return clinicalData;
    }

    public void setClinicalData(String clinicalData) {
        this.clinicalData = clinicalData;
    }

}
