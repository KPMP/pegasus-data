package org.kpmp.cellType;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CellTypeStructureSubregionTest {

	@BeforeEach
	public void setUp() throws Exception {
	}

	@AfterEach
	public void tearDown() throws Exception {
	}

	@Test
	public void testConstructor() {
		CellTypeStructureSubregion subregion = new CellTypeStructureSubregion("subregionName");

		assertEquals("subregionName", subregion.getSubregionName());
		assertEquals(0, subregion.getCellTypes().size());
	}

	@Test
	public void testAddCellType() throws Exception {
		CellTypeStructureSubregion subregion = new CellTypeStructureSubregion("subregionName");
		CellType cellType1 = new CellType();
		cellType1.setCellType("new cell type");
		cellType1.setCellTypeId(1);
		subregion.addCellType(cellType1);

		assertEquals(1, subregion.getCellTypes().size());
		assertEquals("new cell type", subregion.getCellTypes().get(0).getCellType());

		subregion.addCellType(cellType1);
		assertEquals(1, subregion.getCellTypes().size());

		CellType cellType2 = new CellType();
		cellType2.setCellType("another cell type");
		cellType2.setCellTypeId(2);
		subregion.addCellType(cellType2);
		assertEquals(2, subregion.getCellTypes().size());
		assertEquals("another cell type", subregion.getCellTypes().get(1).getCellType());
	}
}
