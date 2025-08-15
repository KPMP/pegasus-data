package org.kpmp.geneExpression;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

interface SNExpressionDataRepositoryNewData extends CrudRepository<SNExpressionDataNewData, String> {

	@Cacheable("snExpression")
	SNExpressionDataNewData findByGeneSymbol(String geneSymbol);

}
