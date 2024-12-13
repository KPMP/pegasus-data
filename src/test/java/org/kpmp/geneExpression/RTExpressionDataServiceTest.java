package org.kpmp.geneExpression;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kpmp.EnrollmentCategoryEnum;
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
	public void testGetByComparisonTypeAndGeneSymbolPerEnrollment_all() throws Exception {

		RTExpressionDataAllSegments allallData = new RTExpressionDataAllSegments();
		List<RTExpressionDataAllSegments> allallDataList = Arrays.asList(allallData);
		RTExpressionDataGTI gallData = new RTExpressionDataGTI();
		List<RTExpressionDataGTI> gallDataList = Arrays.asList(gallData);

		when(rtExpressionDataAllSegmentsRepository.findByGeneSymbolAndEnrollmentCategoryWithCounts("gene", "all"))
				.thenReturn(allallDataList);
		when(rtExpressionDataGTIRepository.findByGeneSymbolAndEnrollmentCategoryWithCounts("gene", "all"))
				.thenReturn(gallDataList);

		RTExpressionByEnrollmentCategory rtExpressionByEnrollmentCategoryGti = rtExpressionDataService
				.getByComparisonTypeAndGeneSymbolPerEnrollment("glom_tub", "gene");
		RTExpressionByEnrollmentCategory rtExpressionByEnrollmentCategoryAll = rtExpressionDataService
				.getByComparisonTypeAndGeneSymbolPerEnrollment("all_segments", "gene");

		assertEquals(rtExpressionByEnrollmentCategoryAll.getAll(), allallDataList);
		assertEquals(rtExpressionByEnrollmentCategoryGti.getAll(), gallDataList);

	}

	@Test
	public void testGetByComparisonTypeAndGeneSymbolPerEnrollment_hrt() throws Exception {

		RTExpressionDataAllSegments allhrtData = new RTExpressionDataAllSegments();
		List<RTExpressionDataAllSegments> allhrtDataList = Arrays.asList(allhrtData);
		RTExpressionDataGTI ghrtData = new RTExpressionDataGTI();
		List<RTExpressionDataGTI> ghrtDataList = Arrays.asList(ghrtData);

		when(rtExpressionDataAllSegmentsRepository.findByGeneSymbolAndEnrollmentCategoryWithCounts("gene", "Healthy Reference"))
				.thenReturn(allhrtDataList);
		when(rtExpressionDataGTIRepository.findByGeneSymbolAndEnrollmentCategoryWithCounts("gene", "Healthy Reference"))
				.thenReturn(ghrtDataList);

		RTExpressionByEnrollmentCategory rtExpressionByEnrollmentCategoryAll = rtExpressionDataService
				.getByComparisonTypeAndGeneSymbolPerEnrollment("all_segments", "gene");
		RTExpressionByEnrollmentCategory rtExpressionByEnrollmentCategoryGti = rtExpressionDataService
				.getByComparisonTypeAndGeneSymbolPerEnrollment("glom_tub", "gene");

		assertEquals(rtExpressionByEnrollmentCategoryAll.getHrt(), allhrtDataList);
		assertEquals(rtExpressionByEnrollmentCategoryGti.getHrt(), ghrtDataList);

	}

	@Test
	public void testGetByComparisonTypeAndGeneSymbolPerEnrollment_ckd() throws Exception {
		RTExpressionDataAllSegments allckdData = new RTExpressionDataAllSegments();
		List<RTExpressionDataAllSegments> allckdDataList = Arrays.asList(allckdData);
		RTExpressionDataGTI gckdData = new RTExpressionDataGTI();
		List<RTExpressionDataGTI> gckdDataList = Arrays.asList(gckdData);

		when(rtExpressionDataAllSegmentsRepository.findByGeneSymbolAndEnrollmentCategoryWithCounts("gene", "CKD"))
				.thenReturn(allckdDataList);
		when(rtExpressionDataGTIRepository.findByGeneSymbolAndEnrollmentCategoryWithCounts("gene", "CKD"))
				.thenReturn(gckdDataList);

		RTExpressionByEnrollmentCategory rtExpressionByEnrollmentCategoryAll = rtExpressionDataService
				.getByComparisonTypeAndGeneSymbolPerEnrollment("all_segments", "gene");
		RTExpressionByEnrollmentCategory rtExpressionByEnrollmentCategoryGti = rtExpressionDataService
				.getByComparisonTypeAndGeneSymbolPerEnrollment("glom_tub", "gene");

		assertEquals(rtExpressionByEnrollmentCategoryAll.getCkd(), allckdDataList);
		assertEquals(rtExpressionByEnrollmentCategoryGti.getCkd(), gckdDataList);

	}

	@Test
	public void testGetByComparisonTypeAndGeneSymbolPerEnrollment_aki() throws Exception {
		RTExpressionDataAllSegments allAkiData = new RTExpressionDataAllSegments();
		List<RTExpressionDataAllSegments> allAkiDataList = Arrays.asList(allAkiData);
		RTExpressionDataGTI gAkiData = new RTExpressionDataGTI();
		List<RTExpressionDataGTI> gAkiDataList = Arrays.asList(gAkiData);

		when(rtExpressionDataAllSegmentsRepository.findByGeneSymbolAndEnrollmentCategoryWithCounts("gene", "AKI"))
				.thenReturn(allAkiDataList);
		when(rtExpressionDataGTIRepository.findByGeneSymbolAndEnrollmentCategoryWithCounts("gene", "AKI"))
				.thenReturn(gAkiDataList);

		RTExpressionByEnrollmentCategory rtExpressionByEnrollmentCategoryGti = rtExpressionDataService
				.getByComparisonTypeAndGeneSymbolPerEnrollment("glom_tub", "gene");
		RTExpressionByEnrollmentCategory rtExpressionByEnrollmentCategoryAll = rtExpressionDataService
				.getByComparisonTypeAndGeneSymbolPerEnrollment("all_segments", "gene");

		assertEquals(rtExpressionByEnrollmentCategoryAll.getAki(), allAkiDataList);
		assertEquals(rtExpressionByEnrollmentCategoryGti.getAki(), gAkiDataList);

	}

	@Test
	public void testGetByComparisonTypeAndGeneSymbolPerEnrollment_dmr() throws Exception {
		RTExpressionDataAllSegments allDmrData = new RTExpressionDataAllSegments();
		List<RTExpressionDataAllSegments> allDmrDataList = Arrays.asList(allDmrData);
		RTExpressionDataGTI gDmrData = new RTExpressionDataGTI();
		List<RTExpressionDataGTI> gDmrDataList = Arrays.asList(gDmrData);

		when(rtExpressionDataAllSegmentsRepository.findByGeneSymbolAndEnrollmentCategoryWithCounts("gene",
				EnrollmentCategoryEnum.DMR.getParticipantEnrollmentCategory())).thenReturn(allDmrDataList);
		when(rtExpressionDataGTIRepository.findByGeneSymbolAndEnrollmentCategoryWithCounts("gene",
				EnrollmentCategoryEnum.DMR.getParticipantEnrollmentCategory())).thenReturn(gDmrDataList);

		RTExpressionByEnrollmentCategory rtExpressionByEnrollmentCategoryGti = rtExpressionDataService
				.getByComparisonTypeAndGeneSymbolPerEnrollment("glom_tub", "gene");
		RTExpressionByEnrollmentCategory rtExpressionByEnrollmentCategoryAll = rtExpressionDataService
				.getByComparisonTypeAndGeneSymbolPerEnrollment("all_segments", "gene");

		assertEquals(rtExpressionByEnrollmentCategoryAll.getDmr(), allDmrDataList);
		assertEquals(rtExpressionByEnrollmentCategoryGti.getDmr(), gDmrDataList);

	}
}
