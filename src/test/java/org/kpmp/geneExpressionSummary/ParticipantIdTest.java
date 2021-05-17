package org.kpmp.geneExpressionSummary;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ParticipantIdTest {

    private ParticipantId participantId;

    @Before
    public void setUp() {
        participantId = new ParticipantId();
    }

    @After
    public void tearDown() {
        participantId = null;
    }

    @Test
    public void testSetTissueType() throws Exception {
        participantId.setTissueType("aki");
        assertEquals("aki", participantId.getTissueType());
    }

    @Test
    public void testSetRedcapId() throws Exception {
        participantId.setRedcapId("27-10039");
        assertEquals("27-10039", participantId.getRedcapId());
    }

}