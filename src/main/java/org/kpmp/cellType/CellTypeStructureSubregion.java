package org.kpmp.cellType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CellTypeStructureSubregion {

	private String subregionName;
	private Set<String> cellTypeNames = new HashSet<String>();
	
	public CellTypeStructureSubregion(String subregionName) {
		this.subregionName = subregionName;
	}

	public String getSubregionName() {
		return subregionName;
	}

	public void setSubregionName(String subregionName) {
		this.subregionName = subregionName;
	}

	public List<String> getCellTypeNames() {
		List<String> cells = new ArrayList<>();
		cells.addAll(cellTypeNames);
		return cells;
	}

	public void addCellType(String cellTypeName) {
		
		cellTypeNames.add(cellTypeName);
	}
	
	

}
