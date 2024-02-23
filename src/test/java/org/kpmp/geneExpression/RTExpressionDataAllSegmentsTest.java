package org.kpmp.geneExpression;

import static org.junit.Assert.assertEquals;

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
		rtExpressionDataAllSegments.setFoldChange(.000999);
		assertEquals("0.000999", rtExpressionDataAllSegments.getFoldChange());

		rtExpressionDataAllSegments.setFoldChange(0.0000506);
		assertEquals("0.0000506", rtExpressionDataAllSegments.getFoldChange());

		rtExpressionDataAllSegments.setFoldChange(0.0000000050655635858229);
		assertEquals("5.07E-9", rtExpressionDataAllSegments.getFoldChange());
	}

	@Test
	void setPVal() {
		rtExpressionDataAllSegments.setPVal(.0000001241234);
		assertEquals("1.24E-7", rtExpressionDataAllSegments.getPVal());
	}

	@Test
	void setStdDev() {
		rtExpressionDataAllSegments.setStdDev(0.123);
		assertEquals(0.123, rtExpressionDataAllSegments.getStdDev(), 0.001);
	}

	@Test
	void setPValLog10() {
		rtExpressionDataAllSegments.setPValLog10(.00123456);
		assertEquals("0.00123", rtExpressionDataAllSegments.getPValLog10());
		rtExpressionDataAllSegments.setPValLog10(0.0000000000455d);
		assertEquals("4.55E-11", rtExpressionDataAllSegments.getPValLog10());
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

}