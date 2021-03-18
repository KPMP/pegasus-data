package org.kpmp.umap;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
		verify(umapPointRepo).findByDataType("experimentType");
		verify(umapPointRepo, times(0)).findAll();
	}

	@Test
	public void testGetUmapPointsWitNoParameter() throws Exception {
		List<UmapPoint> expectedPoints = Arrays.asList(new UmapPoint());
		when(umapPointRepo.findAll()).thenReturn(expectedPoints);

		List<UmapPoint> umapPoints = service.getUmapPoints();

		assertEquals(expectedPoints, umapPoints);
		verify(umapPointRepo).findAll();
		verify(umapPointRepo, times(0)).findByDataType(any(String.class));
	}

}
