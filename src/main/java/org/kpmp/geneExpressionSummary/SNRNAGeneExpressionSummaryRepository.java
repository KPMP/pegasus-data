package org.kpmp.geneExpressionSummary;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SNRNAGeneExpressionSummaryRepository
		extends CrudRepository<SNRNAGeneExpressionExpressionSummaryValue, GeneExpressionId> {

	@Query(value = "SELECT DISTINCT snc.cluster, snc.cluster_name, IF(isnull(snr.gene), null, snc.cell_count) as cell_count, snc.cluster_id as id, IF(isnull(snr.tissue_type), :tissueType, snr.tissue_type) as tissue_type, IF(isnull(snr.gene), :geneSymbol, snr.gene) as gene, snr.p_val as pval, snr.p_val_adj as pval_adj, snr.fold_change, snr.pct_1 as pct1, snr.pct_2 as pct2, snr.avg_exp as avg_exp "
			+ "FROM sn_cluster_v snc "
			+ "LEFT JOIN sn_rnaseq snr ON snc.cluster = snr.cluster AND snc.tissue_type = LCASE(:tissueType) AND snr.gene = :geneSymbol AND snr.tissue_type = LCASE(:tissueType) ORDER BY snr.p_val IS NULL, snr.p_val ASC", nativeQuery = true)
	List<SNRNAGeneExpressionExpressionSummaryValue> findByTissueAndGeneAllClusters(
			@Param("geneSymbol") String geneSymbol, @Param("tissueType") String tissueType);

	@Query(value = "SELECT DISTINCT snr.cluster, c.cluster_name, 0 as cell_count, snr.id, snr.tissue_type, snr.gene, snr.p_val as pval, snr.p_val_adj as pval_adj, snr.fold_change, snr.pct_1 as pct1, snr.pct_2 as pct2, snr.avg_exp as avg_exp "
			+ "FROM sn_rnaseq snr " + "JOIN cluster c ON snr.cluster = c.abbreviation AND c.cluster_name = :cellType "
			+ "WHERE snr.tissue_type = LCASE(:tissueType) " + "ORDER BY snr.fold_change DESC", nativeQuery = true)
	List<SNRNAGeneExpressionExpressionSummaryValue> findExpressionSummaryPerGeneByCellTypeAndTissueType(
			@Param("cellType") String cellType, @Param("tissueType") String tissueType);

	@Query(value = "SELECT COUNT(*) FROM sn_rnaseq snr WHERE snr.gene= :gene", nativeQuery = true)
	long getCountByGene(@Param("gene") String gene);
}
