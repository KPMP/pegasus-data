package org.kpmp.geneExpression;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ExpressionDataServiceTest {

	@Mock
	private SNExpressionDataRepository snRepo;
	@Mock
	private SCExpressionDataRepository scRepo;
    @Mock
    private SNExpressionDataRepository2025 snRepo2025;
	private SNSCExpressionDataService service;

	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		service = new SNSCExpressionDataService(snRepo, scRepo);
	}

	@AfterEach
	public void tearDown() throws Exception {
		MockitoAnnotations.openMocks(this).close();
		service = null;
	}

	@Test
	public void testGetGeneExpressionValuesWhenSingleNuc() throws JSONException, Exception {
		SNExpressionData expressionData1 = mock(SNExpressionData.class);
        SNExpressionData2025 expressionData2025 = mock(SNExpressionData2025.class);
		JSONObject expectedResult1 = new JSONObject();
        JSONObject expectedResult2 = new JSONObject();
		when(expressionData1.getExpressionDataAsJson()).thenReturn(expectedResult1);
        when(expressionData2025.getExpressionDataAsJson()).thenReturn(expectedResult2);
		when(snRepo.findByGeneSymbol("geneSymbol")).thenReturn(expressionData1);
        when(snRepo2025.findByGeneSymbol("geneSymbol")).thenReturn(expressionData2025);

		assertEquals(expectedResult1, service.getGeneExpressionValues("sn", "geneSymbol"));
        assertEquals(expectedResult2, service.getGeneExpressionValues("sn", "geneSymbol"));
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
