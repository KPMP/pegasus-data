package org.kpmp.geneExpression;

import java.util.List;

import org.kpmp.RTComparisonTypeEnum;
import org.kpmp.TissueTypeEnum;
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

	public RTExpressionByTissueType getByComparisonTypeAndGeneSymbolPerTissue(String comparisonType,
			String geneSymbol) {
		RTComparisonTypeEnum rtComparisonTypeEnum = RTComparisonTypeEnum.fromAbbreviation(comparisonType);
		RTExpressionByTissueType rtExpressionByTissueType = new RTExpressionByTissueType();
		switch (rtComparisonTypeEnum) {
		case ALL_SEGMENTS:
			rtExpressionByTissueType
					.setAki(rtExpressionDataAllSegmentsRepository.findByGeneSymbolAndTissueTypeWithCounts(geneSymbol,
							TissueTypeEnum.AKI.getParticipantTissueType()));
			rtExpressionByTissueType
					.setCkd(rtExpressionDataAllSegmentsRepository.findByGeneSymbolAndTissueTypeWithCounts(geneSymbol,
							TissueTypeEnum.CKD.getParticipantTissueType()));
			rtExpressionByTissueType
					.setAll(rtExpressionDataAllSegmentsRepository.findByGeneSymbolAndTissueTypeWithCounts(geneSymbol,
							TissueTypeEnum.ALL.getParticipantTissueType()));
			rtExpressionByTissueType
					.setHrt(rtExpressionDataAllSegmentsRepository.findByGeneSymbolAndTissueTypeWithCounts(geneSymbol,
							TissueTypeEnum.HEALTHY_REFERENCE.getParticipantTissueType()));
			rtExpressionByTissueType.setResistor(
					rtExpressionDataAllSegmentsRepository.findByGeneSymbolAndTissueTypeWithCounts(geneSymbol,
							TissueTypeEnum.RESISTOR.getParticipantTissueType()));
			break;
		case GLOM_V_TI:
			rtExpressionByTissueType.setAki(rtExpressionDataGTIRepository.findByGeneSymbolAndTissueTypeWithCounts(
					geneSymbol, TissueTypeEnum.AKI.getParticipantTissueType()));
			rtExpressionByTissueType.setCkd(rtExpressionDataGTIRepository.findByGeneSymbolAndTissueTypeWithCounts(
					geneSymbol, TissueTypeEnum.CKD.getParticipantTissueType()));
			rtExpressionByTissueType.setAll(rtExpressionDataGTIRepository.findByGeneSymbolAndTissueTypeWithCounts(
					geneSymbol, TissueTypeEnum.ALL.getParticipantTissueType()));
			rtExpressionByTissueType.setHrt(rtExpressionDataGTIRepository.findByGeneSymbolAndTissueTypeWithCounts(
					geneSymbol, TissueTypeEnum.HEALTHY_REFERENCE.getParticipantTissueType()));
			rtExpressionByTissueType.setResistor(rtExpressionDataGTIRepository.findByGeneSymbolAndTissueTypeWithCounts(
					geneSymbol, TissueTypeEnum.RESISTOR.getParticipantTissueType()));
			break;
		case UNKNOWN:
			rtExpressionByTissueType = null;
		}
		return rtExpressionByTissueType;
	}

	public List<? extends RTExpressionData> getByStructure(String structure) {
		return rtExpressionDataAllSegmentsRepository.findByStructure(structure);
	}

}
