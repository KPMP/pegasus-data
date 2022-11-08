package org.kpmp.cellType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Objects;

public class CellTypeStructureSubregion implements Serializable {

	private String subregionName;
	private List<CellType> cellTypes = new ArrayList<>();

	public CellTypeStructureSubregion(String subregionName) {
		this.subregionName = subregionName;
	}

	public String getSubregionName() {
		return subregionName;
	}

	public void setSubregionName(String subregionName) {
		this.subregionName = subregionName;
	}

	public List<CellType> getCellTypes() {
		return this.cellTypes;
	}

	public void addCellType(CellType cellType) {
		if (!cellTypes.contains(cellType)) {
			cellTypes.add(cellType);
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}
		CellTypeStructureSubregion other = (CellTypeStructureSubregion) obj;
		return Objects.equal(this.subregionName, other.subregionName);

	}

	@Override
	public int hashCode() {
		return Objects.hashCode(subregionName);
	}

}
