package org.kpmp.geneExpressionSummary;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kpmp.FullDataTypeEnum;
import org.kpmp.dataSummary.DataTypeSummary;
import org.kpmp.geneExpression.RPExpressionDataRepository;
import org.kpmp.geneExpression.RTExpressionDataAllSegmentsRepository;
import org.kpmp.geneExpressionSummary.regionalProteomics.RPParticipantRepository;
import org.kpmp.geneExpressionSummary.regionalTranscriptomics.RTParticipantRepository;
import org.kpmp.geneExpressionSummary.singleCell.SCRNAGeneExpressionExpressionSummaryValue;
import org.kpmp.geneExpressionSummary.singleCell.SCRNAGeneExpressionSummaryRepository;
import org.kpmp.geneExpressionSummary.singleCell.SCRNAParticipantRepository;
import org.kpmp.geneExpressionSummary.singleNucleus.SNRNAGeneExpressionExpressionSummaryValue;
import org.kpmp.geneExpressionSummary.singleNucleus.SNRNAGeneExpressionExpressionSummaryValue2025;
import org.kpmp.geneExpressionSummary.singleNucleus.SNRNAGeneExpressionSummaryRepository;
import org.kpmp.geneExpressionSummary.singleNucleus.SNRNAGeneExpressionSummaryRepository2025;
import org.kpmp.geneExpressionSummary.singleNucleus.SNRNAParticipantRepository;
import org.kpmp.geneExpressionSummary.singleNucleus.SNRNAParticipantRepository2025;
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

	@Mock
	RPExpressionDataRepository rpExpressionDataRepository;

	@Mock
	private RPParticipantRepository rpParticipantRepository;

    @Mock
    private SNRNAParticipantRepository2025 snrnaParticipantRepository2025;

    @Mock
    private SNRNAGeneExpressionSummaryRepository2025 snrnaGeneExpressionRepository2025;

	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		geneExpressionService = new GeneExpressionSummaryService(
				scrnaGeneExpressionRepository,
				snrnaGeneExpressionRepository, snrnaGeneExpressionRepository2025, scrnaParticipantRepository, snrnaParticipantRepository,
				snrnaParticipantRepository2025, rtParticipantRepository, rtExpressionDataAllSegmentsRepository, rpExpressionDataRepository, rpParticipantRepository);
	}

	@AfterEach
	public void tearDown() throws Exception {
		MockitoAnnotations.openMocks(this).close();
		geneExpressionService = null;
	}

	@SuppressWarnings("rawtypes")
	@Test
	public void testGetByDataTypeEnrollmentCategoryAndGene() throws Exception {
		List<SNRNAGeneExpressionExpressionSummaryValue> snResults = Arrays
				.asList(new SNRNAGeneExpressionExpressionSummaryValue());
        when(snrnaGeneExpressionRepository.findByEnrollmentAndGeneAllClusters("gene", "AKI")).thenReturn(snResults);
        List<SNRNAGeneExpressionExpressionSummaryValue2025> snResults2025 = Arrays
                .asList(new SNRNAGeneExpressionExpressionSummaryValue2025());
        when(snrnaGeneExpressionRepository2025.findByEnrollmentAndGeneAllClusters("gene", "AKI")).thenReturn(snResults2025);
		List<SCRNAGeneExpressionExpressionSummaryValue> scResults = Arrays
				.asList(new SCRNAGeneExpressionExpressionSummaryValue());
		when(scrnaGeneExpressionRepository.findByEnrollmentAndGeneAllClusters("gene", "AKI")).thenReturn(scResults);

		List results = geneExpressionService.getByDataTypeEnrollmentCategoryAndGene("", "gene", "AKI", false);

		assertEquals(2, results.size());
		List<? extends GeneExpressionSummary> resultsSC = geneExpressionService.getByDataTypeEnrollmentCategoryAndGene("sc",
				"gene", "aki", false);
		assertEquals(scResults, resultsSC);
		assertEquals("sc", resultsSC.get(0).getDataType());
		List<? extends GeneExpressionSummary> resultsSN = geneExpressionService.getByDataTypeEnrollmentCategoryAndGene("sn",
				"gene", "aki", false);
		assertEquals(snResults, resultsSN);
		assertEquals("sn", resultsSN.get(0).getDataType());

	}

	@SuppressWarnings("rawtypes")
	@Test
	public void testGetExpressionSummaryPerGeneByCellTypeAndEnrollmentCategory() throws Exception {
		List<SNRNAGeneExpressionExpressionSummaryValue> snResults = Arrays
				.asList(new SNRNAGeneExpressionExpressionSummaryValue());
		when(snrnaGeneExpressionRepository.findExpressionSummaryPerGeneByCellTypeAndEnrollmentCategory("cell type", "AKI"))
				.thenReturn(snResults);
        List<SNRNAGeneExpressionExpressionSummaryValue2025> snResults2025 = Arrays
                .asList(new SNRNAGeneExpressionExpressionSummaryValue2025());
        when(snrnaGeneExpressionRepository2025.findExpressionSummaryPerGeneByCellTypeAndEnrollmentCategory("cell type", "AKI"))
                .thenReturn(snResults2025);
		List<SCRNAGeneExpressionExpressionSummaryValue> scResults = Arrays
				.asList(new SCRNAGeneExpressionExpressionSummaryValue());
		when(scrnaGeneExpressionRepository.findExpressionSummaryPerGeneByCellTypeAndEnrollmentCategory("cell type", "AKI"))
				.thenReturn(scResults);

		List results = geneExpressionService.getExpressionSummaryPerGeneByCellTypeAndEnrollmentCategory("", "cell type", "AKI", false);

		assertEquals(2, results.size());
		List<? extends GeneExpressionSummary> resultsSC = geneExpressionService
				.getExpressionSummaryPerGeneByCellTypeAndEnrollmentCategory("sc", "cell type", "AKI", false);
		assertEquals(scResults, resultsSC);
		assertEquals("sc", resultsSC.get(0).getDataType());
		List<? extends GeneExpressionSummary> resultsSN = geneExpressionService
				.getExpressionSummaryPerGeneByCellTypeAndEnrollmentCategory("sn", "cell type", "AKI", false);
		assertEquals(snResults, resultsSN);
		assertEquals("sn", resultsSN.get(0).getDataType());
        List<? extends GeneExpressionSummary> resultsSN2025 = geneExpressionService
				.getExpressionSummaryPerGeneByCellTypeAndEnrollmentCategory("sn", "cell type", "AKI", true);
        assertEquals(snResults, resultsSN2025);
		assertEquals("sn", resultsSN2025.get(0).getDataType());


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
		when(scrnaGeneExpressionRepository.findByEnrollmentAndGeneAllClusters("gene", "all")).thenReturn(summaryValues);

		assertEquals(1, geneExpressionService.getByDataTypeEnrollmentCategoryAndGene("sc", "gene", "all", false).size());
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
		when(snrnaGeneExpressionRepository.findByEnrollmentAndGeneAllClusters("gene", "all")).thenReturn(summaryValues);

		assertEquals(1, geneExpressionService.getByDataTypeEnrollmentCategoryAndGene("sn", "gene", "all", false).size());
	}

	@Test
	public void testFindDataTypesByGeneWhenBothHaveData() throws Exception {
		when(snrnaGeneExpressionRepository.getCountByGene("gene")).thenReturn(1l);
		when(scrnaGeneExpressionRepository.getCountByGene("gene")).thenReturn(1l);
		when(rtExpressionDataAllSegmentsRepository.getCountByGene("gene")).thenReturn(1l);
		when(rpExpressionDataRepository.getCountByGene("gene")).thenReturn(1l);


		List<String> dataTypes = geneExpressionService.findDataTypesByGene("gene", false);

		assertEquals(4, dataTypes.size());
		assertEquals(Arrays.asList(FullDataTypeEnum.SINGLE_CELL.getAbbreviation(),
						FullDataTypeEnum.SINGLE_NUCLEUS.getAbbreviation(), FullDataTypeEnum.REGIONAL_TRANSCRIPTOMICS.getAbbreviation(), FullDataTypeEnum.REGIONAL_PROTEOMICS.getAbbreviation()),
				dataTypes);
		verify(snrnaGeneExpressionRepository).getCountByGene("gene");
		verify(scrnaGeneExpressionRepository).getCountByGene("gene");
		verify(rtExpressionDataAllSegmentsRepository).getCountByGene("gene");
		verify(rpExpressionDataRepository).getCountByGene("gene");
	}

	@Test
	public void testFindDataTypesByGeneWhenSingleCellHasData() throws Exception {
		when(snrnaGeneExpressionRepository.getCountByGene("gene")).thenReturn(0l);
		when(scrnaGeneExpressionRepository.getCountByGene("gene")).thenReturn(1l);
		when(rtExpressionDataAllSegmentsRepository.getCountByGene("gene")).thenReturn((long) 101);

		List<String> dataTypes = geneExpressionService.findDataTypesByGene("gene", false);

		assertEquals(2, dataTypes.size());
		assertEquals(Arrays.asList(FullDataTypeEnum.SINGLE_CELL.getAbbreviation(),
				FullDataTypeEnum.REGIONAL_TRANSCRIPTOMICS.getAbbreviation()), dataTypes);
		verify(snrnaGeneExpressionRepository).getCountByGene("gene");
		verify(scrnaGeneExpressionRepository).getCountByGene("gene");
		verify(rtExpressionDataAllSegmentsRepository).getCountByGene("gene");
	}

	@Test
	public void testFindDataTypesByGeneWhenSingleNucHasData() throws Exception {
		when(snrnaGeneExpressionRepository.getCountByGene("gene")).thenReturn(1l);
		when(scrnaGeneExpressionRepository.getCountByGene("gene")).thenReturn(0l);

		List<String> dataTypes = geneExpressionService.findDataTypesByGene("gene", false);

		assertEquals(1, dataTypes.size());
		assertEquals(Arrays.asList(FullDataTypeEnum.SINGLE_NUCLEUS.getAbbreviation()), dataTypes);
		verify(snrnaGeneExpressionRepository).getCountByGene("gene");
		verify(scrnaGeneExpressionRepository).getCountByGene("gene");
	}

	@Test
	public void testFindDataTypesByGeneWhenNeitherHaveData() throws Exception {
		when(snrnaGeneExpressionRepository.getCountByGene("gene")).thenReturn(0l);
		when(scrnaGeneExpressionRepository.getCountByGene("gene")).thenReturn(0l);

		List<String> dataTypes = geneExpressionService.findDataTypesByGene("gene", false);

		assertEquals(0, dataTypes.size());
		assertEquals(Arrays.asList(), dataTypes);
		verify(snrnaGeneExpressionRepository).getCountByGene("gene");
		verify(scrnaGeneExpressionRepository).getCountByGene("gene");
	}

	@Test
	public void getDataTypeSummaryInformation() throws Exception {

		when(rtParticipantRepository.getCountByEnrollmentCategory("aki")).thenReturn((long) 0);
		when(rtParticipantRepository.getCountByEnrollmentCategory("ckd")).thenReturn((long) 0);
		when(rtParticipantRepository.getCountByEnrollmentCategory("hrt")).thenReturn((long) 0);
		when(rtParticipantRepository.getParticipantCount()).thenReturn((long) 0);
		when(scrnaGeneExpressionRepository.getCountByEnrollment("aki")).thenReturn(Long.valueOf(0));
		when(scrnaGeneExpressionRepository.getCountByEnrollment("ckd")).thenReturn(Long.valueOf(0));
		when(scrnaGeneExpressionRepository.getCountByEnrollment("hrt")).thenReturn(Long.valueOf(0));
		when(scrnaParticipantRepository.getParticipantCount()).thenReturn(Long.valueOf(0));
		when(snrnaGeneExpressionRepository.getCountByEnrollment("aki")).thenReturn(Long.valueOf(0));
		when(snrnaGeneExpressionRepository.getCountByEnrollment("ckd")).thenReturn(Long.valueOf(0));
		when(snrnaGeneExpressionRepository.getCountByEnrollment("hrt")).thenReturn(Long.valueOf(0));
		when(snrnaParticipantRepository.getParticipantCount()).thenReturn(Long.valueOf(0));
		when(rpParticipantRepository.getCountByEnrollmentCategory("aki")).thenReturn((long) 0);
		when(rpParticipantRepository.getCountByEnrollmentCategory("ckd")).thenReturn((long) 0);
		when(rpParticipantRepository.getCountByEnrollmentCategory("hrt")).thenReturn((long) 0);
		when(rpParticipantRepository.getParticipantCount()).thenReturn((long) 0);

		List<DataTypeSummary> result = geneExpressionService.getDataTypeSummaryInformation(false);
		assertEquals(4, result.size());
		DataTypeSummary resultDataSC = result.get(0);
		DataTypeSummary resultDataSN = result.get(1);
		DataTypeSummary resultDataRt = result.get(2);
		DataTypeSummary resultDataRp = result.get(3);

		assertEquals(Long.valueOf(0), resultDataSC.getAkiCount());
		assertEquals(Long.valueOf(0), resultDataSC.getCkdCount());
		assertEquals(Long.valueOf(0), resultDataSC.getHrtCount());
		assertEquals(Long.valueOf(0), resultDataSN.getAkiCount());
		assertEquals(Long.valueOf(0), resultDataSN.getCkdCount());
		assertEquals(Long.valueOf(0), resultDataRt.getHrtCount());
		assertEquals(Long.valueOf(0), resultDataRt.getAkiCount());
		assertEquals(Long.valueOf(0), resultDataRt.getCkdCount());
		assertEquals(Long.valueOf(0), resultDataRt.getHrtCount());
		assertEquals(Long.valueOf(0), resultDataRp.getHrtCount());
		assertEquals(Long.valueOf(0), resultDataRp.getAkiCount());
		assertEquals(Long.valueOf(0), resultDataRp.getCkdCount());
		assertEquals(Long.valueOf(0), resultDataRp.getHrtCount());

		assertEquals("sc", resultDataSC.getDataTypeShort());
		assertEquals("sn", resultDataSN.getDataTypeShort());
		assertEquals("rt", resultDataRt.getDataTypeShort());
		assertEquals("rp", resultDataRp.getDataTypeShort());
		assertEquals(Long.valueOf(0), resultDataSN.getParticipantCount());

	}
}
