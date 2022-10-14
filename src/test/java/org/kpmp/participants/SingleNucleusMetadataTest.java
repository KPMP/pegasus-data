package org.kpmp.participants;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SingleNucleusMetadataTest extends SingleNucleusMetadata {

	private SingleNucleusMetadata snMetadata;

	@Before
	public void setUp() throws Exception {
		snMetadata = new SingleNucleusMetadata();
	}

	@After
	public void tearDown() throws Exception {
		snMetadata = null;
	}

	@Test
	public void testSetBarcode() {
		snMetadata.setBarcode("barcode");
		assertEquals("barcode", snMetadata.getBarcode());
	}

	@Test
	public void testSetRedcapId() {
		snMetadata.setRedcapId("redcapid");
		assertEquals("redcapid", snMetadata.getRedcapId());
	}

}
