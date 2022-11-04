package org.kpmp.cellTypeSummary;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ClusterHierarchyTest {

	private ClusterHierarchy clusterHierarchy;

	@Before
	public void setUp() throws Exception {
		clusterHierarchy = new ClusterHierarchy();
	}

	@After
	public void tearDown() throws Exception {
		clusterHierarchy = null;
	}

	@Test
	public void testSetCellTypeId() {
		clusterHierarchy.setCellTypeId(2);
		assertEquals(2, clusterHierarchy.getCellTypeId());
	}

	@Test
	public void testSetClusterId() {
		clusterHierarchy.setClusterId(4);
		assertEquals(4, clusterHierarchy.getClusterId());
	}

	@Test
	public void testSetStructureRegion() {
		clusterHierarchy.setStructureRegion("structureRegion");
		assertEquals("structureRegion", clusterHierarchy.getStructureRegion());
	}

	@Test
	public void testSetStructureSubregion() {
		clusterHierarchy.setStructureSubregion("structureSubregion");
		assertEquals("structureSubregion", clusterHierarchy.getStructureSubregion());
	}

	@Test
	public void testSetClusterName() {
		clusterHierarchy.setClusterName("clusterName");
		assertEquals("clusterName", clusterHierarchy.getClusterName());
	}

	@Test
	public void testSetIsSingleCellCluster() throws Exception {
		clusterHierarchy.setIsSingleCellCluster("Y");
		assertEquals("Y", clusterHierarchy.getIsSingleCellCluster());
	}

	@Test
	public void testSetIsSingleNucCluster() throws Exception {
		clusterHierarchy.setIsSingleNucCluster("N");
		assertEquals("N", clusterHierarchy.getIsSingleNucCluster());
	}
}
