package org.kpmp.cellType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CellTypeTest {

	private CellType cellType;

	@Before
	public void setUp() {
		cellType = new CellType();
	}

	@After
	public void tearDown() {
		cellType = null;
	}

	@Test
	public void testSetCellTypeId() {
		cellType.setCellTypeId(5);
		assertEquals(5, cellType.getCellTypeId());
	}

	@Test
	public void testSetStructureRegion() {
		cellType.setStructureRegion("region");
		assertEquals("region", cellType.getStructureRegion());
	}

	@Test
	public void testSetStructureSubregion() {
		cellType.setStructureSubregion("subregion");
		assertEquals("subregion", cellType.getStructureSubregion());
	}

	@Test
	public void testSetCellType() {
		cellType.setCellType("type");
		assertEquals("type", cellType.getCellType());
	}

	@Test
	public void testSetReleaseVer() {
		cellType.setReleaseVer(2.0);
		assertEquals((Double) 2.0, cellType.getReleaseVer());
	}

	@Test
	public void testSetReleaseSunset() {
		cellType.setReleaseSunset(2.0);
		assertEquals((Double) 2.0, cellType.getReleaseSunset());
	}

	@Test
	public void testSetCellTypeOrderField() throws Exception {
		cellType.setCellTypeOrdering(5);
		assertEquals(5, cellType.getCellTypeOrdering());
	}

	@Test
	public void testSetSynonyms() throws Exception {
		CellTypeSynonym cellTypeSynonym = new CellTypeSynonym();
		cellTypeSynonym.setCellTypeSynonym("syn1");
		CellTypeSynonym cellTypeSynonym2 = new CellTypeSynonym();
		cellTypeSynonym2.setCellTypeSynonym("syn2");
		List<CellTypeSynonym> synonymList = new ArrayList<>();
		synonymList.add(cellTypeSynonym);
		synonymList.add(cellTypeSynonym2);
		Set<CellTypeSynonym> synonymSet = new HashSet<>(synonymList);
		cellType.setSynonyms(synonymSet);
		List<String> synonyms = cellType.getSynonymStringList();
		assertTrue(synonyms.contains("syn1"));
		assertTrue(synonyms.contains("syn2"));
	}

	@Test
	public void testEqualsAndHashCode() throws Exception {
		cellType.setCellType("same name");
		CellType cellType1 = new CellType();
		cellType1.setCellType("same name");

		assertEquals(cellType, cellType1);
		assertEquals(cellType.hashCode(), cellType1.hashCode());
	}
}