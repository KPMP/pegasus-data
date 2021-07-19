package org.kpmp.geneExpressionSummary;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RTParticipantValueTest {

    RTParticipantValue rtParticipantValue;

    @Before
    public void setUp() throws Exception {
        rtParticipantValue = new RTParticipantValue();
    }

    @After
    public void tearDown() throws Exception {
        rtParticipantValue = null;
    }

    @Test
    void setRedcapId() {
        rtParticipantValue.setRedcapId("rcid");
        assertEquals("rcid", rtParticipantValue.getRedcapId());
    }

    @Test
    void setTissueType() {
        rtParticipantValue.setTissueType("ttype");
        assertEquals("ttype", rtParticipantValue.getTissueType());
    }
}