package org.kpmp.dataSummary;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class AtlasRepoSummaryResultTest {

	private AtlasRepoSummaryResult result;
	@Mock
	private List<AtlasRepoSummaryRow> summaryRows;

	@Before
	public void setUp() throws Exception {
		result = new AtlasRepoSummaryResult();
	}

	@After
	public void tearDown() throws Exception {
		result = null;
	}

	@Test
	public void testConstructor() throws Exception {
		Long totalFiles = 5l;
		AtlasRepoSummaryResult constructorTest = new AtlasRepoSummaryResult(summaryRows, totalFiles);

		assertEquals(summaryRows, constructorTest.getSummaryRows());
		assertEquals(totalFiles, constructorTest.getTotalFiles());

	}

	@Test
	public void testSetSummaryRows() {
		result.setSummaryRows(summaryRows);

		assertEquals(summaryRows, result.getSummaryRows());
	}

	@Test
	public void testSetTotalFiles() {
		Long totalFiles = 58l;
		result.setTotalFiles(totalFiles);

		assertEquals(totalFiles, result.getTotalFiles());
	}

}
