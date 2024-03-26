package org.kpmp.participant;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ParticipantRepoDataTest {
    private ParticipantRepoData participantRepoData;

        @Before
    public void setUp() throws Exception {
        participantRepoData = new ParticipantRepoData();
    }

    @After
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
