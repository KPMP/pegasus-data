package org.kpmp.geneExpressionSummary;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kpmp.geneExpressionSummary.singleCell.SCRNAGeneExpressionExpressionSummaryValueNewData;

public class SCRNAGeneExpressionSummaryValueNewDataTest {

	private SCRNAGeneExpressionExpressionSummaryValueNewData scrnaGeneExpressionValue;

	@BeforeEach
	public void setUp() throws Exception {
		scrnaGeneExpressionValue = new SCRNAGeneExpressionExpressionSummaryValueNewData();
	}

	@AfterEach
	public void tearDown() throws Exception {
		scrnaGeneExpressionValue = null;
	}

	@Test
	public void testGetDataType() throws Exception {
		assertEquals("sc", scrnaGeneExpressionValue.getDataType());
	}

	@SuppressWarnings("null")
	@Test
	public void testSetAvgExpression() throws Exception {
		scrnaGeneExpressionValue.setAvgExp(0.1);
		assertEquals(0.1, scrnaGeneExpressionValue.getAvgExp(), 0.001);

	}

	@Test
	public void testSetCluster() throws Exception {
		scrnaGeneExpressionValue.setCluster("cluster");
		assertEquals("cluster", scrnaGeneExpressionValue.getCluster());
	}

	@SuppressWarnings("null")
	@Test
	public void testSetFoldChange() throws Exception {
		scrnaGeneExpressionValue.setFoldChange(0.2);
		assertEquals(0.2, scrnaGeneExpressionValue.getFoldChange(), 0.001);
	}

	@Test
	public void testSetGene() throws Exception {
		scrnaGeneExpressionValue.setGene("gene");
		assertEquals("gene", scrnaGeneExpressionValue.getGene());

	}

	@SuppressWarnings("null")
	@Test
	public void testSetId() throws Exception {
		scrnaGeneExpressionValue.setId(1);
		assertEquals(1, scrnaGeneExpressionValue.getId(), 0.001);

	}

	@SuppressWarnings("null")
	@Test
	public void testSetPct1() throws Exception {
		scrnaGeneExpressionValue.setPct1(0.3);
		assertEquals(0.3, scrnaGeneExpressionValue.getPct1(), 0.001);

	}

	@SuppressWarnings("null")
	@Test
	public void testSetPct2() throws Exception {
		scrnaGeneExpressionValue.setPct2(0.4);
		assertEquals(0.4, scrnaGeneExpressionValue.getPct2(), 0.001);

	}

	@SuppressWarnings("null")
	@Test
	public void testSetPVal() throws Exception {
		scrnaGeneExpressionValue.setPVal(0.5);
		assertEquals(0.5, scrnaGeneExpressionValue.getPVal(), 0.001);

	}

	@SuppressWarnings("null")
	@Test
	public void testSetPValAdj() throws Exception {
		scrnaGeneExpressionValue.setPValAdj(0.6);
		assertEquals(0.6, scrnaGeneExpressionValue.getPValAdj(), 0.001);

	}

	@Test
	public void testSetEnrollmentCategory() throws Exception {
		scrnaGeneExpressionValue.setEnrollmentCategory("tissue");
		assertEquals("tissue", scrnaGeneExpressionValue.getEnrollmentCategory());
	}

	@Test
	public void testSetClusterName() throws Exception {
		scrnaGeneExpressionValue.setClusterName("cluster name");
		assertEquals("cluster name", scrnaGeneExpressionValue.getClusterName());
	}

	@Test
	public void testSetCellCount() throws Exception {
		scrnaGeneExpressionValue.setCellCount(42);
		assertEquals(Integer.valueOf(42), scrnaGeneExpressionValue.getCellCount());
	}

	@Test
	public void testEquals() throws Exception {
		scrnaGeneExpressionValue.setGene("gene");
		scrnaGeneExpressionValue.setCluster("cluster");
		SCRNAGeneExpressionExpressionSummaryValueNewData value = new SCRNAGeneExpressionExpressionSummaryValueNewData();
		value.setGene("gene");
		value.setCluster("cluster");
		assertEquals(value, scrnaGeneExpressionValue);
	}

}
