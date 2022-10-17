package org.kpmp.participant;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SpatialViewerDataTypeTest {

	private SpatialViewerDataType type;

	@Before
	public void setUp() throws Exception {
		type = new SpatialViewerDataType();
	}

	@After
	public void tearDown() throws Exception {
		type = null;
	}

	@Test
	public void testSetDataType() {
		type.setDataType("cool spatial type");

		assertEquals("cool spatial type", type.getDataType());
	}

	@Test
	public void testSetTableName() {
		type.setTableName("sv_file_v");

		assertEquals("sv_file_v", type.getTableName());
	}

}
