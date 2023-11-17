package org.kpmp.geneExpression;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ExpressionDataServiceTest {

	@Mock
	private SNExpressionDataRepository snRepo;
	@Mock
	private SCExpressionDataRepository scRepo;
	private SNSCExpressionDataService service;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		service = new SNSCExpressionDataService(snRepo, scRepo);
	}

	@After
	public void tearDown() throws Exception {
		MockitoAnnotations.openMocks(this).close();
		service = null;
	}

	@Test
	public void testGetGeneExpressionValuesWhenSingleNuc() throws JSONException, Exception {
		SNExpressionData expressionData = mock(SNExpressionData.class);
		JSONObject expectedResult = new JSONObject();
		when(expressionData.getExpressionDataAsJson()).thenReturn(expectedResult);
		when(snRepo.findByGeneSymbol("geneSymbol")).thenReturn(expressionData);

		assertEquals(expectedResult, service.getGeneExpressionValues("sn", "geneSymbol"));
	}

	@Test
	public void testGetGeneExpressionValuesWhenSingleCell() throws JSONException, Exception {
		SCExpressionData expressionData = mock(SCExpressionData.class);
		JSONObject expectedResult = new JSONObject();
		when(expressionData.getExpressionDataAsJson()).thenReturn(expectedResult);
		when(scRepo.findByGeneSymbol("geneSymbol")).thenReturn(expressionData);

		assertEquals(expectedResult, service.getGeneExpressionValues("sc", "geneSymbol"));
	}

	@Test
	public void testGetGeneExpressionValuesWhenOtherDataType() throws JSONException {

		try {
			service.getGeneExpressionValues("garbage", "geneSymbol");
			fail("Should have thrown exception");
		} catch (Exception expected) {
			assertEquals("Invalid data type: garbage", expected.getMessage());
		}
	}

}
