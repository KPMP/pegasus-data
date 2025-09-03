package org.kpmp.geneExpression;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

interface SCExpressionDataRepository2025 extends CrudRepository<SCExpressionData2025, String> {

	@Cacheable("scExpression2025")
	SCExpressionData2025 findByGeneSymbol(String geneSymbol);

}
