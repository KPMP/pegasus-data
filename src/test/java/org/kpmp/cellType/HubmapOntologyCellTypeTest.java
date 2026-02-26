package org.kpmp.cellType;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HubmapOntologyCellTypeTest {
    @Test
    void testGettersAndSetters() {
        HubmapOntologyCellType cellType = new HubmapOntologyCellType();
        cellType.setHubmapOntologyId("HUBMAP:123");
        cellType.setCell_type("Podocyte");

        assertEquals("HUBMAP:123", cellType.getHubmapOntologyId());
        assertEquals("Podocyte", cellType.getCell_type());
    }

    @Test
    void testDefaultValues() {
        HubmapOntologyCellType cellType = new HubmapOntologyCellType();
        assertNull(cellType.getHubmapOntologyId());
        assertNull(cellType.getCell_type());
    }
}
