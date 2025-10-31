package org.kpmp.umap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kpmp.geneExpression.SNSCExpressionDataService;
import org.kpmp.geneExpression.SNSCExpressionDataService2025;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UmapDataServiceTest {

	private UmapDataService service;
	@Mock
	private SNSCExpressionDataService expressionDataService;
    @Mock 
    private SNSCExpressionDataService2025 expressionDataService2025;
	@Mock
	private SCMetadataRepository scMetadataRepository;
	@Mock
	private SNMetadataRepository snMetadataRepository;
    @Mock 
    private SNMetadataRepository2025 snMetadataRepository2025;

	private static double DOUBLE_PRECISION = 0.000001d;

	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		service = new UmapDataService(scMetadataRepository, snMetadataRepository, expressionDataService);
	}

	@AfterEach
	public void tearDown() throws Exception {
		MockitoAnnotations.openMocks(this).close();
		service = null;
	}

	@Test
	public void testGetPlotDataFeatureData() throws Exception {
		SNMetadata snMetadata = new SNMetadata();
		snMetadata.setUmapX(3d);
		snMetadata.setUmapY(4d);
		snMetadata.setClusterName("clusterName");
		snMetadata.setClusterAbbreviation("clusterAbbrev");
		snMetadata.setBarcode("barcodeNotInExpression");
		SNMetadata snMetadata2 = new SNMetadata();
		snMetadata2.setUmapX(5d);
		snMetadata2.setUmapY(6d);
		snMetadata2.setClusterName("clusterName");
		snMetadata2.setClusterAbbreviation("clusterAbbrev");
		snMetadata2.setBarcode("barcode");
		List<SNMetadata> expectedPoints = Arrays.asList(snMetadata, snMetadata2);
		when(snMetadataRepository.findCount()).thenReturn(7);
		when(snMetadataRepository.findLimited(2)).thenReturn(expectedPoints);
		when(expressionDataService.getGeneExpressionValues("sn", "gene"))
				.thenReturn(new JSONObject("{ 'barcode': 0.4d , 'barcode2': 2.2d}"));

		PlotData plotData = service.getPlotData("sn", "gene", "all");

		List<FeatureData> featureData = plotData.getFeatureData();
		assertEquals(2, featureData.size());
		FeatureData featureDataWithExpressionValues = featureData.get(0);
		assertEquals(1, featureDataWithExpressionValues.getXValues().size());
		assertEquals(1, featureDataWithExpressionValues.getXValues().size());
		assertEquals(5d, featureDataWithExpressionValues.getXValues().get(0), DOUBLE_PRECISION);
		assertEquals(1, featureDataWithExpressionValues.getYValues().size());
		assertEquals(6d, featureDataWithExpressionValues.getYValues().get(0), DOUBLE_PRECISION);
		assertEquals(1, featureDataWithExpressionValues.getExpression().size());
		assertEquals(0.4d, featureDataWithExpressionValues.getExpression().get(0), DOUBLE_PRECISION);
		assertEquals("0.4<br>clusterName", featureDataWithExpressionValues.getHoverDisplay().get(0));

		FeatureData featureDataWithZeroExpressionValue = featureData.get(1);
		assertEquals(1, featureDataWithZeroExpressionValue.getXValues().size());
		assertEquals(3d, featureDataWithZeroExpressionValue.getXValues().get(0), DOUBLE_PRECISION);
		assertEquals(1, featureDataWithZeroExpressionValue.getYValues().size());
		assertEquals(4d, featureDataWithZeroExpressionValue.getYValues().get(0), DOUBLE_PRECISION);
		assertEquals(1, featureDataWithZeroExpressionValue.getExpression().size());
		assertEquals(0d, featureDataWithZeroExpressionValue.getExpression().get(0), DOUBLE_PRECISION);
		assertEquals("clusterName", featureDataWithZeroExpressionValue.getHoverDisplay().get(0));

	}

	@Test
	public void testGetPlotDataReferenceDataOneCluster() throws Exception {
		SNMetadata snMetadata = new SNMetadata();
		snMetadata.setUmapX(3d);
		snMetadata.setUmapY(4d);
		snMetadata.setClusterName("clusterName");
		snMetadata.setClusterAbbreviation("clusterAbbreviation");
		snMetadata.setClusterColor("color");
		snMetadata.setBarcode("barcodeNotInExpression");
		SNMetadata snMetadata2 = new SNMetadata();
		snMetadata2.setUmapX(5d);
		snMetadata2.setUmapY(6d);
		snMetadata2.setClusterName("clusterName");
		snMetadata2.setClusterAbbreviation("clusterAbbreviation");
		snMetadata2.setClusterColor("color");
		snMetadata2.setBarcode("barcode");
		List<SNMetadata> expectedPoints = Arrays.asList(snMetadata, snMetadata2);
		when(snMetadataRepository.findCount()).thenReturn(7);
		when(snMetadataRepository.findLimited(2)).thenReturn(expectedPoints);
		when(expressionDataService.getGeneExpressionValues("sn", "gene")).thenReturn(new JSONObject());

		PlotData plotData = service.getPlotData("sn", "gene", "all");

		List<ReferenceCluster> clusters = plotData.getReferenceData();
		assertEquals(1, clusters.size());
		assertEquals("clusterAbbreviation", clusters.get(0).getClusterAbbreviation());
		assertEquals("color", clusters.get(0).getColor());
		List<Double> xValues = clusters.get(0).getXValues();
		assertEquals(2, xValues.size());
		assertEquals(3d, xValues.get(0), DOUBLE_PRECISION);
		assertEquals(5d, xValues.get(1), DOUBLE_PRECISION);
		List<Double> yValues = clusters.get(0).getYValues();
		assertEquals(2, yValues.size());
		assertEquals(4d, yValues.get(0), DOUBLE_PRECISION);
		assertEquals(6d, yValues.get(1), DOUBLE_PRECISION);

	}

	@Test
	public void testGetPlotDataReferenceDataMultipleClusters() throws Exception {
		SNMetadata snMetadata = new SNMetadata();
		snMetadata.setUmapX(3d);
		snMetadata.setUmapY(4d);
		snMetadata.setClusterAbbreviation("clusterAbbrev1");
		snMetadata.setClusterColor("color1");
		snMetadata.setBarcode("barcodeNotInExpression");
		SNMetadata snMetadata2 = new SNMetadata();
		snMetadata2.setUmapX(5d);
		snMetadata2.setUmapY(6d);
		snMetadata2.setClusterAbbreviation("clusterAbbrev2");
		snMetadata2.setClusterColor("color2");
		snMetadata2.setBarcode("barcode");
		List<SNMetadata> expectedPoints = Arrays.asList(snMetadata, snMetadata2);
		when(snMetadataRepository.findCount()).thenReturn(7);
		when(snMetadataRepository.findLimited(2)).thenReturn(expectedPoints);
		when(expressionDataService.getGeneExpressionValues("sn", "gene")).thenReturn(new JSONObject());

		PlotData plotData = service.getPlotData("sn", "gene", "all");

		List<ReferenceCluster> clusters = plotData.getReferenceData();
		assertEquals(2, clusters.size());
		assertEquals("clusterAbbrev2", clusters.get(0).getClusterAbbreviation());
		assertEquals("color2", clusters.get(0).getColor());
		assertEquals("clusterAbbrev1", clusters.get(1).getClusterAbbreviation());
		assertEquals("color1", clusters.get(1).getColor());
		List<Double> xValues = clusters.get(1).getXValues();
		assertEquals(1, xValues.size());
		assertEquals(3d, xValues.get(0), DOUBLE_PRECISION);
		List<Double> yValues = clusters.get(1).getYValues();
		assertEquals(1, yValues.size());
		assertEquals(4d, yValues.get(0), DOUBLE_PRECISION);
		xValues = clusters.get(0).getXValues();
		yValues = clusters.get(0).getYValues();
		assertEquals(5d, xValues.get(0), DOUBLE_PRECISION);
		assertEquals(6d, yValues.get(0), DOUBLE_PRECISION);

	}

}
