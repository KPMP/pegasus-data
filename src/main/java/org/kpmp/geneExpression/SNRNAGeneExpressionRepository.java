package org.kpmp.geneExpression;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SNRNAGeneExpressionRepository extends CrudRepository<SNRNAGeneExpressionValue, Integer> {

    List<SNRNAGeneExpressionValue> findByGeneEquals(String geneSymbol);
    List<SNRNAGeneExpressionValue> findAll();

}
