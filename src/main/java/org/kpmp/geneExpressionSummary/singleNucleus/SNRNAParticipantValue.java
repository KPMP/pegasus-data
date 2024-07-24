package org.kpmp.geneExpressionSummary.singleNucleus;

import org.kpmp.geneExpressionSummary.Participant;
import org.kpmp.geneExpressionSummary.ParticipantId;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name = "sn_participant_tissue_v")
@IdClass(ParticipantId.class)
public class SNRNAParticipantValue implements Participant, Serializable {

	private static final long serialVersionUID = -2404700991479961255L;

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
