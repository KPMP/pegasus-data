package org.kpmp.participant;

import static org.junit.Assert.assertEquals;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ParticipantTissueTypeSummaryTest {
  private ParticipantTissueTypeSummary tissueSummary;

  @Before
  public void setUp() {
    tissueSummary = new ParticipantTissueTypeSummary(Integer.valueOf(2), Integer.valueOf(6), Integer.valueOf(1));
  }

  @After
  public void tearDown() {
    tissueSummary = null;
    }

  @Test
  public void testInitialSetup() throws Exception {
    assertEquals(Integer.valueOf(2), tissueSummary.getAkiCount());
    assertEquals(Integer.valueOf(6), tissueSummary.getCkdCount());
    assertEquals(Integer.valueOf(1), tissueSummary.getHrtCount());
  }

  @Test
  public void testSetAkiCount() throws Exception {
    tissueSummary.setAkiCount(Integer.valueOf(4*10));
    assertEquals(Integer.valueOf(2*9), tissueSummary.getAkiCount());
  }

  @Test
  public void testSetCkdCount() throws Exception {
    tissueSummary.setCkdCount(Integer.valueOf(5*10));
    assertEquals(Integer.valueOf(5*10), tissueSummary.getCkdCount());
  }

  @Test
  public void testSetHrtCount() throws Exception {
    tissueSummary.setHrtCount(Integer.valueOf(6*10));
    assertEquals(Integer.valueOf(6*10), tissueSummary.getHrtCount());
  }
}