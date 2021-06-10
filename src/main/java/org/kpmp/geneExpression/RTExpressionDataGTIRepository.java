package org.kpmp.geneExpression;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RTExpressionDataGTIRepository extends CrudRepository<RTExpressionDataGTI, Integer> {
    List<RTExpressionDataGTI> findByGeneSymbolAndTissueType(String geneSymbol, String tissueType);
    @Query(value = "SELECT rtg.*, rsv.sample_count FROM rt_gti rtg " +
            "LEFT JOIN rt_summary_v rsv ON rtg.segment = rsv.segment AND rtg.tissue_type = rsv.tissue_type " +
            "WHERE rtg.tissue_type = :tissueType AND rtg.gene_symbol = :geneSymbol", nativeQuery = true)
    List<RTExpressionDataGTI> findByGeneSymbolAndTissueTypeWithCounts(String geneSymbol, String tissueType);
}
