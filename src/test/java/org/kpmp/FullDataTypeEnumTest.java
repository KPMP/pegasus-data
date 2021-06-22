package org.kpmp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FullDataTypeEnumTest {

	@Test
	public void testGetLong() {
		assertEquals(4, FullDataTypeEnum.values().length);
		assertEquals("Single-nucleus RNA-seq (snRNA-seq)", FullDataTypeEnum.SINGLE_NUCLEUS_FULL.getFull());
		assertEquals("Single-cell RNA-seq (scRNA-seq)", FullDataTypeEnum.SINGLE_CELL_FULL.getFull());
		assertEquals("Regional transcriptomics", FullDataTypeEnum.REGIONAL_TRANSCRIPTOMICS_FULL.getFull());
		assertEquals("", FullDataTypeEnum.UNKNOWN_FULL.getFull());
	}

	@Test
	public void testFromLong() {
		assertEquals(FullDataTypeEnum.SINGLE_CELL_FULL, FullDataTypeEnum.fromLong("Single-cell RNA-seq (scRNA-seq)"));
		assertEquals(FullDataTypeEnum.SINGLE_NUCLEUS_FULL, FullDataTypeEnum.fromLong("Single-nucleus RNA-seq (snRNA-seq)"));
		assertEquals(FullDataTypeEnum.REGIONAL_TRANSCRIPTOMICS_FULL, FullDataTypeEnum.fromLong("Regional transcriptomics"));
		assertEquals(FullDataTypeEnum.UNKNOWN_FULL, FullDataTypeEnum.fromLong("xxx"));
		assertEquals(FullDataTypeEnum.UNKNOWN_FULL, FullDataTypeEnum.fromLong(""));
		assertEquals(FullDataTypeEnum.UNKNOWN_FULL, FullDataTypeEnum.fromLong(null));
	}
}