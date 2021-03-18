package org.kpmp;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kpmp.autocomplete.AutocompleteResult;
import org.kpmp.autocomplete.AutocompleteService;
import org.kpmp.cellType.CellTypeHierarchy;
import org.kpmp.cellType.CellTypeService;
import org.kpmp.gene.GeneService;
import org.kpmp.gene.MyGeneInfoHit;
import org.kpmp.umap.UmapDataService;
import org.kpmp.umap.UmapPoint;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class QueryTest {

	@Mock
	private CellTypeService cellTypeService;
	@Mock
	private AutocompleteService autocompleteService;
	@Mock
	private GeneService geneService;
	private Query query;
	@Mock
	private UmapDataService umapDataService;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		query = new Query(geneService, autocompleteService, cellTypeService, umapDataService);
	}

	@After
	public void tearDown() throws Exception {
		query = null;
	}

	@Test
	public void testGenes() throws Exception {
		List<MyGeneInfoHit> expectedResult = Arrays.asList(new MyGeneInfoHit());
		when(geneService.querySymbolAndAlias("query")).thenReturn(expectedResult);

		assertEquals(expectedResult, query.genes("query"));
	}

	@Test
	public void testAutocomplete() throws Exception {
		List<AutocompleteResult> expectedResults = Arrays.asList(new AutocompleteResult());
		when(autocompleteService.query("autocomplete")).thenReturn(expectedResults);

		assertEquals(expectedResults, query.autocomplete("autocomplete"));
	}

	@Test
	public void testGetCellTypeHierarchy() throws Exception {
		CellTypeHierarchy expectedResult = new CellTypeHierarchy();
		when(cellTypeService.getCellTypeHierarchy()).thenReturn(expectedResult);

		assertEquals(expectedResult, query.getCellTypeHierarchy());
	}

	@Test
	public void testGetUmapPoints() throws Exception {
		List<UmapPoint> expectedList = Arrays.asList(new UmapPoint());
		when(umapDataService.getUmapPoints("data type")).thenReturn(expectedList);

		assertEquals(expectedList, query.getUmapPoints("data type"));
		verify(umapDataService).getUmapPoints("data type");
		verify(umapDataService, times(0)).getUmapPoints();
	}

	@Test
	public void testGetUmapPointsWhenNullDataType() throws Exception {
		List<UmapPoint> expectedList = Arrays.asList(new UmapPoint());
		when(umapDataService.getUmapPoints()).thenReturn(expectedList);

		assertEquals(expectedList, query.getUmapPoints(null));
		verify(umapDataService, times(0)).getUmapPoints(any(String.class));
		verify(umapDataService).getUmapPoints();
	}

}
