package org.kpmp.geneExpressionSummary;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

	public List<? extends GeneExpressionSummary> getByDataTypeTissueTypeAndGene(String dataType, String geneSymbol,
			String tissueType) {
		List<? extends GeneExpressionSummary> results = new ArrayList<>();
		DataTypeEnum dataTypeEnum = DataTypeEnum.fromAbbreviation(dataType);
		switch (dataTypeEnum) {
		case SINGLE_CELL:
			return scrnaGeneExpressionRepository.findByTissueAndGeneAllClusters(geneSymbol, tissueType).stream().distinct().collect(Collectors.toList());
		case SINGLE_NUCLEUS:
			return snrnaGeneExpressionRepository.findByTissueAndGeneAllClusters(geneSymbol, tissueType).stream().distinct().collect(Collectors.toList());
		case UNKNOWN:
			List<GeneExpressionSummary> allResults = new ArrayList<>();
			allResults.addAll(scrnaGeneExpressionRepository.findByTissueAndGeneAllClusters(geneSymbol, tissueType));
			allResults.addAll(snrnaGeneExpressionRepository.findByTissueAndGeneAllClusters(geneSymbol, tissueType));
			return allResults;

		}
		return results;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<? extends GeneExpressionSummary> getExpressionSummaryPerGeneByCellTypeAndTissueType(String dataType,
			String cellType, String tissueType) {
		List<? extends GeneExpressionSummary> results = new ArrayList<>();
		DataTypeEnum dataTypeEnum = DataTypeEnum.fromAbbreviation(dataType);
		switch (dataTypeEnum) {
		case SINGLE_CELL:
			results = scrnaGeneExpressionRepository.findExpressionSummaryPerGeneByCellTypeAndTissueType(cellType,
					tissueType);
			break;
		case SINGLE_NUCLEUS:
			results = snrnaGeneExpressionRepository.findExpressionSummaryPerGeneByCellTypeAndTissueType(cellType,
					tissueType);
			break;
		case UNKNOWN:
			List allResults = new ArrayList<>();
			allResults.addAll(scrnaGeneExpressionRepository
					.findExpressionSummaryPerGeneByCellTypeAndTissueType(cellType, tissueType));
			allResults.addAll(snrnaGeneExpressionRepository
					.findExpressionSummaryPerGeneByCellTypeAndTissueType(cellType, tissueType));
			results = allResults;
		}
		return results;
	}

}
