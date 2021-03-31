package org.kpmp.geneExpressionSummary;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
interface SCRNAGeneExpressionSummaryRepository extends CrudRepository<SCRNAGeneExpressionSummaryValue, String> {

	@Query(value = "SELECT scc.cluster, scc.cluster_name, scc.cell_count, scc.cluster_id as id, scr.tissue_type, scr.gene, scr.p_val, scr.p_val_adj, scr.fold_change, scr.pct_1, scr.pct_2, scr.specificity, scr.avg_exp "
			+ "FROM sc_cluster_v scc "
			+ "LEFT JOIN sc_rnaseq scr ON scc.cluster = scr.cluster AND scr.gene = :geneSymbol AND scr.tissue_type = :tissueType ORDER BY scr.p_val IS NULL, scr.p_val ASC", nativeQuery = true)
	List<SCRNAGeneExpressionSummaryValue> findByTissueAndGeneAllClusters(@Param("geneSymbol") String geneSymbol,
			@Param("tissueType") String tissueType);

}
