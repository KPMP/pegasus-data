package org.kpmp.umap;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kpmp.TissueTypeEnum;
import org.kpmp.geneExpression.ExpressionDataService;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UmapDataServiceTest {

	private UmapDataService service;
	@Mock
	private ExpressionDataService expressionDataService;
	@Mock
	private SCMetadataRepository scMetadataRepository;
	@Mock
	private SNMetadataRepository snMetadataRepository;

	private static double DOUBLE_PRECISION = 0.000001d;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		service = new UmapDataService(scMetadataRepository, snMetadataRepository, expressionDataService);
	}

	@After
	public void tearDown() throws Exception {
		service = null;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void testGetUmapPointsWhenBarcodeNotPresentInExpressionData() throws Exception {
		List expectedPoints = Arrays.asList(new SNMetadata());
		when(snMetadataRepository.findAll()).thenReturn(expectedPoints);
		when(expressionDataService.getGeneExpressionValues("sn", "geneSymbol")).thenReturn(new JSONObject());

		List<? extends UmapPoint> umapPoints = service.getUmapPoints("sn", "geneSymbol", "all");

		assertEquals(expectedPoints, umapPoints);
		assertEquals(0d, umapPoints.get(0).getExpressionValue(), DOUBLE_PRECISION);
		verify(expressionDataService).getGeneExpressionValues("sn", "geneSymbol");
		verify(snMetadataRepository).findAll();
		verify(scMetadataRepository, times(0)).findAll();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void testGetUmapPointsWhenBarcodeNotPresentInExpressionDataAKIDAta() throws Exception {
		List expectedPoints = Arrays.asList(new SNMetadata());
		when(snMetadataRepository.findByTissueType(any(String.class))).thenReturn(expectedPoints);
		when(expressionDataService.getGeneExpressionValues("sn", "geneSymbol")).thenReturn(new JSONObject());

		List<? extends UmapPoint> umapPoints = service.getUmapPoints("sn", "geneSymbol", "aki");

		assertEquals(expectedPoints, umapPoints);
		assertEquals(0d, umapPoints.get(0).getExpressionValue(), DOUBLE_PRECISION);
		verify(expressionDataService).getGeneExpressionValues("sn", "geneSymbol");
		verify(snMetadataRepository, times(0)).findAll();
		verify(snMetadataRepository).findByTissueType(TissueTypeEnum.AKI.getParticipantTissueType());
		verify(scMetadataRepository, times(0)).findAll();
	}

	@SuppressWarnings({ "rawtypes" })
	@Test
	public void testGetUmapPointsWhenBarcodeNotPresentInExpressionDataUnknownData() throws Exception {
		when(expressionDataService.getGeneExpressionValues("sn", "geneSymbol")).thenReturn(new JSONObject());

		List<? extends UmapPoint> umapPoints = service.getUmapPoints("sn", "geneSymbol", "tissue type");

		assertEquals(new ArrayList(), umapPoints);
		verify(expressionDataService).getGeneExpressionValues("sn", "geneSymbol");
		verify(snMetadataRepository, times(0)).findAll();
		verify(snMetadataRepository, times(0)).findByTissueType(any(String.class));
		verify(scMetadataRepository, times(0)).findAll();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testGetUmapPointsWhenBardoceFoundInExpressionData() throws Exception {
		UmapPoint umapPoint = new SCMetadata();
		umapPoint.setBarcode("barcode");
		List expectedPoints = Arrays.asList(umapPoint);
		when(scMetadataRepository.findAll()).thenReturn(expectedPoints);
		when(expressionDataService.getGeneExpressionValues("sc", "geneSymbol"))
				.thenReturn(new JSONObject("{ 'barcode': 0.4d , 'barcode2': 2.2d}"));

		List<? extends UmapPoint> umapPoints = service.getUmapPoints("sc", "geneSymbol", "all");

		assertEquals(expectedPoints, umapPoints);
		assertEquals(0.4d, umapPoints.get(0).getExpressionValue(), DOUBLE_PRECISION);
		verify(scMetadataRepository).findAll();
		verify(snMetadataRepository, times(0)).findAll();
		verify(expressionDataService).getGeneExpressionValues("sc", "geneSymbol");
	}

}
