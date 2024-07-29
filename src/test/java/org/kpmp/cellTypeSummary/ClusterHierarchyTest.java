package org.kpmp.cellTypeSummary;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClusterHierarchyTest {

	private ClusterHierarchy clusterHierarchy;

	@BeforeEach
	public void setUp() throws Exception {
		clusterHierarchy = new ClusterHierarchy();
	}

	@AfterEach
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

	@Test
	public void testSetIsRPCluster() throws Exception {
		clusterHierarchy.setIsRegionalProteomics("N");
		assertEquals("N", clusterHierarchy.getIsRegionalProteomics());
	}

	@Test
	public void testSetIsRTCluster() throws Exception {
		clusterHierarchy.setIsRegionalTranscriptomics("N");
		assertEquals("N", clusterHierarchy.getIsRegionalTranscriptomics());
	}
}
