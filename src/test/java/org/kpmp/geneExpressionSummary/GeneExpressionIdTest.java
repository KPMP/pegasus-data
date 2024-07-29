package org.kpmp.geneExpressionSummary;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GeneExpressionIdTest {

    private GeneExpressionId geneExpressionId;

    @BeforeEach
    public void setUp() {
        geneExpressionId = new GeneExpressionId();
    }

    @AfterEach
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
