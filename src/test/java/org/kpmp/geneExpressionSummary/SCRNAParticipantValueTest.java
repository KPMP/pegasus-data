package org.kpmp.geneExpressionSummary;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kpmp.geneExpressionSummary.singleCell.SCRNAParticipantValue;

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
	public void testSetTissueType() throws Exception {
		scrnaParticipantValue.setTissueType("aki");
		assertEquals("aki", scrnaParticipantValue.getTissueType());
	}

	public void setRedcapId() throws Exception {
		scrnaParticipantValue.setRedcapId("27-10039");
		assertEquals("27-10039", scrnaParticipantValue.getRedcapId());
	}

}
