package org.kpmp.geneExpressionSummary;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SCRNAParticipantValueTest {

	SCRNAParticipantValue scrnaParticipantValue;

	@BeforeEach
	public void setUp() throws Exception {
		scrnaParticipantValue = new SCRNAParticipantValue();
	}

	@AfterEach
	public void tearDown() throws Exception {
		scrnaParticipantValue = null;
	}

	@Test
	public void testSetEnrollmentCategory() throws Exception {
		scrnaParticipantValue.setEnrollmentCategory("aki");
		assertEquals("aki", scrnaParticipantValue.getEnrollmentCategory());
	}

	public void setRedcapId() throws Exception {
		scrnaParticipantValue.setRedcapId("27-10039");
		assertEquals("27-10039", scrnaParticipantValue.getRedcapId());
	}

}
