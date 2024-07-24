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
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;

@Controller
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

    @QueryMapping
	public List<AutocompleteResult> autocomplete(@Argument String searchTerm) throws IOException, Exception {
		return autocompleteService.query(searchTerm);
	}

    @QueryMapping
	public CellTypeHierarchy cellTypeHierarchy() throws IOException {
		return cellTypeService.getCellTypeHierarchy();
	}

    @QueryMapping
	public List<? extends GeneExpressionSummary> geneExpressionSummary(@Argument String dataType, @Argument String geneSymbol,
        @Argument String cellType, @Argument String tissueType) throws IOException {
		List<? extends GeneExpressionSummary> results = new ArrayList<>();
		if (cellType.isEmpty()) {
			results = geneExpressionSummaryService.getByDataTypeTissueTypeAndGene(dataType, geneSymbol, tissueType);
		} else if (geneSymbol.isEmpty()) {
			results = geneExpressionSummaryService.getExpressionSummaryPerGeneByCellTypeAndTissueType(dataType,
					cellType, tissueType);
		}
		return results;
	}

    @QueryMapping
	public List<ClusterHierarchy> getClusterHieararchies(@Argument String cellType) throws IOException {
		return clusterHierarchyService.findClustersByCellType(cellType);
	}

    @QueryMapping
	public PlotData getUmapPlotData(@Argument String dataType, @Argument String geneSymbol, @Argument String tissueType) throws Exception {
		try {
			return umapService.getPlotData(dataType, geneSymbol, tissueType);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

    @QueryMapping
	public List<DataTypeSummary> getDataTypeSummaryInformation() throws Exception {
		try {
			return geneExpressionSummaryService.getDataTypeSummaryInformation();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

    @QueryMapping
	public List<DataTypeSummary> getSummaryData() throws Exception {
		try {
			return dataSummaryService.getSummaryData();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

    @QueryMapping
	public List<String> dataTypesForConcept(@Argument String geneSymbol, @Argument String clusterName) throws Exception {
		if (geneSymbol != null && !geneSymbol.isEmpty()) {
			return geneExpressionSummaryService.findDataTypesByGene(geneSymbol);
		} else if (clusterName != null && !clusterName.isEmpty()) {
			return clusterHierarchyService.findDataTypesByClusterName(clusterName);
		}
		throw new Exception("Must provide either a cluster or a gene symbol.");

	}

    @QueryMapping
	public RTExpressionByTissueType getRTGeneExpressionByTissue(@Argument String comparisonType, @Argument String geneSymbol)
			throws Exception {
		try {
			return rtExpressionDataService.getByComparisonTypeAndGeneSymbolPerTissue(comparisonType, geneSymbol);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

    @QueryMapping
	public RPExpressionByTissueType getRPGeneExpressionByTissueAndProtein(@Argument String geneSymbol, @Argument String protein) throws Exception {
		try {
			return rpExpressionDataService.getByGeneSymbolAndProteinPerTissue(geneSymbol, protein);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

    @QueryMapping
	public List<? extends RTExpressionData> getRTGeneExpressionByStructure(@Argument String structure) throws Exception {
		try {
			return rtExpressionDataService.getByStructure(structure);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

    @QueryMapping
	public List<RPExpressionData> getRPGeneExpressionByStructure(@Argument String structure) throws Exception {
		try {
			return rpExpressionDataService.getByStructure(structure);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

    @QueryMapping
	public List<RPAccessionGroup> getRPGeneExpressionByTissue(@Argument String geneSymbol)
			throws Exception {
		try {
			return rpExpressionDataService.getByGeneSymbolPerTissue(geneSymbol);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

    @QueryMapping
	public ParticipantDataTypeSummary getDataTypeInformationByParticipant(@Argument String redcapId) {
		return participantService.getExperimentCounts(redcapId);
	}

    @QueryMapping
	public ParticipantRepoDataTypeSummary getRepoDataTypeInformationByParticipant(@Argument String redcapId) {
		return participantService.getDataTypeCounts(redcapId);
	}

    @QueryMapping
	public ParticipantSummaryDataset participantSummaryDataset(@Argument String redcapId) throws Exception {
        logger.info(redcapId);
		try {
			return participantService.getParticipantSummaryDataset(redcapId);
		} catch (Exception e) {
			logger.error(e.getMessage());
			ParticipantSummaryDataset emptyResult = new ParticipantSummaryDataset();
			emptyResult.setRedcapId(redcapId);

			return emptyResult;
		}
	}

    @QueryMapping
	public ParticipantRepoDataTypeInformation getTotalParticipantFilesCount(@Argument String redcapId) throws Exception {
		try {
			return this.participantService.getTotalFilesCount(redcapId);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

    @QueryMapping
	public List<ParticipantTissueTypeSummary> getTissueTypeSummaryData() throws Exception {
		try {
			return participantService.getTissueData();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

    @QueryMapping
	public AtlasRepoSummaryResult getAtlasSummaryRows() throws Exception {
		try {
			return dataSummaryService.getAtlasRepoSummary();
		} catch (Exception e) {
			logger.error("Unable to get Atlas Summary data: ", e.getMessage());
			throw e;
		}
	}

    @QueryMapping
    public List<AtlasMessage> getAtlasMessages() throws Exception {
        try{
            return atlasMessageService.getAtlasMessage();
        }catch (Exception e){
            logger.error("Unable to get Atlas Message data: ", e.getMessage());
            throw e;
        }
    }

    @QueryMapping
	public List<ParticipantRepoDataTypeInformation> getExperimentalStrategyCountsByParticipant(@Argument String redcapId) {
		return participantService.getExperimentalStrategyCountsByParticipant(redcapId);
	}
}