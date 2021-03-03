package org.kpmp.cellType;

import static org.junit.Assert.*;

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
		assertEquals(0, subregion.getCellTypeNames().size());
	}

	@Test
	public void testAddCellType() throws Exception {
		CellTypeStructureSubregion subregion = new CellTypeStructureSubregion("subregionName");
		subregion.addCellType("new cell type");
		
		assertEquals(1, subregion.getCellTypeNames().size());
		assertEquals("new cell type", subregion.getCellTypeNames().get(0));
		
		subregion.addCellType("new cell type");
		assertEquals(1, subregion.getCellTypeNames().size());
		assertEquals("new cell type", subregion.getCellTypeNames().get(0));
		
		subregion.addCellType("another cell type");
		assertEquals(2, subregion.getCellTypeNames().size());
	}
}
