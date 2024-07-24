package org.kpmp.participant;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kpmp.dataSummary.AtlasRepositoryLinkInformation;

public class ParticipantRepoDataTypeInformationTest {

    private ParticipantRepoDataTypeInformation info;

    @BeforeEach
    public void setUp() throws Exception {
        info = new ParticipantRepoDataTypeInformation("abc", 1, mock(AtlasRepositoryLinkInformation.class));
    }

    @AfterEach
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
        AtlasRepositoryLinkInformation linkInfo = mock(AtlasRepositoryLinkInformation.class);
        info.setLinkInformation(linkInfo);
        assertEquals(linkInfo, info.getLinkInformation());
    }

}
