package org.kpmp.geneExpressionSummary;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.kpmp.geneExpressionSummary.regionalTranscriptomics.RTParticipantValue;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(Lifecycle.PER_CLASS)
class RTParticipantValueTest {

    RTParticipantValue rtParticipantValue;

    @BeforeAll
    public void setUp() throws Exception {
        rtParticipantValue = new RTParticipantValue();
    }

    @AfterAll
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