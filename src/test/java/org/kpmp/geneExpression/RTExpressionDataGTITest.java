package org.kpmp.geneExpression;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RTExpressionDataGTITest {

	private RTExpressionDataGTI rtExpressionDataGTI;

	@BeforeEach
	void setUp() {
		rtExpressionDataGTI = new RTExpressionDataGTI();
	}

	@AfterEach
	void tearDown() {
		rtExpressionDataGTI = null;
	}

	@Test
	void setId() {
		rtExpressionDataGTI.setId(123);
		assertEquals(rtExpressionDataGTI.getId(), 123, 0.001);
	}

	@Test
	void setGeneSymbol() {
		rtExpressionDataGTI.setGeneSymbol("gene");
		assertEquals("gene", rtExpressionDataGTI.getGeneSymbol());
	}

	@Test
	void setSegment() {
		rtExpressionDataGTI.setSegment("segment");
		assertEquals("segment", rtExpressionDataGTI.getSegment());
	}

	@Test
	void setFoldChange() {
		rtExpressionDataGTI.setFoldChange(1.23456);
		assertEquals("1,23", rtExpressionDataGTI.getFoldChangeString());
	}

	@Test
	void setPVal() {
		rtExpressionDataGTI.setPVal(1.241234);
		assertEquals("1.24", rtExpressionDataGTI.getPValString());
	}

	@Test
	void setStdDev() {
		rtExpressionDataGTI.setStdDev(0.123);
		assertEquals(0.123, rtExpressionDataGTI.getStdDev(), 0.001);
	}

	@Test
	void setPValLog10() {
		rtExpressionDataGTI.setPValLog10(1.23456);
		assertEquals("1.23", rtExpressionDataGTI.getPValLog10String());
	}

	@Test
	void setTissueType() {
		rtExpressionDataGTI.setTissueType("tt");
		assertEquals("tt", rtExpressionDataGTI.getTissueType());
	}

	@Test
	void setSampleCount() {
		rtExpressionDataGTI.setSampleCount(12);
		assertEquals(12, (int) rtExpressionDataGTI.getSampleCount());
	}

	@Test
	void setSegmentName() {
		rtExpressionDataGTI.setSegmentName("segmentName");
		assertEquals("segmentName", rtExpressionDataGTI.getSegmentName());
	}

}