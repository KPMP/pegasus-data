package org.kpmp;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kpmp.atlasMessage.AtlasMessage;
import org.kpmp.atlasMessage.AtlasMessageService;
import org.kpmp.autocomplete.AutocompleteResult;
import org.kpmp.autocomplete.AutocompleteService;
import org.kpmp.cellType.CellTypeHierarchy;
import org.kpmp.cellType.CellTypeService;
import org.kpmp.cellTypeSummary.ClusterHierarchy;
import org.kpmp.cellTypeSummary.ClusterHierarchyService;
import org.kpmp.dataSummary.AtlasRepoSummaryResult;
import org.kpmp.dataSummary.DataSummaryService;
import org.kpmp.dataSummary.DataTypeSummary;
import org.kpmp.geneExpression.RPExpressionByEnrollmentCategory;
import org.kpmp.geneExpression.RPExpressionDataService;
import org.kpmp.geneExpression.RTExpressionByEnrollmentCategory;
import org.kpmp.geneExpression.RTExpressionDataAllSegments;
import org.kpmp.geneExpression.RTExpressionDataService;
import org.kpmp.geneExpressionSummary.GeneExpressionSummaryService;
import org.kpmp.geneExpressionSummary.singleCell.SCRNAGeneExpressionExpressionSummaryValue;
import org.kpmp.geneExpressionSummary.singleNucleus.SNRNAGeneExpressionExpressionSummaryValue;
import org.kpmp.participant.ParticipantClinicalDataset;
import org.kpmp.participant.ParticipantDataTypeSummary;
import org.kpmp.participant.ParticipantRepoDataTypeInformation;
import org.kpmp.participant.ParticipantRepoDataTypeSummary;
import org.kpmp.participant.ParticipantService;
import org.kpmp.participant.ParticipantSummaryDataset;
import org.kpmp.participant.ParticipantEnrollmentCategorySummary;
import org.kpmp.umap.FeatureData;
import org.kpmp.umap.PlotData;
import org.kpmp.umap.ReferenceCluster;
import org.kpmp.umap.UmapDataService;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class QueryControllerTest {

	@Mock
	private CellTypeService cellTypeService;
	@Mock
	private AutocompleteService autocompleteService;
	@Mock
	private GeneExpressionSummaryService geneExpressionService;
	@Mock
	private DataSummaryService dataSummaryService;
	private QueryController query;
	@Mock
	private UmapDataService umapDataService;
	@Mock
	private ClusterHierarchyService clusterHierarchyService;
	@Mock
	private RTExpressionDataService rtExpressionDataService;
	@Mock
	private ParticipantService participantService;
	@Mock
	private ParticipantEnrollmentCategorySummary participantEnrollmentCategorySummary;
    @Mock
    private AtlasMessageService atlasMessageService;

	@Mock
	private RPExpressionDataService rpExpressionDataService;

	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		query = new QueryController(autocompleteService, cellTypeService, umapDataService, geneExpressionService,
				dataSummaryService, clusterHierarchyService, rtExpressionDataService, rpExpressionDataService, 
        participantService, atlasMessageService);
	}

	@AfterEach
	public void tearDown() throws Exception {
		MockitoAnnotations.openMocks(this).close();
		query = null;
	}

	@Test
	public void testGetAtlasSummaryRows() throws Exception {
		AtlasRepoSummaryResult expectedResult = mock(AtlasRepoSummaryResult.class);
		when(dataSummaryService.getAtlasRepoSummary()).thenReturn(expectedResult);

		assertEquals(expectedResult, query.getAtlasSummaryRows());
	}

	@Test
	public void testGetAtlasSummaryRows_throwsException() throws Exception {
		when(dataSummaryService.getAtlasRepoSummary()).thenThrow(new Exception("ack"));

		try {
			query.getAtlasSummaryRows();
			fail("Should have thrown exception");
		} catch (Exception e) {
			assertEquals("ack", e.getMessage());
		}
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

		assertEquals(expectedResult, query.cellTypeHierarchy());
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void geneExpression() throws Exception {
		List expectedResultSN1 = Arrays.asList(new SNRNAGeneExpressionExpressionSummaryValue());
		List expectedResultSN2 = Arrays.asList(new SNRNAGeneExpressionExpressionSummaryValue());
		when(geneExpressionService.getByDataTypeEnrollmentCategoryAndGene("sn", "gene", "aki")).thenReturn(expectedResultSN1);
		when(geneExpressionService.getExpressionSummaryPerGeneByCellTypeAndEnrollmentCategory("sn", "cell type", "aki"))
				.thenReturn(expectedResultSN2);

		List expectedResultSC1 = Arrays.asList(new SCRNAGeneExpressionExpressionSummaryValue());
		List expectedResultSC2 = Arrays.asList(new SCRNAGeneExpressionExpressionSummaryValue());
		when(geneExpressionService.getByDataTypeEnrollmentCategoryAndGene("sc", "gene", "aki")).thenReturn(expectedResultSC1);
		when(geneExpressionService.getExpressionSummaryPerGeneByCellTypeAndEnrollmentCategory("sc", "cell type", "aki"))
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
		List<DataTypeSummary> expectedResult = new ArrayList<>();

		expectedResult.add(new DataTypeSummary(OmicsTypeEnum.TRANSCRIPTOMICS.getEnum(),
				FullDataTypeEnum.SINGLE_CELL_FULL.getFullName(), DataTypeEnum.SINGLE_CELL.getAbbreviation(),
				Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0)));
		expectedResult.add(new DataTypeSummary(OmicsTypeEnum.NONE.getEnum(),
				FullDataTypeEnum.SINGLE_NUCLEUS_FULL.getFullName(), DataTypeEnum.SINGLE_NUCLEUS.getAbbreviation(),
				Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0)));
		when(geneExpressionService.getDataTypeSummaryInformation()).thenReturn(expectedResult);

		List<DataTypeSummary> datasetSummary = query.getDataTypeSummaryInformation();

		assertEquals(expectedResult, datasetSummary);
	}

	@Test
	public void testGetRTGeneExpression() throws Exception {
		List<RTExpressionDataAllSegments> data = Arrays.asList(new RTExpressionDataAllSegments());
		RTExpressionByEnrollmentCategory rtExpressionByEnrollmentCategory = new RTExpressionByEnrollmentCategory();
		rtExpressionByEnrollmentCategory.setAki(data);
		when(rtExpressionDataService.getByComparisonTypeAndGeneSymbolPerEnrollment("all_segments", "gene"))
				.thenReturn(rtExpressionByEnrollmentCategory);
		assertEquals(rtExpressionByEnrollmentCategory, query.getRTGeneExpressionByEnrollment("all_segments", "gene"));
		assertEquals(rtExpressionByEnrollmentCategory.getAki(), data);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testGetRTGeneExpressionByStructure() throws Exception {
		List data = Arrays.asList(new RTExpressionDataAllSegments());
		when(rtExpressionDataService.getByStructure("tubulers")).thenReturn(data);
		assertEquals(data, query.getRTGeneExpressionByStructure("tubulers"));
	}

	@Test
	public void testGetRPGeneExpressionByEnrollmentAndProtein() throws Exception {
		RPExpressionByEnrollmentCategory expected = new RPExpressionByEnrollmentCategory();
		when(rpExpressionDataService.getByGeneSymbolAndProteinPerEnrollment("APOL1", "steak")).thenReturn(expected);

		RPExpressionByEnrollmentCategory result = query.getRPGeneExpressionByEnrollmentAndProtein("APOL1", "steak");

		assertEquals(expected, result);
	}

	@Test
	public void testGetDataTypeInformationByParticipant() throws Exception {
		ParticipantDataTypeSummary expected = mock(ParticipantDataTypeSummary.class);
		when(participantService.getExperimentCounts("123")).thenReturn(expected);

		ParticipantDataTypeSummary result = query.getDataTypeInformationByParticipant("123");

		assertEquals(expected, result);

	}

	@Test
	public void testGetRepoDataTypeInformationByParticipant() throws Exception {
		ParticipantRepoDataTypeSummary expected = mock(ParticipantRepoDataTypeSummary.class);
		when(participantService.getDataTypeCounts("123")).thenReturn(expected);

		ParticipantRepoDataTypeSummary result = query.getRepoDataTypeInformationByParticipant("123");

		assertEquals(expected, result);

	}

    @Test
    public void testGetParticipantClincialDataset() throws Exception {
        ParticipantClinicalDataset expected = new ParticipantClinicalDataset();
		when(participantService.getParticipantClinicalDataset("participant_id")).thenReturn(expected);

		assertEquals(expected, query.getParticipantClinicalDataset("participant_id"));
		verify(participantService).getParticipantClinicalDataset("participant_id");
    }

    @Test
	public void testParticipantSummaryDataset() throws Exception {
		ParticipantSummaryDataset expected = new ParticipantSummaryDataset();
		when(participantService.getParticipantSummaryDataset("participant_id")).thenReturn(expected);

		assertEquals(expected, query.participantSummaryDataset("participant_id"));
		verify(participantService).getParticipantSummaryDataset("participant_id");
	}

    @Test
	public void getParticipantEnrollmentCategorySummary() throws Exception {
		List<ParticipantEnrollmentCategorySummary> expectedResult = new ArrayList<>();

		expectedResult.add(
				new ParticipantEnrollmentCategorySummary(Long.valueOf(4), Long.valueOf(5), Long.valueOf(6), Long.valueOf(7)));

		List<ParticipantEnrollmentCategorySummary> enrollmentSummary = query.getEnrollmentCategorySummaryData();

		assertEquals(expectedResult, enrollmentSummary);
	}

    @Test
    public void testGetAtlasMessage() throws Exception {
        AtlasMessage atlasMessage = new AtlasMessage();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String endDateString = "2023-12-25";
        String startDateString = "2023-12-01";
        Date endDate = dateFormat.parse(endDateString);
        Date startDate = dateFormat.parse(startDateString);
        atlasMessage.setId(0);
        atlasMessage.setApplication("Explorer");
        atlasMessage.setEndDate(endDate);
        atlasMessage.setStartDate(startDate);
        atlasMessage.setMessage("THE END IS NEAR");
        List<AtlasMessage> expectedResult = Arrays.asList(new AtlasMessage());
        when(atlasMessageService.getAtlasMessage()).thenReturn(expectedResult);
        assertEquals(expectedResult, query.getAtlasMessages());
    }

	@Test
	public void testGetExperimentalStrategyCountsByParticipant() {
		List<ParticipantRepoDataTypeInformation> expectedResults = new ArrayList<>();
		when(participantService.getExperimentalStrategyCountsByParticipant("redcapId")).thenReturn(expectedResults);

		List<ParticipantRepoDataTypeInformation> result = query.getExperimentalStrategyCountsByParticipant("redcapId");

		assertEquals(expectedResults, result);
	}
}