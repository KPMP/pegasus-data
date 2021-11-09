package org.kpmp.autocomplete;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kpmp.cellType.CellType;
import org.kpmp.cellType.CellTypeRepository;
import org.kpmp.cellType.CellTypeSynonym;
import org.kpmp.gene.GeneService;
import org.kpmp.gene.MyGeneInfoHit;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
	void query() throws IOException, Exception {
		MyGeneInfoHit hit = new MyGeneInfoHit();
		hit.setSymbol("symbol");
		List<String> gAliases = new ArrayList<String>();
		gAliases.add("gAlias");
		hit.setAlias(gAliases);
		hit.setId("gId");
		List<MyGeneInfoHit> hits = new ArrayList<MyGeneInfoHit>();
		hits.add(hit);
		CellType ct = new CellType();
		ct.setCellType("cellType");
		ct.setCellTypeId(5);
		CellTypeSynonym cellTypeSynonym = new CellTypeSynonym();
		cellTypeSynonym.setCellTypeSynonym("cts");
		Set<CellTypeSynonym> cellTypeSynonymSet = new HashSet<>();
		cellTypeSynonymSet.add(cellTypeSynonym);
		ct.setSynonyms(cellTypeSynonymSet);
		List<CellType> cellTypes = new ArrayList<CellType>();
		cellTypes.add(ct);
		when(geneService.querySymbolAndAlias("searchTerm")).thenReturn(hits);
		when(cellTypeRepository.findByCellTypeContainingOrSynonymContaining("searchTerm")).thenReturn(cellTypes);
		List<AutocompleteResult> results = autocompleteService.query("searchTerm");
		assertEquals(2, results.size());
		AutocompleteResult result1 = results.get(0);
		AutocompleteResult result2 = results.get(1);
		assertEquals(gAliases, result1.getAliases());
		assertEquals("cts", result2.getAliases().get(0));
		assertEquals("symbol", result1.getValue());
		assertEquals("cellType", result2.getValue());
		assertEquals("gId", result1.getId());
		assertEquals("5", result2.getId());
		assertEquals("cell_type", result2.getType());
		assertEquals("gene", result1.getType());
	}

	@Test
	void testConvertCellTypesToAutocompleteResultsRemovesDuplicatesForCellTypeResults() throws Exception {
		CellType ct1 = new CellType();
		CellTypeSynonym cellTypeSynonym2 = new CellTypeSynonym();
		cellTypeSynonym2.setCellTypeSynonym("cts");
		ct1.setCellType("same name");
		Set<CellTypeSynonym> cellTypeSynonymSet2 = new HashSet<>();
		cellTypeSynonymSet2.add(cellTypeSynonym2);
		ct1.setSynonyms(cellTypeSynonymSet2);
		CellType ct2 = new CellType();
		ct2.setCellType("same name");
		ct2.setSynonyms(cellTypeSynonymSet2);
		List<CellType> cellTypes = new ArrayList<>();
		cellTypes.add(ct1);
		cellTypes.add(ct2);

		assertEquals(ct1, ct2);
		List<AutocompleteResult> autocompleteResults = autocompleteService
				.convertCellTypesToAutocompleteResults(cellTypes, null, null);
		assertEquals(1, autocompleteResults.size());
	}

	@Test
	void testConvertCellTypesToAutocompleteResultsRemovesDuplicatesForStructureRegionResults() throws Exception {
		CellType ct1 = new CellType();
		ct1.setStructureRegion("same name");
		CellType ct2 = new CellType();
		ct2.setStructureRegion("same name");
		List<CellType> regions = new ArrayList<>();
		regions.add(ct1);
		regions.add(ct2);

		assertEquals(ct1, ct2);
		List<AutocompleteResult> autocompleteResults = autocompleteService.convertCellTypesToAutocompleteResults(null,
				regions, null);
		assertEquals(1, autocompleteResults.size());
	}

	@Test
	void testConvertCellTypesToAutocompleteResultsRemovesDuplicatesForStructureSubregionResults() throws Exception {
		CellType ct1 = new CellType();
		ct1.setStructureSubregion("same name");
		CellType ct2 = new CellType();
		ct2.setStructureSubregion("same name");
		List<CellType> subregions = new ArrayList<>();
		subregions.add(ct1);
		subregions.add(ct2);

		assertEquals(ct1, ct2);
		List<AutocompleteResult> autocompleteResults = autocompleteService.convertCellTypesToAutocompleteResults(null,
				null, subregions);
		assertEquals(1, autocompleteResults.size());
	}

}