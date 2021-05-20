package org.kpmp.geneExpressionSummary;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SCRNAGeneExpressionSummaryRepository
		extends CrudRepository<SCRNAGeneExpressionExpressionSummaryValue, GeneExpressionId> {

	@Query(value = "SELECT DISTINCT scc.cluster, scc.cluster_name, scc.cell_count, scc.cluster_id as id, IF(isnull(scr.tissue_type), :tissueType, scr.tissue_type) as tissue_type, IF(isnull(scr.gene), :geneSymbol, scr.gene) as gene, scr.p_val as p_val, scr.p_val_adj as p_val_adj, scr.fold_change, scr.pct_1, scr.pct_2, scr.specificity, scr.avg_exp as avg_exp "
			+ "FROM sc_cluster_v scc "
			+ "LEFT JOIN sc_rnaseq scr ON scc.cluster = scr.cluster AND scr.gene = :geneSymbol AND scr.tissue_type = LCASE(:tissueType) "
			+ "WHERE scc.tissue_type = LCASE(:tissueType) ORDER BY scr.p_val IS NULL, scr.p_val ASC", nativeQuery = true)
	List<SCRNAGeneExpressionExpressionSummaryValue> findByTissueAndGeneAllClusters(
			@Param("geneSymbol") String geneSymbol, @Param("tissueType") String tissueType);

	@Query(value = "SELECT DISTINCT scr.cluster, c.cluster_name, 0 as cell_count, scr.id, scr.tissue_type, scr.gene, scr.p_val as p_val, scr.p_val_adj as p_val_adj, scr.fold_change, scr.pct_1, scr.pct_2, scr.specificity, scr.avg_exp as avg_exp "
			+ "FROM sc_rnaseq scr " + "JOIN cluster c ON scr.cluster = c.abbreviation AND c.cluster_name = :cellType "
			+ "WHERE scr.tissue_type = LCASE(:tissueType) " + "ORDER BY scr.fold_change DESC", nativeQuery = true)
	List<SCRNAGeneExpressionExpressionSummaryValue> findExpressionSummaryPerGeneByCellTypeAndTissueType(
			@Param("cellType") String cellType, @Param("tissueType") String tissueType);

	@Query(value = "SELECT COUNT(*) FROM sc_rnaseq scr WHERE scr.gene= :gene", nativeQuery = true)
	long getCountByGene(@Param("gene") String gene);

	@Query(value = "select count(*) from (select p.redcap_id from sc_metadata sc "
	+ "join participant p on sc.specimen_id = p.redcap_id "
	+ "where p.tissue_type=:tissueType group by p.redcap_id) as mycount", nativeQuery = true)
	long getCountByTissue(@Param("tissueType") String tissueType);
}
