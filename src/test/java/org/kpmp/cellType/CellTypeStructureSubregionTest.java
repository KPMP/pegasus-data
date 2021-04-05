package org.kpmp.cellType;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CellTypeStructureSubregionTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
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
		subregion.addCellType(cellType1);

		assertEquals(1, subregion.getCellTypes().size());
		assertEquals("new cell type", subregion.getCellTypes().get(0).getCellType());

		subregion.addCellType(cellType1);
		assertEquals(1, subregion.getCellTypes().size());

		CellType cellType2 = new CellType();
		cellType2.setCellType("another cell type");
		subregion.addCellType(cellType2);
		assertEquals(2, subregion.getCellTypes().size());
		assertEquals("another cell type", subregion.getCellTypes().get(1).getCellType());
	}
}
