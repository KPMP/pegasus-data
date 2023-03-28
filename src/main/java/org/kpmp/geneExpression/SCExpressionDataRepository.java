package org.kpmp.geneExpression;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

interface SCExpressionDataRepository extends CrudRepository<SCExpressionData, String> {

	@Cacheable("scExpression")
	SCExpressionData findByGeneSymbol(String geneSymbol);

}
