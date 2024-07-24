package org.kpmp.geneExpressionSummary.regionalTranscriptomics;

import org.kpmp.geneExpressionSummary.Participant;
import org.kpmp.geneExpressionSummary.ParticipantId;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name = "rt_participant_tissue_v")
@IdClass(ParticipantId.class)
public
class RTParticipantValue implements Participant, Serializable {

	private static final long serialVersionUID = 5368734102102552073L;

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
