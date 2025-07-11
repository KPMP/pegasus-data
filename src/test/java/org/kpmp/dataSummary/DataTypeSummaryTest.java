package org.kpmp.dataSummary;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kpmp.FullDataTypeEnum;
import org.kpmp.OmicsTypeEnum;

public class DataTypeSummaryTest {

	private DataTypeSummary datasetSummary;

	@BeforeEach
	public void setUp() {

		datasetSummary = new DataTypeSummary(OmicsTypeEnum.TRANSCRIPTOMICS.getEnum(),
				FullDataTypeEnum.SINGLE_CELL.getFullName(), FullDataTypeEnum.SINGLE_CELL.getAbbreviation(),
				Long.valueOf(2), Long.valueOf(4), Long.valueOf(6), Long.valueOf(7), Long.valueOf(8));
	}

	@AfterEach
	public void tearDown() {
		datasetSummary = null;
	}

	@Test
	public void testInitalSetup() throws Exception {
		assertEquals(OmicsTypeEnum.TRANSCRIPTOMICS.getEnum(), datasetSummary.getOmicsType());
		assertEquals(FullDataTypeEnum.SINGLE_CELL.getFullName(), datasetSummary.getDataType());
		assertEquals(FullDataTypeEnum.SINGLE_CELL.getAbbreviation(), datasetSummary.getDataTypeShort());
		assertEquals(Long.valueOf(2), datasetSummary.getAkiCount());
		assertEquals(Long.valueOf(4), datasetSummary.getCkdCount());
		assertEquals(Long.valueOf(6), datasetSummary.getHrtCount());
		assertEquals(Long.valueOf(7), datasetSummary.getDmrCount());
		assertEquals(Long.valueOf(8), datasetSummary.getParticipantCount());
	}

	@Test
	public void testSetOmicsType() throws Exception {
		datasetSummary.setOmicsType(OmicsTypeEnum.TRANSCRIPTOMICS.getEnum());
		assertEquals(OmicsTypeEnum.TRANSCRIPTOMICS.getEnum(), datasetSummary.getOmicsType());
	}

	@Test
	public void testSetDataType() throws Exception {
		datasetSummary.setDataTypeType(FullDataTypeEnum.SINGLE_CELL.getFullName());
		assertEquals(FullDataTypeEnum.SINGLE_CELL.getFullName(), datasetSummary.getDataType());
	}

	@Test
	public void testSetDataTypeShort() throws Exception {
		datasetSummary.setDataTypeShort(FullDataTypeEnum.SINGLE_CELL.getAbbreviation());
		assertEquals(FullDataTypeEnum.SINGLE_CELL.getAbbreviation(), datasetSummary.getDataTypeShort());
	}

	@Test
	public void testSetAkiCount() throws Exception {
		datasetSummary.setAkiCount(Long.valueOf(2 * 10));
		assertEquals(Long.valueOf(2 * 10), datasetSummary.getAkiCount());
	}

	@Test
	public void testSetCkdCount() throws Exception {
		datasetSummary.setCkdCount(Long.valueOf(4 * 10));
		assertEquals(Long.valueOf(4 * 10), datasetSummary.getCkdCount());
	}

	@Test
	public void testSetHrtCount() throws Exception {
		datasetSummary.setHrtCount(Long.valueOf(6 * 10));
		assertEquals(Long.valueOf(6 * 10), datasetSummary.getHrtCount());
	}

	@Test
	public void testSetDmrCount() throws Exception {
		datasetSummary.setDmrCount(Long.valueOf(7 * 10));
		assertEquals(Long.valueOf(7 * 10), datasetSummary.getDmrCount());
	}

	@Test
	public void testSetParticipantCount() throws Exception {
		datasetSummary.setParticipantCount(Long.valueOf(8 * 10));
		assertEquals(Long.valueOf(8 * 10), datasetSummary.getParticipantCount());
	}

}