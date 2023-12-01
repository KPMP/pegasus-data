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

        rpExpressionByTissueType.setAki(mapToList(resultsToMap(rpExpressionDataRepository.findByGeneSymbolAndTissueTypeWithCounts(geneSymbol, TissueTypeEnum.AKI.getParticipantTissueType()))));
        rpExpressionByTissueType.setCkd(mapToList(resultsToMap(rpExpressionDataRepository.findByGeneSymbolAndTissueTypeWithCounts(geneSymbol, TissueTypeEnum.CKD.getParticipantTissueType()))));
        rpExpressionByTissueType.setDmr(mapToList(resultsToMap(rpExpressionDataRepository.findByGeneSymbolAndTissueTypeWithCounts(geneSymbol, TissueTypeEnum.DMR.getParticipantTissueType()))));
        rpExpressionByTissueType.setHrt(mapToList(resultsToMap(rpExpressionDataRepository.findByGeneSymbolAndTissueTypeWithCounts(geneSymbol, TissueTypeEnum.HEALTHY_REFERENCE.getParticipantTissueType()))));
        rpExpressionByTissueType.setAll(mapToList(resultsToMap(rpExpressionDataRepository.findByGeneSymbolAndTissueTypeWithCounts(geneSymbol, TissueTypeEnum.ALL.getParticipantTissueType()))));

        return rpExpressionByTissueType;

    }

    public Map<String, List<RPExpressionData>> resultsToMap(List<RPExpressionData> expressionDataList) {
        Map<String, List<RPExpressionData>> returnMap = new HashMap<>();
        for (RPExpressionData expressionData : expressionDataList) {
            returnMap.computeIfAbsent(expressionData.getAccession(), k -> new ArrayList<>()).add(expressionData);
        }
        return returnMap;
    }

    public List<RPAccessionGroup> mapToList(Map<String, List<RPExpressionData>> expressionDataMap) {
        List<RPAccessionGroup> accessionGroups = new ArrayList<>();
        for (Map.Entry<String, List<RPExpressionData>> expressionDataEntry: expressionDataMap.entrySet()) {
            accessionGroups.add(new RPAccessionGroup(expressionDataEntry.getKey(), expressionDataEntry.getValue()));
        }
        return accessionGroups;
    }

}
