package org.kpmp.geneExpression;

import org.kpmp.TissueTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RPExpressionDataService {

    RPExpressionDataRepository rpExpressionDataRepository;

    @Autowired
    public RPExpressionDataService(RPExpressionDataRepository rpExpressionDataRepository) {
        this.rpExpressionDataRepository = rpExpressionDataRepository;
    }

    public RPExpressionByTissueType getByGeneSymbolPerTissue(String geneSymbol) {
        RPExpressionByTissueType rpExpressionByTissueType = new RPExpressionByTissueType();

        rpExpressionByTissueType.setAki(convertToMap(rpExpressionDataRepository.findByGeneSymbolAndTissueTypeWithCounts(geneSymbol, TissueTypeEnum.AKI.getParticipantTissueType())));
        rpExpressionByTissueType.setCkd(convertToMap(rpExpressionDataRepository.findByGeneSymbolAndTissueTypeWithCounts(geneSymbol, TissueTypeEnum.CKD.getParticipantTissueType())));
        rpExpressionByTissueType.setDmr(convertToMap(rpExpressionDataRepository.findByGeneSymbolAndTissueTypeWithCounts(geneSymbol, TissueTypeEnum.DMR.getParticipantTissueType())));
        rpExpressionByTissueType.setHrt(convertToMap(rpExpressionDataRepository.findByGeneSymbolAndTissueTypeWithCounts(geneSymbol, TissueTypeEnum.HEALTHY_REFERENCE.getParticipantTissueType())));
        rpExpressionByTissueType.setAll(convertToMap(rpExpressionDataRepository.findByGeneSymbolAndTissueTypeWithCounts(geneSymbol, TissueTypeEnum.ALL.getParticipantTissueType())));

        return rpExpressionByTissueType;

    }

    public Map<String, List<RPExpressionData>> convertToMap(List<RPExpressionData> expressionDataList) {
        Map<String, List<RPExpressionData>> returnMap = new HashMap<>();
        for (RPExpressionData expressionData : expressionDataList) {
            returnMap.computeIfAbsent(expressionData.getAccession(), k -> new ArrayList<>()).add(expressionData);
        }
        return returnMap;
    }

}
