package org.kpmp.geneExpression;

import org.kpmp.geneExpressionSummary.GeneExpressionId;
import org.kpmp.geneExpressionSummary.SCRNAGeneExpressionExpressionSummaryValue;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RTExpressionDataAllSegmentsRepository extends CrudRepository<RTExpressionDataAllSegments, Integer> {
    List<RTExpressionDataAllSegments> findByGeneSymbolAndTissueType(String geneSymbol, String tissueType);
}
