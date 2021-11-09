package org.kpmp.geneExpressionSummary;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SNRNAParticipantValueTest {

	SNRNAParticipantValue snrnaParticipantValue;

	@Before
	public void setUp() throws Exception {
		snrnaParticipantValue = new SNRNAParticipantValue();
	}

	@After
	public void tearDown() throws Exception {
		snrnaParticipantValue = null;
	}

	@Test
	public void testSetTissueType() throws Exception {
		snrnaParticipantValue.setTissueType("aki");
		assertEquals("aki", snrnaParticipantValue.getTissueType());
	}

	public void setRedcapId() throws Exception {
		snrnaParticipantValue.setRedcapId("27-10039");
		assertEquals("27-10039", snrnaParticipantValue.getRedcapId());
	}

}
