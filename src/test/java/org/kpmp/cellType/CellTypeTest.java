package org.kpmp.cellType;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellTypeTest {

    private CellType cellType;

    @BeforeEach
    void setUp() {
        cellType = new CellType();
    }

    @AfterEach
    void tearDown() {
        cellType = null;
    }

    @Test
    void setCellTypeId() {
        cellType.setCellTypeId(5);
        assertEquals(5, cellType.getCellTypeId());
    }

    @Test
    void setStructureRegion() {
        cellType.setStructureRegion("region");
        assertEquals("region", cellType.getStructureRegion());
    }

    @Test
    void setStructureSubregion() {
        cellType.setStructureSubregion("subregion");
        assertEquals("subregion", cellType.getStructureSubregion());
    }

    @Test
    void setCellType() {
        cellType.setCellType("type");
        assertEquals("type", cellType.getCellType());
    }

    @Test
    void setAbbreviation() {
        cellType.setAbbreviation("ABBREV");
        assertEquals("ABBREV", cellType.getAbbreviation());
    }

    @Test
    void setReleaseVer() {
        cellType.setReleaseVer(2.0);
        assertEquals((Double)2.0, cellType.getReleaseVer());
    }

    @Test
    void setReleaseSunset() {
        cellType.setReleaseSunset(2.0);
        assertEquals((Double)2.0, cellType.getReleaseSunset());
    }
}