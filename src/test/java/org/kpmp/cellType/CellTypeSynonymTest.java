package org.kpmp.cellType;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CellTypeSynonymTest {

    private CellTypeSynonym cellTypeSynonym;

    @BeforeEach
    void setUp() {
        cellTypeSynonym = new CellTypeSynonym();
    }

    @AfterEach
    void tearDown() {
        cellTypeSynonym = null;
    }

    @Test
    void testSetCellTypeId() {
        cellTypeSynonym.setCellTypeId(23);
        assertEquals(23, cellTypeSynonym.getCellTypeId());
    }

    @Test
    void testSetCellTypeSynonym() {
        cellTypeSynonym.setCellTypeSynonym("synonym");
        assertEquals("synonym", cellTypeSynonym.getCellTypeSynonym());
    }

}