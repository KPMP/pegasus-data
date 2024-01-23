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


}