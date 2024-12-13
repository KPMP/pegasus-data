package org.kpmp.geneExpressionSummary.regionalProteomics;

import org.kpmp.geneExpressionSummary.Participant;
import org.kpmp.geneExpressionSummary.ParticipantId;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "rp_participant_tissue_v")
@IdClass(ParticipantId.class)
public class RPParticipantValue implements Participant, Serializable {

    @Column(name = "enrollment_category")
    private String enrollmentCategory;

    @Column(name = "redcap_id")
    private String redcapId;

    @Override
    @Id
    public String getRedcapId() {
        return redcapId;
    }

    @Override
    public void setRedcapId(String redcapId) {
        this.redcapId = redcapId;
    }

    @Override
    public String getEnrollmentCategory() {
        return enrollmentCategory;
    }

    @Override
    public void setEnrollmentCategory(String enrollmentCategory) {
        this.enrollmentCategory = enrollmentCategory;
    }

}
