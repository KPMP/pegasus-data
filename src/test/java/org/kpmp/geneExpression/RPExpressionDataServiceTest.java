package org.kpmp.geneExpression;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kpmp.TissueTypeEnum;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

public class RPExpressionDataServiceTest {

    @Mock
    RPExpressionDataRepository rpExpressionDataRepository;

    RPExpressionDataService rpExpressionDataService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        rpExpressionDataService = new RPExpressionDataService(rpExpressionDataRepository);
    }

    @After
    public void tearDown() throws Exception {
        MockitoAnnotations.openMocks(this).close();
        rpExpressionDataService = null;
    }

    @Test
    public void testGetByGeneSymbolPerTissue() throws Exception {

        List<String> accessions = new ArrayList<>();
        accessions.add("a1");
        accessions.add("a2");
        when(rpExpressionDataRepository.findAccessionByGeneSymbol("gene")).thenReturn(accessions);

        List<RPExpressionData>  rpExpressionDataAll = Arrays.asList(new RPExpressionData[]{new RPExpressionData()});

        List<RPExpressionData>  rpExpressionDataAll2 = Arrays.asList(new RPExpressionData[]{new RPExpressionData()});

        when(rpExpressionDataRepository.findByGeneSymbolAndTissueTypeAndProteinWithCounts("gene", TissueTypeEnum.ALL.getParticipantTissueType(), "a1")).
                thenReturn(rpExpressionDataAll);

        when(rpExpressionDataRepository.findByGeneSymbolAndTissueTypeAndProteinWithCounts("gene", TissueTypeEnum.ALL.getParticipantTissueType(), "a2")).
                thenReturn(rpExpressionDataAll2);

        List<RPAccessionGroup> rpAccessionGroups = rpExpressionDataService.getByGeneSymbolPerTissue("gene");

        assertEquals("a1", rpAccessionGroups.get(0).getAccession());
        assertEquals("a2", rpAccessionGroups.get(1).getAccession());
        RPExpressionByTissueType rpExpressionByTissueType1 = rpAccessionGroups.get(0).getRpExpressionByTissueType();
        RPExpressionByTissueType rpExpressionByTissueType2 = rpAccessionGroups.get(1).getRpExpressionByTissueType();


        assertEquals(rpExpressionByTissueType1.getAll(), rpExpressionDataAll);
        assertEquals(rpExpressionByTissueType2.getAll(), rpExpressionDataAll2);
        
    }

    @Test
    public void testGetByGeneSymbolAndProteinPerTissue() throws Exception {
        List<RPExpressionData>  rpExpressionDataAll = Arrays.asList(new RPExpressionData[]{new RPExpressionData()});
       
        when(rpExpressionDataRepository.findByGeneSymbolAndTissueTypeAndProteinWithCounts("gene", TissueTypeEnum.ALL.getParticipantTissueType(), "protein")).
                thenReturn(rpExpressionDataAll);

        RPExpressionByTissueType rpExpressionByTissueType = rpExpressionDataService.getByGeneSymbolAndProteinPerTissue("gene", "protein");

        assertEquals(rpExpressionByTissueType.getAll(), rpExpressionDataAll);
    }

    @Test
    public void testGetByStructure() throws Exception {
        List<RPExpressionData> rpExpressionDataList = new ArrayList<>();
        when(rpExpressionDataRepository.findByStructure("structure")).thenReturn(rpExpressionDataList);
        assertEquals(rpExpressionDataList, rpExpressionDataService.getByStructure("structure"));
    }
}
