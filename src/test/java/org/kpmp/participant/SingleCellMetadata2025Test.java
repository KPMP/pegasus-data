package org.kpmp.participant;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SingleCellMetadata2025Test {

	private SingleCellMetadata2025 scMetadata;

	@BeforeEach
	public void setUp() throws Exception {
		scMetadata = new SingleCellMetadata2025();
	}

	@AfterEach
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
