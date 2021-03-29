package org.kpmp.umap;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UmapPointTest {

	private static double DOUBLE_PRECISION = 0.000001d;
	private UmapPoint point;

	@Before
	public void setUp() throws Exception {
		point = new UmapPoint();
	}

	@After
	public void tearDown() throws Exception {
		point = null;
	}

	@Test
	public void testSetUmapX() {
		point.setUmapX(2.4d);
		assertEquals(2.4d, point.getUmapX(), DOUBLE_PRECISION);
	}

	@Test
	public void testSetUmapY() {
		point.setUmapY(2.5d);
		assertEquals(2.5d, point.getUmapY(), DOUBLE_PRECISION);
	}

	@Test
	public void testSetClusterName() {
		point.setClusterName("cluster name");
		assertEquals("cluster name", point.getClusterName());
	}

	@Test
	public void testSetClusterColor() {
		point.setClusterColor("cluster color");
		assertEquals("cluster color", point.getClusterColor());
	}

	@Test
	public void testSetExperimentType() {
		point.setDataType("experiment type");
		assertEquals("experiment type", point.getDataType());
	}

	@Test
	public void testSetExpressionValue() throws Exception {
		point.setExpressionValue(4d);
		assertEquals(4d, point.getExpressionValue(), DOUBLE_PRECISION);
	}

	@Test
	public void testSetBarcode() throws Exception {
		point.setBarcode("barcode");
		assertEquals("barcode", point.getBarcode());
	}

}
