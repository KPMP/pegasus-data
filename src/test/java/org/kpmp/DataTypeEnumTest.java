package org.kpmp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


public class DataTypeEnumTest {

	@Test
	public void testGetAbbreviation() {
		assertEquals(13, DataTypeEnum.values().length);
		assertEquals("sn", DataTypeEnum.SINGLE_NUCLEUS.getAbbreviation());
		assertEquals("sc", DataTypeEnum.SINGLE_CELL.getAbbreviation());
		assertEquals("rt", DataTypeEnum.REGIONAL_TRANSCRIPTOMICS.getAbbreviation());
		assertEquals("codex", DataTypeEnum.CODEX.getAbbreviation());
		assertEquals("sl", DataTypeEnum.SPATIAL_LIPIDOMICS.getAbbreviation());
		assertEquals("sm", DataTypeEnum.SPATIAL_METABOLOMICS.getAbbreviation());
		assertEquals("sng", DataTypeEnum.SPATIAL_NGLYCOMICS.getAbbreviation());
		assertEquals("rp", DataTypeEnum.REGIONAL_PROTEOMICS.getAbbreviation());
        assertEquals("imc", DataTypeEnum.IMAGING_MASS_CYTOMETRY.getAbbreviation());
		assertEquals("", DataTypeEnum.UNKNOWN.getAbbreviation());
	}

	@Test
	public void testFromAbbreviation() {
		assertEquals(DataTypeEnum.SINGLE_CELL, DataTypeEnum.fromAbbreviation("sc"));
		assertEquals(DataTypeEnum.SINGLE_NUCLEUS, DataTypeEnum.fromAbbreviation("sn"));
		assertEquals(DataTypeEnum.REGIONAL_TRANSCRIPTOMICS, DataTypeEnum.fromAbbreviation("rt"));
		assertEquals(DataTypeEnum.CODEX, DataTypeEnum.fromAbbreviation("codex"));
		assertEquals(DataTypeEnum.SPATIAL_LIPIDOMICS, DataTypeEnum.fromAbbreviation("sl"));
		assertEquals(DataTypeEnum.SPATIAL_METABOLOMICS, DataTypeEnum.fromAbbreviation("sm"));
		assertEquals(DataTypeEnum.SPATIAL_NGLYCOMICS, DataTypeEnum.fromAbbreviation("sng"));
		assertEquals(DataTypeEnum.REGIONAL_PROTEOMICS, DataTypeEnum.fromAbbreviation("rp"));
        assertEquals(DataTypeEnum.IMAGING_MASS_CYTOMETRY, DataTypeEnum.fromAbbreviation("imc"));
		assertEquals(DataTypeEnum.UNKNOWN, DataTypeEnum.fromAbbreviation("xxx"));
		assertEquals(DataTypeEnum.UNKNOWN, DataTypeEnum.fromAbbreviation(""));
		assertEquals(DataTypeEnum.UNKNOWN, DataTypeEnum.fromAbbreviation(null));
	}

}
