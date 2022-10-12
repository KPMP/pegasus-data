package org.kpmp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FullDataTypeEnumTest {

	@Test
	public void testGetLong() {
		assertEquals(11, FullDataTypeEnum.values().length);
		assertEquals("Single-nucleus RNA-seq (snRNA-seq)", FullDataTypeEnum.SINGLE_NUCLEUS_FULL.getFull());
		assertEquals("Single-cell RNA-seq (scRNA-seq)", FullDataTypeEnum.SINGLE_CELL_FULL.getFull());
		assertEquals("Regional transcriptomics", FullDataTypeEnum.REGIONAL_TRANSCRIPTOMICS_FULL.getFull());
		assertEquals("CODEX", FullDataTypeEnum.CODEX_FULL.getFull());
		assertEquals("Spatial Lipidomics", FullDataTypeEnum.SPATIAL_LIPIDOMICS_FULL.getFull());
		assertEquals("Spatial Metabolomics", FullDataTypeEnum.SPATIAL_METABOLOMICS_FULL.getFull());
		assertEquals("Spatial N-glycomics", FullDataTypeEnum.SPATIAL_NGLYCOMICS_FULL.getFull());	
		assertEquals("", FullDataTypeEnum.UNKNOWN_FULL.getFull());
	}

	@Test
	public void testFromLong() {
		assertEquals(FullDataTypeEnum.SINGLE_CELL_FULL, FullDataTypeEnum.fromLong("Single-cell RNA-seq (scRNA-seq)"));
		assertEquals(FullDataTypeEnum.SINGLE_NUCLEUS_FULL, FullDataTypeEnum.fromLong("Single-nucleus RNA-seq (snRNA-seq)"));
		assertEquals(FullDataTypeEnum.REGIONAL_TRANSCRIPTOMICS_FULL, FullDataTypeEnum.fromLong("Regional transcriptomics"));
		assertEquals(FullDataTypeEnum.CODEX_FULL, FullDataTypeEnum.fromLong("CODEX"));
		assertEquals(FullDataTypeEnum.SPATIAL_LIPIDOMICS_FULL, FullDataTypeEnum.fromLong("Spatial Lipidomics"));
		assertEquals(FullDataTypeEnum.SPATIAL_METABOLOMICS_FULL, FullDataTypeEnum.fromLong("Spatial Metabolomics"));
		assertEquals(FullDataTypeEnum.SPATIAL_NGLYCOMICS_FULL, FullDataTypeEnum.fromLong("Spatial N-glycomics"));	
		assertEquals(FullDataTypeEnum.UNKNOWN_FULL, FullDataTypeEnum.fromLong("xxx"));
		assertEquals(FullDataTypeEnum.UNKNOWN_FULL, FullDataTypeEnum.fromLong(""));
		assertEquals(FullDataTypeEnum.UNKNOWN_FULL, FullDataTypeEnum.fromLong(null));
	}
}