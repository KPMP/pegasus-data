package org.kpmp.geneExpression;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RTExpressionDataGTIRepository extends CrudRepository<RTExpressionDataGTI, Integer> {
	@Cacheable("rtGTIByGeneAndEnrollment")
	@Query(value = "SELECT rtg.*, rsv.sample_count, s.segment_name FROM rt_gti rtg "
			+ "LEFT JOIN rt_summary_v rsv ON rtg.segment = rsv.segment AND rtg.enrollment_category = rsv.enrollment_category "
			+ "LEFT JOIN segment s on rtg.segment = s.abbreviation "
			+ "WHERE rtg.enrollment_category = :enrollmentCategory AND rtg.gene_symbol = :geneSymbol " 
	       		+ "GROUP BY rtg.segment", nativeQuery = true)
	List<RTExpressionDataGTI> findByGeneSymbolAndEnrollmentCategoryWithCounts(String geneSymbol, String enrollmentCategory);
}
