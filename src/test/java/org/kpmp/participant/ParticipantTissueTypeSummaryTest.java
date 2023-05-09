package org.kpmp.participant;

import static org.junit.Assert.assertEquals;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ParticipantTissueTypeSummaryTest {
  private ParticipantTissueTypeSummary tissueSummary;

  @Before
  public void setUp() {
    tissueSummary = new ParticipantTissueTypeSummary(Long.valueOf(4), Long.valueOf(5), Long.valueOf(6), Long.valueOf(7));
  }

  @After
  public void tearDown() {
    tissueSummary = null;
    }

  @Test
  public void testInitialSetup() throws Exception {
    assertEquals(Long.valueOf(4), tissueSummary.getAkiCount());
    assertEquals(Long.valueOf(5), tissueSummary.getCkdCount());
    assertEquals(Long.valueOf(6), tissueSummary.getHrtCount());
    assertEquals(Long.valueOf(7), tissueSummary.getResistorCount());
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