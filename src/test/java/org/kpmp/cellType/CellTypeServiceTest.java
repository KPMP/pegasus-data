package org.kpmp.cellType;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CellTypeServiceTest {

	@Mock
	private CellTypeRepository cellTypeRepo;
	private CellTypeService service;

	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);

		service = new CellTypeService(cellTypeRepo);
	}

	@AfterEach
	public void tearDown() throws Exception {
		MockitoAnnotations.openMocks(this).close();
		service = null;
		cellTypeRepo = null;
	}

	@Test
	public void testGetCellTypeHierarchy() {
		ArrayList<CellType> cellTypes = new ArrayList<CellType>();
		CellType cellType1 = new CellType();
		cellType1.setCellType("cell type 1");
		cellType1.setStructureSubregion("subregion 1");
		cellType1.setStructureRegion("region 1");
		cellTypes.add(cellType1);
		when(cellTypeRepo.findAllByCellTypeIsNotNullOrderByCellTypeOrdering()).thenReturn(cellTypes);

		CellTypeHierarchy cellTypeHierarchy = service.getCellTypeHierarchy();

		assertEquals(1, cellTypeHierarchy.getCellTypeRegions().size());
		CellTypeStructureRegion region = cellTypeHierarchy.getCellTypeRegions().get(0);
		assertEquals("region 1", region.getRegionName());
		assertEquals(1, region.getCellTypeSubregions().size());
		CellTypeStructureSubregion subregion = region.getCellTypeSubregions().get(0);
		assertEquals("subregion 1", subregion.getSubregionName());
		assertEquals(1, subregion.getCellTypes().size());
		assertEquals("cell type 1", subregion.getCellTypes().get(0).getCellType());
	}

}
