package org.kpmp.cellType;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CellTypeStructureRegionTest {

	private CellTypeStructureRegion region;

	@Before
	public void setUp() throws Exception {
		region = new CellTypeStructureRegion("region1");
	}

	@After
	public void tearDown() throws Exception {
		region = null;
	}

	@Test
	public void testConstructor() {
		assertEquals("region1", region.getRegionName());
		assertEquals(0, region.getCellTypeSubregions().size());
	}

	@Test
	public void testSetCellTypeSubregions() {
		CellTypeStructureSubregion expectedSubregion = new CellTypeStructureSubregion("subregion");
		expectedSubregion.addCellType("cell type 1");
		region.addCellTypeSubregion(expectedSubregion);

		assertEquals(1, region.getCellTypeSubregions().size());
		CellTypeStructureSubregion actualSubregion = region.getCellTypeSubregions().get(0);
		assertEquals(expectedSubregion, actualSubregion);
		assertEquals(1, actualSubregion.getCellTypeNames().size());
		assertEquals("cell type 1", actualSubregion.getCellTypeNames().get(0));
	}

	@Test
	public void testSetCellTypeSubregionsWhenAddSameSubregion() {
		CellTypeStructureSubregion expectedSubregion = new CellTypeStructureSubregion("subregion");
		expectedSubregion.addCellType("cell type 1");
		region.addCellTypeSubregion(expectedSubregion);
		CellTypeStructureSubregion expectedSubregion2 = new CellTypeStructureSubregion("subregion");
		expectedSubregion2.addCellType("cell type 2");
		region.addCellTypeSubregion(expectedSubregion2);

		assertEquals(1, region.getCellTypeSubregions().size());
		CellTypeStructureSubregion actualSubregion = region.getCellTypeSubregions().get(0);
		assertEquals(expectedSubregion, actualSubregion);
		List<String> actualCellTypes = actualSubregion.getCellTypeNames();
		assertEquals(2, actualCellTypes.size());
		List<String> expectedCellTypes = Arrays.asList("cell type 1", "cell type 2");
		assertTrue(expectedCellTypes.containsAll(actualCellTypes) && actualCellTypes.containsAll(expectedCellTypes));
	}
	
	@Test
	public void testSetCellTypeSubregionsWhenAddDifferentSubregion() {
		CellTypeStructureSubregion expectedSubregion = new CellTypeStructureSubregion("subregion");
		expectedSubregion.addCellType("cell type 1");
		region.addCellTypeSubregion(expectedSubregion);
		CellTypeStructureSubregion expectedSubregion2 = new CellTypeStructureSubregion("subregion2");
		expectedSubregion2.addCellType("cell type 2");
		region.addCellTypeSubregion(expectedSubregion2);
		
		List<CellTypeStructureSubregion> actualSubregions = region.getCellTypeSubregions();
		assertEquals(2, actualSubregions.size());
		List<CellTypeStructureSubregion> expectedSubregions = Arrays.asList(expectedSubregion, expectedSubregion2);
		assertEquals(expectedSubregions.size(), actualSubregions.size());
		assertTrue(expectedSubregions.containsAll(actualSubregions) && actualSubregions.containsAll(expectedSubregions));
	}
}
