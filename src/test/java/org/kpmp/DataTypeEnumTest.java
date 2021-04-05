package org.kpmp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DataTypeEnumTest {

	@Test
	public void testGetAbbreviation() {
		assertEquals(3, DataTypeEnum.values().length);
		assertEquals("sn", DataTypeEnum.SINGLE_NUCLEUS.getAbbreviation());
		assertEquals("sc", DataTypeEnum.SINGLE_CELL.getAbbreviation());
		assertEquals("", DataTypeEnum.UNKNOWN.getAbbreviation());
	}

	@Test
	public void testFromAbbreviation() {
		assertEquals(DataTypeEnum.SINGLE_CELL, DataTypeEnum.fromAbbreviation("sc"));
		assertEquals(DataTypeEnum.SINGLE_NUCLEUS, DataTypeEnum.fromAbbreviation("sn"));
		assertEquals(DataTypeEnum.UNKNOWN, DataTypeEnum.fromAbbreviation("xxx"));
		assertEquals(DataTypeEnum.UNKNOWN, DataTypeEnum.fromAbbreviation(""));
		assertEquals(DataTypeEnum.UNKNOWN, DataTypeEnum.fromAbbreviation(null));
	}

}
