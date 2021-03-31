package org.kpmp.geneExpressionSummary;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GeneExpressionIdTest {

    private GeneExpressionId geneExpressionId;

    @Before
    public void setUp() throws Exception {
        geneExpressionId = new GeneExpressionId();
    }

    @After
    public void tearDown() throws Exception {
        geneExpressionId = null;
    }

    @Test
    public void testSetCluster() throws Exception {
        this.geneExpressionId.setCluster("cluster");
        assertEquals("cluster", this.geneExpressionId.getCluster());
    }

    @Test
    public void testSetGene() throws Exception {
        this.geneExpressionId.setGene("gene");
        assertEquals("gene", this.geneExpressionId.getGene());
    }
}
