package org.kpmp.geneExpressionSummary;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.Assert.assertEquals;

public class SCRNAParticipantValueTest {
    
    SCRNAParticipantValue scrnaParticipantValue;

    @Before
    public void setUp() throws Exception {
        scrnaParticipantValue = new SCRNAParticipantValue();
    }

    @After
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
