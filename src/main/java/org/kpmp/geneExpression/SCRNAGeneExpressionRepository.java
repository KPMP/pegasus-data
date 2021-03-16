package org.kpmp.geneExpression;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SCRNAGeneExpressionRepository extends CrudRepository<SCRNAGeneExpressionValue, Integer> {

    List<SCRNAGeneExpressionValue> findByGeneEquals(String geneSymbol);
    List<SCRNAGeneExpressionValue> findAll();

}
