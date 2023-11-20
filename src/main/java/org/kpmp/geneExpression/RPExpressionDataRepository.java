package org.kpmp.geneExpression;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface RPExpressionDataRepository extends CrudRepository<RPExpressionData, Integer> {

    @Cacheable("rpExpCountByGene")
    @Query(value = "SELECT COUNT(*) FROM rp_expression WHERE gene_symbol = :gene", nativeQuery = true)
    long getCountByGene(@Param("gene") String gene);

}
