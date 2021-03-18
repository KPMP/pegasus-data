package org.kpmp.umap;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UmapPointIdTest {

	private UmapPointId id;
	private static double DOUBLE_PRECISION = 0.000001d;

	@Before
	public void setUp() throws Exception {
		id = new UmapPointId();
	}

	@After
	public void tearDown() throws Exception {
		id = null;
	}

	@Test
	public void testGetUmapX() {
		id.setUmapX(24.5d);
		assertEquals(24.5d, id.getUmapX(), DOUBLE_PRECISION);
	}

	@Test
	public void testGetUmapY() {
		id.setUmapY(34.6d);
		assertEquals(34.6d, id.getUmapY(), DOUBLE_PRECISION);
	}

}
