package org.kpmp.geneExpressionSummary;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GeneExpressionIdTest {

    private GeneExpressionId geneExpressionId;

    @Before
    public void setUp() {
        geneExpressionId = new GeneExpressionId();
    }

    @After
    public void tearDown() {
        geneExpressionId = null;
    }

    @Test
    public void testSetCluster() throws Exception {
        geneExpressionId.setCluster("cluster");
        assertEquals("cluster", geneExpressionId.getCluster());
    }

    @Test
    public void testSetGene() throws Exception {
        geneExpressionId.setGene("gene");
        assertEquals("gene", geneExpressionId.getGene());
    }

}
