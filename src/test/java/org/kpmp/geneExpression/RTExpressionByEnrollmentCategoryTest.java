package org.kpmp.geneExpression;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RTExpressionByEnrollmentCategoryTest {

	private RTExpressionByEnrollmentCategory rtExpressionByEnrollmentCategory;

	@BeforeEach
	public void setUp() throws Exception {
		rtExpressionByEnrollmentCategory = new RTExpressionByEnrollmentCategory();
	}

	@AfterEach
	public void tearDown() throws Exception {
		rtExpressionByEnrollmentCategory = null;
	}

	@Test
	public void setAll() {
		RTExpressionDataAllSegments allData = new RTExpressionDataAllSegments();
		List<? extends RTExpressionData> allataList = Arrays.asList(allData);
		rtExpressionByEnrollmentCategory.setAll(allataList);
		List<? extends RTExpressionData> actual = rtExpressionByEnrollmentCategory.getAll();
		assertEquals(allataList, actual);
	}

	@Test
	public void setAki() {
		RTExpressionDataAllSegments allAkiData = new RTExpressionDataAllSegments();
		List<? extends RTExpressionData> allAkiDataList = Arrays.asList(allAkiData);
		rtExpressionByEnrollmentCategory.setAki(allAkiDataList);
		List<? extends RTExpressionData> actual = rtExpressionByEnrollmentCategory.getAki();
		assertEquals(allAkiDataList, actual);
	}

	@Test
	public void setCkd() {
		RTExpressionDataAllSegments allCkdData = new RTExpressionDataAllSegments();
		List<? extends RTExpressionData> allCkdDataList = Arrays.asList(allCkdData);
		rtExpressionByEnrollmentCategory.setCkd(allCkdDataList);
		List<? extends RTExpressionData> actual = rtExpressionByEnrollmentCategory.getCkd();
		assertEquals(allCkdDataList, actual);
	}

	@Test
	public void setHrt() {
		RTExpressionDataAllSegments allHrtData = new RTExpressionDataAllSegments();
		List<? extends RTExpressionData> allHrtDataList = Arrays.asList(allHrtData);
		rtExpressionByEnrollmentCategory.setHrt(allHrtDataList);
		List<? extends RTExpressionData> actual = rtExpressionByEnrollmentCategory.getHrt();
		assertEquals(allHrtDataList, actual);
	}

	@Test
	public void setDmr() {
		RTExpressionDataAllSegments allDmrData = new RTExpressionDataAllSegments();
		List<? extends RTExpressionData> allDmrDataList = Arrays.asList(allDmrData);
		rtExpressionByEnrollmentCategory.setDmr(allDmrDataList);
		List<? extends RTExpressionData> actual = rtExpressionByEnrollmentCategory.getDmr();
		assertEquals(allDmrDataList, actual);
	}

}