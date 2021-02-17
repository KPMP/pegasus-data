package org.kpmp.gene;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GeneServiceTest {

    private GeneService geneService;

    @Before
    public void setUp() throws Exception {
        geneService = new GeneService();
    }

    @After
    public void tearDown() throws Exception {
        geneService = null;
    }

    @Test
    public void testQuerySymbolAndAlias() throws Exception {
        MyGeneInfoResult result1 = new MyGeneInfoResult();
        List<MyGeneInfoHit> hits1 = new ArrayList<>();
        MyGeneInfoHit hit1 = new MyGeneInfoHit();
        hits1.add(hit1);
        result1.setHits(hits1);
        MyGeneInfoResult result2 = new MyGeneInfoResult();
        List<MyGeneInfoHit> hits2 = new ArrayList<>();
        MyGeneInfoHit hit2 = new MyGeneInfoHit();
        hits2.add(hit2);
        result2.setHits(hits2);
        GeneService geneService2 = Mockito.spy(geneService);
        Mockito.doReturn(result1).when(geneService2).query(GeneService.GET_MY_GENE_INFO_QUERY_SYMBOL, "query");
        Mockito.doReturn(result2).when(geneService2).query(GeneService.GET_MY_GENE_INFO_QUERY_ALIAS, "query");
        List<MyGeneInfoHit> results = geneService2.querySymbolAndAlias("query");
        assertEquals(results.get(0), hit1);
        assertEquals(results.get(1), hit2);
    }

}
