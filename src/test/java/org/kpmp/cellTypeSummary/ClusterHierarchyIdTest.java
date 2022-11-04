package org.kpmp.cellTypeSummary;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ClusterHierarchyIdTest {

	private ClusterHierarchyId id;

	@Before
	public void setUp() throws Exception {
		id = new ClusterHierarchyId();
	}

	@After
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
