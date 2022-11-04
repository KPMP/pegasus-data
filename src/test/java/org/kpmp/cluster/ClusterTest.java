package org.kpmp.cluster;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ClusterTest {

	private Cluster cluster;

	@Before
	public void setUp() throws Exception {
		cluster = new Cluster();
	}

	@After
	public void tearDown() throws Exception {
		cluster = null;
	}

	@Test
	public void testSetClusterId() {
		cluster.setClusterId(34);
		assertEquals(34, cluster.getClusterId());
	}

	@Test
	public void testSetClusterName() {
		cluster.setClusterName("cluster name");
		assertEquals("cluster name", cluster.getClusterName());
	}

	@Test
	public void testSetAbbreviation() {
		cluster.setAbbreviation("abbrev");
		assertEquals("abbrev", cluster.getAbbreviation());
	}

	@Test
	public void testSetClusterColor() {
		cluster.setClusterColor("color");
		assertEquals("color", cluster.getClusterColor());
	}

}
