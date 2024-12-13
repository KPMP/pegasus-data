package org.kpmp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class EnrollmentCategoryEnumTest {

	@Test
	public void testGetParticipantEnrollmentCategory() {
		assertEquals(6, EnrollmentCategoryEnum.values().length);
		assertEquals("all", EnrollmentCategoryEnum.ALL.getParticipantEnrollmentCategory());
		assertEquals("AKI", EnrollmentCategoryEnum.AKI.getParticipantEnrollmentCategory());
		assertEquals("CKD", EnrollmentCategoryEnum.CKD.getParticipantEnrollmentCategory());
		assertEquals("DM-R", EnrollmentCategoryEnum.DMR.getParticipantEnrollmentCategory());
		assertEquals("Healthy Reference", EnrollmentCategoryEnum.HEALTHY_REFERENCE.getParticipantEnrollmentCategory());
		assertEquals("", EnrollmentCategoryEnum.UNKNOWN.getParticipantEnrollmentCategory());
	}

	@Test
	public void testFromRequestType() throws Exception {
		assertEquals(EnrollmentCategoryEnum.ALL, EnrollmentCategoryEnum.fromRequestType("alL"));
		assertEquals(EnrollmentCategoryEnum.AKI, EnrollmentCategoryEnum.fromRequestType("aKi"));
		assertEquals(EnrollmentCategoryEnum.CKD, EnrollmentCategoryEnum.fromRequestType("ckd"));
		assertEquals(EnrollmentCategoryEnum.HEALTHY_REFERENCE, EnrollmentCategoryEnum.fromRequestType("hrt"));
		assertEquals(EnrollmentCategoryEnum.UNKNOWN, EnrollmentCategoryEnum.fromRequestType("garbage"));
		assertEquals(EnrollmentCategoryEnum.UNKNOWN, EnrollmentCategoryEnum.fromRequestType(null));
	}
}
