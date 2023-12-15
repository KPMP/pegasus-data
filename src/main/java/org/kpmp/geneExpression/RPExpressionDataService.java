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

    public List<RPAccessionGroup> getByGeneSymbolPerTissue(String geneSymbol) {
        List<String> accessionNums = rpExpressionDataRepository.findAccessionByGeneSymbol(geneSymbol);
        List<RPAccessionGroup> groups = new ArrayList<>();
        for (String accession: accessionNums) {
            RPExpressionByTissueType rpExpressionByTissueType = getByGeneSymbolAndProteinPerTissue(geneSymbol, accession);
            RPAccessionGroup group = new RPAccessionGroup(accession, rpExpressionByTissueType);
            groups.add(group);
        }
        return groups;
    }

    public RPExpressionByTissueType getByGeneSymbolAndProteinPerTissue(String geneSymbol, String protein) {
        RPExpressionByTissueType rpExpressionByTissueType = new RPExpressionByTissueType();

        rpExpressionByTissueType.setAki(rpExpressionDataRepository.findByGeneSymbolAndTissueTypeAndProteinWithCounts(geneSymbol, TissueTypeEnum.AKI.getParticipantTissueType(), protein));
        rpExpressionByTissueType.setCkd(rpExpressionDataRepository.findByGeneSymbolAndTissueTypeAndProteinWithCounts(geneSymbol, TissueTypeEnum.CKD.getParticipantTissueType(), protein));
        rpExpressionByTissueType.setDmr(rpExpressionDataRepository.findByGeneSymbolAndTissueTypeAndProteinWithCounts(geneSymbol, TissueTypeEnum.DMR.getParticipantTissueType(), protein));
        rpExpressionByTissueType.setHrt(rpExpressionDataRepository.findByGeneSymbolAndTissueTypeAndProteinWithCounts(geneSymbol, TissueTypeEnum.HEALTHY_REFERENCE.getParticipantTissueType(), protein));
        rpExpressionByTissueType.setAll(rpExpressionDataRepository.findByGeneSymbolAndTissueTypeAndProteinWithCounts(geneSymbol, TissueTypeEnum.ALL.getParticipantTissueType(), protein));

        return rpExpressionByTissueType;
    }

    public List<? extends RPExpressionData> getByStructure(String structure) {
        return rpExpressionDataRepository.findByStructure(structure);
    }
}
