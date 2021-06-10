package org.kpmp.geneExpression;

import org.kpmp.geneExpressionSummary.GeneExpressionId;
import org.kpmp.geneExpressionSummary.SCRNAGeneExpressionExpressionSummaryValue;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RTExpressionDataAllSegmentsRepository extends CrudRepository<RTExpressionDataAllSegments, Integer> {
    List<RTExpressionDataAllSegments> findByGeneSymbolAndTissueType(String geneSymbol, String tissueType);
    @Query(value = "SELECT rts.*, rsv.sample_count FROM rt_segments rts " +
            "LEFT JOIN rt_summary_v rsv ON rts.segment = rsv.segment AND rts.tissue_type = rsv.tissue_type " +
            "WHERE rts.tissue_type = :tissueType AND rts.gene_symbol = :geneSymbol", nativeQuery = true)
    List<RTExpressionDataAllSegments> findByGeneSymbolAndTissueTypeWithCounts(String geneSymbol, String tissueType);
}

