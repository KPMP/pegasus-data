package org.kpmp.geneExpression;

import org.springframework.data.repository.CrudRepository;

interface SCExpressionDataRepository extends CrudRepository<SCExpressionData, String> {

	SCExpressionData findByGeneSymbol(String geneSymbol);

}
