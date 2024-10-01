package org.kpmp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class OmicsTypeEnumTest {

	@Test
	public void testGetAbbreviation() {
		assertEquals(2, OmicsTypeEnum.values().length);
		assertEquals("TRANSCRIPTOMICS", OmicsTypeEnum.TRANSCRIPTOMICS.getEnum());
		assertEquals("", OmicsTypeEnum.NONE.getEnum());
	}

	@Test
	public void testFromAbbreviation() {
		assertEquals(OmicsTypeEnum.TRANSCRIPTOMICS, OmicsTypeEnum.fromEnum("TRANSCRIPTOMICS"));
		assertEquals(OmicsTypeEnum.NONE, OmicsTypeEnum.fromEnum(""));
	}

}
