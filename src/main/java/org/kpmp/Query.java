package org.kpmp;

import java.io.IOException;
import java.util.ArrayList;
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
import org.kpmp.umap.UmapDataService;
import org.kpmp.umap.UmapPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;

@Component
public class Query implements GraphQLQueryResolver {

	private GeneService geneService;
	private AutocompleteService autocompleteService;
	private CellTypeService cellTypeService;
	private GeneExpressionService geneExpressionService;
	private UmapDataService umapService;


	@Autowired
	public Query(GeneService geneService, AutocompleteService autocompleteService, CellTypeService cellTypeService,
			UmapDataService umapService, GeneExpressionService geneExpressionService) {

		this.geneService = geneService;
		this.autocompleteService = autocompleteService;
		this.cellTypeService = cellTypeService;
		this.umapService = umapService;
		this.geneExpressionService = geneExpressionService;
	}

	public List<MyGeneInfoHit> genes(String symbol) throws IOException {
		return geneService.querySymbolAndAlias(symbol);
	}

	public List<AutocompleteResult> autocomplete(String searchTerm) throws IOException {
		return autocompleteService.query(searchTerm);
	}

	public CellTypeHierarchy getCellTypeHierarchy() throws IOException {
		return cellTypeService.getCellTypeHierarchy();
	}

	public List<? extends GeneExpressionValue> geneExpression(String dataType, String geneSymbol, String cellType, String tissueType) throws IOException {
		List<? extends GeneExpressionValue> results = new ArrayList<>();
		if (cellType.isEmpty()) {
			results = geneExpressionService.getByDataTypeTissueTypeAndGene(dataType, geneSymbol, tissueType);
		} else if (geneSymbol.isEmpty()) {
			results = geneExpressionService.getExpressionSummaryPerGeneByCellTypeAndTissueType(dataType, cellType, tissueType);
		}
		return results;
	}

	public List<UmapPoint> getUmapPoints(String dataType) {
		if (dataType == null) {
			return umapService.getUmapPoints();
		}
		return umapService.getUmapPoints(dataType);
	}
}
