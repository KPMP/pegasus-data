package org.kpmp.geneExpressionSummary.singleCell;

import org.kpmp.geneExpressionSummary.ParticipantId;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "sn_participant_tissue_v")
@IdClass(ParticipantId.class)
public class SCRNAParticipantValue implements Serializable {

	private static final long serialVersionUID = 8746341469788157316L;

	@Column(name = "tissue_type")
	private String tissueType;

	@Column(name = "redcap_id")
	private String redcapId;

	@Id
	public String getRedcapId() {
		return redcapId;
	}

	public void setRedcapId(String redcapId) {
		this.redcapId = redcapId;
	}

	public String getTissueType() {
		return tissueType;
	}

	public void setTissueType(String tissueType) {
		this.tissueType = tissueType;
	}
}
