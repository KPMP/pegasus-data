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
    private SNExpressionDataRepositoryNewData snRepoNewData;
    @Mock 
    private SCExpressionDataRepositoryNewData scRepoNewData;
	private SNSCExpressionDataService service;

	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		service = new SNSCExpressionDataService(snRepo, snRepoNewData, scRepo, scRepoNewData);
	}

	@AfterEach
	public void tearDown() throws Exception {
		MockitoAnnotations.openMocks(this).close();
		service = null;
	}

	@Test
	public void testGetGeneExpressionValuesWhenSingleNuc() throws JSONException, Exception {
		SNExpressionData expressionData1 = mock(SNExpressionData.class);
        SNExpressionDataNewData expressionDataNewData = mock(SNExpressionDataNewData.class);
		JSONObject expectedResult1 = new JSONObject();
        JSONObject expectedResult2 = new JSONObject();
		when(expressionData1.getExpressionDataAsJson()).thenReturn(expectedResult1);
        when(expressionDataNewData.getExpressionDataAsJson()).thenReturn(expectedResult2);
		when(snRepo.findByGeneSymbol("geneSymbol")).thenReturn(expressionData1);
        when(snRepoNewData.findByGeneSymbol("geneSymbol")).thenReturn(expressionDataNewData);

		assertEquals(expectedResult1, service.getGeneExpressionValues("sn", "geneSymbol", false));
        assertEquals(expectedResult2, service.getGeneExpressionValues("sn", "geneSymbol", true));
	}

	@Test
	public void testGetGeneExpressionValuesWhenSingleCell() throws JSONException, Exception {
		SCExpressionData expressionData = mock(SCExpressionData.class);
		JSONObject expectedResult = new JSONObject();
		when(expressionData.getExpressionDataAsJson()).thenReturn(expectedResult);
		when(scRepo.findByGeneSymbol("geneSymbol")).thenReturn(expressionData);

		assertEquals(expectedResult, service.getGeneExpressionValues("sc", "geneSymbol", false));
	}

	@Test
	public void testGetGeneExpressionValuesWhenOtherDataType() throws JSONException {

		try {
			service.getGeneExpressionValues("garbage", "geneSymbol", false);
			fail("Should have thrown exception");
		} catch (Exception expected) {
			assertEquals("Invalid data type: garbage", expected.getMessage());
		}
	}

}
