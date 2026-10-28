package org.kpmp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
import org.kpmp.cellType.CellTypeService;
import org.kpmp.cellType.HubmapCellTypeMappingService;
import org.kpmp.cellType.HubmapOntologyCellType;
import org.kpmp.cellTypeSummary.CellTypeClusterHierarchy;
import org.kpmp.cellTypeSummary.CellTypeClusterHierarchyService;
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
    @Mock
    private UmapDataService2025 umapDataService2025;
    @Mock
    private RTExpressionDataService rtExpressionDataService;
    @Mock
    private RPExpressionDataService rpExpressionDataService;
    @Mock
    private AtlasMessageService atlasMessageService;
    @Mock
    private ParticipantService2025 participantService2025;
    @Mock
    private HubmapCellTypeMappingService hubmapCellTypeMappingService;
    @Mock
    private CellTypeClusterHierarchyService cellTypeClusterHierarchyService;

    private QueryController query;
    private AutoCloseable mocks;

    @BeforeEach
    public void setUp() throws Exception {
        mocks = MockitoAnnotations.openMocks(this);
        query = new QueryController(autocompleteService, cellTypeService, umapDataService2025,
                geneExpressionService2025, dataSummaryService, rtExpressionDataService, rpExpressionDataService,
                participantService2025, atlasMessageService, hubmapCellTypeMappingService,
                cellTypeClusterHierarchyService);
    }

    @AfterEach
    public void tearDown() throws Exception {
        mocks.close();
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

        Exception e = assertThrows(Exception.class, () -> query.getAtlasSummaryRows());
        assertEquals("ack", e.getMessage());
    }

    @Test
    public void testAutocomplete() throws Exception {
        List<AutocompleteResult> expectedResults = Arrays.asList(new AutocompleteResult());
        when(autocompleteService.query("autocomplete")).thenReturn(expectedResults);

        assertEquals(expectedResults, query.autocomplete("autocomplete"));
    }

    @Test
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void geneExpression2025() throws Exception {
        List snByGene = Arrays.asList(new SNRNAGeneExpressionExpressionSummaryValue2025());
        List snByCellType = Arrays.asList(new SNRNAGeneExpressionExpressionSummaryValue2025());
        List scByGene = Arrays.asList(new SCRNAGeneExpressionExpressionSummaryValue2025());
        List scByCellType = Arrays.asList(new SCRNAGeneExpressionExpressionSummaryValue2025());

        when(geneExpressionService2025.getByDataTypeEnrollmentCategoryAndGene("sn", "gene", "aki"))
                .thenReturn(snByGene);
        when(geneExpressionService2025.getExpressionSummaryPerGeneByCellTypeAndEnrollmentCategory("sn", "cell type", "aki"))
                .thenReturn(snByCellType);
        when(geneExpressionService2025.getByDataTypeEnrollmentCategoryAndGene("sc", "gene", "aki"))
                .thenReturn(scByGene);
        when(geneExpressionService2025.getExpressionSummaryPerGeneByCellTypeAndEnrollmentCategory("sc", "cell type", "aki"))
                .thenReturn(scByCellType);

        assertEquals(snByGene, query.geneExpressionSummary2025("sn", "gene", "", "aki"));
        assertEquals(scByGene, query.geneExpressionSummary2025("sc", "gene", "", "aki"));
        assertEquals(snByCellType, query.geneExpressionSummary2025("sn", "", "cell type", "aki"));
        assertEquals(scByCellType, query.geneExpressionSummary2025("sc", "", "cell type", "aki"));
    }

    @Test
    public void testGetUmapPlotData2025() throws Exception {
        List<FeatureData> featureData = new ArrayList<>();
        List<ReferenceCluster> referenceData = new ArrayList<>();
        PlotData expectedPlotData = new PlotData(referenceData, featureData);
        when(umapDataService2025.getPlotData("sn", "gene", "all")).thenReturn(expectedPlotData);

        PlotData result = query.getUmapPlotData2025("sn", "gene", "all");

        assertEquals(expectedPlotData, result);
        verify(umapDataService2025).getPlotData("sn", "gene", "all");
    }

    @Test
    public void dataTypesForConceptWhenGeneSymbolAndNullClusterName() throws Exception {
        List<String> expected = Arrays.asList("1", "2");
        when(geneExpressionService2025.findDataTypesByGene("gene")).thenReturn(expected);

        assertEquals(expected, query.dataTypesForConcept2025("gene", null));
        verify(cellTypeClusterHierarchyService, times(0)).findDataTypesByClusterName(any(String.class));
    }

    @Test
    public void dataTypesForConceptWhenGeneSymbolAndBlankClusterName() throws Exception {
        List<String> expected = Arrays.asList("1", "2");
        when(geneExpressionService2025.findDataTypesByGene("gene")).thenReturn(expected);

        assertEquals(expected, query.dataTypesForConcept2025("gene", ""));
        verify(cellTypeClusterHierarchyService, times(0)).findDataTypesByClusterName(any(String.class));
    }

    @Test
    public void dataTypesForConceptWhenClusterNameAndNullGene() throws Exception {
        List<String> expected = Arrays.asList("1", "2");
        when(cellTypeClusterHierarchyService.findDataTypesByClusterName("cluster")).thenReturn(expected);

        assertEquals(expected, query.dataTypesForConcept2025(null, "cluster"));
        verify(geneExpressionService2025, times(0)).findDataTypesByGene(any(String.class));
    }

    @Test
    public void dataTypesForConceptWhenClusterNameAndBlankGene() throws Exception {
        List<String> expected = Arrays.asList("1", "2");
        when(cellTypeClusterHierarchyService.findDataTypesByClusterName("cluster")).thenReturn(expected);

        assertEquals(expected, query.dataTypesForConcept2025("", "cluster"));
        verify(geneExpressionService2025, times(0)).findDataTypesByGene(any(String.class));
    }

    @Test
    public void getGeneDatasetInformation() throws Exception {
        List<DataTypeSummary> expected = new ArrayList<>();
        expected.add(new DataTypeSummary(OmicsTypeEnum.TRANSCRIPTOMICS.getEnum(),
                FullDataTypeEnum.SINGLE_CELL.getFullName(), FullDataTypeEnum.SINGLE_CELL.getAbbreviation(),
                Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0)));
        expected.add(new DataTypeSummary(OmicsTypeEnum.NONE.getEnum(),
                FullDataTypeEnum.SINGLE_NUCLEUS.getFullName(), FullDataTypeEnum.SINGLE_NUCLEUS.getAbbreviation(),
                Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0)));
        when(geneExpressionService2025.getDataTypeSummaryInformation()).thenReturn(expected);

        assertEquals(expected, query.getDataTypeSummaryInformation2025());
    }

    @Test
    public void testGetRTGeneExpression() throws Exception {
        List<RTExpressionDataAllSegments> data = Arrays.asList(new RTExpressionDataAllSegments());
        RTExpressionByEnrollmentCategory expected = new RTExpressionByEnrollmentCategory();
        expected.setAki(data);
        when(rtExpressionDataService.getByComparisonTypeAndGeneSymbolPerEnrollment("all_segments", "gene"))
                .thenReturn(expected);

        RTExpressionByEnrollmentCategory result = query.getRTGeneExpressionByEnrollment("all_segments", "gene");

        assertEquals(expected, result);
        assertEquals(data, result.getAki());
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

        assertEquals(expected, query.getRPGeneExpressionByEnrollmentAndProtein("APOL1", "steak"));
    }

    @Test
    public void testGetDataTypeInformationByParticipant() throws Exception {
        ParticipantDataTypeSummary2025 expected = mock(ParticipantDataTypeSummary2025.class);
        when(participantService2025.getExperimentCounts("123")).thenReturn(expected);

        assertEquals(expected, query.getDataTypeInformationByParticipant2025("123"));
    }

    @Test
    public void testGetRepoDataTypeInformationByParticipant() throws Exception {
        ParticipantRepoDataTypeSummary expected = mock(ParticipantRepoDataTypeSummary.class);
        when(participantService2025.getDataTypeCounts("123")).thenReturn(expected);

        assertEquals(expected, query.getRepoDataTypeInformationByParticipant("123"));
    }

    @Test
    public void testGetParticipantClinicalDataset() throws Exception {
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

    // TODO: needs the controller's getEnrollmentCategorySummaryData() implementation to stub correctly.
    // As originally written, nothing was stubbed, so the controller had nothing to return.

    @Test
    public void testGetAtlasMessage() throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = dateFormat.parse("2023-12-01");
        Date endDate = dateFormat.parse("2023-12-25");

        AtlasMessage atlasMessage = new AtlasMessage();
        atlasMessage.setId(0);
        atlasMessage.setApplication("Explorer");
        atlasMessage.setStartDate(startDate);
        atlasMessage.setEndDate(endDate);
        atlasMessage.setMessage("THE END IS NEAR");

        List<AtlasMessage> expectedResult = Arrays.asList(atlasMessage);
        when(atlasMessageService.getAtlasMessage()).thenReturn(expectedResult);

        assertEquals(expectedResult, query.getAtlasMessages());
    }

    @Test
    public void testGetExperimentalStrategyCountsByParticipant() {
        List<ParticipantRepoDataTypeInformation> expected = new ArrayList<>();
        when(participantService2025.getExperimentalStrategyCountsByParticipant("redcapId")).thenReturn(expected);

        assertEquals(expected, query.getExperimentalStrategyCountsByParticipant("redcapId"));
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

        assertEquals(expectedList, query.getHubmapTermMap());
        verify(hubmapCellTypeMappingService).buildHubmapIdToCellTypeMap();
    }
}