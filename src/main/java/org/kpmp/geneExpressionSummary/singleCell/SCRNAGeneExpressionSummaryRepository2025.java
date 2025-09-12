package org.kpmp.geneExpressionSummary.singleCell;

import java.util.List;

import org.kpmp.geneExpressionSummary.GeneExpressionId;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SCRNAGeneExpressionSummaryRepository2025
		extends CrudRepository<SCRNAGeneExpressionExpressionSummaryValue2025, GeneExpressionId> {

	@Cacheable("scCountsAllClusters2025")
	@Query(value = "SELECT DISTINCT scc.cluster, scc.cluster_name, scc.cell_count, scc.cluster_id as id, IF(isnull(scr.enrollment_category), :enrollmentCategory, scr.enrollment_category) as enrollment_category, IF(isnull(scr.gene), :geneSymbol, scr.gene) as gene, scr.p_val as p_val, scr.p_val_adj as p_val_adj, scr.fold_change, scr.pct_1, scr.pct_2, scr.avg_exp as avg_exp "
			+ "FROM sc_cluster_2025_v scc "
			+ "LEFT JOIN sc_rnaseq_2025 scr ON scc.cluster = scr.cluster AND scr.gene = :geneSymbol AND scr.enrollment_category = LCASE(:enrollmentCategory) "
			+ "WHERE scc.enrollment_category = LCASE(:enrollmentCategory) ORDER BY scr.p_val IS NULL, scr.p_val ASC", nativeQuery = true)
	List<SCRNAGeneExpressionExpressionSummaryValue2025> findByEnrollmentAndGeneAllClusters(
			@Param("geneSymbol") String geneSymbol, @Param("enrollmentCategory") String enrollmentCategory);

	@Cacheable("scCountsSummaryPerGene2025")
	@Query(value = "SELECT DISTINCT scr.cluster, c.cluster_name, 0 as cell_count, scr.id, scr.enrollment_category, scr.gene, scr.p_val as p_val, scr.p_val_adj as p_val_adj, scr.fold_change, scr.pct_1, scr.pct_2, scr.avg_exp as avg_exp "
			+ "FROM sc_rnaseq_2025 scr " + "JOIN cluster_2025 c ON scr.cluster = c.abbreviation AND c.cluster_name = :cellType "
			+ "WHERE scr.enrollment_category = LCASE(:enrollmentCategory) " + "ORDER BY scr.fold_change DESC", nativeQuery = true)
	List<SCRNAGeneExpressionExpressionSummaryValue2025> findExpressionSummaryPerGeneByCellTypeAndEnrollmentCategory(
			@Param("cellType") String cellType, @Param("enrollmentCategory") String enrollmentCategory);

	@Cacheable("scCountsByGene2025")
	@Query(value = "SELECT COUNT(*) FROM sc_rnaseq_2025 scr WHERE scr.gene= :gene", nativeQuery = true)
	long getCountByGene(@Param("gene") String gene);

	@Cacheable("scCountsByEnrollment2025")
	@Query(value = "select count(*) from (select p.redcap_id from sc_metadata_2025 sc "
			+ "join participant p on sc.redcap_id = p.redcap_id "
			+ "where p.enrollment_category=:enrollmentCategory group by p.redcap_id) as mycount", nativeQuery = true)
	long getCountByEnrollment(@Param("enrollmentCategory") String enrollmentCategory);

}
