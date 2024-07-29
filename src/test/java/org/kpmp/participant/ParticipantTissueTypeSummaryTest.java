package org.kpmp.participant;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParticipantTissueTypeSummaryTest {
  private ParticipantTissueTypeSummary tissueSummary;

  @BeforeEach
  public void setUp() {
    tissueSummary = new ParticipantTissueTypeSummary(Long.valueOf(4), Long.valueOf(5), Long.valueOf(6), Long.valueOf(7));
  }

  @AfterEach
  public void tearDown() {
    tissueSummary = null;
    }

  @Test
  public void testInitialSetup() throws Exception {
    assertEquals(Long.valueOf(4), tissueSummary.getAkiCount());
    assertEquals(Long.valueOf(5), tissueSummary.getCkdCount());
    assertEquals(Long.valueOf(6), tissueSummary.getHrtCount());
    assertEquals(Long.valueOf(7), tissueSummary.getDmrCount());
  }

  @Test
  public void testSetAkiCount() throws Exception {
    tissueSummary.setAkiCount(Long.valueOf(4*10));
    assertEquals(Long.valueOf(4*10), tissueSummary.getAkiCount());
  }

  @Test
  public void testSetCkdCount() throws Exception {
    tissueSummary.setCkdCount(Long.valueOf(5*10));
    assertEquals(Long.valueOf(5*10), tissueSummary.getCkdCount());
  }

  @Test
  public void testSetHrtCount() throws Exception {
    tissueSummary.setHrtCount(Long.valueOf(6*10));
    assertEquals(Long.valueOf(6*10), tissueSummary.getHrtCount());
  }
}