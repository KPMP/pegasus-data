package org.kpmp.participants;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ParticipantExperimentalStrategySummaryTest {

	private ParticipantExperimentalStrategySummary summary;

	@Before
	public void setUp() throws Exception {
		summary = new ParticipantExperimentalStrategySummary();
	}

	@After
	public void tearDown() throws Exception {
		summary = null;
	}

	@Test
	public void testSetSpatialViewerExperiments() {
		List<ParticipantExperimentalStragegyInformation> experimentInfo = Arrays
				.asList(mock(ParticipantExperimentalStragegyInformation.class));

		summary.setSpatialViewerExperiments(experimentInfo);

		assertEquals(experimentInfo, summary.getSpatialViewerExperiments());
	}

	@Test
	public void testSetExplorerExperiments() {
		List<ParticipantExperimentalStragegyInformation> experimentInfo = Arrays
				.asList(mock(ParticipantExperimentalStragegyInformation.class));

		summary.setExplorerExperiments(experimentInfo);

		assertEquals(experimentInfo, summary.getExplorerExperiments());
	}

}
