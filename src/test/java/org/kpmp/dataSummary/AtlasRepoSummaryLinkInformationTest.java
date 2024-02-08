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
		AtlasRepoSummaryLinkInformation linkInfo = new AtlasRepoSummaryLinkInformation("type", "value");

		assertEquals("type", linkInfo.getLinkType());
		assertEquals("value", linkInfo.getLinkValue());
	}

	@Test
	public void testSetLinkType() {
		AtlasRepoSummaryLinkInformation linkInfo = new AtlasRepoSummaryLinkInformation("type", "value");
		linkInfo.setLinkType("anotherType");

		assertEquals("anotherType", linkInfo.getLinkType());
	}

	@Test
	public void testSetLinkValue() {
		AtlasRepoSummaryLinkInformation linkInfo = new AtlasRepoSummaryLinkInformation("type", "value");
		linkInfo.setLinkValue("another value");

		assertEquals("another value", linkInfo.getLinkValue());
	}

	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void testEqualsAndHashCode() throws Exception {
		AtlasRepoSummaryLinkInformation base = new AtlasRepoSummaryLinkInformation("linkType", "linkValue");
		AtlasRepoSummaryLinkInformation same = new AtlasRepoSummaryLinkInformation("linkType", "linkValue");
		AtlasRepoSummaryLinkInformation differentLinkType = new AtlasRepoSummaryLinkInformation("another type",
				"linkValue");
		AtlasRepoSummaryLinkInformation differentLinkValue = new AtlasRepoSummaryLinkInformation("linkType",
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
