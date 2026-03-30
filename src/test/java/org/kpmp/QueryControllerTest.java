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
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kpmp.atlasMessage.AtlasMessage;
import org.kpmp.atlasMessage.AtlasMessageService;
import org.kpmp.autocomplete.AutocompleteResult;
import org.kpmp.autocomplete.AutocompleteService;
import org.kpmp.cellType.CellTypeService;
import org.kpmp.cellType.HubmapCellTypeMappingService;
import org.kpmp.cellType.HubmapOntologyCellType;
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
import org.kpmp.geneExpressionSummary.GeneExpressionSummaryService2025;
import org.kpmp.geneExpressionSummary.singleCell.SCRNAGeneExpressionExpressionSummaryValue2025;
import org.kpmp.geneExpressionSummary.singleNucleus.SNRNAGeneExpressionExpressionSummaryValue2025;
import org.kpmp.participant.*;
import org.kpmp.umap.FeatureData;
import org.kpmp.umap.PlotData;
import org.kpmp.umap.ReferenceCluster;
import org.kpmp.umap.UmapDataService2025;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class QueryControllerTest {

	@Mock
	private CellTypeService cellTypeService;
	@Mock
	private AutocompleteService autocompleteService;
	@Mock
    private GeneExpressionSummaryService2025 geneExpressionService2025;
	@Mock
	private DataSummaryService dataSummaryService;
	private QueryController query;
	@Mock
	private UmapDataService2025 umapDataService;
	@Mock
	private ClusterHierarchyService clusterHierarchyService;
	@Mock
	private RTExpressionDataService rtExpressionDataService;
	@Mock
	private ParticipantEnrollmentCategorySummary participantEnrollmentCategorySummary;
    @Mock
    private AtlasMessageService atlasMessageService;

	@Mock
	private RPExpressionDataService rpExpressionDataService;

    @Mock
    private UmapDataService2025 umapDataService2025;

    @Mock ParticipantService2025 participantService2025;
	@Mock
	HubmapCellTypeMappingService hubmapCellTypeMappingService;

	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		query = new QueryController(autocompleteService, cellTypeService, umapDataService2025,
				geneExpressionService2025, dataSummaryService, clusterHierarchyService, rtExpressionDataService, rpExpressionDataService, 
        participantService2025, atlasMessageService, hubmapCellTypeMappingService);
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
	public void geneExpression2025() throws Exception {
		List expectedResultSN1 = Arrays.asList(new SNRNAGeneExpressionExpressionSummaryValue2025());
		List expectedResultSN2 = Arrays.asList(new SNRNAGeneExpressionExpressionSummaryValue2025());
        List expectedResultSN3 = Arrays.asList(new SNRNAGeneExpressionExpressionSummaryValue2025());
        List expectedResultSN4 = Arrays.asList(new SCRNAGeneExpressionExpressionSummaryValue2025());
		when(geneExpressionService2025.getByDataTypeEnrollmentCategoryAndGene("sn", "gene", "aki")).thenReturn(expectedResultSN1);
		when(geneExpressionService2025.getExpressionSummaryPerGeneByCellTypeAndEnrollmentCategory("sn", "cell type", "aki"))
				.thenReturn(expectedResultSN2);

        when(geneExpressionService2025.getByDataTypeEnrollmentCategoryAndGene("sn", "gene", "aki")).thenReturn(expectedResultSN3);
		when(geneExpressionService2025.getExpressionSummaryPerGeneByCellTypeAndEnrollmentCategory("sn", "cell type", "aki"))
				.thenReturn(expectedResultSN4);

		List expectedResultSC1 = Arrays.asList(new SCRNAGeneExpressionExpressionSummaryValue2025());
		List expectedResultSC2 = Arrays.asList(new SCRNAGeneExpressionExpressionSummaryValue2025());
		when(geneExpressionService2025.getByDataTypeEnrollmentCategoryAndGene("sc", "gene", "aki")).thenReturn(expectedResultSC1);
		when(geneExpressionService2025.getExpressionSummaryPerGeneByCellTypeAndEnrollmentCategory("sc", "cell type", "aki"))
				.thenReturn(expectedResultSC2);

		assertEquals(expectedResultSN1, query.geneExpressionSummary2025("sn", "gene", "", "aki"));
		assertEquals(expectedResultSC1, query.geneExpressionSummary2025("sc", "gene", "", "aki"));

		assertEquals(expectedResultSN2, query.geneExpressionSummary2025("sn", "", "cell type", "aki"));
		assertEquals(expectedResultSC2, query.geneExpressionSummary2025("sc", "", "cell type", "aki"));

        assertEquals(expectedResultSN2, query.geneExpressionSummary2025("sn", "", "cell type", "aki"));
	}

    @Test
	public void testGetUmapPlotData2025() throws Exception {
		List<FeatureData> featureData = new ArrayList<>();
		List<ReferenceCluster> referenceData = new ArrayList<>();
		PlotData expectedPlotData1 = new PlotData(referenceData, featureData);
        PlotData expectedPlotData2 = new PlotData(referenceData, featureData);
		when(umapDataService2025.getPlotData("sn", "gene", "all")).thenReturn(expectedPlotData1);
        when(umapDataService2025.getPlotData("sn", "gene", "all")).thenReturn(expectedPlotData2);

		PlotData umapPlotData1 = query.getUmapPlotData2025("sn", "gene", "all");
        PlotData umapPlotData2 = query.getUmapPlotData2025("sn", "gene", "all");

		assertEquals(expectedPlotData1, umapPlotData1);
		verify(umapDataService).getPlotData("sn", "gene", "all");

        assertEquals(expectedPlotData2, umapPlotData2);
        verify(umapDataService).getPlotData("sn", "gene", "all");
	}

	@Test
	public void testGetClusterHierarchies() throws Exception {
		List<ClusterHierarchy> expectedList = Arrays.asList(new ClusterHierarchy());
		when(clusterHierarchyService.findClustersByCellType2025("cell type")).thenReturn(expectedList);

		assertEquals(expectedList, query.getClusterHieararchies2025("cell type"));
		verify(clusterHierarchyService).findClustersByCellType2025("cell type");
	}

	@Test
	public void dataTypesForConceptWhenGeneSymbolAndNullClusterName() throws Exception {
		List<String> expectedResult1 = Arrays.asList("1", "2");
        List<String> expectedResult2 = Arrays.asList("3", "4");
		when(geneExpressionService2025.findDataTypesByGene("gene")).thenReturn(expectedResult1);

		List<String> dataTypesForConcept1 = query.dataTypesForConcept2025("gene", null);
        List<String> dataTypesForConcept2 = query.dataTypesForConcept2025("gene", null);

		assertEquals(expectedResult1, dataTypesForConcept1);
		verify(clusterHierarchyService, times(0)).findDataTypesByClusterName2025(any(String.class));

        assertEquals(expectedResult2, dataTypesForConcept2);
        verify(clusterHierarchyService, times(0)).findDataTypesByClusterName2025(any(String.class));
	}

	@Test
	public void dataTypesForConceptWhenGeneSymbolAndBlankClusterName() throws Exception {
		List<String> expectedResult1 = Arrays.asList("1", "2");
        List<String> expectedResult2 = Arrays.asList("3", "4");
		when(geneExpressionService2025.findDataTypesByGene("gene")).thenReturn(expectedResult1);
        when(geneExpressionService2025.findDataTypesByGene("gene")).thenReturn(expectedResult2);

		List<String> dataTypesForConcept1 = query.dataTypesForConcept2025("gene", "");
        List<String> dataTypesForConcept2 = query.dataTypesForConcept2025("gene", "");

		assertEquals(expectedResult1, dataTypesForConcept1);
		verify(clusterHierarchyService, times(0)).findDataTypesByClusterName2025(any(String.class));
        assertEquals(expectedResult2, dataTypesForConcept2);
        verify(clusterHierarchyService, times(0)).findDataTypesByClusterName2025(any(String.class));

	}

	@Test
	public void dataTypesForConceptWhenClusterNameAndNullGene() throws Exception {
		List<String> expectedResult1 = Arrays.asList("1", "2");
        List<String> expectedResult2 = Arrays.asList("3", "4");
        when(clusterHierarchyService.findDataTypesByClusterName2025("cluster")).thenReturn(expectedResult1);
        when(clusterHierarchyService.findDataTypesByClusterName2025("cluster")).thenReturn(expectedResult2);

		List<String> dataTypesForConcept1 = query.dataTypesForConcept2025(null, "cluster");
        List<String> dataTypesForConcept2 = query.dataTypesForConcept2025(null, "cluster");

		assertEquals(expectedResult1, dataTypesForConcept1);
		verify(geneExpressionService2025, times(0)).findDataTypesByGene(any(String.class));
        assertEquals(expectedResult2, dataTypesForConcept2);
        verify(geneExpressionService2025, times(0)).findDataTypesByGene(any(String.class));
	}

	@Test
	public void dataTypesForConceptWhenClusterNameAndBlankGene() throws Exception {
		List<String> expectedResult1 = Arrays.asList("1", "2");
        List<String> expectedResult2 = Arrays.asList("3", "4");
        when(clusterHierarchyService.findDataTypesByClusterName2025("cluster")).thenReturn(expectedResult1);
        when(clusterHierarchyService.findDataTypesByClusterName2025("cluster")).thenReturn(expectedResult2);

		List<String> dataTypesForConcept1 = query.dataTypesForConcept2025("", "cluster");
        List<String> dataTypesForConcept2 = query.dataTypesForConcept2025("", "cluster");


		assertEquals(expectedResult1, dataTypesForConcept1);
		verify(geneExpressionService2025, times(0)).findDataTypesByGene(any(String.class));
        assertEquals(expectedResult2, dataTypesForConcept2);
        verify(geneExpressionService2025, times(0)).findDataTypesByGene(any(String.class));
	}

	@Test
	public void getGeneDatasetInformation() throws Exception {
		List<DataTypeSummary> expectedResult1 = new ArrayList<>();
        List<DataTypeSummary> expectedResult2 = new ArrayList<>();

		expectedResult1.add(new DataTypeSummary(OmicsTypeEnum.TRANSCRIPTOMICS.getEnum(),
				FullDataTypeEnum.SINGLE_CELL.getFullName(), FullDataTypeEnum.SINGLE_CELL.getAbbreviation(),
				Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0)));
		expectedResult1.add(new DataTypeSummary(OmicsTypeEnum.NONE.getEnum(),
				FullDataTypeEnum.SINGLE_NUCLEUS.getFullName(), FullDataTypeEnum.SINGLE_NUCLEUS.getAbbreviation(),
				Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0)));
		when(geneExpressionService2025.getDataTypeSummaryInformation()).thenReturn(expectedResult1);

        expectedResult2.add(new DataTypeSummary(OmicsTypeEnum.TRANSCRIPTOMICS.getEnum(),
				FullDataTypeEnum.SINGLE_CELL.getFullName(), FullDataTypeEnum.SINGLE_CELL.getAbbreviation(),
				Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0)));
		expectedResult2.add(new DataTypeSummary(OmicsTypeEnum.NONE.getEnum(),
				FullDataTypeEnum.SINGLE_NUCLEUS.getFullName(), FullDataTypeEnum.SINGLE_NUCLEUS.getAbbreviation(),
				Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0)));
		when(geneExpressionService2025.getDataTypeSummaryInformation()).thenReturn(expectedResult2);

		List<DataTypeSummary> datasetSummary1 = query.getDataTypeSummaryInformation2025();
        List<DataTypeSummary> datasetSummary2 = query.getDataTypeSummaryInformation2025();

		assertEquals(expectedResult1, datasetSummary1);
        assertEquals(expectedResult2, datasetSummary2);
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
		ParticipantDataTypeSummary2025 expected1 = mock(ParticipantDataTypeSummary2025.class);
        ParticipantDataTypeSummary2025 expected2 = mock(ParticipantDataTypeSummary2025.class);
		when(participantService2025.getExperimentCounts("123")).thenReturn(expected1);
        when(participantService2025.getExperimentCounts("123")).thenReturn(expected2);

		ParticipantDataTypeSummary2025 result1 = query.getDataTypeInformationByParticipant2025("123");
        ParticipantDataTypeSummary2025 result2 = query.getDataTypeInformationByParticipant2025("123");

		assertEquals(expected1, result1);
        assertEquals(expected2, result2);

	}

	@Test
	public void testGetRepoDataTypeInformationByParticipant() throws Exception {
		ParticipantRepoDataTypeSummary expected = mock(ParticipantRepoDataTypeSummary.class);
		when(participantService2025.getDataTypeCounts("123")).thenReturn(expected);

		ParticipantRepoDataTypeSummary result = query.getRepoDataTypeInformationByParticipant("123");

		assertEquals(expected, result);

	}

    @Test
    public void testGetParticipantClincialDataset() throws Exception {
        ParticipantClinicalDataset expected = new ParticipantClinicalDataset();
		when(participantService2025.getParticipantClinicalDataset("participant_id")).thenReturn(expected);

		assertEquals(expected, query.getParticipantClinicalDataset("participant_id"));
		verify(participantService2025).getParticipantClinicalDataset("participant_id");
    }

    @Test
	public void testParticipantSummaryDataset() throws Exception {
		ParticipantSummaryDataset expected = new ParticipantSummaryDataset();
		when(participantService2025.getParticipantSummaryDataset("participant_id")).thenReturn(expected);

		assertEquals(expected, query.participantSummaryDataset("participant_id"));
		verify(participantService2025).getParticipantSummaryDataset("participant_id");
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
		when(participantService2025.getExperimentalStrategyCountsByParticipant("redcapId")).thenReturn(expectedResults);

		List<ParticipantRepoDataTypeInformation> result = query.getExperimentalStrategyCountsByParticipant("redcapId");

		assertEquals(expectedResults, result);
	}
	@Test
	public void testGetHubmapTermMap() {
		HubmapOntologyCellType cellType1 = mock(HubmapOntologyCellType.class);
		when(cellType1.getHubmapOntologyId()).thenReturn("HUBMAP:001");
		when(cellType1.getCellType()).thenReturn("Podocyte");

		HubmapOntologyCellType cellType2 = mock(HubmapOntologyCellType.class);
		when(cellType2.getHubmapOntologyId()).thenReturn("HUBMAP:002");
		when(cellType2.getCellType()).thenReturn("Tubule cell");

		List<HubmapOntologyCellType> expectedList = Arrays.asList(cellType1, cellType2);
		when(hubmapCellTypeMappingService.buildHubmapIdToCellTypeMap()).thenReturn(expectedList);

		List<HubmapOntologyCellType> result = query.getHubmapTermMap();
		assertEquals(expectedList, result);
		verify(hubmapCellTypeMappingService).buildHubmapIdToCellTypeMap();
	}
}