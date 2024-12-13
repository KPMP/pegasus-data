package org.kpmp.geneExpressionSummary;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParticipantIdTest {

    private ParticipantId participantId;

    @BeforeEach
    public void setUp() {
        participantId = new ParticipantId();
    }

    @AfterEach
    public void tearDown() {
        participantId = null;
    }

    @Test
    public void testSetEnrollmentCategory() throws Exception {
        participantId.setEnrollmentCategory("aki");
        assertEquals("aki", participantId.getEnrollmentCategory());
    }

    @Test
    public void testSetRedcapId() throws Exception {
        participantId.setRedcapId("27-10039");
        assertEquals("27-10039", participantId.getRedcapId());
    }

}