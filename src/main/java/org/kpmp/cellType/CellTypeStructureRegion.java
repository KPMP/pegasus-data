package org.kpmp.cellType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CellTypeStructureRegion implements Serializable {

	private String regionName;
	private Map<String, CellTypeStructureSubregion> subregionMap = new HashMap<>();
	private List<String> subregionOrderdList = new ArrayList<>();

	public CellTypeStructureRegion(String regionName) {
		this.regionName = regionName;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public List<CellTypeStructureSubregion> getCellTypeSubregions() {
		List<CellTypeStructureSubregion> subregions = new ArrayList<>();
		for (String subregionName : subregionOrderdList) {
			subregions.add(subregionMap.get(subregionName));
		}
		return subregions;
	}

	public void addCellTypeSubregion(CellTypeStructureSubregion newSubregion) {
		String subregionName = newSubregion.getSubregionName();
		if (subregionMap.containsKey(subregionName)) {
			CellTypeStructureSubregion existingSubregion = subregionMap.get(subregionName);
			List<CellType> newCellTypes = newSubregion.getCellTypes();
			for (CellType cellType : newCellTypes) {
				existingSubregion.addCellType(cellType);
			}
		} else {
			subregionOrderdList.add(newSubregion.getSubregionName());
			subregionMap.put(subregionName, newSubregion);
		}
	}

}
