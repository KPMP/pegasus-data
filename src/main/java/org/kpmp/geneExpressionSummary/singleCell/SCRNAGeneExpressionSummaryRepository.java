package org.kpmp.geneExpressionSummary.singleCell;

import java.util.List;

import org.kpmp.geneExpressionSummary.GeneExpressionId;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SCRNAGeneExpressionSummaryRepository
		extends CrudRepository<SCRNAGeneExpressionExpressionSummaryValue, GeneExpressionId> {

	@Cacheable("scCountsAllClusters")
	@Query(value = "SELECT DISTINCT scc.cluster, scc.cluster_name, scc.cell_count, scc.cluster_id as id, IF(isnull(scr.enrollment_category), :enrollmentCategory, scr.enrollment_category) as enrollment_category, IF(isnull(scr.gene), :geneSymbol, scr.gene) as gene, scr.p_val as p_val, scr.p_val_adj as p_val_adj, scr.fold_change, scr.pct_1, scr.pct_2, scr.avg_exp as avg_exp "
			+ "FROM sc_cluster_v scc "
			+ "LEFT JOIN sc_rnaseq scr ON scc.cluster = scr.cluster AND scr.gene = :geneSymbol AND scr.enrollment_category = LCASE(:enrollmentCategory) "
			+ "WHERE scc.enrollment_category = LCASE(:enrollmentCategory) ORDER BY scr.p_val IS NULL, scr.p_val ASC", nativeQuery = true)
	List<SCRNAGeneExpressionExpressionSummaryValue> findByEnrollmentAndGeneAllClusters(
			@Param("geneSymbol") String geneSymbol, @Param("enrollmentCategory") String enrollmentCategory);

	@Cacheable("scCountsSummaryPerGene")
	@Query(value = "SELECT DISTINCT scr.cluster, c.cluster_name, 0 as cell_count, scr.id, scr.enrollment_category, scr.gene, scr.p_val as p_val, scr.p_val_adj as p_val_adj, scr.fold_change, scr.pct_1, scr.pct_2, scr.avg_exp as avg_exp "
			+ "FROM sc_rnaseq scr " + "JOIN cluster c ON scr.cluster = c.abbreviation AND c.cluster_name = :cellType "
			+ "WHERE scr.enrollment_category = LCASE(:enrollmentCategory) " + "ORDER BY scr.fold_change DESC", nativeQuery = true)
	List<SCRNAGeneExpressionExpressionSummaryValue> findExpressionSummaryPerGeneByCellTypeAndEnrollmentCategory(
			@Param("cellType") String cellType, @Param("enrollmentCategory") String enrollmentCategory);

	@Cacheable("scCountsByGene")
	@Query(value = "SELECT COUNT(*) FROM sc_rnaseq scr WHERE scr.gene= :gene", nativeQuery = true)
	long getCountByGene(@Param("gene") String gene);

	@Cacheable("scCountsByEnrollment")
	@Query(value = "select count(*) from (select p.redcap_id from sc_metadata sc "
			+ "join participant p on sc.specimen_id = p.redcap_id "
			+ "where p.enrollment_category=:enrollmentCategory group by p.redcap_id) as mycount", nativeQuery = true)
	long getCountByEnrollment(@Param("enrollmentCategory") String enrollmentCategory);
}