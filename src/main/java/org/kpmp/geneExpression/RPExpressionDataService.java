package org.kpmp.geneExpression;

import java.util.ArrayList;
import java.util.List;

import org.kpmp.TissueTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        rpExpressionByTissueType.setAll(rpExpressionDataRepository.findByGeneSymbolAndTissueTypeAndProteinWithCounts(geneSymbol, TissueTypeEnum.ALL.getParticipantTissueType(), protein));

        return rpExpressionByTissueType;
    }

    public List<RPExpressionData> getByStructure(String structure) {
        return rpExpressionDataRepository.findByStructure(structure);
    }
}
