package org.kpmp.cellType;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HubmapOntologyCellTypeTest {
    @Test
    void testGettersAndSetters() {
        HubmapOntologyCellType cellType = new HubmapOntologyCellType();
        cellType.setHubmapOntologyId("HUBMAP:123");
        cellType.setCellType("Podocyte");

        assertEquals("HUBMAP:123", cellType.getHubmapOntologyId());
        assertEquals("Podocyte", cellType.getCellType());
    }

    @Test
    void testDefaultValues() {
        HubmapOntologyCellType cellType = new HubmapOntologyCellType();
        assertNull(cellType.getHubmapOntologyId());
        assertNull(cellType.getCellType());
    }
}
