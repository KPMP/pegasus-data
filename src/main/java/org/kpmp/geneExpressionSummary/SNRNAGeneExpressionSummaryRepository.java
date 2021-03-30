package org.kpmp.geneExpressionSummary;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SNRNAGeneExpressionSummaryRepository extends CrudRepository<SNRNAGeneExpressionSummartValue, String> {

    @Query(value = "SELECT snc.cluster, snc.cluster_name, snc.cell_count, snc.cluster_id as id, snr.tissue_type, snr.gene, snr.p_val as pval, snr.p_val_adj as pval_adj, snr.fold_change, snr.pct_1 as pct1, snr.pct_2 as pct2, snr.avg_exp as avg_expression " +
            "FROM sn_cluster_v snc " +
            "LEFT JOIN sn_rnaseq snr ON snc.cluster = snr.cluster AND snr.gene = :geneSymbol AND snr.tissue_type = :tissueType ORDER BY snr.p_val IS NULL, snr.p_val ASC", nativeQuery = true)
    List<SNRNAGeneExpressionSummartValue> findByTissueAndGeneAllClusters(@Param("geneSymbol") String geneSymbol, @Param("tissueType") String tissueType);

}
