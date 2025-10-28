package org.kpmp.cellType;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "celltype_synonym_2025")
@IdClass(CellTypeSynonymId.class)
public class CellTypeSynonym implements Serializable {

	private static final long serialVersionUID = -5650797130903696615L;

	@Id
	@Column(name = "cell_type_id")
	private int cellTypeId;

	@Id
	@Column(name = "cell_type_synonym")
	private String cellTypeSynonym;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cell_type_id", nullable = false, insertable = false, updatable = false)
	private CellType cellType;

	public int getCellTypeId() {
		return cellTypeId;
	}

	public void setCellTypeId(int cellTypeId) {
		this.cellTypeId = cellTypeId;
	}

	public String getCellTypeSynonym() {
		return cellTypeSynonym;
	}

	public void setCellTypeSynonym(String cellTypeSynonym) {
		this.cellTypeSynonym = cellTypeSynonym;
	}
}
