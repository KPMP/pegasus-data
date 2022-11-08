package org.kpmp.geneExpressionSummary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "sn_participant_tissue_v")
@IdClass(ParticipantId.class)
public class SCRNAParticipantValue implements Serializable {

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
