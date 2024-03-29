package org.kpmp.participant;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SingleCellMetadataTest {

	private SingleCellMetadata scMetadata;

	@Before
	public void setUp() throws Exception {
		scMetadata = new SingleCellMetadata();
	}

	@After
	public void tearDown() throws Exception {
		scMetadata = null;
	}

	@Test
	public void testSetBarcode() {
		scMetadata.setBarcode("barcode");
		assertEquals("barcode", scMetadata.getBarcode());
	}

	@Test
	public void testSetRedcapId() {
		scMetadata.setRedcapId("redcapid");
		assertEquals("redcapid", scMetadata.getRedcapId());
	}

}
