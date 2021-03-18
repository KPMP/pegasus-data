package org.kpmp.umap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UmapDataServiceTest {

	@Mock
	private UmapPointRepository umapPointRepo;
	private UmapDataService service;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		service = new UmapDataService(umapPointRepo);
	}

	@After
	public void tearDown() throws Exception {
		service = null;
	}

	@Test
	public void testGetUmapPoints() {
		List<UmapPoint> expectedPoints = Arrays.asList(new UmapPoint());
		when(umapPointRepo.findByDataType("experimentType")).thenReturn(expectedPoints);

		List<UmapPoint> umapPoints = service.getUmapPoints("experimentType");

		assertEquals(expectedPoints, umapPoints);
	}

}
