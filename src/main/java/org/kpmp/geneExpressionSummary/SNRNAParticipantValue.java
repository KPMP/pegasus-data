package org.kpmp.geneExpressionSummary;

import org.kpmp.DataTypeEnum;
import org.springframework.lang.Nullable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "sn_participant_tissue_v")
@IdClass(ParticipantId.class)
public class SNRNAParticipantValue implements Participant {

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
