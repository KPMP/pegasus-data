package org.kpmp.cellType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Nullable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cell_type")
public class CellType implements Serializable {

	private static final long serialVersionUID = -7240769211757430938L;

	@Id
	@Column(name = "cell_type_id")
	private int cellTypeId;

	@Column(name = "structure_region")
	private String structureRegion;

	@Nullable
	@Column(name = "structure_subregion")
	private String structureSubregion;

	@Nullable
	@Column(name = "cell_type")
	private String cellType;

	@Column(name = "release_ver")
	private Double releaseVer;

	@Column(name = "release_sunset")
	private Double releaseSunset;

	@Column(name = "cell_type_order")
	private int cellTypeOrdering;

	@OneToMany(mappedBy = "cellType", fetch = FetchType.EAGER)
	private Set<CellTypeSynonym> synonyms;

	public int getCellTypeId() {
		return cellTypeId;
	}

	public void setCellTypeId(int cellTypeId) {
		this.cellTypeId = cellTypeId;
	}

	public String getStructureRegion() {
		return structureRegion;
	}

	public void setStructureRegion(String structureRegion) {
		this.structureRegion = structureRegion;
	}

	public String getStructureSubregion() {
		return structureSubregion;
	}

	public void setStructureSubregion(String structureSubregion) {
		this.structureSubregion = structureSubregion;
	}

	public String getCellType() {
		return cellType;
	}

	public void setCellType(String cellType) {
		this.cellType = cellType;
	}

	public Double getReleaseVer() {
		return releaseVer;
	}

	public void setReleaseVer(Double releaseVer) {
		this.releaseVer = releaseVer;
	}

	public Double getReleaseSunset() {
		return releaseSunset;
	}

	public void setReleaseSunset(Double releaseSunset) {
		this.releaseSunset = releaseSunset;
	}

	public List<String> getSynonymStringList() {
		List<String> synonymList = new ArrayList<>();
		for (CellTypeSynonym synonym : synonyms) {
			synonymList.add(synonym.getCellTypeSynonym());
		}
		return synonymList;
	}

	public Set<CellTypeSynonym> getSynonyms() {
		return synonyms;
	}

	public void setSynonyms(Set<CellTypeSynonym> synonyms) {
		this.synonyms = synonyms;
	}

	public int getCellTypeOrdering() {
		return cellTypeOrdering;
	}

	public void setCellTypeOrdering(int cellTypeOrdering) {
		this.cellTypeOrdering = cellTypeOrdering;
	}

	@Override
	public int hashCode() {
		return cellType.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof CellType))
			return false;
		CellType cellType = (CellType) obj;
		return cellType.getCellType().equals(this.getCellType());
	}

}
