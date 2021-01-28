package org.kpmp.autoComplete;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kpmp.autocomplete.AutocompleteResult;
import org.kpmp.autocomplete.AutocompleteService;
import org.kpmp.cellType.CellType;
import org.kpmp.cellType.CellTypeRepository;
import org.kpmp.gene.GeneService;
import org.kpmp.gene.MyGeneInfoHit;
import org.kpmp.gene.MyGeneInfoResult;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AutocompleteServiceTest {

    private AutocompleteService autocompleteService;

    @Mock
    private GeneService geneService;

    @Mock
    private CellTypeRepository cellTypeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        autocompleteService = new AutocompleteService(geneService, cellTypeRepository);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void query() throws IOException {
        MyGeneInfoResult mgResult = new MyGeneInfoResult();
        MyGeneInfoHit hit = new MyGeneInfoHit();
        hit.setSymbol("symbol");
        List<String> gAliases = new ArrayList<String>();
        gAliases.add("gAlias");
        hit.setAlias(gAliases);
        hit.setId("gId");
        List<MyGeneInfoHit> hits = new ArrayList<MyGeneInfoHit>();
        hits.add(hit);
        mgResult.setHits(hits);
        CellType ct = new CellType();
        ct.setCellType("cellType");
        ct.setCellTypeId(5);
        List<CellType> cellTypes = new ArrayList<CellType>();
        cellTypes.add(ct);
        when(geneService.query("searchTerm")).thenReturn(mgResult);
        when(cellTypeRepository.findByCellTypeContaining("searchTerm")).thenReturn(cellTypes);
        List<AutocompleteResult> results = autocompleteService.query("searchTerm");
        assertEquals(2, results.size());
        AutocompleteResult result1 = results.get(0);
        AutocompleteResult result2 = results.get(1);
        assertEquals(gAliases, result1.getAliases());
        assertNull(result2.getAliases());
        assertEquals("symbol", result1.getValue());
        assertEquals("cellType", result2.getValue());
        assertEquals("gId", result1.getId());
        assertEquals("5", result2.getId());
        assertEquals("cell_type", result2.getType());
        assertEquals("gene", result1.getType());
    }

}