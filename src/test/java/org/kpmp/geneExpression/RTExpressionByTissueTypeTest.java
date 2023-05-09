package org.kpmp.geneExpression;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RTExpressionByTissueTypeTest {

	private RTExpressionByTissueType rtExpressionByTissueType;

	@Before
	public void setUp() throws Exception {
		rtExpressionByTissueType = new RTExpressionByTissueType();
	}

	@After
	public void tearDown() throws Exception {
		rtExpressionByTissueType = null;
	}

	@Test
	public void setAll() {
		RTExpressionDataAllSegments allData = new RTExpressionDataAllSegments();
		List<? extends RTExpressionData> allataList = Arrays.asList(allData);
		rtExpressionByTissueType.setAll(allataList);
		List<? extends RTExpressionData> actual = rtExpressionByTissueType.getAll();
		assertEquals(allataList, actual);
	}

	@Test
	public void setAki() {
		RTExpressionDataAllSegments allAkiData = new RTExpressionDataAllSegments();
		List<? extends RTExpressionData> allAkiDataList = Arrays.asList(allAkiData);
		rtExpressionByTissueType.setAki(allAkiDataList);
		List<? extends RTExpressionData> actual = rtExpressionByTissueType.getAki();
		assertEquals(allAkiDataList, actual);
	}

	@Test
	public void setCkd() {
		RTExpressionDataAllSegments allCkdData = new RTExpressionDataAllSegments();
		List<? extends RTExpressionData> allCkdDataList = Arrays.asList(allCkdData);
		rtExpressionByTissueType.setCkd(allCkdDataList);
		List<? extends RTExpressionData> actual = rtExpressionByTissueType.getCkd();
		assertEquals(allCkdDataList, actual);
	}

	@Test
	public void setHrt() {
		RTExpressionDataAllSegments allHrtData = new RTExpressionDataAllSegments();
		List<? extends RTExpressionData> allHrtDataList = Arrays.asList(allHrtData);
		rtExpressionByTissueType.setHrt(allHrtDataList);
		List<? extends RTExpressionData> actual = rtExpressionByTissueType.getHrt();
		assertEquals(allHrtDataList, actual);
	}

	@Test
	public void setResistor() {
		RTExpressionDataAllSegments allResistorData = new RTExpressionDataAllSegments();
		List<? extends RTExpressionData> allResistorDataList = Arrays.asList(allResistorData);
		rtExpressionByTissueType.setResistor(allResistorDataList);
		List<? extends RTExpressionData> actual = rtExpressionByTissueType.getResistor();
		assertEquals(allResistorDataList, actual);
	}

}