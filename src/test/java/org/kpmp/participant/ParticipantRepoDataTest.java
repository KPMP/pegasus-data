package org.kpmp.participant;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParticipantRepoDataTest {
    private ParticipantRepoData participantRepoData;

        @BeforeEach
    public void setUp() throws Exception {
        participantRepoData = new ParticipantRepoData();
    }

    @AfterEach
    public void tearDown() throws Exception {
        participantRepoData = null;
    }

    @Test
    public void testSetDataCategory() {
        participantRepoData.setDataCategory("dataCategory");

        assertEquals("dataCategory", participantRepoData.getDataCategory());
    }

    @Test
    public void testSetExperimentalStrategy() {
        participantRepoData.setExperimentalStrategy("strategy");

        assertEquals("strategy", participantRepoData.getExperimentalStrategy());
    }

    @Test
    public void testSetDataType() {
        participantRepoData.setDataType("dataType");

        assertEquals("dataType", participantRepoData.getDataType());
    }

    @Test
    public void testSetCount() {
        participantRepoData.setCount(3);

        assertEquals(3, participantRepoData.getCount());
    }
}
