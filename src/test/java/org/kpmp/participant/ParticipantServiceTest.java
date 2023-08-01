package org.kpmp.participant;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import org.kpmp.TissueTypeEnum;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kpmp.dataSummary.DataSummaryRepository;
import org.kpmp.geneExpressionSummary.RTParticipantRepository;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		participantService = new ParticipantService(dataSummaryRepo, svTypeRepo, scMetadataRepo, snMetadataRepo,
				rtParticipantRepo, participantSummaryDatasetRepository);
	}

	@After
	public void tearDown() throws Exception {
		MockitoAnnotations.openMocks(this).close();
		participantService = null;
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
		expectedResult.setTissueType("9");
		expectedResult.setClinicalData("10");

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
		assertEquals("9", result.getTissueType());
		assertEquals("10", result.getClinicalData());
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

		ParticipantDataTypeSummary result = participantService.getExperimentCounts("redcapId");

		List<ParticipantDataTypeInformation> spatialViewerDataTypes = result.getSpatialViewerDataTypes();
		assertEquals(2, spatialViewerDataTypes.size());
		List<ParticipantDataTypeInformation> explorerDataTypes = result.getExplorerDataTypes();
		assertEquals(3, explorerDataTypes.size());
		for (ParticipantDataTypeInformation participantDataTypeInformation : spatialViewerDataTypes) {
			if (participantDataTypeInformation.getDataType().equals("Light Microscopy")) {
				assertEquals(new Integer(5), participantDataTypeInformation.getCount());
				assertEquals(false, participantDataTypeInformation.isAggregatedData());
			} else if (participantDataTypeInformation.getDataType().equals("Spatial Lipidomics")) {
				assertEquals(new Integer(2), participantDataTypeInformation.getCount());
				assertEquals(false, participantDataTypeInformation.isAggregatedData());
			}
		}
		for (ParticipantDataTypeInformation participantDataTypeInformation : explorerDataTypes) {
			assertEquals(new Integer(1), participantDataTypeInformation.getCount());
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
				assertEquals(new Integer(3), participantDataTypeInformation.getCount());
			}
			else if (participantDataTypeInformation.getDataType().equals("Imaging")) {
				assertEquals(new Integer(4), participantDataTypeInformation.getCount());
			}
		}
	}

	@Test
	public void testGetTissueCounts() throws Exception {
		when(participantSummaryDatasetRepository.getDataSummaryCount(TissueTypeEnum.AKI.getParticipantTissueType())).thenReturn(Long .valueOf(4));
		when(participantSummaryDatasetRepository.getDataSummaryCount(TissueTypeEnum.CKD.getParticipantTissueType())).thenReturn(Long .valueOf(5));
		when(participantSummaryDatasetRepository.getDataSummaryCount(TissueTypeEnum.HEALTHY_REFERENCE.getParticipantTissueType())).thenReturn(Long .valueOf(6));

		List<ParticipantTissueTypeSummary> result = participantService.getTissueData();
		ParticipantTissueTypeSummary resultDataAki = result.get(0);
		ParticipantTissueTypeSummary resultDataCkd = result.get(0);
		ParticipantTissueTypeSummary resultDataHrt = result.get(0);


		assertEquals(Long.valueOf(4), resultDataAki.getAkiCount());
		assertEquals(Long.valueOf(5), resultDataCkd.getCkdCount());
		assertEquals(Long.valueOf(6), resultDataHrt.getHrtCount());
	}
}