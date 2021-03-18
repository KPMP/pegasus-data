package org.kpmp.geneExpression;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SNRNAGeneExpressionValueTest {

    SNRNAGeneExpressionValue snrnaGeneExpressionValue;

    @Before
    public void setUp() throws Exception {
        snrnaGeneExpressionValue = new SNRNAGeneExpressionValue();
    }

    @After
    public void tearDown() throws Exception {
        snrnaGeneExpressionValue = null;
    }

    @Test
    public void testGettersAndSetters() throws Exception {
        snrnaGeneExpressionValue.setAvgExpression(0.1);
        snrnaGeneExpressionValue.setCellType("ct");
        snrnaGeneExpressionValue.setCluster("cluster");
        snrnaGeneExpressionValue.setFoldChange(0.2);
        snrnaGeneExpressionValue.setGene("gene");
        snrnaGeneExpressionValue.setId(1);
        snrnaGeneExpressionValue.setPct1(0.3);
        snrnaGeneExpressionValue.setPct2(0.4);
        snrnaGeneExpressionValue.setPVal(0.5);
        snrnaGeneExpressionValue.setPValAdj(0.6);
        snrnaGeneExpressionValue.setTissueType("tissue");
        assertEquals(0.1, snrnaGeneExpressionValue.getAvgExpression(), 0.001);
        assertEquals("ct", snrnaGeneExpressionValue.getCellType());
        assertEquals("cluster", snrnaGeneExpressionValue.getCluster());
        assertEquals("gene", snrnaGeneExpressionValue.getGene());
        assertEquals("tissue", snrnaGeneExpressionValue.getTissueType());
        assertEquals("sn", snrnaGeneExpressionValue.getDataType());
        assertEquals(0.2, snrnaGeneExpressionValue.getFoldChange(), 0.001);
        assertEquals(0.3, snrnaGeneExpressionValue.getPct1(), 0.001);
        assertEquals(0.4, snrnaGeneExpressionValue.getPct2(), 0.001);
        assertEquals(0.5, snrnaGeneExpressionValue.getPVal(), 0.001);
        assertEquals(0.6, snrnaGeneExpressionValue.getPValAdj(), 0.001);
        assertEquals(1, snrnaGeneExpressionValue.getId(), 0.001);
    }
}
