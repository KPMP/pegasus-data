package org.kpmp.geneExpression;

import org.kpmp.RTComparisonTypeEnum;
import org.kpmp.TissueTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RTExpressionDataService {

    private RTExpressionDataAllSegmentsRepository rtExpressionDataAllSegmentsRepository;
    private RTExpressionDataGTIRepository rtExpressionDataGTIRepository;

    @Autowired
    public RTExpressionDataService(RTExpressionDataAllSegmentsRepository rtExpressionDataAllSegmentsRepository, RTExpressionDataGTIRepository rtExpressionDataGTIRepository) {
        this.rtExpressionDataAllSegmentsRepository = rtExpressionDataAllSegmentsRepository;
        this.rtExpressionDataGTIRepository = rtExpressionDataGTIRepository;
    }

    public RTExpressionByTissueType getByComparisonTypeAndGeneSymbolPerTissue(String comparisonType, String geneSymbol) {
        RTComparisonTypeEnum rtComparisonTypeEnum = RTComparisonTypeEnum.fromAbbreviation(comparisonType);
        RTExpressionByTissueType rtExpressionByTissueType = new RTExpressionByTissueType();
        switch (rtComparisonTypeEnum) {
            case ALL_SEGMENTS:
                rtExpressionByTissueType.setAki(rtExpressionDataAllSegmentsRepository.findByGeneSymbolAndTissueType(geneSymbol, TissueTypeEnum.AKI.getParticipantTissueType()));
                rtExpressionByTissueType.setCkd(rtExpressionDataAllSegmentsRepository.findByGeneSymbolAndTissueType(geneSymbol, TissueTypeEnum.CKD.getParticipantTissueType()));
                rtExpressionByTissueType.setAll(rtExpressionDataAllSegmentsRepository.findByGeneSymbolAndTissueType(geneSymbol, TissueTypeEnum.ALL.getParticipantTissueType()));
                rtExpressionByTissueType.setHrt(rtExpressionDataAllSegmentsRepository.findByGeneSymbolAndTissueType(geneSymbol, TissueTypeEnum.HEALTHY_REFERENCE.getRequestType()));
                break;
            case GLOM_V_TI:
                rtExpressionByTissueType.setAki(rtExpressionDataGTIRepository.findByGeneSymbolAndTissueType(geneSymbol, TissueTypeEnum.AKI.getParticipantTissueType()));
                rtExpressionByTissueType.setCkd(rtExpressionDataGTIRepository.findByGeneSymbolAndTissueType(geneSymbol, TissueTypeEnum.CKD.getParticipantTissueType()));
                rtExpressionByTissueType.setAll(rtExpressionDataGTIRepository.findByGeneSymbolAndTissueType(geneSymbol, TissueTypeEnum.ALL.getParticipantTissueType()));
                rtExpressionByTissueType.setHrt(rtExpressionDataGTIRepository.findByGeneSymbolAndTissueType(geneSymbol, TissueTypeEnum.HEALTHY_REFERENCE.getRequestType()));
                break;
            case UNKNOWN:
                rtExpressionByTissueType = null;
        }
        return rtExpressionByTissueType;
    }

}
