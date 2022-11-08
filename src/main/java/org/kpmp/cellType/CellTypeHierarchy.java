package org.kpmp.cellType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class CellTypeHierarchy implements Serializable {
	
	private Map<String, CellTypeStructureRegion> cellTypeRegionMap = new HashMap<>();

	public List<CellTypeStructureRegion> getCellTypeRegions() {
		List<CellTypeStructureRegion> regions = new ArrayList<>();
		regions.addAll(cellTypeRegionMap.values());
		return regions;
	}

	public void addCellTypeStructureRegion(CellTypeStructureRegion region) {
		
		if (cellTypeRegionMap.containsKey(region.getRegionName())) {
			CellTypeStructureRegion existingRegion = cellTypeRegionMap.get(region.getRegionName());
			List<CellTypeStructureSubregion> subregions = region.getCellTypeSubregions();
			for (CellTypeStructureSubregion subregion : subregions) {
				existingRegion.addCellTypeSubregion(subregion);
			}
		} else {
			cellTypeRegionMap.put(region.getRegionName(), region);
		}
	}

	
	
}

