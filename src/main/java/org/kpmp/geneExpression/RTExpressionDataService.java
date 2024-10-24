package org.kpmp.geneExpression;

import java.util.List;

import org.kpmp.RTComparisonTypeEnum;
import org.kpmp.EnrollmentCategoryEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RTExpressionDataService {

	private RTExpressionDataAllSegmentsRepository rtExpressionDataAllSegmentsRepository;
	private RTExpressionDataGTIRepository rtExpressionDataGTIRepository;

	@Autowired
	public RTExpressionDataService(RTExpressionDataAllSegmentsRepository rtExpressionDataAllSegmentsRepository,
			RTExpressionDataGTIRepository rtExpressionDataGTIRepository) {
		this.rtExpressionDataAllSegmentsRepository = rtExpressionDataAllSegmentsRepository;
		this.rtExpressionDataGTIRepository = rtExpressionDataGTIRepository;
	}

	public RTExpressionByEnrollmentCategory getByComparisonTypeAndGeneSymbolPerEnrollment(String comparisonType,
			String geneSymbol) {
		RTComparisonTypeEnum rtComparisonTypeEnum = RTComparisonTypeEnum.fromAbbreviation(comparisonType);
		RTExpressionByEnrollmentCategory rtExpressionByEnrollmentCategory = new RTExpressionByEnrollmentCategory();
		switch (rtComparisonTypeEnum) {
		case ALL_SEGMENTS:
			rtExpressionByEnrollmentCategory
					.setAki(rtExpressionDataAllSegmentsRepository.findByGeneSymbolAndEnrollmentCategoryWithCounts(geneSymbol,
							EnrollmentCategoryEnum.AKI.getParticipantEnrollmentCategory()));
			rtExpressionByEnrollmentCategory
					.setCkd(rtExpressionDataAllSegmentsRepository.findByGeneSymbolAndEnrollmentCategoryWithCounts(geneSymbol,
							EnrollmentCategoryEnum.CKD.getParticipantEnrollmentCategory()));
			rtExpressionByEnrollmentCategory
					.setAll(rtExpressionDataAllSegmentsRepository.findByGeneSymbolAndEnrollmentCategoryWithCounts(geneSymbol,
							EnrollmentCategoryEnum.ALL.getParticipantEnrollmentCategory()));
			rtExpressionByEnrollmentCategory
					.setHrt(rtExpressionDataAllSegmentsRepository.findByGeneSymbolAndEnrollmentCategoryWithCounts(geneSymbol,
							EnrollmentCategoryEnum.HEALTHY_REFERENCE.getParticipantEnrollmentCategory()));
			rtExpressionByEnrollmentCategory.setDmr(
					rtExpressionDataAllSegmentsRepository.findByGeneSymbolAndEnrollmentCategoryWithCounts(geneSymbol,
							EnrollmentCategoryEnum.DMR.getParticipantEnrollmentCategory()));
			break;
		case GLOM_V_TI:
			rtExpressionByEnrollmentCategory.setAki(rtExpressionDataGTIRepository.findByGeneSymbolAndEnrollmentCategoryWithCounts(
					geneSymbol, EnrollmentCategoryEnum.AKI.getParticipantEnrollmentCategory()));
			rtExpressionByEnrollmentCategory.setCkd(rtExpressionDataGTIRepository.findByGeneSymbolAndEnrollmentCategoryWithCounts(
					geneSymbol, EnrollmentCategoryEnum.CKD.getParticipantEnrollmentCategory()));
			rtExpressionByEnrollmentCategory.setAll(rtExpressionDataGTIRepository.findByGeneSymbolAndEnrollmentCategoryWithCounts(
					geneSymbol, EnrollmentCategoryEnum.ALL.getParticipantEnrollmentCategory()));
			rtExpressionByEnrollmentCategory.setHrt(rtExpressionDataGTIRepository.findByGeneSymbolAndEnrollmentCategoryWithCounts(
					geneSymbol, EnrollmentCategoryEnum.HEALTHY_REFERENCE.getParticipantEnrollmentCategory()));
			rtExpressionByEnrollmentCategory.setDmr(rtExpressionDataGTIRepository.findByGeneSymbolAndEnrollmentCategoryWithCounts(
					geneSymbol, EnrollmentCategoryEnum.DMR.getParticipantEnrollmentCategory()));
			break;
		case UNKNOWN:
			rtExpressionByEnrollmentCategory = null;
		}
		return rtExpressionByEnrollmentCategory;
	}

	public List<? extends RTExpressionData> getByStructure(String structure) {
		return rtExpressionDataAllSegmentsRepository.findByStructure(structure);
	}

}
