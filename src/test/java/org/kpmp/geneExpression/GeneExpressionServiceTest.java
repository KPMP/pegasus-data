package org.kpmp.geneExpression;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kpmp.gene.GeneService;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class GeneExpressionServiceTest {

    private GeneExpressionService geneExpressionService;
    @Mock
    private SNRNAGeneExpressionRepository snrnaGeneExpressionRepository;
    @Mock
    private SCRNAGeneExpressionRepository scrnaGeneExpressionRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        geneExpressionService = new GeneExpressionService(scrnaGeneExpressionRepository, snrnaGeneExpressionRepository);
    }

    @After
    public void tearDown() throws Exception {
        geneExpressionService = null;
    }

    @Test
    public void testGetByDataTypeTissueTypeAndGene() throws Exception {
        List<SNRNAGeneExpressionValue> snResults = Arrays.asList(new SNRNAGeneExpressionValue());
        when(snrnaGeneExpressionRepository.findByTissueAndGeneAllClusters("gene", "aki")).thenReturn(snResults);
        List<SCRNAGeneExpressionValue> scResults = Arrays.asList(new SCRNAGeneExpressionValue());
        when(scrnaGeneExpressionRepository.findByTissueAndGeneAllClusters("gene", "aki")).thenReturn(scResults);
        List results = geneExpressionService.getByDataTypeTissueTypeAndGene("", "gene", "aki");
        assertEquals(2, results.size());
        List<? extends GeneExpressionValue> resultsSC = geneExpressionService.getByDataTypeTissueTypeAndGene("sc", "gene", "aki");
        assertEquals(scResults, resultsSC);
        assertEquals("sc", resultsSC.get(0).getDataType());
        List<? extends GeneExpressionValue> resultsSN = geneExpressionService.getByDataTypeTissueTypeAndGene("sn", "gene", "aki");
        assertEquals(snResults, resultsSN);
        assertEquals("sn", resultsSN.get(0).getDataType());

    }


}
