package org.kpmp.cellType;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CellTypeService {

	private CellTypeRepository cellTypeRepo;

	@Autowired
	public CellTypeService(CellTypeRepository cellTypeRepo) {
		this.cellTypeRepo = cellTypeRepo;
	}

	public CellTypeHierarchy getCellTypeHierarchy() {
		List<CellType> cellTypes = cellTypeRepo.findAllByCellTypeIsNotNullOrderByCellTypeOrdering();
		CellTypeHierarchy hierarchy = new CellTypeHierarchy();

		for (CellType cellType : cellTypes) {
			CellTypeStructureSubregion subregion = new CellTypeStructureSubregion(cellType.getStructureSubregion());
			subregion.addCellType(cellType);
			CellTypeStructureRegion region = new CellTypeStructureRegion(cellType.getStructureRegion());
			region.addCellTypeSubregion(subregion);
			hierarchy.addCellTypeStructureRegion(region);
		}

		return hierarchy;
	}

}
