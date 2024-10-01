package org.kpmp.gene;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;


public class MyGeneInfoResultTest {

    private MyGeneInfoResult myGeneInfoResult;

    @BeforeEach
    public void setUp() throws Exception {
        myGeneInfoResult = new MyGeneInfoResult();
    }

    @AfterEach
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
