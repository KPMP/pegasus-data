package org.kpmp.geneExpression;

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

    public RPExpressionByTissueType getByGeneSymbolPerTissue(String geneSymbol) {
        RPExpressionByTissueType rpExpressionByTissueType = new RPExpressionByTissueType();

        rpExpressionByTissueType.setAki(rpExpressionDataRepository.findByGeneSymbolAndTissueTypeWithCounts(geneSymbol, TissueTypeEnum.AKI.getParticipantTissueType()));
        rpExpressionByTissueType.setCkd(rpExpressionDataRepository.findByGeneSymbolAndTissueTypeWithCounts(geneSymbol, TissueTypeEnum.CKD.getParticipantTissueType()));
        rpExpressionByTissueType.setDmr(rpExpressionDataRepository.findByGeneSymbolAndTissueTypeWithCounts(geneSymbol, TissueTypeEnum.DMR.getParticipantTissueType()));
        rpExpressionByTissueType.setHrt(rpExpressionDataRepository.findByGeneSymbolAndTissueTypeWithCounts(geneSymbol, TissueTypeEnum.HEALTHY_REFERENCE.getParticipantTissueType()));
        rpExpressionByTissueType.setAll(rpExpressionDataRepository.findByGeneSymbolAndTissueTypeWithCounts(geneSymbol, TissueTypeEnum.ALL.getParticipantTissueType()));

        return rpExpressionByTissueType;

    }
}
