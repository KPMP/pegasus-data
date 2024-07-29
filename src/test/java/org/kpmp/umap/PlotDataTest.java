package org.kpmp.umap;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class PlotDataTest {

	@Mock
	private List<FeatureData> featureData;
	@Mock
	private List<ReferenceCluster> referenceData;
	private PlotData plotData;

	@BeforeEach
	public void setUp() throws Exception {
		plotData = new PlotData(referenceData, featureData);
	}

	@AfterEach
	public void tearDown() throws Exception {
		plotData = null;
	}

	@Test
	public void testConstructor() throws Exception {
		assertEquals(referenceData, plotData.getReferenceData());
		assertEquals(featureData, plotData.getFeatureData());
	}

	@Test
	public void testSetReferenceData() {
		List<ReferenceCluster> differentReferenceData = new ArrayList<>();
		plotData.setReferenceData(differentReferenceData);
		assertEquals(differentReferenceData, plotData.getReferenceData());
	}

	@Test
	public void testSetFeatureData() {
		List<FeatureData> differentFeatureData = new ArrayList<>();
		plotData.setFeatureData(differentFeatureData);
		assertEquals(differentFeatureData, plotData.getFeatureData());
	}

}
