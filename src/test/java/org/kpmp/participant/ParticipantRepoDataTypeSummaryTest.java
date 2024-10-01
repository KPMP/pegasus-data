package org.kpmp.participant;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParticipantRepoDataTypeSummaryTest {

	private ParticipantRepoDataTypeSummary summary;

	@BeforeEach
	public void setUp() throws Exception {
		summary = new ParticipantRepoDataTypeSummary();
	}

	@AfterEach
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
