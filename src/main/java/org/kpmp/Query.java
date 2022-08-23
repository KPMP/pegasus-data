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
import org.kpmp.dataSummary.DataSummaryService;
import org.kpmp.datasetSummary.DatasetSummary;
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
	private DataSummaryService dataSummaryService;
	private UmapDataService umapService;
	private ClusterHierarchyService clusterHierarchyService;
	private RTExpressionDataService rtExpressionDataService;

	private Logger logger = LoggerFactory.getLogger(Query.class);

	@Autowired
	public Query(GeneService geneService, AutocompleteService autocompleteService, CellTypeService cellTypeService,
			UmapDataService umapService, GeneExpressionSummaryService geneExpressionSummaryService,
			DataSummaryService dataSummaryService, ClusterHierarchyService clusterHierarchyService,
			RTExpressionDataService rtExpressionDataService) {

		this.geneService = geneService;
		this.autocompleteService = autocompleteService;
		this.cellTypeService = cellTypeService;
		this.umapService = umapService;
		this.geneExpressionSummaryService = geneExpressionSummaryService;
		this.dataSummaryService = dataSummaryService;
		this.clusterHierarchyService = clusterHierarchyService;
		this.rtExpressionDataService = rtExpressionDataService;
	}

	public List<MyGeneInfoHit> genes(String symbol) throws IOException, Exception {
		logger.info("Gene search on symbol: " + symbol);
		return geneService.querySymbolAndAlias(symbol);
	}

	public List<AutocompleteResult> autocomplete(String searchTerm) throws IOException, Exception {
		logger.info("Autocomplete search term: " + searchTerm);
		return autocompleteService.query(searchTerm);
	}

	public CellTypeHierarchy getCellTypeHierarchy() throws IOException {
		logger.info("getting cell type hierarchy");
		return cellTypeService.getCellTypeHierarchy();
	}

	public List<? extends GeneExpressionSummary> geneExpressionSummary(String dataType, String geneSymbol,
			String cellType, String tissueType) throws IOException {
		logger.info("Gene expression summary for dataType: " + dataType + " gene: " + " cell type: " + cellType
				+ " and tissue type: " + tissueType);
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
		logger.info("Cluster hierarchies by cellType: " + cellType);
		return clusterHierarchyService.findClustersByCellType(cellType);
	}

	public PlotData getUmapPlotData(String dataType, String geneSymbol, String tissueType) throws Exception {
		logger.info("Plot data for data type: " + dataType + " gene symbol: " + geneSymbol + " and tissue type: "
				+ tissueType);
		try {
			return umapService.getPlotData(dataType, geneSymbol, tissueType);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	public List<DatasetSummary> getGeneDatasetInformation(String geneSymbol) throws Exception {
		logger.info("gene dataset information for gene: " + geneSymbol);
		try {
			return geneExpressionSummaryService.getGeneDatasetInformation(geneSymbol);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	public List<DatasetSummary> getSummaryData() throws Exception {
		logger.info("summary data");
		try {
			return dataSummaryService.getSummaryData();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	public List<String> dataTypesForConcept(String geneSymbol, String clusterName) throws Exception {
		logger.info("data types for concept given gene symbol: " + geneSymbol + " and cluster name: " + clusterName);
		if (geneSymbol != null && !geneSymbol.isEmpty()) {
			return geneExpressionSummaryService.findDataTypesByGene(geneSymbol);
		} else if (clusterName != null && !clusterName.isEmpty()) {
			return clusterHierarchyService.findDataTypesByClusterName(clusterName);
		}
		throw new Exception("Must provide either a cluster or a gene symbol.");

	}

	public RTExpressionByTissueType getRTGeneExpressionByTissue(String comparisonType, String geneSymbol)
			throws Exception {
		logger.info("regional transcriptomics gene expression by tissue for comparison: " + comparisonType
				+ " and gene symbol: " + geneSymbol);
		try {
			return rtExpressionDataService.getByComparisonTypeAndGeneSymbolPerTissue(comparisonType, geneSymbol);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	public List<? extends RTExpressionData> getRTGeneExpressionByStructure(String structure) throws Exception {
		logger.info("regional transcriptomics gene expression by structure for structure: " + structure);
		try {
			return rtExpressionDataService.getByStructure(structure);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
}
