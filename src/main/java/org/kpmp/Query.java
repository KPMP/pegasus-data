package org.kpmp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.kpmp.autocomplete.AutocompleteResult;
import org.kpmp.autocomplete.AutocompleteService;
import org.kpmp.cellType.CellTypeHierarchy;
import org.kpmp.cellType.CellTypeService;
import org.kpmp.cellTypeSummary.ClusterHierarchy;
import org.kpmp.cellTypeSummary.ClusterHierarchyService;
import org.kpmp.datasetSummary.DatasetSummary;
import org.kpmp.datasetSummary.DatasetSummaryService;
import org.kpmp.gene.GeneService;
import org.kpmp.gene.MyGeneInfoHit;
import org.kpmp.geneExpression.RTExpressionByTissueType;
import org.kpmp.geneExpression.RTExpressionData;
import org.kpmp.geneExpression.RTExpressionDataService;
import org.kpmp.geneExpressionSummary.GeneExpressionSummary;
import org.kpmp.geneExpressionSummary.GeneExpressionSummaryService;
import org.kpmp.umap.PlotData;
import org.kpmp.umap.UmapDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private RTExpressionDataService rtExpressionDataService;
	private DatasetSummaryService datasetSummaryService;

	private Logger logger = LoggerFactory.getLogger(Query.class);

	@Autowired
	public Query(GeneService geneService, AutocompleteService autocompleteService, CellTypeService cellTypeService,
			UmapDataService umapService, GeneExpressionSummaryService geneExpressionSummaryService,
			ClusterHierarchyService clusterHierarchyService, RTExpressionDataService rtExpressionDataService) {

		this.geneService = geneService;
		this.autocompleteService = autocompleteService;
		this.cellTypeService = cellTypeService;
		this.umapService = umapService;
		this.geneExpressionSummaryService = geneExpressionSummaryService;
		this.clusterHierarchyService = clusterHierarchyService;
		this.rtExpressionDataService = rtExpressionDataService;
	}

	public List<MyGeneInfoHit> genes(String symbol) throws IOException, Exception {
		return geneService.querySymbolAndAlias(symbol);
	}

	public List<AutocompleteResult> autocomplete(String searchTerm) throws IOException, Exception {
		return autocompleteService.query(searchTerm);
	}

	public CellTypeHierarchy getCellTypeHierarchy() throws IOException {
		return cellTypeService.getCellTypeHierarchy();
	}

	public List<? extends GeneExpressionSummary> geneExpressionSummary(String dataType, String geneSymbol,
			String cellType, String tissueType) throws IOException {
		List<? extends GeneExpressionSummary> results = new ArrayList<>();
		if (cellType.isEmpty()) {
			results = geneExpressionSummaryService.getByDataTypeTissueTypeAndGene(dataType, geneSymbol, tissueType);
		} else if (geneSymbol.isEmpty()) {
			results = geneExpressionSummaryService.getExpressionSummaryPerGeneByCellTypeAndTissueType(dataType,
					cellType, tissueType);
		}
		return results;
	}

	public List<ClusterHierarchy> getClusterHieararchies(String cellType) throws IOException {
		return clusterHierarchyService.findClustersByCellType(cellType);
	}

	public PlotData getUmapPlotData(String dataType, String geneSymbol, String tissueType) throws Exception {
		try {
			return umapService.getPlotData(dataType, geneSymbol, tissueType);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	public List<DatasetSummary> getGeneDatasetInformation(String geneSymbol) throws Exception {
		try {
			return geneExpressionSummaryService.getGeneDatasetInformation(geneSymbol);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	public List<String> dataTypesForConcept(String geneSymbol, String clusterName) throws Exception {
		if (geneSymbol != null && !geneSymbol.isEmpty()) {
			return geneExpressionSummaryService.findDataTypesByGene(geneSymbol);
		} else if (clusterName != null && !clusterName.isEmpty()) {
			return clusterHierarchyService.findDataTypesByClusterName(clusterName);
		}
		throw new Exception("Must provide either a cluster or a gene symbol.");

	}

	public RTExpressionByTissueType getRTGeneExpressionByTissue(String comparisonType, String geneSymbol)
			throws Exception {
		try {
			return rtExpressionDataService.getByComparisonTypeAndGeneSymbolPerTissue(comparisonType, geneSymbol);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	public List<? extends RTExpressionData> getRTGeneExpressionByStructure(String structure) throws Exception {
		try {
			return rtExpressionDataService.getByStructure(structure);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	public List<DatasetSummary> getSummaryData(String datatype) throws Exception {
		try {
			return datasetSummaryService.getSummaryData(datatype);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
}
