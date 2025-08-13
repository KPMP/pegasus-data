package org.kpmp.participant;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "sn_metadata_2025")
class SingleNucleusMetadata2025 implements Serializable {

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
