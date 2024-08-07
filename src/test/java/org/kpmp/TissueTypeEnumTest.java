package org.kpmp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TissueTypeEnumTest {

	@Test
	public void testGetParticipantTissueType() {
		assertEquals(6, TissueTypeEnum.values().length);
		assertEquals("all", TissueTypeEnum.ALL.getParticipantTissueType());
		assertEquals("AKI", TissueTypeEnum.AKI.getParticipantTissueType());
		assertEquals("CKD", TissueTypeEnum.CKD.getParticipantTissueType());
		assertEquals("DM-R", TissueTypeEnum.DMR.getParticipantTissueType());
		assertEquals("Healthy Reference", TissueTypeEnum.HEALTHY_REFERENCE.getParticipantTissueType());
		assertEquals("", TissueTypeEnum.UNKNOWN.getParticipantTissueType());
	}

	@Test
	public void testFromRequestType() throws Exception {
		assertEquals(TissueTypeEnum.ALL, TissueTypeEnum.fromRequestType("alL"));
		assertEquals(TissueTypeEnum.AKI, TissueTypeEnum.fromRequestType("aKi"));
		assertEquals(TissueTypeEnum.CKD, TissueTypeEnum.fromRequestType("ckd"));
		assertEquals(TissueTypeEnum.HEALTHY_REFERENCE, TissueTypeEnum.fromRequestType("hrt"));
		assertEquals(TissueTypeEnum.UNKNOWN, TissueTypeEnum.fromRequestType("garbage"));
		assertEquals(TissueTypeEnum.UNKNOWN, TissueTypeEnum.fromRequestType(null));
	}
}
