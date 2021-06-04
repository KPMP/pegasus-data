package org.kpmp;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RTComparisonTypeEnumTest {

    @Test
    public void testGetAbbreviation() {
        assertEquals(3, RTComparisonTypeEnum.values().length);
        assertEquals("all_segments", RTComparisonTypeEnum.ALL_SEGMENTS.getAbbreviation());
        assertEquals("glom_tub", RTComparisonTypeEnum.GLOM_V_TI.getAbbreviation());
        assertEquals("", RTComparisonTypeEnum.UNKNOWN.getAbbreviation());
    }

    @Test
    public void testFromAbbreviation() {
        assertEquals(RTComparisonTypeEnum.ALL_SEGMENTS, RTComparisonTypeEnum.fromAbbreviation("all_segments"));
        assertEquals(RTComparisonTypeEnum.GLOM_V_TI, RTComparisonTypeEnum.fromAbbreviation("glom_tub"));
        assertEquals(RTComparisonTypeEnum.UNKNOWN, RTComparisonTypeEnum.fromAbbreviation(""));
    }
}
