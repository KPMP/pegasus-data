package org.kpmp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DataTypeEnumTest {

	@Test
	public void testGetAbbreviation() {
		assertEquals(8, DataTypeEnum.values().length);
		assertEquals("sn", DataTypeEnum.SINGLE_NUCLEUS.getAbbreviation());
		assertEquals("sc", DataTypeEnum.SINGLE_CELL.getAbbreviation());
		assertEquals("rt", DataTypeEnum.REGIONAL_TRANSCRIPTOMICS.getAbbreviation());
		assertEquals("codex", DataTypeEnum.CODEX.getAbbreviation());
		assertEquals("", DataTypeEnum.UNKNOWN.getAbbreviation());
	}

	@Test
	public void testFromAbbreviation() {
		assertEquals(DataTypeEnum.SINGLE_CELL, DataTypeEnum.fromAbbreviation("sc"));
		assertEquals(DataTypeEnum.SINGLE_NUCLEUS, DataTypeEnum.fromAbbreviation("sn"));
		assertEquals(DataTypeEnum.REGIONAL_TRANSCRIPTOMICS, DataTypeEnum.fromAbbreviation("rt"));
		assertEquals(DataTypeEnum.CODEX, DataTypeEnum.fromAbbreviation("codex"));
		assertEquals(DataTypeEnum.UNKNOWN, DataTypeEnum.fromAbbreviation("xxx"));
		assertEquals(DataTypeEnum.UNKNOWN, DataTypeEnum.fromAbbreviation(""));
		assertEquals(DataTypeEnum.UNKNOWN, DataTypeEnum.fromAbbreviation(null));
	}

}
