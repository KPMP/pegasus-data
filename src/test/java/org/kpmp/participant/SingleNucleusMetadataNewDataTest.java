package org.kpmp.participant;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SingleNucleusMetadataNewDataTest {

	private SingleNucleusMetadataNewData snMetadata;

	@BeforeEach
	public void setUp() throws Exception {
		snMetadata = new SingleNucleusMetadataNewData();
	}

	@AfterEach
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
