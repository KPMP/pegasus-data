package org.kpmp.participant;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(Lifecycle.PER_CLASS)
class ParticipantSummaryDatasetTest {
    ParticipantSummaryDataset participantSummaryDataset;

    @BeforeAll
    public void setUp() throws Exception {
        this.participantSummaryDataset = new ParticipantSummaryDataset();
    }

    @AfterAll
    public void tearDown() throws Exception {
        participantSummaryDataset = null;
    }

    @Test
    void setParticipantId() {
        participantSummaryDataset.setParticipantId(1);
        assertEquals(1, participantSummaryDataset.getParticipantId());
    }   

    @Test
    void setOldParticipantId() {
        participantSummaryDataset.setOldParticipantId("oldid");
        assertEquals("oldid", participantSummaryDataset.getOldParticipantId());
    }
    
    @Test
    void setRedcapId() {
        participantSummaryDataset.setRedcapId("rcid");
        assertEquals("rcid", participantSummaryDataset.getRedcapId());
    }
    
    @Test
    void setAgeBinned() {
        participantSummaryDataset.setAgeBinned("age");
        assertEquals("age", participantSummaryDataset.getAgeBinned());
    }
    
    @Test
    void setSex() {
        participantSummaryDataset.setSex("male");
        assertEquals("male", participantSummaryDataset.getSex());
    }

    @Test
    void setTissueSource() {
        participantSummaryDataset.setTissueSource("tissue");
        assertEquals("tissue", participantSummaryDataset.getTissueSource());
    }

    @Test
    void setProtocol() {
        participantSummaryDataset.setProtocol("protocol");
        assertEquals("protocol", participantSummaryDataset.getProtocol());
    }

    @Test
    void setSampleType() {
        participantSummaryDataset.setSampleType("sample");
        assertEquals("sample", participantSummaryDataset.getSampleType());
    }

    @Test
    void setEnrollmentCategory() {
        participantSummaryDataset.setEnrollmentCategory("tissue");
        assertEquals("tissue", participantSummaryDataset.getEnrollmentCategory());
    }

    @Test
    void setClinicalData() {
        participantSummaryDataset.setClinicalData("clinical");
        assertEquals("clinical", participantSummaryDataset.getClinicalData());
    }

}