package org.kpmp.dataSummary;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class AtlasRepoSummaryResultTest {

	private AtlasRepoSummaryResult result;
	@Mock
	private List<AtlasRepoSummaryRow> summaryRows;

	@BeforeEach
	public void setUp() throws Exception {
		result = new AtlasRepoSummaryResult();
	}

	@AfterEach
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
