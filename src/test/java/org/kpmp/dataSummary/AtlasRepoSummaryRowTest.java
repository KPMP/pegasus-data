package org.kpmp.dataSummary;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class AtlasRepoSummaryRowTest {

	@Mock
	AtlasRepositoryLinkInformation linkInformation;
	private AtlasRepoSummaryRow row;

	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		row = new AtlasRepoSummaryRow("omicsType", linkInformation);
	}

	@AfterEach
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
		row.setAkiCount(Long.valueOf(43));

		assertEquals(Long.valueOf(43), row.getAkiCount());
	}

	@Test
	public void testSetCkdCount() {
		row.setCkdCount(Long.valueOf(44));

		assertEquals(Long.valueOf(44), row.getCkdCount());
	}

	@Test
	public void testSetHrtCount() {
		row.setHrtCount(Long.valueOf(45));

		assertEquals(Long.valueOf(45), row.getHrtCount());
	}

	@Test
	public void testSetDmrCount() {
		row.setDmrCount(Long.valueOf(46));

		assertEquals(Long.valueOf(46), row.getDmrCount());
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
