package org.kpmp.gene;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;


public class MyGeneInfoResultTest {

    private MyGeneInfoResult myGeneInfoResult;

    @Before
    public void setUp() throws Exception {
        myGeneInfoResult = new MyGeneInfoResult();
    }

    @After
    public void tearDown() throws Exception {
        myGeneInfoResult = null;
    }

    @Test
    public void testSetTotal() {
        myGeneInfoResult.setTotal(99);
        assertEquals(99, myGeneInfoResult.getTotal());
    }

    @Test
    public void testSetHits() {
        List<MyGeneInfoHit> hits = Arrays.asList(new MyGeneInfoHit());
        myGeneInfoResult.setHits(hits);
        assertEquals(hits, myGeneInfoResult.getHits());
    }
}
