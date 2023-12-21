package org.kpmp.geneExpression;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RPAccessionGroupTest {

    private RPAccessionGroup rpAccessionGroup;

    @Before
    public void setUp() throws Exception {
        rpAccessionGroup = new RPAccessionGroup();
    }

    @After
    public void tearDown() throws Exception {
        rpAccessionGroup = null;
    }

    @Test
    public void testConstructor() throws Exception {
        RPExpressionByTissueType rpExpressionByTissueType = new RPExpressionByTissueType();
        RPAccessionGroup rpAccessionGroup1 = new RPAccessionGroup("accession", rpExpressionByTissueType);
        assertEquals(rpExpressionByTissueType, rpAccessionGroup1.getRpExpressionByTissueType());
        assertEquals("accession", rpAccessionGroup1.getAccession());
    }

    @Test
    public void testSetters() throws Exception {
        RPExpressionByTissueType rpExpressionByTissueType = new RPExpressionByTissueType();
        rpAccessionGroup.setAccession("1234");
        rpAccessionGroup.setRpExpressionByTissueType(rpExpressionByTissueType);
        assertEquals("1234", rpAccessionGroup.getAccession());
        assertEquals(rpExpressionByTissueType, rpAccessionGroup.getRpExpressionByTissueType());
    }


}
