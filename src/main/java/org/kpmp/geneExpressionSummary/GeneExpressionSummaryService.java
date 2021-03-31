package org.kpmp.geneExpressionSummary;

import java.util.ArrayList;
import java.util.List;

import org.kpmp.DataTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeneExpressionSummaryService {

	private SCRNAGeneExpressionSummaryRepository scrnaGeneExpressionRepository;
	private SNRNAGeneExpressionSummaryRepository snrnaGeneExpressionRepository;

	@Autowired
	public GeneExpressionSummaryService(SCRNAGeneExpressionSummaryRepository scrnaGeneExpressionRepository,
			SNRNAGeneExpressionSummaryRepository snrnaGeneExpressionRepository) {
		this.scrnaGeneExpressionRepository = scrnaGeneExpressionRepository;
		this.snrnaGeneExpressionRepository = snrnaGeneExpressionRepository;
	}

	public List<? extends GeneSummaryPerCluster> getByDataTypeTissueTypeAndGene(String dataType, String geneSymbol,
			String tissueType) {
		List<? extends GeneSummaryPerCluster> results = new ArrayList<>();
		DataTypeEnum dataTypeEnum = DataTypeEnum.fromAbbreviation(dataType);
		switch (dataTypeEnum) {
		case SINGLE_CELL:
			return scrnaGeneExpressionRepository.findByTissueAndGeneAllClusters(geneSymbol, tissueType);
		case SINGLE_NUCLEUS:
			return snrnaGeneExpressionRepository.findByTissueAndGeneAllClusters(geneSymbol, tissueType);
		case UNKNOWN:
			List<GeneSummaryPerCluster> allResults = new ArrayList<>();
			allResults.addAll(scrnaGeneExpressionRepository.findByTissueAndGeneAllClusters(geneSymbol, tissueType));
			allResults.addAll(snrnaGeneExpressionRepository.findByTissueAndGeneAllClusters(geneSymbol, tissueType));
			return allResults;

		}
		return results;
	}

}
