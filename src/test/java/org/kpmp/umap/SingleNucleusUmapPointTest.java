package org.kpmp.umap;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SingleNucleusUmapPointTest {

	private SingleNucleusUmapPoint point;
	private static final double DELTA_FOR_DOUBLE_ASSERTIONS = 0.000001;

	@Before
	public void setUp() throws Exception {
		point = new SingleNucleusUmapPoint();
	}

	@After
	public void tearDown() throws Exception {
		point = null;
	}

	@Test
	public void testSetBarcode() {
		point.setBarcode("barcode");
		assertEquals("barcode", point.getBarcode());
	}

	@Test
	public void testSetLibraryId() {
		point.setLibraryId("library");
		assertEquals("library", point.getLibraryId());
	}

	@Test
	public void testSetnCountRNA() {
		point.setnCountRNA(34);
		assertEquals(34, point.getnCountRNA());
	}

	@Test
	public void testSetnFeatureRNA() {
		point.setnFeatureRNA(25);
		assertEquals(25, point.getnFeatureRNA());
	}

	@Test
	public void testSetPercentER() {
		point.setPercentER(35.2d);
		assertEquals(35.2d, point.getPercentER(), DELTA_FOR_DOUBLE_ASSERTIONS);
	}

	@Test
	public void testSetPercentMT() {
		point.setPercentMT(3.4d);
		assertEquals(3.4d, point.getPercentMT(), DELTA_FOR_DOUBLE_ASSERTIONS);
	}

	@Test
	public void testSetExperiment() {
		point.setExperiment("experiment");
		assertEquals("experiment", point.getExperiment());
	}

	@Test
	public void testSetSpecimenId() {
		point.setSpecimenId("specimen");
		assertEquals("specimen", point.getSpecimenId());
	}

	@Test
	public void testSetRegion() {
		point.setRegion("region");
		assertEquals("region", point.getRegion());
	}

	@Test
	public void testSetPercentCortex() {
		point.setPercentCortex(54);
		assertEquals(54, point.getPercentCortex());
	}

	@Test
	public void testSetPercentMedulla() {
		point.setPercentMedulla(4);
		assertEquals(4, point.getPercentMedulla());
	}

	@Test
	public void testSetSeuratClusterId() {
		point.setSeruatClusterId(34);
		assertEquals(34, point.getSeuratClusterId());
	}

	@Test
	public void testSetCellTypesubClassL1() {
		point.setCellTypeSubClassL1("subclass 1");
		assertEquals("subclass 1", point.getCellTypeSubClassL1());
	}

	@Test
	public void testSetCellTypeSubClassL2() {
		point.setCellTypeSubClassL2("subclass 2");
		assertEquals("subclass 2", point.getCellTypeSubClassL2());
	}

	@Test
	public void testSetCellTypeClass() {
		point.setCellTypeClass("class");
		assertEquals("class", point.getCellTypeClass());
	}

	@Test
	public void testSetUmapX() {
		point.setUmapX(24.9d);
		assertEquals(24.9d, point.getUmapX(), DELTA_FOR_DOUBLE_ASSERTIONS);
	}

	@Test
	public void testSetUmapY() {
		point.setUmapY(34.5d);
		assertEquals(34.5d, point.getUmapY(), DELTA_FOR_DOUBLE_ASSERTIONS);
	}

}
