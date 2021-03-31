package org.kpmp;

import static org.junit.Assert.assertEquals;
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
import org.kpmp.cellTypeSummary.ClusterHierarchy;
import org.kpmp.cellTypeSummary.ClusterHierarchyService;
import org.kpmp.gene.GeneService;
import org.kpmp.gene.MyGeneInfoHit;
import org.kpmp.geneExpressionSummary.GeneExpressionSummaryService;
import org.kpmp.geneExpressionSummary.SNRNAGeneExpressionSummartValue;
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
	@Mock
	private GeneExpressionSummaryService geneExpressionService;
	private Query query;
	@Mock
	private UmapDataService umapDataService;
	@Mock
	private ClusterHierarchyService clusterHierarchyService;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		query = new Query(geneService, autocompleteService, cellTypeService, umapDataService, geneExpressionService,
				clusterHierarchyService);
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void geneExpression() throws Exception {
		List expectedResult = Arrays.asList(new SNRNAGeneExpressionSummartValue());
		when(geneExpressionService.getByDataTypeTissueTypeAndGene("sn", "gene", "aki")).thenReturn(expectedResult);
		assertEquals(expectedResult, query.expressionSummaryPerClusterByGene("sn", "gene", "aki"));
	}

	public void testGetUmapPoints() throws Exception {
		List<UmapPoint> expectedList = Arrays.asList(new UmapPoint());
		when(umapDataService.getUmapPoints("data type", "geneSymbol")).thenReturn(expectedList);

		assertEquals(expectedList, query.getUmapPoints("data type", "geneSymbol"));
		verify(umapDataService).getUmapPoints("data type", "geneSymbol");
	}

	@Test
	public void testGetClusterHierarchies() throws Exception {
		List<ClusterHierarchy> expectedList = Arrays.asList(new ClusterHierarchy());
		when(clusterHierarchyService.findClustersByCellType("cell type")).thenReturn(expectedList);

		assertEquals(expectedList, query.getClusterHieararchies("cell type"));
		verify(clusterHierarchyService).findClustersByCellType("cell type");
	}
}
