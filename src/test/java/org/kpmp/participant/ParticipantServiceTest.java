package org.kpmp.participant;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import org.kpmp.EnrollmentCategoryEnum;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kpmp.dataSummary.DataSummaryRepository;
import org.kpmp.geneExpressionSummary.regionalProteomics.RPParticipantRepository;
import org.kpmp.geneExpressionSummary.regionalTranscriptomics.RTParticipantRepository;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

public class ParticipantServiceTest {

	private ParticipantService participantService;
	@Mock
	private ParticipantSummaryDatasetRepository participantSummaryDatasetRepository;
	@Mock
	private DataSummaryRepository dataSummaryRepo;
	@Mock
	private SpatialViewerTypeRepository svTypeRepo;
	@Mock
	private SingleCellMetadataRepository scMetadataRepo;
	@Mock
	private SingleNucleusMetadataRepository snMetadataRepo;
	@Mock
	private RTParticipantRepository rtParticipantRepo;
	@Mock
	private ParticipantRepoDataRepository fileByParticipantRepo;
	@Mock
	private RPParticipantRepository rpParticipantRepository;
    @Mock 
    private ParticipantClinicalDatasetRepository participantClinicalDatasetRepo;

	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		participantService = new ParticipantService(dataSummaryRepo, svTypeRepo, scMetadataRepo, snMetadataRepo,
				rtParticipantRepo, participantSummaryDatasetRepository, rpParticipantRepository,fileByParticipantRepo, participantClinicalDatasetRepo);
	}

	@AfterEach
	public void tearDown() throws Exception {
		MockitoAnnotations.openMocks(this).close();
		participantService = null;
	}

    @Test
    public void testGetParticipantClincialDataset() throws Exception {
        ParticipantSummaryDataset newPart = new ParticipantSummaryDataset();
        newPart.setRedcapId("1234");
        newPart.setParticipantId(99);

        ParticipantClinicalDataset expectedResult = new ParticipantClinicalDataset();
        expectedResult.setA1c("40%");
        expectedResult.setAlbuminuria("very bad");
        expectedResult.setBaselineEgfr("no good");
        expectedResult.setDiabetesDuration("40-49 Years");
        expectedResult.setDiabetesHistory("Yes");
        expectedResult.setKdigoStage("Stage 4");
        expectedResult.setOnRaasBlockade("Yes");
        expectedResult.setParticipantClinicalId(0);
        expectedResult.setParticipantId(99);
        expectedResult.setProteinuria("proteinuria");
        expectedResult.setRace("alien from outer space");
        expectedResult.setAgeBinned("age");
        expectedResult.setTissueSource("tissueSource");
        expectedResult.setProtocol("protocol");
        expectedResult.setSampleType("sample");
        expectedResult.setSex("sex");



        when(participantSummaryDatasetRepository.findIdByRedcapId(newPart.getRedcapId())).thenReturn(newPart.getParticipantId());

        when(participantService.getParticipantClinicalDataset(newPart.getRedcapId())).thenReturn(expectedResult);

        ParticipantClinicalDataset result = participantService.getParticipantClinicalDataset("1234");
        
        assertEquals(0, result.getParticipantClinicalId());
        assertEquals(99, result.getParticipantId());
        assertEquals("40%", result.getA1c());
        assertEquals("very bad", result.getAlbuminuria());
        assertEquals("no good", result.getBaselineEgfr());
        assertEquals("40-49 Years", result.getDiabetesDuration());
        assertEquals("Yes", result.getDiabetesHistory());
        assertEquals("Stage 4", result.getKdigoStage());
        assertEquals("Yes", result.getOnRaasBlockade());
        assertEquals("proteinuria", result.getProteinuria());
        assertEquals("alien from outer space", result.getRace());
        assertEquals("age", result.getAgeBinned());
        assertEquals("tissueSource", result.getTissueSource());
        assertEquals("protocol", result.getProtocol());
        assertEquals("sex", result.getSex());
        assertEquals("sample", result.getSampleType());
    }

	@Test
	public void testGetParticipantSummaryDataset() throws Exception {

		ParticipantSummaryDataset expectedResult = new ParticipantSummaryDataset();
		expectedResult.setParticipantId(1);
		expectedResult.setOldParticipantId("2");
		expectedResult.setRedcapId("3");
		expectedResult.setSex("4");
		expectedResult.setAgeBinned("5");
		expectedResult.setTissueSource("6");
		expectedResult.setProtocol("7");
		expectedResult.setSampleType("8");
		expectedResult.setEnrollmentCategory("9");

		when(participantSummaryDatasetRepository.findByRedcapId("1")).thenReturn(expectedResult);

		when(participantService.getParticipantSummaryDataset("1")).thenReturn(expectedResult);

		ParticipantSummaryDataset result = participantService.getParticipantSummaryDataset("1");

		assertEquals(1, result.getParticipantId());
		assertEquals("2", result.getOldParticipantId());
		assertEquals("3", result.getRedcapId());
		assertEquals("4", result.getSex());
		assertEquals("5", result.getAgeBinned());
		assertEquals("6", result.getTissueSource());
		assertEquals("7", result.getProtocol());
		assertEquals("8", result.getSampleType());
		assertEquals("9", result.getEnrollmentCategory());
	}

	@Test
	public void testGetExperimentCounts() {
		SpatialViewerDataType dataType1 = new SpatialViewerDataType();
		dataType1.setDataType("Light Microscopy");
		dataType1.setTableName("sv_file_v");
		SpatialViewerDataType dataType2 = new SpatialViewerDataType();
		dataType2.setDataType("Spatial Lipidomics");
		dataType2.setTableName("sv_link_v");
		when(svTypeRepo.findAll()).thenReturn(Arrays.asList(dataType1, dataType2));
		when(dataSummaryRepo.getParticipantSvFileDataTypeCount("redcapId", "Light Microscopy")).thenReturn(5);
		when(dataSummaryRepo.getParticipantSvLinkDataTypeCount("redcapId", "Spatial Lipidomics")).thenReturn(2);
		when(scMetadataRepo.existsByRedcapId("redcapId")).thenReturn(true);
		when(snMetadataRepo.existsByRedcapId("redcapId")).thenReturn(true);
		when(rtParticipantRepo.existsByRedcapId("redcapId")).thenReturn(true);
		when(rpParticipantRepository.existsByRedcapId("redcapId")).thenReturn(true);

		ParticipantDataTypeSummary result = participantService.getExperimentCounts("redcapId");

		List<ParticipantDataTypeInformation> spatialViewerDataTypes = result.getSpatialViewerDataTypes();
		assertEquals(2, spatialViewerDataTypes.size());
		List<ParticipantDataTypeInformation> explorerDataTypes = result.getExplorerDataTypes();
		assertEquals(4, explorerDataTypes.size());
		for (ParticipantDataTypeInformation participantDataTypeInformation : spatialViewerDataTypes) {
			if (participantDataTypeInformation.getDataType().equals("Light Microscopy")) {
				assertEquals(Integer.valueOf(5), participantDataTypeInformation.getCount());
				assertEquals(false, participantDataTypeInformation.isAggregatedData());
			} else if (participantDataTypeInformation.getDataType().equals("Spatial Lipidomics")) {
				assertEquals(Integer.valueOf(2), participantDataTypeInformation.getCount());
				assertEquals(false, participantDataTypeInformation.isAggregatedData());
			}
		}
		for (ParticipantDataTypeInformation participantDataTypeInformation : explorerDataTypes) {
			assertEquals(Integer.valueOf(1), participantDataTypeInformation.getCount());
			assertEquals(true, participantDataTypeInformation.isAggregatedData());
		}

		ArgumentCaptor<String> redcapIdCaptor = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<String> dataTypeCaptor = ArgumentCaptor.forClass(String.class);
		verify(dataSummaryRepo).getParticipantSvFileDataTypeCount(redcapIdCaptor.capture(), dataTypeCaptor.capture());
		assertEquals("redcapId", redcapIdCaptor.getValue());
		assertEquals("Light Microscopy", dataTypeCaptor.getValue());
		verify(dataSummaryRepo).getParticipantSvLinkDataTypeCount(redcapIdCaptor.capture(), dataTypeCaptor.capture());
		assertEquals("redcapId", redcapIdCaptor.getValue());
		assertEquals("Spatial Lipidomics", dataTypeCaptor.getValue());

	}

	@Test
	public void testGetDataTypeCounts() throws Exception {
		String dataType1 = "Transcriptomics", dataType2 = "Imaging";
		when(dataSummaryRepo.getRepoDataTypes()).thenReturn(Arrays.asList(dataType1, dataType2));
		when(dataSummaryRepo.getParticipantRepoFileDataTypeCount("redcapId", dataType1)).thenReturn(3);
		when(dataSummaryRepo.getParticipantRepoFileDataTypeCount("redcapId", dataType2)).thenReturn(4);

		ParticipantRepoDataTypeSummary result = participantService.getDataTypeCounts("redcapId");

		List<ParticipantRepoDataTypeInformation> repositoryDataTypes = result.getRepositoryDataTypes();
		assertEquals(2, repositoryDataTypes.size());
		for (ParticipantRepoDataTypeInformation participantDataTypeInformation : repositoryDataTypes) {
			if(participantDataTypeInformation.getDataType().equals("Transcriptomics")) {
				assertEquals(Integer.valueOf(3), participantDataTypeInformation.getCount());
			}
			else if (participantDataTypeInformation.getDataType().equals("Imaging")) {
				assertEquals(Integer.valueOf(4), participantDataTypeInformation.getCount());
			}
		}
	}

	@Test
	public void testGetEnrollmentCounts() throws Exception {
		when(participantSummaryDatasetRepository.getDataSummaryCount(EnrollmentCategoryEnum.AKI.getParticipantEnrollmentCategory())).thenReturn(Long .valueOf(4));
		when(participantSummaryDatasetRepository.getDataSummaryCount(EnrollmentCategoryEnum.CKD.getParticipantEnrollmentCategory())).thenReturn(Long .valueOf(5));
		when(participantSummaryDatasetRepository.getDataSummaryCount(EnrollmentCategoryEnum.HEALTHY_REFERENCE.getParticipantEnrollmentCategory())).thenReturn(Long .valueOf(6));

		List<ParticipantEnrollmentCategorySummary> result = participantService.getEnrollmentData();
		ParticipantEnrollmentCategorySummary resultDataAki = result.get(0);
		ParticipantEnrollmentCategorySummary resultDataCkd = result.get(0);
		ParticipantEnrollmentCategorySummary resultDataHrt = result.get(0);


		assertEquals(Long.valueOf(4), resultDataAki.getAkiCount());
		assertEquals(Long.valueOf(5), resultDataCkd.getCkdCount());
		assertEquals(Long.valueOf(6), resultDataHrt.getHrtCount());
	}
	
	@Test
	public void testGetTotalFilesCount() throws Exception {
		when(dataSummaryRepo.getParticipantIDString("123")).thenReturn("abc");
		when(dataSummaryRepo.getParticipantTotalFileCount("abc")).thenReturn(5);
		
		ParticipantRepoDataTypeInformation result = participantService.getTotalFilesCount("123");
		
		assertEquals(Integer.valueOf(5), result.getCount());
		assertEquals("redcap_id", result.getLinkInformation().getLinkType());
		assertEquals("123", result.getLinkInformation().getLinkValue());
	}

	@Test
	public void testGetExperimentalStrategyCountsByParticipant() {
		ParticipantRepoData experimentGroup1 = new ParticipantRepoData();
		experimentGroup1.setExperimentalStrategy("experiment1");
		experimentGroup1.setCount(1);
		experimentGroup1.setDataCategory("something else");
		ParticipantRepoData experimentGroup2 = new ParticipantRepoData();
		experimentGroup2.setExperimentalStrategy("");
		experimentGroup2.setCount(2);
		experimentGroup2.setDataType("Clinical Study Data");
		experimentGroup2.setDataCategory("another category");
		ParticipantRepoData experimentGroup3 = new ParticipantRepoData();
		experimentGroup3.setExperimentalStrategy("biomarker data");
		experimentGroup3.setDataCategory("Biomarker");
		experimentGroup3.setCount(3);
		List<ParticipantRepoData> experimentsByParticipant = Arrays.asList(experimentGroup1, experimentGroup2, experimentGroup3);
		when(fileByParticipantRepo.findFileCountsByParticipant("participantId")).thenReturn(experimentsByParticipant);
		ReflectionTestUtils.setField(participantService, "CLINICAL_STUDY_DATA", "Clinical Study Data");
        ReflectionTestUtils.setField(participantService, "BIOMARKERS", "Biomarkers");
		ReflectionTestUtils.setField(participantService, "BIOMARKER", "Biomarker");

		List<ParticipantRepoDataTypeInformation> actualResults = participantService.getExperimentalStrategyCountsByParticipant("participantId");

		assertEquals(3, actualResults.size());
		for (ParticipantRepoDataTypeInformation participantRepoDataTypeInformation : actualResults) {
			if (participantRepoDataTypeInformation.getDataType().equals("experiment1")) {
				assertEquals(Integer.valueOf(1), participantRepoDataTypeInformation.getCount());
			} else if (participantRepoDataTypeInformation.getDataType().equals("Clinical Study Data")) {
				assertEquals(Integer.valueOf(2), participantRepoDataTypeInformation.getCount());
			} else if (participantRepoDataTypeInformation.getDataType().equals("Biomarkers")) {
				assertEquals(Integer.valueOf(3), participantRepoDataTypeInformation.getCount());
			} else {
				fail("unexpected data type: " + participantRepoDataTypeInformation.getDataType());
			}
		}		
	}

	@Test
	public void testGetExperimentalStrategyCountsByParticipant_combinesClinicalDataCounts() {
		ParticipantRepoData experimentGroup1 = new ParticipantRepoData();
		experimentGroup1.setExperimentalStrategy("");
		experimentGroup1.setCount(1);
		experimentGroup1.setDataType("Clinical Study Data");
		experimentGroup1.setDataCategory("something else");
		ParticipantRepoData experimentGroup2 = new ParticipantRepoData();
		experimentGroup2.setExperimentalStrategy("");
		experimentGroup2.setCount(2);
		experimentGroup2.setDataType("Clinical Study Data");
		experimentGroup2.setDataCategory("another category");
		List<ParticipantRepoData> experimentsByParticipant = Arrays.asList(experimentGroup1, experimentGroup2);
		when(fileByParticipantRepo.findFileCountsByParticipant("participantId")).thenReturn(experimentsByParticipant);
		ReflectionTestUtils.setField(participantService, "CLINICAL_STUDY_DATA", "Clinical Study Data");
        ReflectionTestUtils.setField(participantService, "BIOMARKERS", "Biomarkers");
		ReflectionTestUtils.setField(participantService, "BIOMARKER", "Biomarker");

		List<ParticipantRepoDataTypeInformation> actualResults = participantService.getExperimentalStrategyCountsByParticipant("participantId");

		assertEquals(1, actualResults.size());
		for (ParticipantRepoDataTypeInformation participantRepoDataTypeInformation : actualResults) {
			if (participantRepoDataTypeInformation.getDataType().equals("Clinical Study Data")) {
				assertEquals(Integer.valueOf(3), participantRepoDataTypeInformation.getCount());
			} else {
				fail("unexpected data type: " + participantRepoDataTypeInformation.getDataType());
			}
		}		
	}

	@Test
	public void testGetExperimentalStrategyCountsByParticipant_combinesBiomarkerCounts() {
		ParticipantRepoData experimentGroup1 = new ParticipantRepoData();
		experimentGroup1.setExperimentalStrategy("strat1");
		experimentGroup1.setCount(1);
		experimentGroup1.setDataType("stuff");
		experimentGroup1.setDataCategory("Biomarker");
		ParticipantRepoData experimentGroup2 = new ParticipantRepoData();
		experimentGroup2.setExperimentalStrategy("strat2");
		experimentGroup2.setCount(2);
		experimentGroup2.setDataType("more stuff");
		experimentGroup2.setDataCategory("Biomarker");
		List<ParticipantRepoData> experimentsByParticipant = Arrays.asList(experimentGroup1, experimentGroup2);
		when(fileByParticipantRepo.findFileCountsByParticipant("participantId")).thenReturn(experimentsByParticipant);
		ReflectionTestUtils.setField(participantService, "CLINICAL_STUDY_DATA", "Clinical Study Data");
        ReflectionTestUtils.setField(participantService, "BIOMARKERS", "Biomarkers");
		ReflectionTestUtils.setField(participantService, "BIOMARKER", "Biomarker");

		List<ParticipantRepoDataTypeInformation> actualResults = participantService.getExperimentalStrategyCountsByParticipant("participantId");

		assertEquals(1, actualResults.size());
		for (ParticipantRepoDataTypeInformation participantRepoDataTypeInformation : actualResults) {
			if (participantRepoDataTypeInformation.getDataType().equals("Biomarkers")) {
				assertEquals(Integer.valueOf(3), participantRepoDataTypeInformation.getCount());
			} else {
				fail("unexpected data type: " + participantRepoDataTypeInformation.getDataType());
			}
		}		
	}
}