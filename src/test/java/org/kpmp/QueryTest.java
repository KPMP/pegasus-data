package org.kpmp;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
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
import org.kpmp.dataSummary.DataSummaryService;
import org.kpmp.gene.GeneService;
import org.kpmp.gene.MyGeneInfoHit;
import org.kpmp.geneExpression.RTExpressionByTissueType;
import org.kpmp.geneExpression.RTExpressionDataAllSegments;
import org.kpmp.geneExpression.RTExpressionDataService;
import org.kpmp.geneExpressionSummary.GeneExpressionSummaryService;
import org.kpmp.geneExpressionSummary.SCRNAGeneExpressionExpressionSummaryValue;
import org.kpmp.geneExpressionSummary.SNRNAGeneExpressionExpressionSummaryValue;
import org.kpmp.participant.ParticipantDataTypeSummary;
import org.kpmp.participant.ParticipantTissueTypeSummary;
import org.kpmp.repositorySummary.TissueTypeSummaryByDataType;
import org.kpmp.participant.ParticipantService;
import org.kpmp.participant.ParticipantSummaryDataset;
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
	@Mock
	private DataSummaryService dataSummaryService;
	private Query query;
	@Mock
	private UmapDataService umapDataService;
	@Mock
	private ClusterHierarchyService clusterHierarchyService;
	@Mock
	private RTExpressionDataService rtExpressionDataService;
	@Mock
	private ParticipantService participantService;
	@Mock
	private ParticipantTissueTypeSummary participantTissueTypeSummary;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		query = new Query(geneService, autocompleteService, cellTypeService, umapDataService, geneExpressionService,
				dataSummaryService, clusterHierarchyService, rtExpressionDataService, participantService);
	}

	@After
	public void tearDown() throws Exception {
		MockitoAnnotations.openMocks(this).close();
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
		List<TissueTypeSummaryByDataType> expectedResult = new ArrayList<>();

		expectedResult.add(new TissueTypeSummaryByDataType(OmicsTypeEnum.TRANSCRIPTOMICS.getEnum(),
				FullDataTypeEnum.SINGLE_CELL_FULL.getFullName(), DataTypeEnum.SINGLE_CELL.getAbbreviation(),
				Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0)));
		expectedResult.add(new TissueTypeSummaryByDataType(OmicsTypeEnum.NONE.getEnum(),
				FullDataTypeEnum.SINGLE_NUCLEUS_FULL.getFullName(), DataTypeEnum.SINGLE_NUCLEUS.getAbbreviation(),
				Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0)));
		when(geneExpressionService.getGeneDatasetInformation("AAA")).thenReturn(expectedResult);

		List<TissueTypeSummaryByDataType> datasetSummary = query.getGeneDatasetInformation("AAA");

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
	public void testGetDataTypeInformationByParticipant() throws Exception {
		ParticipantDataTypeSummary expected = mock(ParticipantDataTypeSummary.class);
		when(participantService.getExperimentCounts("123")).thenReturn(expected);

		ParticipantDataTypeSummary result = query.getDataTypeInformationByParticipant("123");

		assertEquals(expected, result);

	}

	public void testParticipantSummaryDataset() throws Exception {
		ParticipantSummaryDataset expected = new ParticipantSummaryDataset();
		when(participantService.getParticipantSummaryDataset("participant_id")).thenReturn(expected);

		assertEquals(expected, query.participantSummaryDataset("participant_id"));
		verify(participantService).getParticipantSummaryDataset("participant_id");
	}

	public void testParticipantClinicalDataset() throws Exception {
		ParticipantSummaryDataset expected = new ParticipantSummaryDataset();
		when(participantService.getParticipantSummaryDataset("participant_id")).thenReturn(expected);

		assertEquals(expected, query.participantClinicalDataset("participant_id"));
	}
	
	public void getParticipantTissueTypeSummary() throws Exception {
		List<ParticipantTissueTypeSummary> expectedResult = new ArrayList<>();

		expectedResult.add(new ParticipantTissueTypeSummary(Long.valueOf(4), Long.valueOf(5), Long.valueOf(6)));
		
		List<ParticipantTissueTypeSummary> tissueSummary = query.getTissueTypeSummaryData();

		assertEquals(expectedResult, tissueSummary);
	}
}