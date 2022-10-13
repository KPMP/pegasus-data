package org.kpmp.participants;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ParticipantDataTypeSummaryTest {

	private ParticipantDataTypeSummary summary;

	@Before
	public void setUp() throws Exception {
		summary = new ParticipantDataTypeSummary();
	}

	@After
	public void tearDown() throws Exception {
		summary = null;
	}

	@Test
	public void testSetSpatialViewerDataTypes() {
		List<ParticipantDataTypeInformation> experimentInfo = Arrays
				.asList(mock(ParticipantDataTypeInformation.class));

		summary.setSpatialViewerDataTypes(experimentInfo);

		assertEquals(experimentInfo, summary.getSpatialViewerDataTypes());
	}

	@Test
	public void testSetExplorerDataType() {
		List<ParticipantDataTypeInformation> experimentInfo = Arrays
				.asList(mock(ParticipantDataTypeInformation.class));

		summary.setExplorerDataTypes(experimentInfo);

		assertEquals(experimentInfo, summary.getExplorerDataTypes());
	}

}
