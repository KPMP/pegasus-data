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
        MyGeneInfoHit hit1a = new MyGeneInfoHit();
        hit1a.setSymbol("a");
        MyGeneInfoHit hit1b = new MyGeneInfoHit();
        hit1b.setSymbol("b");
        MyGeneInfoHit hit1c = new MyGeneInfoHit();
        hit1c.setSymbol("z");
        hits1.add(hit1b);
        hits1.add(hit1a);
        hits1.add(hit1c);
        hits1.add(hit1c);
        hits1.add(hit1c);
        hits1.add(hit1c);
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
        assertEquals(results.get(0), hit1a);
        assertEquals(results.get(1), hit1b);
        assertEquals(results.get(5), hit1c);
        assertEquals(results.get(6), hit2);
    }

}
