package org.kpmp.geneExpressionSummary;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class GeneExpressionSummaryServiceTest {

	private GeneExpressionSummaryService geneExpressionService;
	@Mock
	private SNRNAGeneExpressionSummaryRepository snrnaGeneExpressionRepository;
	@Mock
	private SCRNAGeneExpressionSummaryRepository scrnaGeneExpressionRepository;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		geneExpressionService = new GeneExpressionSummaryService(scrnaGeneExpressionRepository,
				snrnaGeneExpressionRepository);
	}

	@After
	public void tearDown() throws Exception {
		geneExpressionService = null;
	}

	@Test
	public void testGetByDataTypeTissueTypeAndGene() throws Exception {
		List<SNRNAGeneExpressionExpressionSummaryValue> snResults = Arrays.asList(new SNRNAGeneExpressionExpressionSummaryValue());
		when(snrnaGeneExpressionRepository.findByTissueAndGeneAllClusters("gene", "aki")).thenReturn(snResults);
		List<SCRNAGeneExpressionExpressionSummaryValue> scResults = Arrays.asList(new SCRNAGeneExpressionExpressionSummaryValue());
		when(scrnaGeneExpressionRepository.findByTissueAndGeneAllClusters("gene", "aki")).thenReturn(scResults);
		List results = geneExpressionService.getByDataTypeTissueTypeAndGene("", "gene", "aki");
		assertEquals(2, results.size());
		List<? extends GeneExpressionSummary> resultsSC = geneExpressionService.getByDataTypeTissueTypeAndGene("sc",
				"gene", "aki");
		assertEquals(scResults, resultsSC);
		assertEquals("sc", resultsSC.get(0).getDataType());
		List<? extends GeneExpressionSummary> resultsSN = geneExpressionService.getByDataTypeTissueTypeAndGene("sn",
				"gene", "aki");
		assertEquals(snResults, resultsSN);
		assertEquals("sn", resultsSN.get(0).getDataType());

	}

	@Test
	public void testGetExpressionSummaryPerGeneByCellTypeAndTissueType() throws Exception {
		List<SNRNAGeneExpressionExpressionSummaryValue> snResults = Arrays.asList(new SNRNAGeneExpressionExpressionSummaryValue());
		when(snrnaGeneExpressionRepository.findExpressionSummaryPerGeneByCellTypeAndTissueType("cell type", "aki")).thenReturn(snResults);
		List<SCRNAGeneExpressionExpressionSummaryValue> scResults = Arrays.asList(new SCRNAGeneExpressionExpressionSummaryValue());
		when(scrnaGeneExpressionRepository.findExpressionSummaryPerGeneByCellTypeAndTissueType("cell type", "aki")).thenReturn(scResults);
		List results = geneExpressionService.getExpressionSummaryPerGeneByCellTypeAndTissueType("", "cell type", "aki");
		assertEquals(2, results.size());
		List<? extends GeneExpressionSummary> resultsSC = geneExpressionService.getExpressionSummaryPerGeneByCellTypeAndTissueType("sc", "cell type", "aki");
		assertEquals(scResults, resultsSC);
		assertEquals("sc", resultsSC.get(0).getDataType());
		List<? extends GeneExpressionSummary> resultsSN = geneExpressionService.getExpressionSummaryPerGeneByCellTypeAndTissueType("sn", "cell type", "aki");
		assertEquals(snResults, resultsSN);
		assertEquals("sn", resultsSN.get(0).getDataType());
	}


}
