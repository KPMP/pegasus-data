package org.kpmp.geneExpressionSummary;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SNRNAGeneExpressionSummaryValueTest {

    SNRNAGeneExpressionExpressionSummaryValue snrnaGeneExpressionValue;

    @Before
    public void setUp() throws Exception {
        snrnaGeneExpressionValue = new SNRNAGeneExpressionExpressionSummaryValue();
    }

    @After
    public void tearDown() throws Exception {
        snrnaGeneExpressionValue = null;
    }

    @Test
    public void testGetDataType() throws Exception {
        assertEquals("sn", snrnaGeneExpressionValue.getDataType());
    }

    @Test
    public void testSetAvgExpression() throws Exception {
        snrnaGeneExpressionValue.setAvgExpression(0.1);
        assertEquals(0.1, snrnaGeneExpressionValue.getAvgExpression(), 0.001);

    }

    @Test
    public void testSetCluster() throws Exception {
        snrnaGeneExpressionValue.setCluster("cluster");
        assertEquals("cluster", snrnaGeneExpressionValue.getCluster());
    }

    @Test
    public void testSetFoldChange() throws Exception {
        snrnaGeneExpressionValue.setFoldChange(0.2);
        assertEquals(0.2, snrnaGeneExpressionValue.getFoldChange(), 0.001);
    }

    @Test
    public void testSetGene() throws Exception {
        snrnaGeneExpressionValue.setGene("gene");
        assertEquals("gene", snrnaGeneExpressionValue.getGene());

    }

    @Test
    public void testSetId() throws Exception {
        snrnaGeneExpressionValue.setId(1);
        assertEquals(1, snrnaGeneExpressionValue.getId(), 0.001);

    }

    @Test
    public void testSetPct1() throws Exception {
        snrnaGeneExpressionValue.setPct1(0.3);
        assertEquals(0.3, snrnaGeneExpressionValue.getPct1(), 0.001);

    }

    @Test
    public void testSetPct2() throws Exception {
        snrnaGeneExpressionValue.setPct2(0.4);
        assertEquals(0.4, snrnaGeneExpressionValue.getPct2(), 0.001);

    }

    @Test
    public void testSetPVal() throws Exception {
        snrnaGeneExpressionValue.setPVal(0.5);
        assertEquals(0.5, snrnaGeneExpressionValue.getPVal(), 0.001);

    }

    @Test
    public void testSetPValAdj() throws Exception {
        snrnaGeneExpressionValue.setPValAdj(0.6);
        assertEquals(0.6, snrnaGeneExpressionValue.getPValAdj(), 0.001);

    }

    @Test
    public void testSetTissueType() throws Exception {
        snrnaGeneExpressionValue.setTissueType("tissue");
        assertEquals("tissue", snrnaGeneExpressionValue.getTissueType());
    }

    @Test
    public void testSetClusterName() throws Exception {
        snrnaGeneExpressionValue.setClusterName("cluster name");
        assertEquals("cluster name", snrnaGeneExpressionValue.getClusterName());
    }

    @Test
    public void testSetCellCount() throws Exception {
        snrnaGeneExpressionValue.setCellCount(42);
        assertEquals(Integer.valueOf(42), snrnaGeneExpressionValue.getCellCount());
    }
}
