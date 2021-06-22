package org.kpmp.geneExpressionSummary;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RTSummaryValueTest {

    RTSummaryValue rtSummaryValue;

    @BeforeEach
    void setUp() {
        rtSummaryValue = new RTSummaryValue();
    }

    @AfterEach
    void tearDown() {
        rtSummaryValue = null;
    }

    @Test
    void setSegment() {
        rtSummaryValue.setSegment("segment");
        assertEquals("segment", rtSummaryValue.getSegment());
    }

    @Test
    void setAllCount() {
        rtSummaryValue.setAllCount(45);
        assertEquals(45, rtSummaryValue.getAllCount(), 0.01);
    }

    @Test
    void setHrtCount() {
        rtSummaryValue.setHrtCount(7);
        assertEquals(7, rtSummaryValue.getHrtCount(), 0.01);
    }

    @Test
    void setAkiCount() {
        rtSummaryValue.setAkiCount(4);
        assertEquals(4, rtSummaryValue.getAkiCount(), 0.01);
    }

    @Test
    void setCkdCount() {
        rtSummaryValue.setCkdCount(36);
        assertEquals(36, rtSummaryValue.getCkdCount(), 0.01);
    }
}