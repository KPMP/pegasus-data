package org.kpmp.participant;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "participant_clinical")
public class ParticipantClinicalDataset implements Serializable{
    @Id
    @Column(name="participant_clinical_id")
    private int participantClinicalId;
    @Column(unique = true, name = "participant_id")
    private int participantId;
    @Column(name = "kdigo_stage")
    private String kdigoStage;
    @Column(name = "baseline_egfr")
    private String baselineEgfr;
    @Column(name = "proteinuria")
    private String proteinuria;
    @Column(name = "a1c")
    private String a1c;
    @Column(name = "albuminuria")
    private String albuminuria;
    @Column(name = "diabetes_history")
    private String diabetesHistory;
    @Column(name = "diabetes_duration")
    private String diabetesDuration;
    @Column(name = "hypertension_history")
    private String hypertensionHistory;
    @Column(name = "hypertension_duration")
    private String hypertensionDuration;
    @Column(name = "on_raas_blockade")
    private String onRaasBlockade;


    public int getParticipantClinicalId() {
        return this.participantClinicalId;
    }

    public void setParticipantClinicalId(int participantClinicalId) {
        this.participantClinicalId = participantClinicalId;
    }

    public int getParticipantId() {
        return this.participantId;
    }

    public void setParticipantId(int participantId) {
        this.participantId = participantId;
    }

    public String getKdigoStage() {
        return this.kdigoStage;
    }

    public void setKdigoStage(String kdigoStage) {
        this.kdigoStage = kdigoStage;
    }

    public String getBaselineEgfr() {
        return this.baselineEgfr;
    }

    public void setBaselineEgfr(String baselineEgfr) {
        this.baselineEgfr = baselineEgfr;
    }

    public String getProteinuria() {
        return this.proteinuria;
    }

    public void setProteinuria(String proteinuria) {
        this.proteinuria = proteinuria;
    }

    public String getA1c() {
        return this.a1c;
    }

    public void setA1c(String a1c) {
        this.a1c = a1c;
    }

    public String getAlbuminuria() {
        return this.albuminuria;
    }

    public void setAlbuminuria(String albuminuria) {
        this.albuminuria = albuminuria;
    }

    public String getDiabetesHistory() {
        return this.diabetesHistory;
    }

    public void setDiabetesHistory(String diabetesHistory) {
        this.diabetesHistory = diabetesHistory;
    }

    public String getDiabetesDuration() {
        return this.diabetesDuration;
    }

    public void setDiabetesDuration(String diabetesDuration) {
        this.diabetesDuration = diabetesDuration;
    }

    public String getHypertensionHistory() {
        return this.hypertensionHistory;
    }

    public void setHypertensionHistory(String hypertensionHistory) {
        this.hypertensionHistory = hypertensionHistory;
    }

    public String getHypertensionDuration() {
        return this.hypertensionDuration;
    }

    public void setHypertensionDuration(String hypertensionDuration) {
        this.hypertensionDuration = hypertensionDuration;
    }

    public String getOnRaasBlockade() {
        return this.onRaasBlockade;
    }

    public void setOnRaasBlockade(String onRaasBlockade) {
        this.onRaasBlockade = onRaasBlockade;
    }
    
}

