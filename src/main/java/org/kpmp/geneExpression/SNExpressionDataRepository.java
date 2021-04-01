package org.kpmp.geneExpression;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

interface SNExpressionDataRepository extends CrudRepository<SNExpressionData, String> {

	@Cacheable("snExpression")
	SNExpressionData findByGeneSymbol(String geneSymbol);

}
