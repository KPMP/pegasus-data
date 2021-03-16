package org.kpmp.umap;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SingleCellUMAPPointTest {

	private static final double DELTA_FOR_DOUBLE_ASSERTIONS = 0.000001;
	private SingleCellUMAPPoint point;

	@Before
	public void setUp() throws Exception {
		point = new SingleCellUMAPPoint();
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
	public void testSetOrigIdent() {
		point.setOrigIdent("origIdent");
		assertEquals("origIdent", point.getOrigIdent());
	}

	@Test
	public void testSetnCountRNA() {
		point.setnCountRNA(3);
		assertEquals(3, point.getnCountRNA());
	}

	@Test
	public void testSetnFeatureRNA() {
		point.setnFeatureRNA(34);
		assertEquals(34, point.getnFeatureRNA());
	}

	@Test
	public void testSetPercentMT() {
		point.setPercentMT(3.1d);
		assertEquals(3.1d, point.getPercentMT(), DELTA_FOR_DOUBLE_ASSERTIONS);
	}

	@Test
	public void testSetCellTypeSubClassL2() {
		point.setCellTypeSubClassL2("subclass 2");
		assertEquals("subclass 2", point.getCellTypeSubClassL2());
	}

	@Test
	public void testSetCellTypeSubClassL1() {
		point.setCellTypeSubClassL1("subclass 1");
		assertEquals("subclass 1", point.getCellTypeSubClassL1());
	}

	@Test
	public void testSetSampleType() {
		point.setSampleType("sample type");
		assertEquals("sample type", point.getSampleType());
	}

	@Test
	public void testSetUmapX() {
		point.setUmapX(3.4d);
		assertEquals(3.4d, point.getUmapX(), DELTA_FOR_DOUBLE_ASSERTIONS);
	}

	@Test
	public void testSetUmapY() {
		point.setUmapY(2.4d);
		assertEquals(2.4d, point.getUmapY(), DELTA_FOR_DOUBLE_ASSERTIONS);
	}

	@Test
	public void testSetSpecimenId() {
		point.setSpecimenId("specimen id");
		assertEquals("specimen id", point.getSpecimenId());
	}

	@Test
	public void testSetClusterId() {
		point.setClusterId(3);
		assertEquals(3, point.getClusterId());
	}

	@Test
	public void testSetCellTypeClass() {
		point.setCellTypeClass("class");
		assertEquals("class", point.getCellTypeClass());
	}

	@Test
	public void testSetLibraryId() {
		point.setLibraryId("library");
		assertEquals("library", point.getLibraryId());
	}

}
