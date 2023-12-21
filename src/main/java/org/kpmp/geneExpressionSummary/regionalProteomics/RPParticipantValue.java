package org.kpmp.geneExpressionSummary.regionalProteomics;

import org.kpmp.geneExpressionSummary.Participant;
import org.kpmp.geneExpressionSummary.ParticipantId;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "rp_participant_tissue_v")
@IdClass(ParticipantId.class)
public class RPParticipantValue implements Participant, Serializable {

    @Column(name = "tissue_type")
    private String tissueType;

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
    public String getTissueType() {
        return tissueType;
    }

    @Override
    public void setTissueType(String tissueType) {
        this.tissueType = tissueType;
    }

}
