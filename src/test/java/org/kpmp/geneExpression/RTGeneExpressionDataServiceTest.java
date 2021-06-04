package org.kpmp.geneExpression;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class RTGeneExpressionDataServiceTest {

    @Mock
    private RTExpressionDataAllSegmentsRepository rtExpressionDataAllSegmentsRepository;
    @Mock
    private RTExpressionDataGTIRepository rtExpressionDataGTIRepository;
    private RTExpressionDataService rtExpressionDataService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        rtExpressionDataService = new RTExpressionDataService(rtExpressionDataAllSegmentsRepository, rtExpressionDataGTIRepository);
    }

    @After
    public void tearDown() throws Exception {
        rtExpressionDataService = null;
    }

    @Test
    public void testGetByComparisonTypeGeneSymbolAndTissueType() throws Exception {
        RTExpressionDataAllSegments aData = new RTExpressionDataAllSegments();
        List<RTExpressionDataAllSegments> aDataList = Arrays.asList(aData);
        RTExpressionDataGTI gData = new RTExpressionDataGTI();
        List<RTExpressionDataGTI> gDataList = Arrays.asList(gData);
        when(rtExpressionDataAllSegmentsRepository.findByGeneSymbol("gene")).thenReturn(aDataList);
        when(rtExpressionDataGTIRepository.findByGeneSymbol("gene")).thenReturn(gDataList);
        assertEquals(aDataList, rtExpressionDataService.getByComparisonTypeAndGeneSymbol("all_segments", "gene"));
        assertEquals(gDataList, rtExpressionDataService.getByComparisonTypeAndGeneSymbol("glom_tub", "gene"));
        assertEquals(null, rtExpressionDataService.getByComparisonTypeAndGeneSymbol("blah", "gene"));
    }

}
