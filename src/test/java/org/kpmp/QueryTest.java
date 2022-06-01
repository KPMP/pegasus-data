package org.kpmp;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
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
import org.kpmp.datasetSummary.DatasetSummary;
import org.kpmp.datasetSummary.DatasetSummaryService;
import org.kpmp.gene.GeneService;
import org.kpmp.gene.MyGeneInfoHit;
import org.kpmp.geneExpression.RTExpressionByTissueType;
import org.kpmp.geneExpression.RTExpressionDataAllSegments;
import org.kpmp.geneExpression.RTExpressionDataService;
import org.kpmp.geneExpressionSummary.GeneExpressionSummaryService;
import org.kpmp.geneExpressionSummary.SCRNAGeneExpressionExpressionSummaryValue;
import org.kpmp.geneExpressionSummary.SNRNAGeneExpressionExpressionSummaryValue;
import org.kpmp.umap.FeatureData;
import org.kpmp.umap.PlotData;
import org.kpmp.umap.ReferenceCluster;
import org.kpmp.umap.UmapDataService;
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
	@Mock
	private RTExpressionDataService rtExpressionDataService;
	@Mock
	private DatasetSummaryService datasetSummaryService;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		query = new Query(geneService, autocompleteService, cellTypeService, umapDataService, geneExpressionService,
				clusterHierarchyService, rtExpressionDataService, datasetSummaryService);
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
		List expectedResultSN1 = Arrays.asList(new SNRNAGeneExpressionExpressionSummaryValue());
		List expectedResultSN2 = Arrays.asList(new SNRNAGeneExpressionExpressionSummaryValue());
		when(geneExpressionService.getByDataTypeTissueTypeAndGene("sn", "gene", "aki")).thenReturn(expectedResultSN1);
		when(geneExpressionService.getExpressionSummaryPerGeneByCellTypeAndTissueType("sn", "cell type", "aki"))
				.thenReturn(expectedResultSN2);

		List expectedResultSC1 = Arrays.asList(new SCRNAGeneExpressionExpressionSummaryValue());
		List expectedResultSC2 = Arrays.asList(new SCRNAGeneExpressionExpressionSummaryValue());
		when(geneExpressionService.getByDataTypeTissueTypeAndGene("sc", "gene", "aki")).thenReturn(expectedResultSC1);
		when(geneExpressionService.getExpressionSummaryPerGeneByCellTypeAndTissueType("sc", "cell type", "aki"))
				.thenReturn(expectedResultSC2);

		assertEquals(expectedResultSN1, query.geneExpressionSummary("sn", "gene", "", "aki"));
		assertEquals(expectedResultSC1, query.geneExpressionSummary("sc", "gene", "", "aki"));

		assertEquals(expectedResultSN2, query.geneExpressionSummary("sn", "", "cell type", "aki"));
		assertEquals(expectedResultSC2, query.geneExpressionSummary("sc", "", "cell type", "aki"));
	}

	@Test
	public void testGetUmapPlotData() throws Exception {
		List<FeatureData> featureData = new ArrayList<>();
		List<ReferenceCluster> referenceData = new ArrayList<>();
		PlotData expectedPlotData = new PlotData(referenceData, featureData);
		when(umapDataService.getPlotData("sn", "gene", "all")).thenReturn(expectedPlotData);

		PlotData umapPlotData = query.getUmapPlotData("sn", "gene", "all");

		assertEquals(expectedPlotData, umapPlotData);
		verify(umapDataService).getPlotData("sn", "gene", "all");
	}

	@Test
	public void testGetClusterHierarchies() throws Exception {
		List<ClusterHierarchy> expectedList = Arrays.asList(new ClusterHierarchy());
		when(clusterHierarchyService.findClustersByCellType("cell type")).thenReturn(expectedList);

		assertEquals(expectedList, query.getClusterHieararchies("cell type"));
		verify(clusterHierarchyService).findClustersByCellType("cell type");
	}

	@Test
	public void dataTypesForConceptWhenGeneSymbolAndNullClusterName() throws Exception {
		List<String> expectedResult = Arrays.asList("1", "2");
		when(geneExpressionService.findDataTypesByGene("gene")).thenReturn(expectedResult);

		List<String> dataTypesForConcept = query.dataTypesForConcept("gene", null);

		assertEquals(expectedResult, dataTypesForConcept);
		verify(clusterHierarchyService, times(0)).findDataTypesByClusterName(any(String.class));
	}

	@Test
	public void dataTypesForConceptWhenGeneSymbolAndBlankClusterName() throws Exception {
		List<String> expectedResult = Arrays.asList("1", "2");
		when(geneExpressionService.findDataTypesByGene("gene")).thenReturn(expectedResult);

		List<String> dataTypesForConcept = query.dataTypesForConcept("gene", "");

		assertEquals(expectedResult, dataTypesForConcept);
		verify(clusterHierarchyService, times(0)).findDataTypesByClusterName(any(String.class));
	}

	@Test
	public void dataTypesForConceptWhenClusterNameAndNullGene() throws Exception {
		List<String> expectedResult = Arrays.asList("1", "2");
		when(clusterHierarchyService.findDataTypesByClusterName("cluster")).thenReturn(expectedResult);

		List<String> dataTypesForConcept = query.dataTypesForConcept(null, "cluster");

		assertEquals(expectedResult, dataTypesForConcept);
		verify(geneExpressionService, times(0)).findDataTypesByGene(any(String.class));
	}

	@Test
	public void dataTypesForConceptWhenClusterNameAndBlankGene() throws Exception {
		List<String> expectedResult = Arrays.asList("1", "2");
		when(clusterHierarchyService.findDataTypesByClusterName("cluster")).thenReturn(expectedResult);

		List<String> dataTypesForConcept = query.dataTypesForConcept("", "cluster");

		assertEquals(expectedResult, dataTypesForConcept);
		verify(geneExpressionService, times(0)).findDataTypesByGene(any(String.class));
	}

	@Test
	public void getGeneDatasetInformation() throws Exception {
		List<DatasetSummary> expectedResult = new ArrayList<>();

		expectedResult.add(new DatasetSummary(OmicsTypeEnum.TRANSCRIPTOMICS.getEnum(),
				FullDataTypeEnum.SINGLE_CELL_FULL.getFull(), DataTypeEnum.SINGLE_CELL.getAbbreviation(),
				Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0)));
		expectedResult.add(new DatasetSummary(OmicsTypeEnum.NONE.getEnum(),
				FullDataTypeEnum.SINGLE_NUCLEUS_FULL.getFull(), DataTypeEnum.SINGLE_NUCLEUS.getAbbreviation(),
				Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0)));
		when(geneExpressionService.getGeneDatasetInformation("AAA")).thenReturn(expectedResult);

		List<DatasetSummary> datasetSummary = query.getGeneDatasetInformation("AAA");

		assertEquals(expectedResult, datasetSummary);
	}

	@Test
	public void testGetRTGeneExpression() throws Exception {
		List<RTExpressionDataAllSegments> data = Arrays.asList(new RTExpressionDataAllSegments());
		RTExpressionByTissueType rtExpressionByTissueType = new RTExpressionByTissueType();
		rtExpressionByTissueType.setAki(data);
		when(rtExpressionDataService.getByComparisonTypeAndGeneSymbolPerTissue("all_segments", "gene"))
				.thenReturn(rtExpressionByTissueType);
		assertEquals(rtExpressionByTissueType, query.getRTGeneExpressionByTissue("all_segments", "gene"));
		assertEquals(rtExpressionByTissueType.getAki(), data);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testGetRTGeneExpressionByStructure() throws Exception {
		List data = Arrays.asList(new RTExpressionDataAllSegments());
		when(rtExpressionDataService.getByStructure("tubulers")).thenReturn(data);
		assertEquals(data, query.getRTGeneExpressionByStructure("tubulers"));
	}

	@Test
	public void testGetSummaryData() throws Exception {
		List<DatasetSummary> expectedResult = new ArrayList<>();

		expectedResult.add(new DatasetSummary(OmicsTypeEnum.TRANSCRIPTOMICS.getEnum(),
				FullDataTypeEnum.SPATIAL_TRANSCRIPTOMICS_FULL.getFull(), DataTypeEnum.SPATIAL_TRANSCRIPTOMICS.getAbbreviation(),
				Long.valueOf(5), Long.valueOf(15), Long.valueOf(0), Long.valueOf(20)));

		expectedResult.add(new DatasetSummary(OmicsTypeEnum.TRANSCRIPTOMICS.getEnum(),
			FullDataTypeEnum.TISSUE_IMAGING_AND_CYTOMETRY_3D_FULL.getFull(), DataTypeEnum.TISSUE_IMAGING_AND_CYTOMETRY_3D.getAbbreviation(),
			Long.valueOf(5), Long.valueOf(15), Long.valueOf(5), Long.valueOf(25)));

		expectedResult.add(new DatasetSummary(OmicsTypeEnum.TRANSCRIPTOMICS.getEnum(),
			FullDataTypeEnum.LIGHT_MICROSCOPIC_WHOLE_SLIDE_IMAGES_FULL.getFull(), DataTypeEnum.LIGHT_MICROSCOPIC_WHOLE_SLIDE_IMAGES.getAbbreviation(),
			Long.valueOf(5), Long.valueOf(15), Long.valueOf(2), Long.valueOf(22)));
	
		when(datasetSummaryService.getSummaryData()).thenReturn(expectedResult);

		List<DatasetSummary> result = datasetSummaryService.getSummaryData();

		assertEquals(FullDataTypeEnum.SPATIAL_TRANSCRIPTOMICS_FULL.getFull(), result.get(0).getDataType());
		assertEquals(DataTypeEnum.SPATIAL_TRANSCRIPTOMICS.getAbbreviation(), result.get(0).getDataTypeShort());
		assertEquals(Long.valueOf(5), result.get(0).getAkiCount());
		assertEquals(Long.valueOf(15), result.get(0).getCkdCount());
		assertEquals(Long.valueOf(0), result.get(0).getHrtCount());
		assertEquals(Long.valueOf(20), result.get(0).getParticipantCount());

		assertEquals(FullDataTypeEnum.TISSUE_IMAGING_AND_CYTOMETRY_3D_FULL.getFull(), result.get(1).getDataType());
		assertEquals(DataTypeEnum.SPATIAL_TRANSCRIPTOMICS.getAbbreviation(), result.get(0).getDataTypeShort());
		assertEquals(Long.valueOf(5), result.get(1).getAkiCount());
		assertEquals(Long.valueOf(15), result.get(1).getCkdCount());
		assertEquals(Long.valueOf(5), result.get(1).getHrtCount());
		assertEquals(Long.valueOf(25), result.get(1).getParticipantCount());

		assertEquals(FullDataTypeEnum.LIGHT_MICROSCOPIC_WHOLE_SLIDE_IMAGES_FULL.getFull(), result.get(2).getDataType());
		assertEquals(DataTypeEnum.SPATIAL_TRANSCRIPTOMICS.getAbbreviation(), result.get(0).getDataTypeShort());
		assertEquals(Long.valueOf(5), result.get(2).getAkiCount());
		assertEquals(Long.valueOf(15), result.get(2).getCkdCount());
		assertEquals(Long.valueOf(2), result.get(2).getHrtCount());
		assertEquals(Long.valueOf(22), result.get(2).getParticipantCount());
	}
}
