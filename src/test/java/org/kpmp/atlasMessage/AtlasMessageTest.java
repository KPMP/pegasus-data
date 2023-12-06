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
    public void testGetApplication() throws Exception {
        atlasMessage.setApplication("Explorer");
        assertEquals("Explorer", atlasMessage.getApplication());
    }

    @Test
    public void testGetId() throws Exception{
        atlasMessage.setId(1);
        assertEquals(1, atlasMessage.getId());
    }

    @Test
    public void testGetStartDate() throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String startDateString = "2023-12-25";
        Date startDate = dateFormat.parse(startDateString);
        atlasMessage.setStartDate(startDate);
        assertEquals(startDate, atlasMessage.getStartDate());
    }

    @Test
    public void testGetEndDate() throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String endDateString = "2023-12-25";
        Date endDate = dateFormat.parse(endDateString);
        atlasMessage.setEndDate(endDate);
        assertEquals(endDate, atlasMessage.getEndDate());
    }

    @Test
    public void testGetAtlasMessage() throws Exception {
        atlasMessage.setMessage("THE END IS NEAR");
        assertEquals("THE END IS NEAR", atlasMessage.getMessage());
    }
}
