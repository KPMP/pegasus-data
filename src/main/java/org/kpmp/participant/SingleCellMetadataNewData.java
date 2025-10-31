package org.kpmp.participant;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "sc_metadata_2025")
class SingleCellMetadata2025 implements Serializable {

	private static final long serialVersionUID = -7074542040879592316L;
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
