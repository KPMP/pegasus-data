package org.kpmp.geneExpressionSummary;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeneExpressionSummaryService {

	private final String SC_DATATYPE = "sc";
	private final String SN_DATATYPE = "sn";

	private SCRNAGeneExpressionSummaryRepository scrnaGeneExpressionRepository;
	private SNRNAGeneExpressionSummaryRepository snrnaGeneExpressionRepository;

	@Autowired
	public GeneExpressionSummaryService(SCRNAGeneExpressionSummaryRepository scrnaGeneExpressionRepository,
			SNRNAGeneExpressionSummaryRepository snrnaGeneExpressionRepository) {
		this.scrnaGeneExpressionRepository = scrnaGeneExpressionRepository;
		this.snrnaGeneExpressionRepository = snrnaGeneExpressionRepository;
	}

	public List<? extends GeneExpressionSummary> getByDataTypeTissueTypeAndGene(String dataType, String geneSymbol,
																				String tissueType) {
		List<? extends GeneExpressionSummary> results;
		switch (dataType) {
		case SC_DATATYPE:
			results = scrnaGeneExpressionRepository.findByTissueAndGeneAllClusters(geneSymbol, tissueType);
			break;
		case SN_DATATYPE:
			results = snrnaGeneExpressionRepository.findByTissueAndGeneAllClusters(geneSymbol, tissueType);
			break;
		default:
			List<GeneExpressionSummary> allResults = new ArrayList<>();
			allResults.addAll(scrnaGeneExpressionRepository.findByTissueAndGeneAllClusters(geneSymbol, tissueType));
			allResults.addAll(snrnaGeneExpressionRepository.findByTissueAndGeneAllClusters(geneSymbol, tissueType));
			results = allResults;
		}
		return results;
	}

	public List<? extends GeneExpressionSummary> getExpressionSummaryPerGeneByCellTypeAndTissueType(String dataType, String cellType, String tissueType) {
		List<? extends GeneExpressionSummary> results;
		switch (dataType) {
			case SC_DATATYPE:
				results = scrnaGeneExpressionRepository.findExpressionSummaryPerGeneByCellTypeAndTissueType(cellType, tissueType);
				break;
			case SN_DATATYPE:
				results = snrnaGeneExpressionRepository.findExpressionSummaryPerGeneByCellTypeAndTissueType(cellType, tissueType);
				break;
			default:
				List allResults = new ArrayList<>();
				allResults.addAll(scrnaGeneExpressionRepository.findExpressionSummaryPerGeneByCellTypeAndTissueType(cellType, tissueType));
				allResults.addAll(snrnaGeneExpressionRepository.findExpressionSummaryPerGeneByCellTypeAndTissueType(cellType, tissueType));
				results = allResults;
		}
		return results;
	}


}
