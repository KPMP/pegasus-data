package org.kpmp.gene;

import java.io.IOException;
import java.util.List;

import org.kpmp.autocomplete.AutocompleteResult;
import org.kpmp.autocomplete.AutocompleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;

@Component
public class Query implements GraphQLQueryResolver {

	private GeneService geneService;
	private AutocompleteService autocompleteService;

	@Autowired
	public Query(GeneService geneService, AutocompleteService autocompleteService) {
		this.geneService = geneService;
		this.autocompleteService = autocompleteService;
	}

	public List<MyGeneInfoHit> genes(String symbol) throws IOException {
		return geneService.querySymbolAndAlias(symbol);
	}

	public List<AutocompleteResult> autocomplete(String searchTerm) throws IOException {
		return autocompleteService.query(searchTerm);
	}
}
