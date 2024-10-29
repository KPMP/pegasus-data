package org.kpmp.participant;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class ParticipantClinicalDatasetTest {
    ParticipantClinicalDataset participantClinicalDataset;

    @BeforeAll
    public void setUp() throws Exception {
        this.participantClinicalDataset = new ParticipantClinicalDataset();
    }

    @AfterAll
    public void tearDown() throws Exception {
        participantClinicalDataset = null;
    }

    @Test
    void testSetParticipantClicalId() {
        participantClinicalDataset.setParticipantClinicalId(0);
        assertEquals(0, participantClinicalDataset.getParticipantClinicalId());
    }

    @Test
    void testSetParticipantId() {
        participantClinicalDataset.setParticipantId(2);
        assertEquals(2, participantClinicalDataset.getParticipantId());
    }

    @Test
    void testSetKdigoStage() {
        participantClinicalDataset.setKdigoStage("Stage 1");
        assertEquals("Stage 1", participantClinicalDataset.getKdigoStage());
    }

    @Test
    void testSetBaselineEgfr() {
        participantClinicalDataset.setBaselineEgfr("blah");
        assertEquals("blah", participantClinicalDataset.getBaselineEgfr());
    }

    @Test
    void testSetProteinuria() {
        participantClinicalDataset.setProteinuria("proteinuria");
        assertEquals("proteinuria", participantClinicalDataset.getProteinuria());
    }

    @Test
    void testSetA1c() {
        participantClinicalDataset.setA1c("a1c");
        assertEquals("a1c", participantClinicalDataset.getA1c());
    }

    @Test
    void testSetAlbuminuria() {
        participantClinicalDataset.setAlbuminuria("ouchie");
        assertEquals("ouchie", participantClinicalDataset.getAlbuminuria());
    }

    @Test
    void testSetDiabetesHistory() {
        participantClinicalDataset.setDiabetesHistory("nope");
        assertEquals("nope", participantClinicalDataset.getDiabetesHistory());
    }

    @Test
    void testSetDiabetesDuration () {
        participantClinicalDataset.setDiabetesDuration("since the stone age");
        assertEquals("since the stone age", participantClinicalDataset.getDiabetesDuration());
    }

    @Test
    void testSetHypertensionHistory() {
        participantClinicalDataset.setHypertensionHistory("yes");
        assertEquals("yes", participantClinicalDataset.getHypertensionHistory());
    }

    @Test
    void testSetHypertensionDuration() {
        participantClinicalDataset.setHypertensionDuration("since the bronze age");
        assertEquals("since the bronze age", participantClinicalDataset.getHypertensionDuration());
    }

    @Test
    void testSetOnRaasBlockade() {
        participantClinicalDataset.setOnRaasBlockade("yes");
        assertEquals("yes", participantClinicalDataset.getOnRaasBlockade());
    }

    @Test
    void testSetRace(){
        participantClinicalDataset.setRace("alien from outer space");
        assertEquals("alien from outer space", participantClinicalDataset.getRace());
    }

    @Test
    void testSetAgeBinned() {
        participantClinicalDataset.setAgeBinned("age-age2");
        assertEquals("age-age2", participantClinicalDataset.getAgeBinned());
    }
    
}
