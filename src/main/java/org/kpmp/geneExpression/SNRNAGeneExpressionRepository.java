package org.kpmp.geneExpression;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SNRNAGeneExpressionRepository extends CrudRepository<SNRNAGeneExpressionValue, String> {

    @Query(value = "SELECT snc.cluster, snc.cluster_name, snc.cell_count, snc.cluster_id as id, snr.tissue_type, snr.gene, snr.p_val as pval, snr.p_val_adj as pval_adj, snr.fold_change, snr.pct_1 as pct1, snr.pct_2 as pct2, snr.avg_exp as avg_expression " +
            "FROM sn_cluster_v snc " +
            "LEFT JOIN sn_rnaseq snr ON snc.cluster = snr.cluster AND snr.gene = :geneSymbol AND snr.tissue_type = :tissueType ORDER BY snr.p_val IS NULL, snr.p_val ASC", nativeQuery = true)
    List<SNRNAGeneExpressionValue> findByTissueAndGeneAllClusters(@Param("geneSymbol") String geneSymbol, @Param("tissueType") String tissueType);

    @Query(value = "SELECT DISTINCT snr.cluster, c.cluster_name, 0 as cell_count, snr.id, snr.tissue_type, snr.gene, snr.p_val, snr.p_val_adj, snr.fold_change, snr.pct_1, snr.pct_2, snr.specificity, snr.avg_exp " +
            "FROM sn_rnaseq snr " +
            "JOIN cluster c ON snr.cluster = c.abbreviation " +
            "JOIN cluster_celltype cc ON c.cluster_id = cc.cluster_id " +
            "JOIN cell_type ct ON cc.cell_type_id = ct.cell_type_id AND ct.cell_type = :cellType " +
            "WHERE scr.tissue_type = :tissueType " +
            "ORDER BY snr.fold_change ASC", nativeQuery = true)
    List<SNRNAGeneExpressionValue> findExpressionSummaryPerGeneByCellTypeAndTissueType(@Param("cellType") String cellType, @Param("tissueType") String tissueType);


}
