package org.kpmp.dataSummary;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class AtlasRepoSummaryRowTest {

	@Mock
	AtlasRepoSummaryLinkInformation linkInformation;
	private AtlasRepoSummaryRow row;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		row = new AtlasRepoSummaryRow("omicsType", linkInformation);
	}

	@After
	public void tearDown() throws Exception {
		MockitoAnnotations.openMocks(this).close();
		row = null;
	}

	@Test
	public void testConstructor() throws Exception {
		AtlasRepoSummaryLinkInformation expectedLinkInformation = mock(AtlasRepoSummaryLinkInformation.class);
		AtlasRepoSummaryRow rowToTest = new AtlasRepoSummaryRow("stuff", expectedLinkInformation);

		assertEquals("stuff", rowToTest.getOmicsType());
		assertEquals(expectedLinkInformation, rowToTest.getLinkInformation());
		assertEquals(0, rowToTest.getOpenCount());
		assertEquals(0, rowToTest.getControlledCount());
	}

	@Test
	public void testSetOpenCount() {
		row.setOpenCount(43);

		assertEquals(43, row.getOpenCount());
	}

	@Test
	public void testSetControlledCount() {
		row.setControlledCount(44);

		assertEquals(44, row.getControlledCount());
	}

	@Test
	public void testSetOmicsType() {
		row.setOmicsType("omicsType 2");
		assertEquals("omicsType 2", row.getOmicsType());
	}

	@Test
	public void testAddToControlledCount() {
		row.setControlledCount(2);
		row.addToControlledCount(30);

		assertEquals(32, row.getControlledCount());
	}

	@Test
	public void testAddToOpenCount() {
		row.setOpenCount(5);
		row.addToOpenCount(5);

		assertEquals(10, row.getOpenCount());
	}

	@Test
	public void testSetLinkInformation() {
		AtlasRepoSummaryLinkInformation expectedLinkInformation = mock(AtlasRepoSummaryLinkInformation.class);
		row.setLinkInformation(expectedLinkInformation);

		assertEquals(expectedLinkInformation, row.getLinkInformation());

	}
}
