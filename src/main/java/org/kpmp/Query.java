package org.kpmp;

import java.io.IOException;
import java.util.List;

import org.kpmp.autocomplete.AutocompleteResult;
import org.kpmp.autocomplete.AutocompleteService;
import org.kpmp.cellType.CellTypeHierarchy;
import org.kpmp.cellType.CellTypeService;
import org.kpmp.gene.GeneService;
import org.kpmp.gene.MyGeneInfoHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;

@Component
public class Query implements GraphQLQueryResolver {

	private GeneService geneService;
	private AutocompleteService autocompleteService;
	private CellTypeService cellTypeService;

	@Autowired
	public Query(GeneService geneService, AutocompleteService autocompleteService, CellTypeService cellTypeService) {
		this.geneService = geneService;
		this.autocompleteService = autocompleteService;
		this.cellTypeService = cellTypeService;
	}

	public List<MyGeneInfoHit> genes(String symbol) throws IOException {
		return geneService.querySymbolAndAlias(symbol);
	}

	public List<AutocompleteResult> autocomplete(String searchTerm) throws IOException {
		return autocompleteService.query(searchTerm);
	}
	
	public CellTypeHierarchy getCellTypeHierarchy() throws IOException{
		return cellTypeService.getCellTypeHierarchy();
	}
}
