package org.kpmp.cellTypeSummary;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClusterHierarchyIdTest {

	private ClusterHierarchyId id;

	@BeforeEach
	public void setUp() throws Exception {
		id = new ClusterHierarchyId();
	}

	@AfterEach
	public void tearDown() throws Exception {
		id = null;
	}

	@Test
	public void testSetCellTypeId() {
		id.setCellTypeId(43);
		assertEquals(43, id.getCellTypeId());
	}

	@Test
	public void testSetClusterId() {
		id.setClusterId(66);
		assertEquals(66, id.getClusterId());
	}

}
