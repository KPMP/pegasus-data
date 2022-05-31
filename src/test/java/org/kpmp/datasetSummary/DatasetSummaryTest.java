package org.kpmp.geneExpressionSummary;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.kpmp.datasetSummary.DatasetSummary;
import org.kpmp.datasetSummary.DatasetSummaryService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class DatasetSummaryTest {
	private DatasetSummaryService datasetSummaryService;
	@Mock
	private DatasetSummaryRepository datasetSummaryRepository;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		datasetSummaryService = new DatasetSummaryService(datasetSummaryRepository);
	}

	@After
	public void tearDown() throws Exception {
		datasetSummaryService = null;
	}

	@Test
	public void testGetGeneDatasetInformation() throws Exception {
		List<DatasetSummary> result = datasetSummaryService.getSummaryData("meta");
		when(datasetSummaryRepository.getCountByDataType("Spatial Transcriptomics")).thenReturn(Long.valueOf(0));

		assertEquals("sc", result.get(0).getDataType());
		assertEquals(Long.valueOf(0), result.get(0).getAkiCount());
		assertEquals(Long.valueOf(0), result.get(0).getCkdCount());
		assertEquals(Long.valueOf(0), result.get(0).getHrtCount());
	}
}
