package org.kpmp.participant;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SpatialViewerDataTypeTest {

	private SpatialViewerDataType type;

	@BeforeEach
	public void setUp() throws Exception {
		type = new SpatialViewerDataType();
	}

	@AfterEach
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
