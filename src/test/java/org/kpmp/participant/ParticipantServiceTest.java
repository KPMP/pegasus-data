package org.kpmp.participant;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ParticipantServiceTest {

	private ParticipantService participantService;
	@Mock
	private ParticipantSummaryDatasetRepository participantSummaryDatasetRepository;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		participantService = new ParticipantService(
			participantSummaryDatasetRepository);
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
		
		when(participantSummaryDatasetRepository.findByParticipantSummaryDataset("1")).thenReturn(expectedResult);
			
		when(participantService.getParticipantSummaryDataset("1") )
			.thenReturn(expectedResult);

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

}