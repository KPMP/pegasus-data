package org.kpmp.atlasMessage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class AtlasMessageTest {
    @Mock
    private AtlasMessage atlasMessage;
    @BeforeEach
    public void setUp() throws Exception {
        atlasMessage = new AtlasMessage();
    }

    @AfterEach
    public void tearDown() throws Exception {
        atlasMessage = null;
    }

    @Test
    public void testGetAtlasMessage() throws Exception {
        AtlasMessage expectedResult = new AtlasMessage();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String endDateString = "2023-12-25";
        String startDateString = "2023-12-01";
        Date endDate = dateFormat.parse(endDateString);
        Date startDate = dateFormat.parse(startDateString);
        expectedResult.setApplication("Explorer");
        expectedResult.setId(1);
        expectedResult.setMessage("THE END IS NEAR");
        expectedResult.setEndDate(endDate);
        expectedResult.setStartDate(startDate);
        assertEquals("Explorer", expectedResult.getApplication());
        assertEquals(1, expectedResult.getId());
        assertEquals("THE END IS NEAR", expectedResult.getMessage());
        assertEquals(startDate, expectedResult.getStartDate());
        assertEquals(endDate, expectedResult.getEndDate());
    }
}
