package org.kpmp.geneExpressionSummary;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kpmp.geneExpressionSummary.singleNucleus.SNRNAParticipantValue2025;

public class SNRNAParticipantValue2025Test {

	SNRNAParticipantValue2025 snrnaParticipantValue;

	@BeforeEach
	public void setUp() throws Exception {
		snrnaParticipantValue = new SNRNAParticipantValue2025();
	}

	@AfterEach
	public void tearDown() throws Exception {
		snrnaParticipantValue = null;
	}

	@Test
	public void testSetEnrollmentCategory() throws Exception {
		snrnaParticipantValue.setEnrollmentCategory("aki");
		assertEquals("aki", snrnaParticipantValue.getEnrollmentCategory());
	}

	public void setRedcapId() throws Exception {
		snrnaParticipantValue.setRedcapId("27-10039");
		assertEquals("27-10039", snrnaParticipantValue.getRedcapId());
	}

}
