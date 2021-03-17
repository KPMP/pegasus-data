package org.kpmp.umap;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UmapPointTest {

	private static final double DELTA_FOR_DOUBLE_ASSERTIONS = 0.000001;
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
	public void testSetxCoordinate() {
		point.setxCoordinate(4.3d);
		assertEquals(4.3d, point.getxCoordinate(), DELTA_FOR_DOUBLE_ASSERTIONS);
	}

	@Test
	public void testSetyCoordinate() {
		point.setyCoordinate(5.3d);
		assertEquals(5.3d, point.getyCoordinate(), DELTA_FOR_DOUBLE_ASSERTIONS);
	}

	@Test
	public void testSetCluster() {
		point.setCluster("cluster");
		assertEquals("cluster", point.getCluster());
	}

	@Test
	public void testSetColor() throws Exception {
		point.setColor("#121212");
		assertEquals("#121212", point.getColor());
	}

}
