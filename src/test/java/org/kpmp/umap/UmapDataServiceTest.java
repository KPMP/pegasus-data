package org.kpmp.umap;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kpmp.geneExpression.ExpressionDataService;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UmapDataServiceTest {

	@Mock
	private UmapPointRepository umapPointRepo;
	private UmapDataService service;
	@Mock
	private ExpressionDataService expressionDataService;

	private static double DOUBLE_PRECISION = 0.000001d;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		service = new UmapDataService(umapPointRepo, expressionDataService);
	}

	@After
	public void tearDown() throws Exception {
		service = null;
	}

	@Test
	public void testGetUmapPointsWhenBarcodeNotPresentInExpressionData() throws Exception {
		List<UmapPoint> expectedPoints = Arrays.asList(new UmapPoint());
		when(umapPointRepo.findByDataType("dataType")).thenReturn(expectedPoints);
		when(expressionDataService.getGeneExpressionValues("dataType", "geneSymbol")).thenReturn(new JSONObject());

		List<UmapPoint> umapPoints = service.getUmapPoints("dataType", "geneSymbol");

		assertEquals(expectedPoints, umapPoints);
		assertEquals(0d, umapPoints.get(0).getExpressionValue(), DOUBLE_PRECISION);
		verify(umapPointRepo).findByDataType("dataType");
		verify(expressionDataService).getGeneExpressionValues("dataType", "geneSymbol");
		verify(umapPointRepo, times(0)).findAll();
	}

	@Test
	public void testGetUmapPointsWhenBardoceFoundInExpressionData() throws Exception {
		UmapPoint umapPoint = new UmapPoint();
		umapPoint.setBarcode("barcode");
		List<UmapPoint> expectedPoints = Arrays.asList(umapPoint);
		when(umapPointRepo.findByDataType("dataType")).thenReturn(expectedPoints);
		when(expressionDataService.getGeneExpressionValues("dataType", "geneSymbol"))
				.thenReturn(new JSONObject("{ 'barcode': 0.4d , 'barcode2': 2.2d}"));

		List<UmapPoint> umapPoints = service.getUmapPoints("dataType", "geneSymbol");

		assertEquals(expectedPoints, umapPoints);
		assertEquals(0.4d, umapPoints.get(0).getExpressionValue(), DOUBLE_PRECISION);
		verify(umapPointRepo).findByDataType("dataType");
		verify(expressionDataService).getGeneExpressionValues("dataType", "geneSymbol");
		verify(umapPointRepo, times(0)).findAll();
	}

}
