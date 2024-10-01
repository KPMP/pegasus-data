package org.kpmp.cluster;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClusterTest {

	private Cluster cluster;

	@BeforeEach
	public void setUp() throws Exception {
		cluster = new Cluster();
	}

	@AfterEach
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
