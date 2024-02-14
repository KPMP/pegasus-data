package org.kpmp.dataSummary;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AtlasRepoSummaryLinkInformationTest {

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testConstructor() {
		AtlasRepositoryLinkInformation linkInfo = new AtlasRepositoryLinkInformation("type", "value");

		assertEquals("type", linkInfo.getLinkType());
		assertEquals("value", linkInfo.getLinkValue());
	}

	@Test
	public void testSetLinkType() {
		AtlasRepositoryLinkInformation linkInfo = new AtlasRepositoryLinkInformation("type", "value");
		linkInfo.setLinkType("anotherType");

		assertEquals("anotherType", linkInfo.getLinkType());
	}

	@Test
	public void testSetLinkValue() {
		AtlasRepositoryLinkInformation linkInfo = new AtlasRepositoryLinkInformation("type", "value");
		linkInfo.setLinkValue("another value");

		assertEquals("another value", linkInfo.getLinkValue());
	}

	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void testEqualsAndHashCode() throws Exception {
		AtlasRepositoryLinkInformation base = new AtlasRepositoryLinkInformation("linkType", "linkValue");
		AtlasRepositoryLinkInformation same = new AtlasRepositoryLinkInformation("linkType", "linkValue");
		AtlasRepositoryLinkInformation differentLinkType = new AtlasRepositoryLinkInformation("another type",
				"linkValue");
		AtlasRepositoryLinkInformation differentLinkValue = new AtlasRepositoryLinkInformation("linkType",
				"another value");
		String anotherObject = "hey there";

		assertEquals(base.hashCode(), base.hashCode());
		assertEquals(base.hashCode(), same.hashCode());
		assertEquals(true, base.equals(same));
		assertEquals(true, base.equals(base));
		assertEquals(false, base.equals(differentLinkType));
		assertEquals(false, base.equals(differentLinkValue));
		assertEquals(false, base.equals(anotherObject));

	}
}
