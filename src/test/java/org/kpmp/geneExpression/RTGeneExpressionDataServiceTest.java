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
    public void testGetByComparisonTypeAndGeneSymbolPerTissue() throws Exception {
        RTExpressionDataAllSegments allAkiData = new RTExpressionDataAllSegments();
        List<RTExpressionDataAllSegments> allAkiDataList = Arrays.asList(allAkiData);
        RTExpressionDataGTI gAkiData = new RTExpressionDataGTI();
        List<RTExpressionDataGTI> gAkiDataList = Arrays.asList(gAkiData);

        RTExpressionDataAllSegments allhrtData = new RTExpressionDataAllSegments();
        List<RTExpressionDataAllSegments> allhrtDataList = Arrays.asList(allhrtData);
        RTExpressionDataGTI ghrtData = new RTExpressionDataGTI();
        List<RTExpressionDataGTI> ghrtDataList = Arrays.asList(ghrtData);

        RTExpressionDataAllSegments allckdData = new RTExpressionDataAllSegments();
        List<RTExpressionDataAllSegments> allckdDataList = Arrays.asList(allckdData);
        RTExpressionDataGTI gckdData = new RTExpressionDataGTI();
        List<RTExpressionDataGTI> gckdDataList = Arrays.asList(gckdData);

        RTExpressionDataAllSegments allallData = new RTExpressionDataAllSegments();
        List<RTExpressionDataAllSegments> allallDataList = Arrays.asList(allallData);
        RTExpressionDataGTI gallData = new RTExpressionDataGTI();
        List<RTExpressionDataGTI> gallDataList = Arrays.asList(gallData);

        when(rtExpressionDataAllSegmentsRepository.findByGeneSymbolAndTissueType("gene", "AKI")).thenReturn(allAkiDataList);
        when(rtExpressionDataGTIRepository.findByGeneSymbolAndTissueType("gene", "AKI")).thenReturn(gAkiDataList);

        when(rtExpressionDataAllSegmentsRepository.findByGeneSymbolAndTissueType("gene", "CKD")).thenReturn(allckdDataList);
        when(rtExpressionDataGTIRepository.findByGeneSymbolAndTissueType("gene", "CKD")).thenReturn(gckdDataList);

        when(rtExpressionDataAllSegmentsRepository.findByGeneSymbolAndTissueType("gene", "hrt")).thenReturn(allhrtDataList);
        when(rtExpressionDataGTIRepository.findByGeneSymbolAndTissueType("gene", "hrt")).thenReturn(ghrtDataList);

        when(rtExpressionDataAllSegmentsRepository.findByGeneSymbolAndTissueType("gene", "all")).thenReturn(allallDataList);
        when(rtExpressionDataGTIRepository.findByGeneSymbolAndTissueType("gene", "all")).thenReturn(gallDataList);

        RTExpressionByTissueType rtExpressionByTissueTypeAll = rtExpressionDataService.getByComparisonTypeAndGeneSymbolPerTissue("all_segments", "gene");
        RTExpressionByTissueType rtExpressionByTissueTypeGti = rtExpressionDataService.getByComparisonTypeAndGeneSymbolPerTissue("glom_tub", "gene");

        assertEquals(rtExpressionByTissueTypeAll.getAki(), allAkiDataList);
        assertEquals(rtExpressionByTissueTypeAll.getAll(), allallDataList);
        assertEquals(rtExpressionByTissueTypeAll.getCkd(), allckdDataList);
        assertEquals(rtExpressionByTissueTypeAll.getHrt(), allhrtDataList);

        assertEquals(rtExpressionByTissueTypeGti.getAki(), gAkiDataList);
        assertEquals(rtExpressionByTissueTypeGti.getAll(), gallDataList);
        assertEquals(rtExpressionByTissueTypeGti.getCkd(), gckdDataList);
        assertEquals(rtExpressionByTissueTypeGti.getHrt(), ghrtDataList);
    }

}
