package org.kpmp.geneExpression;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

interface SCExpressionDataRepositoryNewData extends CrudRepository<SCExpressionDataNewData, String> {

	@Cacheable("scExpression")
	SCExpressionDataNewData findByGeneSymbol(String geneSymbol);

}
