package org.kpmp.umap;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FeatureDataTest {

	private FeatureData featureData;
	private static double DOUBLE_PRECISION = 0.000001d;

	@BeforeEach
	public void setUp() throws Exception {
		featureData = new FeatureData();
	}

	@AfterEach
	public void tearDown() throws Exception {
		featureData = null;
	}

	@Test
	public void testSetXValue() {
		List<Double> expectedValues = Arrays.asList(1d, 2d);
		featureData.setXValues(expectedValues);

		assertEquals(expectedValues, featureData.getXValues());
	}

	@Test
	public void testAddXalue() {
		assertEquals(0, featureData.getXValues().size());

		featureData.addXValue(4d);

		assertEquals(1, featureData.getXValues().size());
		assertEquals(4d, featureData.getXValues().get(0), DOUBLE_PRECISION);
	}

	@Test
	public void testSetYValues() {
		List<Double> expectedValues = Arrays.asList(3d, 6d);
		featureData.setYValues(expectedValues);

		assertEquals(expectedValues, featureData.getYValues());
	}

	@Test
	public void testAddYValue() {
		assertEquals(0, featureData.getYValues().size());

		featureData.addYValue(5d);

		assertEquals(1, featureData.getYValues().size());
		assertEquals(5d, featureData.getYValues().get(0), DOUBLE_PRECISION);
	}

	@Test
	public void testSetExpression() {
		List<Double> expectedValues = Arrays.asList(5d);
		featureData.setExpression(expectedValues);

		assertEquals(expectedValues, featureData.getExpression());
	}

	@Test
	public void testAddExpression() {
		assertEquals(0, featureData.getExpression().size());

		featureData.addExpression(5d);

		assertEquals(1, featureData.getExpression().size());
		assertEquals(5d, featureData.getExpression().get(0), DOUBLE_PRECISION);
	}

}
