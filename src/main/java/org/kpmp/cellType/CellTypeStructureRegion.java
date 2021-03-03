package org.kpmp.cellType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CellTypeStructureRegion {
	
	private String regionName;
	private Map<String, CellTypeStructureSubregion> subregionMap = new HashMap<>();
	
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
		subregions.addAll(subregionMap.values());
		return subregions;
	}
	
	public void addCellTypeSubregion(CellTypeStructureSubregion subregion) {
		String subregionName = subregion.getSubregionName();
		
		if (subregionMap.containsKey(subregionName)) {
			CellTypeStructureSubregion existingSubregion = subregionMap.get(subregionName);
			List<String> newCellTypes = subregion.getCellTypeNames();
			for (String cellType : newCellTypes) {
				existingSubregion.addCellType(cellType);
			}
		} else {
			subregionMap.put(subregionName, subregion);
		}
	}

}
