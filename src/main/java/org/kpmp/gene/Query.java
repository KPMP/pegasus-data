package org.kpmp.gene;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;

@Component
public class Query implements GraphQLQueryResolver {

	private GeneService geneService;

	@Autowired
	public Query(GeneService geneService) {
		this.geneService = geneService;
	}

	public List<MyGeneInfoHit> genes(String symbol) throws IOException {
		MyGeneInfoResult myGeneInfoResult = geneService.query(symbol);
		return myGeneInfoResult.getHits();
	}
}
