package org.kpmp.participant;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParticipantDataTypeSummaryTest {

	private ParticipantDataTypeSummary summary;

	@BeforeEach
	public void setUp() throws Exception {
		summary = new ParticipantDataTypeSummary();
	}

	@AfterEach
	public void tearDown() throws Exception {
		summary = null;
	}

	@Test
	public void testSetSpatialViewerDataTypes() {
		List<ParticipantDataTypeInformation> experimentInfo = Arrays.asList(mock(ParticipantDataTypeInformation.class));

		summary.setSpatialViewerDataTypes(experimentInfo);

		assertEquals(experimentInfo, summary.getSpatialViewerDataTypes());
	}

	@Test
	public void testSetExplorerDataType() {
		List<ParticipantDataTypeInformation> experimentInfo = Arrays.asList(mock(ParticipantDataTypeInformation.class));

		summary.setExplorerDataTypes(experimentInfo);

		assertEquals(experimentInfo, summary.getExplorerDataTypes());
	}

}
