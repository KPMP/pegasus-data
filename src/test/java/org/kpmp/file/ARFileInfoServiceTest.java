package org.kpmp.file;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ARFileInfoServiceTest {

    @Mock
    ARFileInfoRepository fileInfoRepository;
    private ARFileInfoService service;

    @BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		service = new ARFileInfoService(fileInfoRepository);
	}

    @AfterEach
    public void tearDown() throws Exception {
        		
        MockitoAnnotations.openMocks(this).close();
        service = null;
    }

    @Test
    void testGetRepositoryTotalFileCount() {
        when(fileInfoRepository.getTotalFileCount()).thenReturn(2l);
        assertEquals(Long.valueOf(2), service.getRepositoryTotalFileCount());
    }
}
