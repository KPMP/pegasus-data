package org.kpmp.geneExpression;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RTExpressionDataAllSegmentsTest {

	RTExpressionDataAllSegments rtExpressionDataAllSegments;

	@BeforeEach
	void setUp() {
		rtExpressionDataAllSegments = new RTExpressionDataAllSegments();
	}

	@AfterEach
	void tearDown() {
		rtExpressionDataAllSegments = null;
	}

	@Test
	void setId() {
		rtExpressionDataAllSegments.setId(123);
		assertEquals(rtExpressionDataAllSegments.getId(), 123, 0.001);
	}

	@Test
	void setGeneSymbol() {
		rtExpressionDataAllSegments.setGeneSymbol("gene");
		assertEquals("gene", rtExpressionDataAllSegments.getGeneSymbol());
	}

	@Test
	void setSegment() {
		rtExpressionDataAllSegments.setSegment("segment");
		assertEquals("segment", rtExpressionDataAllSegments.getSegment());
	}

	@Test
	void setFoldChange() {
		rtExpressionDataAllSegments.setFoldChange(123.456);
		assertEquals(123.456, rtExpressionDataAllSegments.getFoldChange(), 0.001);
	}

	@Test
	void setPVal() {
		rtExpressionDataAllSegments.setPVal(124.1234);
		assertEquals(124.1234, rtExpressionDataAllSegments.getPVal(), 0.001);
	}

	@Test
	void setStdDev() {
		rtExpressionDataAllSegments.setStdDev(0.123);
		assertEquals(0.123, rtExpressionDataAllSegments.getStdDev(), 0.001);
	}

	@Test
	void setPValLog10() {
		rtExpressionDataAllSegments.setPValLog10(123.456);
		assertEquals(123.456, rtExpressionDataAllSegments.getPValLog10(), 0.001);
	}

	@Test
	void setTissueType() {
		rtExpressionDataAllSegments.setTissueType("tt");
		assertEquals("tt", rtExpressionDataAllSegments.getTissueType());
	}

	@Test
	void setSampleCount() {
		rtExpressionDataAllSegments.setSampleCount(12);
		assertEquals(12, (int) rtExpressionDataAllSegments.getSampleCount());
	}

	@Test
	void setSegmentName() {
		rtExpressionDataAllSegments.setSegmentName("segmentName");
		assertEquals("segmentName", rtExpressionDataAllSegments.getSegmentName());
	}

	@Test
	void setAdjPVal() {
		rtExpressionDataAllSegments.setAdjPVal(123.456);
		assertEquals(123.456, rtExpressionDataAllSegments.getAdjPVal(), 0.001);
	}

}