package org.kpmp.participant;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParticipantEnrollmentCategorySummaryTest {
  private ParticipantEnrollmentCategorySummary enrollmentSummary;

  @BeforeEach
  public void setUp() {
    enrollmentSummary = new ParticipantEnrollmentCategorySummary(Long.valueOf(4), Long.valueOf(5), Long.valueOf(6), Long.valueOf(7));
  }

  @AfterEach
  public void tearDown() {
    enrollmentSummary = null;
    }

  @Test
  public void testInitialSetup() throws Exception {
    assertEquals(Long.valueOf(4), enrollmentSummary.getAkiCount());
    assertEquals(Long.valueOf(5), enrollmentSummary.getCkdCount());
    assertEquals(Long.valueOf(6), enrollmentSummary.getHrtCount());
    assertEquals(Long.valueOf(7), enrollmentSummary.getDmrCount());
  }

  @Test
  public void testSetAkiCount() throws Exception {
    enrollmentSummary.setAkiCount(Long.valueOf(4*10));
    assertEquals(Long.valueOf(4*10), enrollmentSummary.getAkiCount());
  }

  @Test
  public void testSetCkdCount() throws Exception {
    enrollmentSummary.setCkdCount(Long.valueOf(5*10));
    assertEquals(Long.valueOf(5*10), enrollmentSummary.getCkdCount());
  }

  @Test
  public void testSetHrtCount() throws Exception {
    enrollmentSummary.setHrtCount(Long.valueOf(6*10));
    assertEquals(Long.valueOf(6*10), enrollmentSummary.getHrtCount());
  }
}