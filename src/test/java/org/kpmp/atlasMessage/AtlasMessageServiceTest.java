package org.kpmp.atlasMessage;

import static org.junit.Assert.assertEquals;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

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
        List<AtlasMessage> expectedResult = new ArrayList<>();
        assertEquals(expectedResult, atlasMessageService.getAtlasMessage());
    }
}
