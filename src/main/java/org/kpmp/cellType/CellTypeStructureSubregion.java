package org.kpmp.cellType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class CellTypeStructureSubregion implements Serializable {

	private static final long serialVersionUID = 1366955014710403152L;
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
		return new EqualsBuilder().append(this.subregionName, other.subregionName).build();

	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.subregionName).build();
	}

}
