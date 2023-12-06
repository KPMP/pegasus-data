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

    public RPExpressionByTissueType getByGeneSymbolAndProteinPerTissue(String geneSymbol, String protein) {
        RPExpressionByTissueType rpExpressionByTissueType = new RPExpressionByTissueType();

        rpExpressionByTissueType.setAki(rpExpressionDataRepository.findByGeneSymbolAndTissueTypeAndProteinWithCounts(geneSymbol, TissueTypeEnum.AKI.getParticipantTissueType(), protein));
        rpExpressionByTissueType.setCkd(rpExpressionDataRepository.findByGeneSymbolAndTissueTypeAndProteinWithCounts(geneSymbol, TissueTypeEnum.CKD.getParticipantTissueType(), protein));
        rpExpressionByTissueType.setDmr(rpExpressionDataRepository.findByGeneSymbolAndTissueTypeAndProteinWithCounts(geneSymbol, TissueTypeEnum.DMR.getParticipantTissueType(), protein));
        rpExpressionByTissueType.setHrt(rpExpressionDataRepository.findByGeneSymbolAndTissueTypeAndProteinWithCounts(geneSymbol, TissueTypeEnum.HEALTHY_REFERENCE.getParticipantTissueType(), protein));
        rpExpressionByTissueType.setAll(rpExpressionDataRepository.findByGeneSymbolAndTissueTypeAndProteinWithCounts(geneSymbol, TissueTypeEnum.ALL.getParticipantTissueType(), protein));

        return rpExpressionByTissueType;
    }
}
