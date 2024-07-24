package org.kpmp.geneExpression;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RTExpressionByTissueTypeTest {

	private RTExpressionByTissueType rtExpressionByTissueType;

	@BeforeEach
	public void setUp() throws Exception {
		rtExpressionByTissueType = new RTExpressionByTissueType();
	}

	@AfterEach
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
	public void setDmr() {
		RTExpressionDataAllSegments allDmrData = new RTExpressionDataAllSegments();
		List<? extends RTExpressionData> allDmrDataList = Arrays.asList(allDmrData);
		rtExpressionByTissueType.setDmr(allDmrDataList);
		List<? extends RTExpressionData> actual = rtExpressionByTissueType.getDmr();
		assertEquals(allDmrDataList, actual);
	}

}