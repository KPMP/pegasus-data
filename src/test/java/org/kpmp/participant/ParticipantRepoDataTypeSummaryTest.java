package org.kpmp.participant;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ParticipantRepoDataTypeSummaryTest {

	private ParticipantRepoDataTypeSummary summary;

	@Before
	public void setUp() throws Exception {
		summary = new ParticipantRepoDataTypeSummary();
	}

	@After
	public void tearDown() throws Exception {
		summary = null;
	}

	@Test
	public void testSetRepositoryDataTypes() {
		List<ParticipantRepoDataTypeInformation> experimentInfo = Arrays.asList(mock(ParticipantRepoDataTypeInformation.class));

		summary.setRepositoryDataTypes(experimentInfo);

		assertEquals(experimentInfo, summary.getRepositoryDataTypes());
	}

}
