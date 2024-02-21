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
	AtlasRepositoryLinkInformation linkInformation;
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
		AtlasRepositoryLinkInformation expectedLinkInformation = mock(AtlasRepositoryLinkInformation.class);
		AtlasRepoSummaryRow rowToTest = new AtlasRepoSummaryRow("stuff", expectedLinkInformation);

		assertEquals("stuff", rowToTest.getOmicsType());
		assertEquals(expectedLinkInformation, rowToTest.getLinkInformation());
	}

	@Test
	public void testSetAkiCount() {
		row.setAkiCount(43);

		assertEquals(43, row.getAkiCount());
	}

	@Test
	public void testSetCkdCount() {
		row.setCkdCount(43);

		assertEquals(43, row.getCkdCount());
	}

	@Test
	public void testSetHrtCount() {
		row.setHrtCount(43);

		assertEquals(43, row.getHrtCount());
	}

	@Test
	public void testSetDmrCount() {
		row.setDmrCount(43);

		assertEquals(43, row.getDmrCount());
	}

	@Test
	public void testSetOmicsType() {
		row.setOmicsType("omicsType 2");
		assertEquals("omicsType 2", row.getOmicsType());
	}

	@Test
	public void testSetLinkInformation() {
		AtlasRepositoryLinkInformation expectedLinkInformation = mock(AtlasRepositoryLinkInformation.class);
		row.setLinkInformation(expectedLinkInformation);

		assertEquals(expectedLinkInformation, row.getLinkInformation());

	}
}
