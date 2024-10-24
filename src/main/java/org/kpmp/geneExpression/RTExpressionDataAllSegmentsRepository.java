package org.kpmp.geneExpression;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RTExpressionDataAllSegmentsRepository extends CrudRepository<RTExpressionDataAllSegments, Integer> {
	@Cacheable("rtExpGeneSymbolAndEnrollmentCategoryWithCounts")
	@Query(value = "SELECT rts.*, rsv.sample_count, s.segment_name FROM rt_segments rts "
			+ "LEFT JOIN rt_summary_v rsv ON rts.segment = rsv.segment AND rts.enrollment_category = rsv.enrollment_category "
			+ "LEFT JOIN segment s on rts.segment = s.abbreviation "
			+ "WHERE rts.enrollment_category = :enrollmentCategory AND rts.gene_symbol = :geneSymbol "
	       		+ "GROUP BY rts.segment", nativeQuery = true)
	List<RTExpressionDataAllSegments> findByGeneSymbolAndEnrollmentCategoryWithCounts(String geneSymbol, String enrollmentCategory);

	@Cacheable("rtExpByStructure")
	@Query(value = "CALL rt_diffex_sp(:structure);", nativeQuery = true)
	List<RTExpressionDataAllSegments> findByStructure(@Param("structure") String structure);

	@Cacheable("rtExpCountByGene")
	@Query(value = "SELECT COUNT(*) FROM rt_segments WHERE gene_symbol = :gene", nativeQuery = true)
	long getCountByGene(@Param("gene") String gene);

}
