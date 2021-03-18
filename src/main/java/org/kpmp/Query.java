package org.kpmp;

import java.io.IOException;
import java.util.List;

import org.kpmp.geneExpression.GeneExpressionService;
import org.kpmp.geneExpression.GeneExpressionValue;
import org.kpmp.geneExpression.SCRNAGeneExpressionValue;
import org.kpmp.geneExpression.SNRNAGeneExpressionValue;
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
	private GeneExpressionService geneExpressionService;

	@Autowired
	public Query(GeneService geneService, AutocompleteService autocompleteService, CellTypeService cellTypeService, GeneExpressionService geneExpressionService) {
		this.geneService = geneService;
		this.autocompleteService = autocompleteService;
		this.cellTypeService = cellTypeService;
		this.geneExpressionService = geneExpressionService;
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

	public List<? extends GeneExpressionValue> geneExpression(String dataType, String searchTerm, String tissueType) throws IOException {
		return geneExpressionService.getByDataTypeTissueTypeAndGene(dataType, searchTerm, tissueType);
	}
}
