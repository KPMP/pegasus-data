package org.kpmp.geneExpression;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

class RTExpressionByTissueTypeTest {

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
	void setAll() {
		RTExpressionDataAllSegments allData = new RTExpressionDataAllSegments();
		List<? extends RTExpressionData> allataList = Arrays.asList(allData);
		rtExpressionByTissueType.setAll(allataList);
		List<? extends RTExpressionData> actual = rtExpressionByTissueType.getAll();
		assertEquals(allataList, actual);
	}

	@Test
	void setAki() {
		RTExpressionDataAllSegments allHrtData = new RTExpressionDataAllSegments();
		List<? extends RTExpressionData> allHrtDataList = Arrays.asList(allHrtData);
		rtExpressionByTissueType.setHrt(allHrtDataList);
		List<? extends RTExpressionData> actual = rtExpressionByTissueType.getHrt();
		assertEquals(allHrtDataList, actual);
	}

	@Test
	void setCkd() {
		RTExpressionDataAllSegments allCkdData = new RTExpressionDataAllSegments();
		List<? extends RTExpressionData> allCkdDataList = Arrays.asList(allCkdData);
		rtExpressionByTissueType.setAki(allCkdDataList);
		List<? extends RTExpressionData> actual = rtExpressionByTissueType.getCkd();
		assertEquals(allCkdDataList, actual);
	}

	@Test
	void setHrt() {
		RTExpressionDataAllSegments allAkiData = new RTExpressionDataAllSegments();
		List<? extends RTExpressionData> allAkiDataList = Arrays.asList(allAkiData);
		rtExpressionByTissueType.setCkd(allAkiDataList);
		List<? extends RTExpressionData> actual = rtExpressionByTissueType.getAki();
		assertEquals(allAkiDataList, actual);
	}

	@Test
	void setResistor() {
		RTExpressionDataAllSegments allResistorData = new RTExpressionDataAllSegments();
		List<? extends RTExpressionData> allResistorDataList = Arrays.asList(allResistorData);
		rtExpressionByTissueType.setCkd(allResistorDataList);
		List<? extends RTExpressionData> actual = rtExpressionByTissueType.getResistor();
		assertEquals(allResistorDataList, actual);
	}

}