package org.kpmp;

import java.io.IOException;
import java.util.List;

import org.json.JSONException;
import org.kpmp.autocomplete.AutocompleteResult;
import org.kpmp.autocomplete.AutocompleteService;
import org.kpmp.cellType.CellTypeHierarchy;
import org.kpmp.cellType.CellTypeService;
import org.kpmp.cellTypeSummary.ClusterHierarchy;
import org.kpmp.cellTypeSummary.ClusterHierarchyService;
import org.kpmp.gene.GeneService;
import org.kpmp.gene.MyGeneInfoHit;
import org.kpmp.geneExpressionSummary.GeneExpressionSummaryService;
import org.kpmp.geneExpressionSummary.GeneSummaryPerCluster;
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
	private GeneExpressionSummaryService geneExpressionSummaryService;
	private UmapDataService umapService;
	private ClusterHierarchyService clusterHierarchyService;

	@Autowired
	public Query(GeneService geneService, AutocompleteService autocompleteService, CellTypeService cellTypeService,
			UmapDataService umapService, GeneExpressionSummaryService geneExpressionSummaryService,
			ClusterHierarchyService clusterHierarchyService) {

		this.geneService = geneService;
		this.autocompleteService = autocompleteService;
		this.cellTypeService = cellTypeService;
		this.umapService = umapService;
		this.geneExpressionSummaryService = geneExpressionSummaryService;
		this.clusterHierarchyService = clusterHierarchyService;
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

	public List<? extends GeneSummaryPerCluster> expressionSummaryPerClusterByGene(String dataType, String searchTerm,
			String tissueType) throws IOException {
		return geneExpressionSummaryService.getByDataTypeTissueTypeAndGene(dataType, searchTerm, tissueType);
	}

	public List<UmapPoint> getUmapPoints(String dataType, String geneSymbol) throws JSONException, Exception {
		return umapService.getUmapPoints(dataType, geneSymbol);
	}

	public List<ClusterHierarchy> getClusterHieararchies(String cellType) throws IOException {
		return clusterHierarchyService.findClustersByCellType(cellType);
	}
}
