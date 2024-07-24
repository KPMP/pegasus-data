package org.kpmp.geneExpression;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RPExpressionByTissueTypeTest {

	private RPExpressionByTissueType rpExpression;

	@BeforeEach
	public void setUp() throws Exception {
		rpExpression = new RPExpressionByTissueType();
	}

	@AfterEach
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


}