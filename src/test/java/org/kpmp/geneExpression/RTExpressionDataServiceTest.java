package org.kpmp.geneExpression;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kpmp.TissueTypeEnum;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class RTExpressionDataServiceTest {

	@Mock
	private RTExpressionDataAllSegmentsRepository rtExpressionDataAllSegmentsRepository;
	@Mock
	private RTExpressionDataGTIRepository rtExpressionDataGTIRepository;
	private RTExpressionDataService rtExpressionDataService;

	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		rtExpressionDataService = new RTExpressionDataService(rtExpressionDataAllSegmentsRepository,
				rtExpressionDataGTIRepository);
	}

	@AfterEach
	public void tearDown() throws Exception {
		MockitoAnnotations.openMocks(this).close();
		rtExpressionDataService = null;
	}

	@Test
	public void testGetByComparisonTypeAndGeneSymbolPerTissue_all() throws Exception {

		RTExpressionDataAllSegments allallData = new RTExpressionDataAllSegments();
		List<RTExpressionDataAllSegments> allallDataList = Arrays.asList(allallData);
		RTExpressionDataGTI gallData = new RTExpressionDataGTI();
		List<RTExpressionDataGTI> gallDataList = Arrays.asList(gallData);

		when(rtExpressionDataAllSegmentsRepository.findByGeneSymbolAndTissueTypeWithCounts("gene", "all"))
				.thenReturn(allallDataList);
		when(rtExpressionDataGTIRepository.findByGeneSymbolAndTissueTypeWithCounts("gene", "all"))
				.thenReturn(gallDataList);

		RTExpressionByTissueType rtExpressionByTissueTypeGti = rtExpressionDataService
				.getByComparisonTypeAndGeneSymbolPerTissue("glom_tub", "gene");
		RTExpressionByTissueType rtExpressionByTissueTypeAll = rtExpressionDataService
				.getByComparisonTypeAndGeneSymbolPerTissue("all_segments", "gene");

		assertEquals(rtExpressionByTissueTypeAll.getAll(), allallDataList);
		assertEquals(rtExpressionByTissueTypeGti.getAll(), gallDataList);

	}

	@Test
	public void testGetByComparisonTypeAndGeneSymbolPerTissue_hrt() throws Exception {

		RTExpressionDataAllSegments allhrtData = new RTExpressionDataAllSegments();
		List<RTExpressionDataAllSegments> allhrtDataList = Arrays.asList(allhrtData);
		RTExpressionDataGTI ghrtData = new RTExpressionDataGTI();
		List<RTExpressionDataGTI> ghrtDataList = Arrays.asList(ghrtData);

		when(rtExpressionDataAllSegmentsRepository.findByGeneSymbolAndTissueTypeWithCounts("gene", "Healthy Reference"))
				.thenReturn(allhrtDataList);
		when(rtExpressionDataGTIRepository.findByGeneSymbolAndTissueTypeWithCounts("gene", "Healthy Reference"))
				.thenReturn(ghrtDataList);

		RTExpressionByTissueType rtExpressionByTissueTypeAll = rtExpressionDataService
				.getByComparisonTypeAndGeneSymbolPerTissue("all_segments", "gene");
		RTExpressionByTissueType rtExpressionByTissueTypeGti = rtExpressionDataService
				.getByComparisonTypeAndGeneSymbolPerTissue("glom_tub", "gene");

		assertEquals(rtExpressionByTissueTypeAll.getHrt(), allhrtDataList);
		assertEquals(rtExpressionByTissueTypeGti.getHrt(), ghrtDataList);

	}

	@Test
	public void testGetByComparisonTypeAndGeneSymbolPerTissue_ckd() throws Exception {
		RTExpressionDataAllSegments allckdData = new RTExpressionDataAllSegments();
		List<RTExpressionDataAllSegments> allckdDataList = Arrays.asList(allckdData);
		RTExpressionDataGTI gckdData = new RTExpressionDataGTI();
		List<RTExpressionDataGTI> gckdDataList = Arrays.asList(gckdData);

		when(rtExpressionDataAllSegmentsRepository.findByGeneSymbolAndTissueTypeWithCounts("gene", "CKD"))
				.thenReturn(allckdDataList);
		when(rtExpressionDataGTIRepository.findByGeneSymbolAndTissueTypeWithCounts("gene", "CKD"))
				.thenReturn(gckdDataList);

		RTExpressionByTissueType rtExpressionByTissueTypeAll = rtExpressionDataService
				.getByComparisonTypeAndGeneSymbolPerTissue("all_segments", "gene");
		RTExpressionByTissueType rtExpressionByTissueTypeGti = rtExpressionDataService
				.getByComparisonTypeAndGeneSymbolPerTissue("glom_tub", "gene");

		assertEquals(rtExpressionByTissueTypeAll.getCkd(), allckdDataList);
		assertEquals(rtExpressionByTissueTypeGti.getCkd(), gckdDataList);

	}

	@Test
	public void testGetByComparisonTypeAndGeneSymbolPerTissue_aki() throws Exception {
		RTExpressionDataAllSegments allAkiData = new RTExpressionDataAllSegments();
		List<RTExpressionDataAllSegments> allAkiDataList = Arrays.asList(allAkiData);
		RTExpressionDataGTI gAkiData = new RTExpressionDataGTI();
		List<RTExpressionDataGTI> gAkiDataList = Arrays.asList(gAkiData);

		when(rtExpressionDataAllSegmentsRepository.findByGeneSymbolAndTissueTypeWithCounts("gene", "AKI"))
				.thenReturn(allAkiDataList);
		when(rtExpressionDataGTIRepository.findByGeneSymbolAndTissueTypeWithCounts("gene", "AKI"))
				.thenReturn(gAkiDataList);

		RTExpressionByTissueType rtExpressionByTissueTypeGti = rtExpressionDataService
				.getByComparisonTypeAndGeneSymbolPerTissue("glom_tub", "gene");
		RTExpressionByTissueType rtExpressionByTissueTypeAll = rtExpressionDataService
				.getByComparisonTypeAndGeneSymbolPerTissue("all_segments", "gene");

		assertEquals(rtExpressionByTissueTypeAll.getAki(), allAkiDataList);
		assertEquals(rtExpressionByTissueTypeGti.getAki(), gAkiDataList);

	}

	@Test
	public void testGetByComparisonTypeAndGeneSymbolPerTissue_dmr() throws Exception {
		RTExpressionDataAllSegments allDmrData = new RTExpressionDataAllSegments();
		List<RTExpressionDataAllSegments> allDmrDataList = Arrays.asList(allDmrData);
		RTExpressionDataGTI gDmrData = new RTExpressionDataGTI();
		List<RTExpressionDataGTI> gDmrDataList = Arrays.asList(gDmrData);

		when(rtExpressionDataAllSegmentsRepository.findByGeneSymbolAndTissueTypeWithCounts("gene",
				TissueTypeEnum.DMR.getParticipantTissueType())).thenReturn(allDmrDataList);
		when(rtExpressionDataGTIRepository.findByGeneSymbolAndTissueTypeWithCounts("gene",
				TissueTypeEnum.DMR.getParticipantTissueType())).thenReturn(gDmrDataList);

		RTExpressionByTissueType rtExpressionByTissueTypeGti = rtExpressionDataService
				.getByComparisonTypeAndGeneSymbolPerTissue("glom_tub", "gene");
		RTExpressionByTissueType rtExpressionByTissueTypeAll = rtExpressionDataService
				.getByComparisonTypeAndGeneSymbolPerTissue("all_segments", "gene");

		assertEquals(rtExpressionByTissueTypeAll.getDmr(), allDmrDataList);
		assertEquals(rtExpressionByTissueTypeGti.getDmr(), gDmrDataList);

	}
}
