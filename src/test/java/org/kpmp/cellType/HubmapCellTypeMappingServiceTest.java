package org.kpmp.cellType;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HubmapCellTypeMappingServiceTest {
    private HubmapOnotologyCellTypeRepository repo;
    private HubmapCellTypeMappingService service;

    @BeforeEach
    void setUp() {
        repo = mock(HubmapOnotologyCellTypeRepository.class);
        service = new HubmapCellTypeMappingService(repo);
    }

    @Test
    void testBuildHubmapIdToCellTypeMap_returnsCorrectList() {
        HubmapOntologyCellType cellType1 = mock(HubmapOntologyCellType.class);
        when(cellType1.getHubmapOntologyId()).thenReturn("HUBMAP:001");
        when(cellType1.getCellType()).thenReturn("Podocyte");

        HubmapOntologyCellType cellType2 = mock(HubmapOntologyCellType.class);
        when(cellType2.getHubmapOntologyId()).thenReturn("HUBMAP:002");
        when(cellType2.getCellType()).thenReturn("Tubule cell");

        List<HubmapOntologyCellType> cellTypes = Arrays.asList(cellType1, cellType2);
        when(repo.findAll()).thenReturn(cellTypes);

        List<HubmapOntologyCellType> result = service.buildHubmapIdToCellTypeMap();
        assertEquals(2, result.size());
        assertTrue(result.contains(cellType1));
        assertTrue(result.contains(cellType2));
    }

    @Test
    void testBuildHubmapIdToCellTypeMap_emptyListReturnsEmptyList() {
        when(repo.findAll()).thenReturn(Collections.emptyList());
        List<HubmapOntologyCellType> result = service.buildHubmapIdToCellTypeMap();
        assertTrue(result.isEmpty());
    }
}
