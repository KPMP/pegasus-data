package org.kpmp.geneExpression;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RPAccessionGroupTest {

    private RPAccessionGroup rpAccessionGroup;

    @BeforeEach
    public void setUp() throws Exception {
        rpAccessionGroup = new RPAccessionGroup();
    }

    @AfterEach
    public void tearDown() throws Exception {
        rpAccessionGroup = null;
    }

    @Test
    public void testConstructor() throws Exception {
        RPExpressionByEnrollmentCategory rpExpressionByEnrollmentCategory = new RPExpressionByEnrollmentCategory();
        RPAccessionGroup rpAccessionGroup1 = new RPAccessionGroup("accession", rpExpressionByEnrollmentCategory);
        assertEquals(rpExpressionByEnrollmentCategory, rpAccessionGroup1.getRpExpressionByEnrollmentCategory());
        assertEquals("accession", rpAccessionGroup1.getAccession());
    }

    @Test
    public void testSetters() throws Exception {
        RPExpressionByEnrollmentCategory rpExpressionByEnrollmentCategory = new RPExpressionByEnrollmentCategory();
        rpAccessionGroup.setAccession("1234");
        rpAccessionGroup.setRpExpressionByEnrollmentCategory(rpExpressionByEnrollmentCategory);
        assertEquals("1234", rpAccessionGroup.getAccession());
        assertEquals(rpExpressionByEnrollmentCategory, rpAccessionGroup.getRpExpressionByEnrollmentCategory());
    }


}
