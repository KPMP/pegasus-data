package org.kpmp.geneExpression;

import java.util.ArrayList;
import java.util.List;

import org.kpmp.EnrollmentCategoryEnum;
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
            RPExpressionByEnrollmentCategory rpExpressionByEnrollmentCategory = getByGeneSymbolAndProteinPerTissue(geneSymbol, accession);
            RPAccessionGroup group = new RPAccessionGroup(accession, rpExpressionByEnrollmentCategory);
            groups.add(group);
        }
        return groups;
    }

    public RPExpressionByEnrollmentCategory getByGeneSymbolAndProteinPerTissue(String geneSymbol, String protein) {
        RPExpressionByEnrollmentCategory rpExpressionByEnrollmentCategory = new RPExpressionByEnrollmentCategory();

        rpExpressionByEnrollmentCategory.setAll(rpExpressionDataRepository.findByGeneSymbolAndEnrollmentCategoryAndProteinWithCounts(geneSymbol, EnrollmentCategoryEnum.ALL.getParticipantEnrollmentCategory(), protein));

        return rpExpressionByEnrollmentCategory;
    }

    public List<RPExpressionData> getByStructure(String structure) {
        return rpExpressionDataRepository.findByStructure(structure);
    }
}
