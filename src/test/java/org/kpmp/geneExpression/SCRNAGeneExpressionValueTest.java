package org.kpmp.geneExpression;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SCRNAGeneExpressionValueTest {

    private SCRNAGeneExpressionValue scrnaGeneExpressionValue;

    @Before
    public void setUp() throws Exception {
        scrnaGeneExpressionValue = new SCRNAGeneExpressionValue();
    }

    @After
    public void tearDown() throws Exception {
        scrnaGeneExpressionValue = null;
    }

    @Test
    public void testGettersAndSetters() throws Exception {
        scrnaGeneExpressionValue.setAvgExpression(0.1);
        scrnaGeneExpressionValue.setCellType("ct");
        scrnaGeneExpressionValue.setCluster("cluster");
        scrnaGeneExpressionValue.setFoldChange(0.2);
        scrnaGeneExpressionValue.setGene("gene");
        scrnaGeneExpressionValue.setId(1);
        scrnaGeneExpressionValue.setPct1(0.3);
        scrnaGeneExpressionValue.setPct2(0.4);
        scrnaGeneExpressionValue.setPVal(0.5);
        scrnaGeneExpressionValue.setPValAdj(0.6);
        scrnaGeneExpressionValue.setSpecificity(0.7);
        scrnaGeneExpressionValue.setTissueType("tissue");
        assertEquals(0.1, scrnaGeneExpressionValue.getAvgExpression(), 0.001);
        assertEquals("ct", scrnaGeneExpressionValue.getCellType());
        assertEquals("cluster", scrnaGeneExpressionValue.getCluster());
        assertEquals("gene", scrnaGeneExpressionValue.getGene());
        assertEquals("tissue", scrnaGeneExpressionValue.getTissueType());
        assertEquals("sc", scrnaGeneExpressionValue.getDataType());
        assertEquals(0.2, scrnaGeneExpressionValue.getFoldChange(), 0.001);
        assertEquals(0.3, scrnaGeneExpressionValue.getPct1(), 0.001);
        assertEquals(0.4, scrnaGeneExpressionValue.getPct2(), 0.001);
        assertEquals(0.5, scrnaGeneExpressionValue.getPVal(), 0.001);
        assertEquals(0.6, scrnaGeneExpressionValue.getPValAdj(), 0.001);
        assertEquals(0.7, scrnaGeneExpressionValue.getSpecificity(), 0.001);
        assertEquals(1, scrnaGeneExpressionValue.getId(), 0.001);

    }


}
