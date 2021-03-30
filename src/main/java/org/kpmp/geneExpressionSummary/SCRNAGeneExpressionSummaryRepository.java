package org.kpmp.geneExpressionSummary;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SCRNAGeneExpressionSummaryRepository extends CrudRepository<SCRNAGeneExpressionExpressionSummaryValue, GeneExpressionId> {

    @Query(value = "SELECT scc.cluster, scc.cluster_name, scc.cell_count, scc.cluster_id as id, scr.tissue_type, scr.gene, scr.p_val as p_val, scr.p_val_adj as p_val_adj, scr.fold_change, scr.pct_1, scr.pct_2, scr.specificity, scr.avg_exp as avg_exp " +
        "FROM sc_cluster_v scc " +
        "LEFT JOIN sc_rnaseq scr ON scc.cluster = scr.cluster AND scr.gene = :geneSymbol AND scr.tissue_type = :tissueType ORDER BY scr.p_val IS NULL, scr.p_val ASC", nativeQuery = true)
    List<SCRNAGeneExpressionExpressionSummaryValue> findByTissueAndGeneAllClusters(@Param("geneSymbol") String geneSymbol, @Param("tissueType") String tissueType);

    @Query(value = "SELECT DISTINCT scr.cluster, c.cluster_name, 0 as cell_count, scr.id, scr.tissue_type, scr.gene, scr.p_val as p_val, scr.p_val_adj as p_val_adj, scr.fold_change, scr.pct_1, scr.pct_2, scr.specificity, scr.avg_exp as avg_exp " +
            "FROM sc_rnaseq scr " +
            "JOIN cluster c ON scr.cluster = c.abbreviation " +
            "JOIN cluster_celltype cc ON c.cluster_id = cc.cluster_id " +
            "JOIN cell_type ct ON cc.cell_type_id = ct.cell_type_id AND ct.cell_type = :cellType " +
            "WHERE scr.tissue_type = :tissueType " +
            "ORDER BY scr.fold_change ASC", nativeQuery = true)
    List<SCRNAGeneExpressionExpressionSummaryValue> findExpressionSummaryPerGeneByCellTypeAndTissueType(@Param("cellType") String cellType, @Param("tissueType") String tissueType);


}
