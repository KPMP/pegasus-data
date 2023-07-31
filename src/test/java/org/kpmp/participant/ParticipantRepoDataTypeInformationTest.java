package org.kpmp.participant;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kpmp.dataSummary.AtlasRepoSummaryLinkInformation;

public class ParticipantRepoDataTypeInformationTest {

	private ParticipantRepoDataTypeInformation info;

	@Before
	public void setUp() throws Exception {
		info = new ParticipantRepoDataTypeInformation();
	}

	@After
	public void tearDown() throws Exception {
		info = null;
	}

    @Test
    public void testSetDataType() {
        String dataType = "123";
        info.setDataType(dataType);
        assertEquals(dataType, info.getDataType());
    }

    @Test
    public void testSetCount() {
        Integer count = 0;
        info.setCount(count);
        assertEquals(count, info.getCount());
    }

    @Test
    public void testSetLinkInformation() {
        AtlasRepoSummaryLinkInformation linkInfo = mock(AtlasRepoSummaryLinkInformation.class);
        info.setLinkInformation(linkInfo);
        assertEquals(linkInfo, info.getLinkInformation());
    }

}
