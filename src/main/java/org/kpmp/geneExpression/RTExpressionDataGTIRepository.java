package org.kpmp.geneExpression;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RTExpressionDataGTIRepository extends CrudRepository<RTExpressionDataGTI, Integer> {
	@Cacheable("rtGTIByGeneAndTissue")
	@Query(value = "SELECT rtg.*, rsv.sample_count, s.segment_name FROM rt_gti rtg "
			+ "LEFT JOIN rt_summary_v rsv ON rtg.segment = rsv.segment AND rtg.tissue_type = rsv.tissue_type "
			+ "LEFT JOIN segment s on rtg.segment = s.abbreviation "
			+ "WHERE rtg.tissue_type = :tissueType AND rtg.gene_symbol = :geneSymbol " 
	       		+ "GROUP BY rtg.segment", nativeQuery = true)
	List<RTExpressionDataGTI> findByGeneSymbolAndTissueTypeWithCounts(String geneSymbol, String tissueType);
}
