package org.kpmp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.kpmp.atlasMessage.AtlasMessage;
import org.kpmp.atlasMessage.AtlasMessageService;
import org.kpmp.autocomplete.AutocompleteResult;
import org.kpmp.autocomplete.AutocompleteService;
import org.kpmp.cellType.CellTypeHierarchy;
import org.kpmp.cellType.CellTypeService;
import org.kpmp.cellTypeSummary.ClusterHierarchy;
import org.kpmp.cellTypeSummary.ClusterHierarchyService;
import org.kpmp.dataSummary.AtlasRepoSummaryResult;
import org.kpmp.dataSummary.DataSummaryService;
import org.kpmp.dataSummary.DataTypeSummary;
import org.kpmp.geneExpression.*;
import org.kpmp.geneExpressionSummary.GeneExpressionSummary;
import org.kpmp.geneExpressionSummary.GeneExpressionSummaryService;
import org.kpmp.participant.ParticipantDataTypeSummary;
import org.kpmp.participant.ParticipantRepoDataTypeInformation;
import org.kpmp.participant.ParticipantRepoDataTypeSummary;
import org.kpmp.participant.ParticipantService;
import org.kpmp.participant.ParticipantSummaryDataset;
import org.kpmp.participant.ParticipantTissueTypeSummary;
import org.kpmp.umap.PlotData;
import org.kpmp.umap.UmapDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;

@Component
public class Query implements GraphQLQueryResolver {

	private AutocompleteService autocompleteService;
	private CellTypeService cellTypeService;
	private GeneExpressionSummaryService geneExpressionSummaryService;
	private DataSummaryService dataSummaryService;
	private UmapDataService umapService;
	private ClusterHierarchyService clusterHierarchyService;
	private RTExpressionDataService rtExpressionDataService;

	private RPExpressionDataService rpExpressionDataService;
	private ParticipantService participantService;
  private AtlasMessageService atlasMessageService;
	private Logger logger = LoggerFactory.getLogger(Query.class);

	@Autowired
	public Query(AutocompleteService autocompleteService, CellTypeService cellTypeService,
			UmapDataService umapService, GeneExpressionSummaryService geneExpressionSummaryService,
			DataSummaryService dataSummaryService, ClusterHierarchyService clusterHierarchyService,
			RTExpressionDataService rtExpressionDataService, RPExpressionDataService rpExpressionDataService,
      ParticipantService participantService, AtlasMessageService atlasMessageService) {

		this.autocompleteService = autocompleteService;
		this.cellTypeService = cellTypeService;
		this.umapService = umapService;
		this.geneExpressionSummaryService = geneExpressionSummaryService;
		this.dataSummaryService = dataSummaryService;
		this.clusterHierarchyService = clusterHierarchyService;
		this.rtExpressionDataService = rtExpressionDataService;
		this.rpExpressionDataService = rpExpressionDataService;
		this.participantService = participantService;
        this.atlasMessageService = atlasMessageService;
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

	public List<DataTypeSummary> getDataTypeSummaryInformation() throws Exception {
		try {
			return geneExpressionSummaryService.getDataTypeSummaryInformation();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	public List<DataTypeSummary> getSummaryData() throws Exception {
		try {
			return dataSummaryService.getSummaryData();
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

	public RPExpressionByTissueType getRPGeneExpressionByTissueAndProtein(String geneSymbol, String protein) throws Exception {
		try {
			return rpExpressionDataService.getByGeneSymbolAndProteinPerTissue(geneSymbol, protein);
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

	public RPExpressionByTissueType getRPGeneExpressionByTissue(String geneSymbol)
			throws Exception {
		try {
			return rpExpressionDataService.getByGeneSymbolPerTissue(geneSymbol);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	public ParticipantDataTypeSummary getDataTypeInformationByParticipant(String redcapId) {
		return participantService.getExperimentCounts(redcapId);
	}

	public ParticipantRepoDataTypeSummary getRepoDataTypeInformationByParticipant(String redcapId) {
		return participantService.getDataTypeCounts(redcapId);
	}

	public ParticipantSummaryDataset participantSummaryDataset(String redcap_id) throws Exception {
		try {
			return participantService.getParticipantSummaryDataset(redcap_id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			ParticipantSummaryDataset emptyResult = new ParticipantSummaryDataset();
			emptyResult.setRedcapId(redcap_id);

			return emptyResult;
		}
	}

	public ParticipantSummaryDataset participantClinicalDataset(String redcap_id) throws Exception {
		return this.participantSummaryDataset(redcap_id);
	}

	public ParticipantRepoDataTypeInformation getTotalParticipantFilesCount(String redcap_id) throws Exception {
		try {
			return this.participantService.getTotalFilesCount(redcap_id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	public List<ParticipantTissueTypeSummary> getTissueTypeSummaryData() throws Exception {
		try {
			return participantService.getTissueData();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	public AtlasRepoSummaryResult getAtlasSummaryRows() throws Exception {
		try {
			return dataSummaryService.getAtlasRepoSummary();
		} catch (Exception e) {
			logger.error("Unable to get Atlas Summary data: ", e.getMessage());
			throw e;
		}
	}

    public List<AtlasMessage> getAtlasMessages() throws Exception {
        try{
            return atlasMessageService.getAtlasMessage();
        }catch (Exception e){
            logger.error("Unable to get Atlas Message data: ", e.getMessage());
            throw e;
        }
    }
}