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
		rtExpressionDataGTI.setFoldChange(123.456);
		assertEquals(123.456, rtExpressionDataGTI.getFoldChange(), 0.001);
	}

	@Test
	void setPVal() {
		rtExpressionDataGTI.setPVal(124.1234);
		assertEquals(124.1234, rtExpressionDataGTI.getPVal(), 0.001);
	}

	@Test
	void setStdDev() {
		rtExpressionDataGTI.setStdDev(0.123);
		assertEquals(0.123, rtExpressionDataGTI.getStdDev(), 0.001);
	}

	@Test
	void setPValLog10() {
		rtExpressionDataGTI.setPValLog10(123.456);
		assertEquals(123.456, rtExpressionDataGTI.getPValLog10(), 0.001);
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

	@Test
	void setAdjPVal() {
		rtExpressionDataGTI.setAdjPVal(123.456);
		assertEquals(123.456, rtExpressionDataGTI.getAdjPVal(), 0.001);
	}



}