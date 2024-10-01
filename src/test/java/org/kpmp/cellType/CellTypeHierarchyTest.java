package org.kpmp.cellType;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CellTypeHierarchyTest {

	private CellTypeHierarchy hierarchy;

	@BeforeEach
	public void setUp() throws Exception {
		hierarchy = new CellTypeHierarchy();
	}

	@AfterEach
	public void tearDown() throws Exception {
		hierarchy = null;
	}

	@Test
	public void testAddCellTypeStructureRegion() throws Exception {
		assertEquals(0, hierarchy.getCellTypeRegions().size());
		
		CellTypeStructureRegion expectedRegion = new CellTypeStructureRegion("region");
		hierarchy.addCellTypeStructureRegion(expectedRegion);
		
		assertEquals(1, hierarchy.getCellTypeRegions().size());
		assertEquals(expectedRegion, hierarchy.getCellTypeRegions().get(0));
	}
	
	@Test
	public void testAddCellTypeStructureRegionWhenAddingTwoWithSameName() throws Exception {
		assertEquals(0, hierarchy.getCellTypeRegions().size());
		
		CellTypeStructureRegion region1 = new CellTypeStructureRegion("region");
		hierarchy.addCellTypeStructureRegion(region1);
		CellTypeStructureRegion region2 = new CellTypeStructureRegion("region");
		hierarchy.addCellTypeStructureRegion(region2);
		
		assertEquals(1, hierarchy.getCellTypeRegions().size());
		assertEquals("region", hierarchy.getCellTypeRegions().get(0).getRegionName());
	}
	@Test
	public void testAddCellTypeStructureRegionWhenAddingTwoWithDifferentNames() throws Exception {
		assertEquals(0, hierarchy.getCellTypeRegions().size());
		
		CellTypeStructureRegion region1 = new CellTypeStructureRegion("region");
		hierarchy.addCellTypeStructureRegion(region1);
		CellTypeStructureRegion region2 = new CellTypeStructureRegion("region2");
		hierarchy.addCellTypeStructureRegion(region2);
		
		assertEquals(2, hierarchy.getCellTypeRegions().size());
		List<CellTypeStructureRegion> expectedRegions = Arrays.asList(region1, region2);
		assertTrue(expectedRegions.containsAll(hierarchy.getCellTypeRegions()) && hierarchy.getCellTypeRegions().containsAll(expectedRegions));
	}

}
