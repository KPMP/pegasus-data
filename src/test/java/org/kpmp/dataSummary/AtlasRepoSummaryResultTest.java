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
		int totalFiles = 5;
		AtlasRepoSummaryResult constructorTest = new AtlasRepoSummaryResult(summaryRows, totalFiles);

		assertEquals(summaryRows, constructorTest.getSummaryRows());
		assertEquals(5, constructorTest.getTotalFiles());

	}

	@Test
	public void testSetSummaryRows() {
		result.setSummaryRows(summaryRows);

		assertEquals(summaryRows, result.getSummaryRows());
	}

	@Test
	public void testSetTotalFiles() {
		result.setTotalFiles(58);

		assertEquals(58, result.getTotalFiles());
	}

}
