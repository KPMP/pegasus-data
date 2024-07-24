package org.kpmp.atlasMessage;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class AtlasMessageServiceTest {
    private AtlasMessageService atlasMessageService;
    @Mock
    private AtlasMessageRepository atlasMessageRepository;
    @Mock
    private AtlasMessage atlasMessage;
    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        atlasMessageService = new AtlasMessageService(atlasMessageRepository);
    }

    @AfterEach
    public void tearDown() throws Exception {
        MockitoAnnotations.openMocks(this).close();
        atlasMessageService = null;
    }

    @Test
    public void testGetAtlasMessage() throws Exception {
        AtlasMessage message = new AtlasMessage();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String endDateString = "2023-12-31";
        String startDateString = "2022-12-31";
        Date endDate = format.parse(endDateString);
        Date startDate = format.parse(startDateString);
        message.setApplication("Explorer");
        message.setEndDate(endDate);
        message.setStartDate(startDate);
        message.setId(1);
        AtlasMessage message2 = new AtlasMessage();
        message2.setApplication(null);
        message2.setEndDate(null);
        message2.setStartDate(null);
        message2.setId(2);
        List<AtlasMessage> messageList = Arrays.asList(message, message2);
        when(atlasMessageRepository.getAtlasMessages()).thenReturn(messageList);

        assertEquals(messageList, atlasMessageService.getAtlasMessage());
    }
}
