package org.kpmp.cellType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
		CellType cellType1 = new CellType();
		cellType1.setCellType("cell type 1");
		expectedSubregion.addCellType(cellType1);
		region.addCellTypeSubregion(expectedSubregion);

		assertEquals(1, region.getCellTypeSubregions().size());
		CellTypeStructureSubregion actualSubregion = region.getCellTypeSubregions().get(0);
		assertEquals(expectedSubregion, actualSubregion);
		assertEquals(1, actualSubregion.getCellTypes().size());
		assertEquals("cell type 1", actualSubregion.getCellTypes().get(0).getCellType());
	}

	@Test
	public void testSetCellTypeSubregionsWhenAddSameSubregion() {
		CellType cellType1 = new CellType();
		cellType1.setCellType("cell type 1");
		CellTypeStructureSubregion expectedSubregion = new CellTypeStructureSubregion("subregion");
		expectedSubregion.addCellType(cellType1);
		region.addCellTypeSubregion(expectedSubregion);
		CellType cellType2 = new CellType();
		cellType2.setCellType("cell type 2");
		CellTypeStructureSubregion expectedSubregion2 = new CellTypeStructureSubregion("subregion");
		expectedSubregion2.addCellType(cellType2);
		region.addCellTypeSubregion(expectedSubregion2);

		assertEquals(1, region.getCellTypeSubregions().size());
		CellTypeStructureSubregion actualSubregion = region.getCellTypeSubregions().get(0);
		assertEquals(expectedSubregion, actualSubregion);
		List<CellType> actualCellTypes = actualSubregion.getCellTypes();
		assertEquals(2, actualCellTypes.size());
		List<CellType> expectedCellTypes = Arrays.asList(cellType1, cellType2);
		assertTrue(expectedCellTypes.containsAll(actualCellTypes) && actualCellTypes.containsAll(expectedCellTypes));
	}

	@Test
	public void testSetCellTypeSubregionsWhenAddDifferentSubregion() {
		CellType cellType1 = new CellType();
		cellType1.setCellType("cell type 1");
		CellTypeStructureSubregion expectedSubregion = new CellTypeStructureSubregion("subregion");
		expectedSubregion.addCellType(cellType1);
		region.addCellTypeSubregion(expectedSubregion);
		CellType cellType2 = new CellType();
		cellType2.setCellType("cell type 2");
		CellTypeStructureSubregion expectedSubregion2 = new CellTypeStructureSubregion("subregion2");
		expectedSubregion2.addCellType(cellType2);
		region.addCellTypeSubregion(expectedSubregion2);

		List<CellTypeStructureSubregion> actualSubregions = region.getCellTypeSubregions();
		assertEquals(2, actualSubregions.size());
		List<CellTypeStructureSubregion> expectedSubregions = Arrays.asList(expectedSubregion, expectedSubregion2);
		assertEquals(expectedSubregions.size(), actualSubregions.size());
		assertTrue(
				expectedSubregions.containsAll(actualSubregions) && actualSubregions.containsAll(expectedSubregions));
	}
}
