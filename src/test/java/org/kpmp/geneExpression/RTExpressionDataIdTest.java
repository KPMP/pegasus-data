package org.kpmp.geneExpression;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.Test;

class RTExpressionDataIdTest {

    @Test
    void testGettersAndSetters() {
        RTExpressionDataId idData = new RTExpressionDataId();

        idData.setId(101);
        idData.setGeneSymbol("SOD1");
        idData.setPVal(0.05);

        assertEquals(101, idData.getId());
        assertEquals("SOD1", idData.getGeneSymbol());
        assertEquals(0.05, idData.getPVal());
    }

    @Test
    void testConstructor() {
        RTExpressionDataId idData = new RTExpressionDataId(202, "GAPDH", 0.001);

        assertEquals(202, idData.getId());
        assertEquals("GAPDH", idData.getGeneSymbol());
        assertEquals(0.001, idData.getPVal());
    }

    @Test
    void testEqualsAndHashCode() {
        RTExpressionDataId id1 = new RTExpressionDataId(1, "ALB", 0.01);
        RTExpressionDataId id2 = new RTExpressionDataId(1, "ALB", 0.01);
        RTExpressionDataId id3 = new RTExpressionDataId(2, "ALB", 0.01);
        RTExpressionDataId id4 = new RTExpressionDataId(1, "INS", 0.01);

        assertEquals(id1, id2);
        assertNotEquals(id1, id3);
        assertNotEquals(id1, id4);
        assertNotEquals(null, id1);

        assertEquals(id1.hashCode(), id2.hashCode());
        assertNotEquals(id1.hashCode(), id3.hashCode());
    }
}
