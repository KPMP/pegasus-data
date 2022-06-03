package org.kpmp.geneExpressionSummary;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kpmp.DataTypeEnum;
import org.kpmp.datasetSummary.DatasetSummary;
import org.kpmp.geneExpression.RTExpressionDataAllSegmentsRepository;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class GeneExpressionSummaryServiceTest {

	private GeneExpressionSummaryService geneExpressionService;
	@Mock
	private SNRNAGeneExpressionSummaryRepository snrnaGeneExpressionRepository;
	@Mock
	private SCRNAGeneExpressionSummaryRepository scrnaGeneExpressionRepository;
	@Mock
	private SCRNAParticipantRepository scrnaParticipantRepository;
	@Mock
	private SNRNAParticipantRepository snrnaParticipantRepository;
	@Mock
	private RTExpressionDataAllSegmentsRepository rtExpressionDataAllSegmentsRepository;
	@Mock
	private RTParticipantRepository rtParticipantRepository;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		geneExpressionService = new GeneExpressionSummaryService(scrnaGeneExpressionRepository,
				snrnaGeneExpressionRepository, scrnaParticipantRepository, snrnaParticipantRepository,
				rtParticipantRepository, rtExpressionDataAllSegmentsRepository);
	}

	@After
	public void tearDown() throws Exception {
		MockitoAnnotations.openMocks(this).close();
		geneExpressionService = null;
	}

	@SuppressWarnings("rawtypes")
	@Test
	public void testGetByDataTypeTissueTypeAndGene() throws Exception {
		List<SNRNAGeneExpressionExpressionSummaryValue> snResults = Arrays
				.asList(new SNRNAGeneExpressionExpressionSummaryValue());
		when(snrnaGeneExpressionRepository.findByTissueAndGeneAllClusters("gene", "AKI")).thenReturn(snResults);
		List<SCRNAGeneExpressionExpressionSummaryValue> scResults = Arrays
				.asList(new SCRNAGeneExpressionExpressionSummaryValue());
		when(scrnaGeneExpressionRepository.findByTissueAndGeneAllClusters("gene", "AKI")).thenReturn(scResults);

		List results = geneExpressionService.getByDataTypeTissueTypeAndGene("", "gene", "AKI");

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

	@SuppressWarnings("rawtypes")
	@Test
	public void testGetExpressionSummaryPerGeneByCellTypeAndTissueType() throws Exception {
		List<SNRNAGeneExpressionExpressionSummaryValue> snResults = Arrays
				.asList(new SNRNAGeneExpressionExpressionSummaryValue());
		when(snrnaGeneExpressionRepository.findExpressionSummaryPerGeneByCellTypeAndTissueType("cell type", "AKI"))
				.thenReturn(snResults);
		List<SCRNAGeneExpressionExpressionSummaryValue> scResults = Arrays
				.asList(new SCRNAGeneExpressionExpressionSummaryValue());
		when(scrnaGeneExpressionRepository.findExpressionSummaryPerGeneByCellTypeAndTissueType("cell type", "AKI"))
				.thenReturn(scResults);

		List results = geneExpressionService.getExpressionSummaryPerGeneByCellTypeAndTissueType("", "cell type", "AKI");

		assertEquals(2, results.size());
		List<? extends GeneExpressionSummary> resultsSC = geneExpressionService
				.getExpressionSummaryPerGeneByCellTypeAndTissueType("sc", "cell type", "AKI");
		assertEquals(scResults, resultsSC);
		assertEquals("sc", resultsSC.get(0).getDataType());
		List<? extends GeneExpressionSummary> resultsSN = geneExpressionService
				.getExpressionSummaryPerGeneByCellTypeAndTissueType("sn", "cell type", "AKI");
		assertEquals(snResults, resultsSN);
		assertEquals("sn", resultsSN.get(0).getDataType());
	}

	@Test
	public void testSCDuplicatesAreRemoved() throws Exception {
		SCRNAGeneExpressionExpressionSummaryValue scv = new SCRNAGeneExpressionExpressionSummaryValue();
		SCRNAGeneExpressionExpressionSummaryValue scv2 = new SCRNAGeneExpressionExpressionSummaryValue();
		scv.setGene("gene");
		scv2.setGene("gene");
		scv.setCluster("cluster");
		scv2.setCluster("cluster");
		List<SCRNAGeneExpressionExpressionSummaryValue> summaryValues = new ArrayList<>();
		summaryValues.add(scv);
		summaryValues.add(scv2);
		when(scrnaGeneExpressionRepository.findByTissueAndGeneAllClusters("gene", "all")).thenReturn(summaryValues);

		assertEquals(1, geneExpressionService.getByDataTypeTissueTypeAndGene("sc", "gene", "all").size());
	}

	@Test
	public void testSNDuplicatesAreRemoved() throws Exception {
		SNRNAGeneExpressionExpressionSummaryValue scv = new SNRNAGeneExpressionExpressionSummaryValue();
		SNRNAGeneExpressionExpressionSummaryValue scv2 = new SNRNAGeneExpressionExpressionSummaryValue();
		scv.setGene("gene");
		scv2.setGene("gene");
		scv.setCluster("cluster");
		scv2.setCluster("cluster");
		List<SNRNAGeneExpressionExpressionSummaryValue> summaryValues = new ArrayList<>();
		summaryValues.add(scv);
		summaryValues.add(scv2);
		when(snrnaGeneExpressionRepository.findByTissueAndGeneAllClusters("gene", "all")).thenReturn(summaryValues);

		assertEquals(1, geneExpressionService.getByDataTypeTissueTypeAndGene("sn", "gene", "all").size());
	}

	@Test
	public void testFindDataTypesByGeneWhenBothHaveData() throws Exception {
		when(snrnaGeneExpressionRepository.getCountByGene("gene")).thenReturn(1l);
		when(scrnaGeneExpressionRepository.getCountByGene("gene")).thenReturn(1l);
		when(rtExpressionDataAllSegmentsRepository.getCountByGene("gene")).thenReturn(1l);

		List<String> dataTypes = geneExpressionService.findDataTypesByGene("gene");

		assertEquals(3, dataTypes.size());
		assertEquals(Arrays.asList(DataTypeEnum.SINGLE_CELL.getAbbreviation(),
				DataTypeEnum.SINGLE_NUCLEUS.getAbbreviation(), DataTypeEnum.REGIONAL_TRANSCRIPTOMICS.getAbbreviation()),
				dataTypes);
		verify(snrnaGeneExpressionRepository).getCountByGene("gene");
		verify(scrnaGeneExpressionRepository).getCountByGene("gene");
		verify(rtExpressionDataAllSegmentsRepository).getCountByGene("gene");
	}

	@Test
	public void testFindDataTypesByGeneWhenSingleCellHasData() throws Exception {
		when(snrnaGeneExpressionRepository.getCountByGene("gene")).thenReturn(0l);
		when(scrnaGeneExpressionRepository.getCountByGene("gene")).thenReturn(1l);
		when(rtExpressionDataAllSegmentsRepository.getCountByGene("gene")).thenReturn((long) 101);

		List<String> dataTypes = geneExpressionService.findDataTypesByGene("gene");

		assertEquals(2, dataTypes.size());
		assertEquals(Arrays.asList(DataTypeEnum.SINGLE_CELL.getAbbreviation(),
				DataTypeEnum.REGIONAL_TRANSCRIPTOMICS.getAbbreviation()), dataTypes);
		verify(snrnaGeneExpressionRepository).getCountByGene("gene");
		verify(scrnaGeneExpressionRepository).getCountByGene("gene");
		verify(rtExpressionDataAllSegmentsRepository).getCountByGene("gene");
	}

	@Test
	public void testFindDataTypesByGeneWhenSingleNucHasData() throws Exception {
		when(snrnaGeneExpressionRepository.getCountByGene("gene")).thenReturn(1l);
		when(scrnaGeneExpressionRepository.getCountByGene("gene")).thenReturn(0l);

		List<String> dataTypes = geneExpressionService.findDataTypesByGene("gene");

		assertEquals(1, dataTypes.size());
		assertEquals(Arrays.asList(DataTypeEnum.SINGLE_NUCLEUS.getAbbreviation()), dataTypes);
		verify(snrnaGeneExpressionRepository).getCountByGene("gene");
		verify(scrnaGeneExpressionRepository).getCountByGene("gene");
	}

	@Test
	public void testFindDataTypesByGeneWhenNeitherHaveData() throws Exception {
		when(snrnaGeneExpressionRepository.getCountByGene("gene")).thenReturn(0l);
		when(scrnaGeneExpressionRepository.getCountByGene("gene")).thenReturn(0l);

		List<String> dataTypes = geneExpressionService.findDataTypesByGene("gene");

		assertEquals(0, dataTypes.size());
		assertEquals(Arrays.asList(), dataTypes);
		verify(snrnaGeneExpressionRepository).getCountByGene("gene");
		verify(scrnaGeneExpressionRepository).getCountByGene("gene");
	}

	@Test
	public void testGetGeneDatasetInformation() throws Exception {

		when(rtParticipantRepository.getCountByTissueType("aki")).thenReturn((long) 0);
		when(rtParticipantRepository.getCountByTissueType("ckd")).thenReturn((long) 0);
		when(rtParticipantRepository.getCountByTissueType("hrt")).thenReturn((long) 0);
		when(rtParticipantRepository.getParticipantCount()).thenReturn((long) 0);
		when(scrnaGeneExpressionRepository.getCountByTissue("aki")).thenReturn(Long.valueOf(0));
		when(scrnaGeneExpressionRepository.getCountByTissue("ckd")).thenReturn(Long.valueOf(0));
		when(scrnaGeneExpressionRepository.getCountByTissue("hrt")).thenReturn(Long.valueOf(0));
		when(scrnaParticipantRepository.getParticipantCount()).thenReturn(Long.valueOf(0));
		when(snrnaGeneExpressionRepository.getCountByTissue("aki")).thenReturn(Long.valueOf(0));
		when(snrnaGeneExpressionRepository.getCountByTissue("ckd")).thenReturn(Long.valueOf(0));
		when(snrnaGeneExpressionRepository.getCountByTissue("hrt")).thenReturn(Long.valueOf(0));
		when(snrnaParticipantRepository.getParticipantCount()).thenReturn(Long.valueOf(0));

		List<DatasetSummary> result = geneExpressionService.getGeneDatasetInformation("AAA");
		DatasetSummary resultDataSC = result.get(0);
		DatasetSummary resultDataSN = result.get(1);
		DatasetSummary resultDataRt = result.get(2);

		assertEquals(Long.valueOf(0), resultDataSC.getAkiCount());
		assertEquals(Long.valueOf(0), resultDataSC.getCkdCount());
		assertEquals(Long.valueOf(0), resultDataSC.getHrtCount());
		assertEquals(Long.valueOf(0), resultDataSN.getAkiCount());
		assertEquals(Long.valueOf(0), resultDataSN.getCkdCount());
		assertEquals(Long.valueOf(0), resultDataRt.getHrtCount());
		assertEquals(Long.valueOf(0), resultDataRt.getAkiCount());
		assertEquals(Long.valueOf(0), resultDataRt.getCkdCount());
		assertEquals(Long.valueOf(0), resultDataRt.getHrtCount());
		assertEquals("sc", resultDataSC.getDataTypeShort());
		assertEquals("sn", resultDataSN.getDataTypeShort());
		assertEquals("rt", resultDataRt.getDataTypeShort());
		assertEquals(Long.valueOf(0), resultDataSN.getParticipantCount());

	}
}
