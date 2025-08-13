package org.kpmp.geneExpressionSummary.singleNucleus;

import java.util.List;

import org.kpmp.geneExpressionSummary.GeneExpressionId;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SNRNAGeneExpressionSummaryRepository2025
		extends CrudRepository<SNRNAGeneExpressionExpressionSummaryValue2025, GeneExpressionId> {

	@Cacheable("snCountsBy")
	@Query(value = "SELECT DISTINCT snc.cluster, snc.cluster_name, snc.cell_count as cell_count, snc.cluster_id as id, IF(isnull(snr.enrollment_category), :enrollmentCategory, snr.enrollment_category) as enrollment_category, IF(isnull(snr.gene), :geneSymbol, snr.gene) as gene, snr.p_val as pval, snr.p_val_adj as pval_adj, snr.fold_change, snr.pct_1 as pct1, snr.pct_2 as pct2, snr.avg_exp as avg_exp "
			+ "FROM sn_cluster_v snc "
			+ "LEFT JOIN sn_rnaseq_2025 snr ON snc.cluster = snr.cluster AND snr.gene = :geneSymbol AND snr.enrollment_category = LCASE(:enrollmentCategory) "
			+ "WHERE snc.enrollment_category = LCASE(:enrollmentCategory) ORDER BY snr.p_val IS NULL, snr.p_val ASC", nativeQuery = true)
	List<SNRNAGeneExpressionExpressionSummaryValue2025> findByEnrollmentAndGeneAllClusters(
			@Param("geneSymbol") String geneSymbol, @Param("enrollmentCategory") String enrollmentCategory);

	@Cacheable("snCounts")
	@Query(value = "SELECT DISTINCT snr.cluster, c.cluster_name, 0 as cell_count, snr.id, snr.enrollment_category, snr.gene, snr.p_val as pval, snr.p_val_adj as pval_adj, snr.fold_change, snr.pct_1 as pct1, snr.pct_2 as pct2, snr.avg_exp as avg_exp "
			+ "FROM sn_rnaseq_2025 snr " + "JOIN cluster c ON snr.cluster = c.abbreviation AND c.cluster_name = :cellType "
			+ "WHERE snr.enrollment_category = LCASE(:enrollmentCategory) " + "ORDER BY snr.fold_change DESC", nativeQuery = true)
	List<SNRNAGeneExpressionExpressionSummaryValue2025> findExpressionSummaryPerGeneByCellTypeAndEnrollmentCategory(
			@Param("cellType") String cellType, @Param("enrollmentCategory") String enrollmentCategory);

    @Cacheable("snCounts")
    @Query(value = "select count(*) from (select p.redcap_id from sn_metadata_2025 sn "
	+ "join participant p on sn.specimen_id = p.redcap_id "
	+ "where p.enrollment_category=:enrollmentCategory group by p.redcap_id) as mycount", nativeQuery = true)
	long getCountByEnrollment(@Param("enrollmentCategory") String enrollmentCategory);

    @Cacheable("snCounts")
    @Query(value = "SELECT COUNT(*) FROM sn_rnaseq_2025 snr WHERE snr.gene= :gene", nativeQuery = true)
	long getCountByGene(@Param("gene") String gene);
}
