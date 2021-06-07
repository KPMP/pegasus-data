package org.kpmp.geneExpression;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RTExpressionDataGTIRepository extends CrudRepository<RTExpressionDataGTI, Integer> {
    List<RTExpressionDataGTI> findByGeneSymbolAndTissueType(String geneSymbol, String tissueType);

}
