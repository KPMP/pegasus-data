package org.kpmp.geneExpression;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RPExpressionByTissueTypeTest {

	private RPExpressionByTissueType rpExpression;

	@Before
	public void setUp() throws Exception {
		rpExpression = new RPExpressionByTissueType();
	}

	@After
	public void tearDown() throws Exception {
		rpExpression = null;
	}

	@Test
	public void setAll() {
		List<? extends RPExpressionData> allataList = Arrays.asList(mock(RPExpressionData.class));
		rpExpression.setAll(allataList);
		List<? extends RPExpressionData> actual = rpExpression.getAll();
		assertEquals(allataList, actual);
	}

	@Test
	public void setAki() {
		List<? extends RPExpressionData> allAkiDataList = Arrays.asList(mock(RPExpressionData.class));
		rpExpression.setAki(allAkiDataList);
		List<? extends RPExpressionData> actual = rpExpression.getAki();
		assertEquals(allAkiDataList, actual);
	}

	@Test
	public void setCkd() {
		List<? extends RPExpressionData> allCkdDataList = Arrays.asList(mock(RPExpressionData.class));
		rpExpression.setCkd(allCkdDataList);
		List<? extends RPExpressionData> actual = rpExpression.getCkd();
		assertEquals(allCkdDataList, actual);
	}

	@Test
	public void setHrt() {
		List<? extends RPExpressionData> allHrtDataList = Arrays.asList(mock(RPExpressionData.class));
		rpExpression.setHrt(allHrtDataList);
		List<? extends RPExpressionData> actual = rpExpression.getHrt();
		assertEquals(allHrtDataList, actual);
	}

	@Test
	public void setDmr() {
		List<? extends RPExpressionData> allDmrDataList = Arrays.asList(mock(RPExpressionData.class));
		rpExpression.setDmr(allDmrDataList);
		List<? extends RPExpressionData> actual = rpExpression.getDmr();
		assertEquals(allDmrDataList, actual);
	}

}