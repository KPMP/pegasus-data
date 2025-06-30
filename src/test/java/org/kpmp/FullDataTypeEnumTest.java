package org.kpmp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class FullDataTypeEnumTest {

	@Test
	public void testGetLong() {
		assertEquals(13, FullDataTypeEnum.values().length);
		assertEquals("Single-nucleus RNA-seq (snRNA-seq)", FullDataTypeEnum.SINGLE_NUCLEUS.getFullName());
		assertEquals("Single-cell RNA-seq (scRNA-seq)", FullDataTypeEnum.SINGLE_CELL.getFullName());
		assertEquals("Regional transcriptomics", FullDataTypeEnum.REGIONAL_TRANSCRIPTOMICS.getFullName());
		assertEquals("CODEX", FullDataTypeEnum.CODEX.getFullName());
		assertEquals("Spatial Lipidomics", FullDataTypeEnum.SPATIAL_LIPIDOMICS.getFullName());
		assertEquals("Spatial Metabolomics", FullDataTypeEnum.SPATIAL_METABOLOMICS.getFullName());
		assertEquals("Spatial N-glycomics", FullDataTypeEnum.SPATIAL_NGLYCOMICS.getFullName());
		assertEquals("Regional proteomics", FullDataTypeEnum.REGIONAL_PROTEOMICS.getFullName());
        assertEquals("Imaging Mass Cytometry", FullDataTypeEnum.IMAGING_MASS_CYTOMETRY.getFullName());
		assertEquals("", FullDataTypeEnum.UNKNOWN.getFullName());
	}

	@Test
	public void testFromLong() {
		assertEquals(FullDataTypeEnum.SINGLE_CELL, FullDataTypeEnum.fromLong("Single-cell RNA-seq (scRNA-seq)"));
		assertEquals(FullDataTypeEnum.SINGLE_NUCLEUS, FullDataTypeEnum.fromLong("Single-nucleus RNA-seq (snRNA-seq)"));
		assertEquals(FullDataTypeEnum.REGIONAL_TRANSCRIPTOMICS, FullDataTypeEnum.fromLong("Regional transcriptomics"));
		assertEquals(FullDataTypeEnum.CODEX, FullDataTypeEnum.fromLong("CODEX"));
		assertEquals(FullDataTypeEnum.SPATIAL_LIPIDOMICS, FullDataTypeEnum.fromLong("Spatial Lipidomics"));
		assertEquals(FullDataTypeEnum.SPATIAL_METABOLOMICS, FullDataTypeEnum.fromLong("Spatial Metabolomics"));
		assertEquals(FullDataTypeEnum.SPATIAL_NGLYCOMICS, FullDataTypeEnum.fromLong("Spatial N-glycomics"));
        assertEquals(FullDataTypeEnum.IMAGING_MASS_CYTOMETRY, FullDataTypeEnum.fromLong("Imaging Mass Cytometry"));
		assertEquals(FullDataTypeEnum.UNKNOWN, FullDataTypeEnum.fromLong("xxx"));
		assertEquals(FullDataTypeEnum.UNKNOWN, FullDataTypeEnum.fromLong(""));
		assertEquals(FullDataTypeEnum.UNKNOWN, FullDataTypeEnum.fromLong(null));
	}

	@Test
	public void testGetAbbreviation() {
		assertEquals(13, FullDataTypeEnum.values().length);
		assertEquals("sn", FullDataTypeEnum.SINGLE_NUCLEUS.getAbbreviation());
		assertEquals("sc", FullDataTypeEnum.SINGLE_CELL.getAbbreviation());
		assertEquals("rt", FullDataTypeEnum.REGIONAL_TRANSCRIPTOMICS.getAbbreviation());
		assertEquals("codex", FullDataTypeEnum.CODEX.getAbbreviation());
		assertEquals("sl", FullDataTypeEnum.SPATIAL_LIPIDOMICS.getAbbreviation());
		assertEquals("sm", FullDataTypeEnum.SPATIAL_METABOLOMICS.getAbbreviation());
		assertEquals("sng", FullDataTypeEnum.SPATIAL_NGLYCOMICS.getAbbreviation());
		assertEquals("rp", FullDataTypeEnum.REGIONAL_PROTEOMICS.getAbbreviation());
		assertEquals("imc", FullDataTypeEnum.IMAGING_MASS_CYTOMETRY.getAbbreviation());
		assertEquals("", FullDataTypeEnum.UNKNOWN.getAbbreviation());
	}

	@Test
	public void testFromAbbreviation() {
		assertEquals(FullDataTypeEnum.SINGLE_CELL, FullDataTypeEnum.fromAbbreviation("sc"));
		assertEquals(FullDataTypeEnum.SINGLE_NUCLEUS, FullDataTypeEnum.fromAbbreviation("sn"));
		assertEquals(FullDataTypeEnum.REGIONAL_TRANSCRIPTOMICS, FullDataTypeEnum.fromAbbreviation("rt"));
		assertEquals(FullDataTypeEnum.CODEX, FullDataTypeEnum.fromAbbreviation("codex"));
		assertEquals(FullDataTypeEnum.SPATIAL_LIPIDOMICS, FullDataTypeEnum.fromAbbreviation("sl"));
		assertEquals(FullDataTypeEnum.SPATIAL_METABOLOMICS, FullDataTypeEnum.fromAbbreviation("sm"));
		assertEquals(FullDataTypeEnum.SPATIAL_NGLYCOMICS, FullDataTypeEnum.fromAbbreviation("sng"));
		assertEquals(FullDataTypeEnum.REGIONAL_PROTEOMICS, FullDataTypeEnum.fromAbbreviation("rp"));
		assertEquals(FullDataTypeEnum.IMAGING_MASS_CYTOMETRY, FullDataTypeEnum.fromAbbreviation("imc"));
		assertEquals(FullDataTypeEnum.UNKNOWN, FullDataTypeEnum.fromAbbreviation("xxx"));
		assertEquals(FullDataTypeEnum.UNKNOWN, FullDataTypeEnum.fromAbbreviation(""));
		assertEquals(FullDataTypeEnum.UNKNOWN, FullDataTypeEnum.fromAbbreviation(null));
	}
}