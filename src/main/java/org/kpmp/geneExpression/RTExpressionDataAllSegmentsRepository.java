package org.kpmp.geneExpression;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RTExpressionDataAllSegmentsRepository extends CrudRepository<RTExpressionDataAllSegments, Integer> {
	@Query(value = "SELECT rts.*, rsv.sample_count, s.segment_name FROM rt_segments rts "
			+ "LEFT JOIN rt_summary_v rsv ON rts.segment = rsv.segment AND rts.tissue_type = rsv.tissue_type "
			+ "LEFT JOIN segment s on rts.segment = s.abbreviation "
			+ "WHERE rts.tissue_type = :tissueType AND rts.gene_symbol = :geneSymbol", nativeQuery = true)
	List<RTExpressionDataAllSegments> findByGeneSymbolAndTissueTypeWithCounts(String geneSymbol, String tissueType);

	@Query(value = "CALL rt_diffex_sp(:structure);", nativeQuery = true)
	List<RTExpressionDataAllSegments> findByStructure(@Param("structure") String structure);

	@Query(value = "SELECT COUNT(*) FROM rt_segments WHERE gene_symbol = :gene", nativeQuery = true)
	long getCountByGene(@Param("gene") String gene);

}
