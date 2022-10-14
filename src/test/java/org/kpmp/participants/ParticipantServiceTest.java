package org.kpmp.participants;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kpmp.dataSummary.DataSummaryRepository;
import org.kpmp.geneExpressionSummary.RTParticipantRepository;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ParticipantServiceTest {

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
	private ParticipantService service;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		service = new ParticipantService(dataSummaryRepo, svTypeRepo, scMetadataRepo, snMetadataRepo,
				rtParticipantRepo);
	}

	@After
	public void tearDown() throws Exception {
		service = null;
		MockitoAnnotations.openMocks(this).close();
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

		ParticipantDataTypeSummary result = service.getExperimentCounts("redcapId");

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

}
