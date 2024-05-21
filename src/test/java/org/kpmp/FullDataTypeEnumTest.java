package org.kpmp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FullDataTypeEnumTest {

	@Test
	public void testGetLong() {
		assertEquals(13, FullDataTypeEnum.values().length);
		assertEquals("Single-nucleus RNA-seq (snRNA-seq)", FullDataTypeEnum.SINGLE_NUCLEUS_FULL.getFullName());
		assertEquals("Single-cell RNA-seq (scRNA-seq)", FullDataTypeEnum.SINGLE_CELL_FULL.getFullName());
		assertEquals("Regional transcriptomics", FullDataTypeEnum.REGIONAL_TRANSCRIPTOMICS_FULL.getFullName());
		assertEquals("CODEX", FullDataTypeEnum.CODEX_FULL.getFullName());
		assertEquals("Spatial Lipidomics", FullDataTypeEnum.SPATIAL_LIPIDOMICS_FULL.getFullName());
		assertEquals("Spatial Metabolomics", FullDataTypeEnum.SPATIAL_METABOLOMICS_FULL.getFullName());
		assertEquals("Spatial N-glycomics", FullDataTypeEnum.SPATIAL_NGLYCOMICS_FULL.getFullName());
		assertEquals("Regional proteomics", FullDataTypeEnum.REGIONAL_PROTEOMICS_FULL.getFullName());
        assertEquals("Imaging Mass Cytometry", FullDataTypeEnum.IMAGING_MASS_CYTOMETRY_FULL.getFullName());
		assertEquals("", FullDataTypeEnum.UNKNOWN_FULL.getFullName());
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
        assertEquals(FullDataTypeEnum.IMAGING_MASS_CYTOMETRY_FULL, FullDataTypeEnum.fromLong("Imaging Mass Cytometry"));
		assertEquals(FullDataTypeEnum.UNKNOWN_FULL, FullDataTypeEnum.fromLong("xxx"));
		assertEquals(FullDataTypeEnum.UNKNOWN_FULL, FullDataTypeEnum.fromLong(""));
		assertEquals(FullDataTypeEnum.UNKNOWN_FULL, FullDataTypeEnum.fromLong(null));
	}
}