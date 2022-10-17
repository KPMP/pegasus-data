package org.kpmp.participant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sn_metadata")
class SingleNucleusMetadata {

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
