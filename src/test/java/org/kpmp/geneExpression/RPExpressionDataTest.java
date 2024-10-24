package org.kpmp.geneExpression;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RPExpressionDataTest {

    private RPExpressionData rpExpressionData;

    @BeforeEach
    public void setUp() throws Exception {
        rpExpressionData = new RPExpressionData();
    }

    @AfterEach
    public void tearDown() throws Exception {
        rpExpressionData = null;
    }

    @Test
    public void testSettersAndGetters() throws Exception {

        rpExpressionData.setAccession("accession");
        rpExpressionData.setAdjPVal(123.123);
        rpExpressionData.setComparison("comparison");
        rpExpressionData.setFoldChange(1.1234);
        rpExpressionData.setId(1);
        rpExpressionData.setGeneSymbol("symbol");
        rpExpressionData.setCoveragePct(123);
        rpExpressionData.setSampleCount(321);
        rpExpressionData.setFdrConfidence("confidence");
        rpExpressionData.setDescription("desc");
        rpExpressionData.setNumPeptides(678);
        rpExpressionData.setNumUniquePeptides(456);
        rpExpressionData.setEnrollmentCategory("tissue");
        rpExpressionData.setRegion("region");
        assertEquals(rpExpressionData.getAccession(),"accession");
        assertEquals(rpExpressionData.getAdjPVal(), 123.123);
        assertEquals(rpExpressionData.getComparison(), "comparison");
        assertEquals(rpExpressionData.getFoldChange(), 1.1234);
        assertEquals(rpExpressionData.getId(), 1);
        assertEquals(rpExpressionData.getGeneSymbol(), "symbol");
        assertEquals(rpExpressionData.getCoveragePct(), 123);
        assertEquals(rpExpressionData.getSampleCount(), 321);
        assertEquals(rpExpressionData.getFdrConfidence(), "confidence");
        assertEquals(rpExpressionData.getDescription(), "desc");
        assertEquals(rpExpressionData.getNumPeptides(), 678);
        assertEquals(rpExpressionData.getNumUniquePeptides(), 456);
        assertEquals(rpExpressionData.getEnrollmentCategory(), "tissue");
        assertEquals(rpExpressionData.getRegion(), "region");

    }

}
