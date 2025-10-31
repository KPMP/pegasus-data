package org.kpmp.geneExpression;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

interface SNExpressionDataRepository2025 extends CrudRepository<SNExpressionData2025, String> {

	@Cacheable("snExpression2025")
	SNExpressionData2025 findByGeneSymbol(String geneSymbol);

}
