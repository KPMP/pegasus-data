package org.kpmp.umap;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ReferenceClusterTest {

	private ReferenceCluster cluster;
	private static double DOUBLE_PRECISION = 0.000001d;

	@BeforeEach
	public void setUp() throws Exception {
		cluster = new ReferenceCluster("clusterAbbrev", "color");
	}

	@AfterEach
	public void tearDown() throws Exception {
		cluster = null;
	}

	@Test
	public void testConstructor() throws Exception {
		assertEquals("clusterAbbrev", cluster.getClusterAbbreviation());
		assertEquals("color", cluster.getColor());
	}

	@Test
	public void testSetXValues() {
		List<Double> expectedValues = Arrays.asList(4d, 6d);
		cluster.setXValues(expectedValues);

		assertEquals(expectedValues, cluster.getXValues());
	}

	@Test
	public void testAddXValue() {
		assertEquals(0, cluster.getXValues().size());

		cluster.addXValue(4d);

		assertEquals(1, cluster.getXValues().size());
		assertEquals(4d, cluster.getXValues().get(0), DOUBLE_PRECISION);
	}

	@Test
	public void testSetYValues() {
		List<Double> expectedValues = Arrays.asList(4d, 6d);
		cluster.setYValues(expectedValues);

		assertEquals(expectedValues, cluster.getYValues());
	}

	@Test
	public void testAddYValue() {
		assertEquals(0, cluster.getYValues().size());

		cluster.addYValue(4d);

		assertEquals(1, cluster.getYValues().size());
		assertEquals(4d, cluster.getYValues().get(0), DOUBLE_PRECISION);
	}

	@Test
	public void testSetColor() {
		cluster.setColor("new color");
		assertEquals("new color", cluster.getColor());
	}

	@Test
	public void testSetClusterName() {
		cluster.setClusterName("cluster name too");
		assertEquals("cluster name too", cluster.getClusterName());
	}

	@Test
	public void testSetClusterAbbrevation() {
		cluster.setClusterAbbreviation("cluster abbrev too");
		assertEquals("cluster abbrev too", cluster.getClusterAbbreviation());
	}

}
