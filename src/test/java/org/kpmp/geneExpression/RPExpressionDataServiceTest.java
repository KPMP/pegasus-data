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

        List<RPExpressionData> rpExpressionDataAki = Arrays.asList(new RPExpressionData[]{new RPExpressionData()});
        List<RPExpressionData>  rpExpressionDataCkd = Arrays.asList(new RPExpressionData[]{new RPExpressionData()});
        List<RPExpressionData>  rpExpressionDataHrt = Arrays.asList(new RPExpressionData[]{new RPExpressionData()});
        List<RPExpressionData>  rpExpressionDataDmr = Arrays.asList(new RPExpressionData[]{new RPExpressionData()});
        List<RPExpressionData>  rpExpressionDataAll = Arrays.asList(new RPExpressionData[]{new RPExpressionData()});

        List<RPExpressionData> rpExpressionDataAki2 = Arrays.asList(new RPExpressionData[]{new RPExpressionData()});
        List<RPExpressionData>  rpExpressionDataCkd2 = Arrays.asList(new RPExpressionData[]{new RPExpressionData()});
        List<RPExpressionData>  rpExpressionDataHrt2 = Arrays.asList(new RPExpressionData[]{new RPExpressionData()});
        List<RPExpressionData>  rpExpressionDataDmr2 = Arrays.asList(new RPExpressionData[]{new RPExpressionData()});
        List<RPExpressionData>  rpExpressionDataAll2 = Arrays.asList(new RPExpressionData[]{new RPExpressionData()});

        when(rpExpressionDataRepository.findByGeneSymbolAndTissueTypeAndProteinWithCounts("gene", TissueTypeEnum.AKI.getParticipantTissueType(), "a1")).
                thenReturn(rpExpressionDataAki);
        when(rpExpressionDataRepository.findByGeneSymbolAndTissueTypeAndProteinWithCounts("gene", TissueTypeEnum.CKD.getParticipantTissueType(), "a1")).
                thenReturn(rpExpressionDataCkd);
        when(rpExpressionDataRepository.findByGeneSymbolAndTissueTypeAndProteinWithCounts("gene", TissueTypeEnum.HEALTHY_REFERENCE.getParticipantTissueType(), "a1")).
                thenReturn(rpExpressionDataHrt);
        when(rpExpressionDataRepository.findByGeneSymbolAndTissueTypeAndProteinWithCounts("gene", TissueTypeEnum.DMR.getParticipantTissueType(), "a1")).
                thenReturn(rpExpressionDataDmr);
        when(rpExpressionDataRepository.findByGeneSymbolAndTissueTypeAndProteinWithCounts("gene", TissueTypeEnum.ALL.getParticipantTissueType(), "a1")).
                thenReturn(rpExpressionDataAll);

        when(rpExpressionDataRepository.findByGeneSymbolAndTissueTypeAndProteinWithCounts("gene", TissueTypeEnum.AKI.getParticipantTissueType(), "a2")).
                thenReturn(rpExpressionDataAki2);
        when(rpExpressionDataRepository.findByGeneSymbolAndTissueTypeAndProteinWithCounts("gene", TissueTypeEnum.CKD.getParticipantTissueType(), "a2")).
                thenReturn(rpExpressionDataCkd2);
        when(rpExpressionDataRepository.findByGeneSymbolAndTissueTypeAndProteinWithCounts("gene", TissueTypeEnum.HEALTHY_REFERENCE.getParticipantTissueType(), "a2")).
                thenReturn(rpExpressionDataHrt2);
        when(rpExpressionDataRepository.findByGeneSymbolAndTissueTypeAndProteinWithCounts("gene", TissueTypeEnum.DMR.getParticipantTissueType(), "a2")).
                thenReturn(rpExpressionDataDmr2);
        when(rpExpressionDataRepository.findByGeneSymbolAndTissueTypeAndProteinWithCounts("gene", TissueTypeEnum.ALL.getParticipantTissueType(), "a2")).
                thenReturn(rpExpressionDataAll2);

        List<RPAccessionGroup> rpAccessionGroups = rpExpressionDataService.getByGeneSymbolPerTissue("gene");

        assertEquals("a1", rpAccessionGroups.get(0).getAccession());
        assertEquals("a2", rpAccessionGroups.get(1).getAccession());
        RPExpressionByTissueType rpExpressionByTissueType1 = rpAccessionGroups.get(0).getRpExpressionByTissueType();
        RPExpressionByTissueType rpExpressionByTissueType2 = rpAccessionGroups.get(1).getRpExpressionByTissueType();


        assertEquals(rpExpressionByTissueType1.getHrt(), rpExpressionDataHrt);
        assertEquals(rpExpressionByTissueType1.getCkd(), rpExpressionDataCkd);
        assertEquals(rpExpressionByTissueType1.getAll(), rpExpressionDataAll);
        assertEquals(rpExpressionByTissueType1.getDmr(), rpExpressionDataDmr);

        assertEquals(rpExpressionByTissueType2.getHrt(), rpExpressionDataHrt2);
        assertEquals(rpExpressionByTissueType2.getCkd(), rpExpressionDataCkd2);
        assertEquals(rpExpressionByTissueType2.getAll(), rpExpressionDataAll2);
        assertEquals(rpExpressionByTissueType2.getDmr(), rpExpressionDataDmr2);

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
