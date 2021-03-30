package org.kpmp.geneExpressionSummary;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kpmp.gene.GeneService;
import org.kpmp.geneExpressionSummary.GeneExpressionSummaryService;
import org.kpmp.geneExpressionSummary.GeneSummaryPerCluster;
import org.kpmp.geneExpressionSummary.SCRNAGeneExpressionSummaryRepository;
import org.kpmp.geneExpressionSummary.SCRNAGeneExpressionSummaryValue;
import org.kpmp.geneExpressionSummary.SNRNAGeneExpressionSummaryRepository;
import org.kpmp.geneExpressionSummary.SNRNAGeneExpressionSummartValue;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class GeneExpressionSummaryServiceTest {

    private GeneExpressionSummaryService geneExpressionService;
    @Mock
    private SNRNAGeneExpressionSummaryRepository snrnaGeneExpressionRepository;
    @Mock
    private SCRNAGeneExpressionSummaryRepository scrnaGeneExpressionRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        geneExpressionService = new GeneExpressionSummaryService(scrnaGeneExpressionRepository, snrnaGeneExpressionRepository);
    }

    @After
    public void tearDown() throws Exception {
        geneExpressionService = null;
    }

    @Test
    public void testGetByDataTypeTissueTypeAndGene() throws Exception {
        List<SNRNAGeneExpressionSummartValue> snResults = Arrays.asList(new SNRNAGeneExpressionSummartValue());
        when(snrnaGeneExpressionRepository.findByTissueAndGeneAllClusters("gene", "aki")).thenReturn(snResults);
        List<SCRNAGeneExpressionSummaryValue> scResults = Arrays.asList(new SCRNAGeneExpressionSummaryValue());
        when(scrnaGeneExpressionRepository.findByTissueAndGeneAllClusters("gene", "aki")).thenReturn(scResults);
        List results = geneExpressionService.getByDataTypeTissueTypeAndGene("", "gene", "aki");
        assertEquals(2, results.size());
        List<? extends GeneSummaryPerCluster> resultsSC = geneExpressionService.getByDataTypeTissueTypeAndGene("sc", "gene", "aki");
        assertEquals(scResults, resultsSC);
        assertEquals("sc", resultsSC.get(0).getDataType());
        List<? extends GeneSummaryPerCluster> resultsSN = geneExpressionService.getByDataTypeTissueTypeAndGene("sn", "gene", "aki");
        assertEquals(snResults, resultsSN);
        assertEquals("sn", resultsSN.get(0).getDataType());

    }


}
