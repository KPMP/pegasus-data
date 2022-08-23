package org.kpmp.geneExpression;

import org.springframework.data.repository.CrudRepository;

interface SNExpressionDataRepository extends CrudRepository<SNExpressionData, String> {

	SNExpressionData findByGeneSymbol(String geneSymbol);

}
