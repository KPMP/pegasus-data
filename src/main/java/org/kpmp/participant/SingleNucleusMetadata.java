package org.kpmp.participant;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sn_metadata")
class SingleNucleusMetadata implements Serializable {

	private static final long serialVersionUID = -504357775835334661L;
	@Id
	private String barcode;
	@Column(name = "specimen_id")
	private String redcapId;

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getRedcapId() {
		return redcapId;
	}

	public void setRedcapId(String redcapId) {
		this.redcapId = redcapId;
	}

}
