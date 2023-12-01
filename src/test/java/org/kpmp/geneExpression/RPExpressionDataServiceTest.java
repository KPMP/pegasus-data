package org.kpmp.geneExpression;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kpmp.TissueTypeEnum;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
        List<RPExpressionData> rpExpressionDataAki = Arrays.asList(new RPExpressionData[]{new RPExpressionData()});
        List<RPExpressionData>  rpExpressionDataCkd = Arrays.asList(new RPExpressionData[]{new RPExpressionData()});
        List<RPExpressionData>  rpExpressionDataHrt = Arrays.asList(new RPExpressionData[]{new RPExpressionData()});
        List<RPExpressionData>  rpExpressionDataDmr = Arrays.asList(new RPExpressionData[]{new RPExpressionData()});
        List<RPExpressionData>  rpExpressionDataAll = Arrays.asList(new RPExpressionData[]{new RPExpressionData()});

        when(rpExpressionDataRepository.findByGeneSymbolAndTissueTypeWithCounts("gene", TissueTypeEnum.AKI.getParticipantTissueType())).
                thenReturn(rpExpressionDataAki);
        when(rpExpressionDataRepository.findByGeneSymbolAndTissueTypeWithCounts("gene", TissueTypeEnum.CKD.getParticipantTissueType())).
                thenReturn(rpExpressionDataCkd);
        when(rpExpressionDataRepository.findByGeneSymbolAndTissueTypeWithCounts("gene", TissueTypeEnum.HEALTHY_REFERENCE.getParticipantTissueType())).
                thenReturn(rpExpressionDataHrt);
        when(rpExpressionDataRepository.findByGeneSymbolAndTissueTypeWithCounts("gene", TissueTypeEnum.DMR.getParticipantTissueType())).
                thenReturn(rpExpressionDataDmr);
        when(rpExpressionDataRepository.findByGeneSymbolAndTissueTypeWithCounts("gene", TissueTypeEnum.ALL.getParticipantTissueType())).
                thenReturn(rpExpressionDataAll);

        RPExpressionByTissueType rpExpressionByTissueType = rpExpressionDataService.getByGeneSymbolPerTissue("gene");

        assertEquals(rpExpressionByTissueType.getAki(), rpExpressionDataAki);
        assertEquals(rpExpressionByTissueType.getHrt(), rpExpressionDataHrt);
        assertEquals(rpExpressionByTissueType.getCkd(), rpExpressionDataCkd);
        assertEquals(rpExpressionByTissueType.getAll(), rpExpressionDataAll);
        assertEquals(rpExpressionByTissueType.getDmr(), rpExpressionDataDmr);

    }

    @Test
    public void testGetByGeneSymbolAndProteinPerTissue() throws Exception {
        List<RPExpressionData> rpExpressionDataAki = Arrays.asList(new RPExpressionData[]{new RPExpressionData()});
        List<RPExpressionData>  rpExpressionDataCkd = Arrays.asList(new RPExpressionData[]{new RPExpressionData()});
        List<RPExpressionData>  rpExpressionDataHrt = Arrays.asList(new RPExpressionData[]{new RPExpressionData()});
        List<RPExpressionData>  rpExpressionDataDmr = Arrays.asList(new RPExpressionData[]{new RPExpressionData()});
        List<RPExpressionData>  rpExpressionDataAll = Arrays.asList(new RPExpressionData[]{new RPExpressionData()});
        when(rpExpressionDataRepository.findByGeneSymbolAndTissueTypeAndProteinWithCounts("gene", TissueTypeEnum.AKI.getParticipantTissueType(), "protein")).
                thenReturn(rpExpressionDataAki);
        when(rpExpressionDataRepository.findByGeneSymbolAndTissueTypeAndProteinWithCounts("gene", TissueTypeEnum.CKD.getParticipantTissueType(), "protein")).
                thenReturn(rpExpressionDataCkd);
        when(rpExpressionDataRepository.findByGeneSymbolAndTissueTypeAndProteinWithCounts("gene", TissueTypeEnum.HEALTHY_REFERENCE.getParticipantTissueType(), "protein")).
                thenReturn(rpExpressionDataHrt);
        when(rpExpressionDataRepository.findByGeneSymbolAndTissueTypeAndProteinWithCounts("gene", TissueTypeEnum.DMR.getParticipantTissueType(), "protein")).
                thenReturn(rpExpressionDataDmr);
        when(rpExpressionDataRepository.findByGeneSymbolAndTissueTypeAndProteinWithCounts("gene", TissueTypeEnum.ALL.getParticipantTissueType(), "protein")).
                thenReturn(rpExpressionDataAll);

        RPExpressionByTissueType rpExpressionByTissueType = rpExpressionDataService.getByGeneSymbolAndProteinPerTissue("gene", "protein");

        assertEquals(rpExpressionByTissueType.getAki(), rpExpressionDataAki);
        assertEquals(rpExpressionByTissueType.getHrt(), rpExpressionDataHrt);
        assertEquals(rpExpressionByTissueType.getCkd(), rpExpressionDataCkd);
        assertEquals(rpExpressionByTissueType.getAll(), rpExpressionDataAll);
        assertEquals(rpExpressionByTissueType.getDmr(), rpExpressionDataDmr);
    }
}
