package org.kpmp.geneExpression;

import org.kpmp.RTComparisonTypeEnum;
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

    public List<? extends RTExpressionData> getByComparisonTypeAndGeneSymbol(String comparisonType, String geneSymbol) {
        RTComparisonTypeEnum rtComparisonTypeEnum = RTComparisonTypeEnum.fromAbbreviation(comparisonType);
        List<? extends RTExpressionData> results = new ArrayList<>();
        switch (rtComparisonTypeEnum) {
            case ALL_SEGMENTS:
                results = rtExpressionDataAllSegmentsRepository.findByGeneSymbol(geneSymbol);
                break;
            case GLOM_V_TI:
                results = rtExpressionDataGTIRepository.findByGeneSymbol(geneSymbol);
                break;
            case UNKNOWN:
                results = null;
        }
        return results;
    };

}
