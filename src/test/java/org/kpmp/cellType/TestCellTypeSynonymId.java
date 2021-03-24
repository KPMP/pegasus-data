package org.kpmp.cellType;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCellTypeSynonymId {

    private CellTypeSynonymId CellTypeSynonymId;

    @BeforeEach
    void setUp() {
        CellTypeSynonymId = new CellTypeSynonymId();
    }

    @AfterEach
    void tearDown() {
        CellTypeSynonymId = null;
    }

    @Test
    void testSetCellTypeId() {
        CellTypeSynonymId.setCellTypeId(23);
        assertEquals(23, CellTypeSynonymId.getCellTypeId());
    }

    @Test
    void testSetCellTypeSynonym() {
        CellTypeSynonymId.setCellTypeSynonym("synonym");
        assertEquals("synonym", CellTypeSynonymId.getCellTypeSynonym());
    }
}
