package org.kpmp.geneExpressionSummary;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.kpmp.datasetSummary.DatasetSummary;
import org.kpmp.DataTypeEnum;
import org.kpmp.FullDataTypeEnum;
import org.kpmp.OmicsTypeEnum;
import org.kpmp.TissueTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeneExpressionSummaryService {

	private SCRNAGeneExpressionSummaryRepository scrnaGeneExpressionRepository;
	private SNRNAGeneExpressionSummaryRepository snrnaGeneExpressionRepository;
	private SCRNAParticipantRepository scrnaParticipantRepository;
	private SNRNAParticipantRepository snrnaParticipantRepository;

	@Autowired
	public GeneExpressionSummaryService(SCRNAGeneExpressionSummaryRepository scrnaGeneExpressionRepository,
			SNRNAGeneExpressionSummaryRepository snrnaGeneExpressionRepository,
			SCRNAParticipantRepository scrnaParticipantRepository,
			SNRNAParticipantRepository snrnaParticipantRepository) {
		this.scrnaGeneExpressionRepository = scrnaGeneExpressionRepository;
		this.snrnaGeneExpressionRepository = snrnaGeneExpressionRepository;
		this.scrnaParticipantRepository = scrnaParticipantRepository;
		this.snrnaParticipantRepository = snrnaParticipantRepository;
	}

	public List<? extends GeneExpressionSummary> getByDataTypeTissueTypeAndGene(String dataType, String geneSymbol,
			String tissueType) {
		List<? extends GeneExpressionSummary> results = new ArrayList<>();
		DataTypeEnum dataTypeEnum = DataTypeEnum.fromAbbreviation(dataType);
		TissueTypeEnum tissueTypeEnum = TissueTypeEnum.fromRequestType(tissueType);
		switch (dataTypeEnum) {
		case SINGLE_CELL:
			return scrnaGeneExpressionRepository
					.findByTissueAndGeneAllClusters(geneSymbol, tissueTypeEnum.getParticipantTissueType()).stream()
					.distinct().collect(Collectors.toList());
		case SINGLE_NUCLEUS:
			return snrnaGeneExpressionRepository
					.findByTissueAndGeneAllClusters(geneSymbol, tissueTypeEnum.getParticipantTissueType()).stream()
					.distinct().collect(Collectors.toList());
		case UNKNOWN:
			List<GeneExpressionSummary> allResults = new ArrayList<>();
			allResults.addAll(scrnaGeneExpressionRepository.findByTissueAndGeneAllClusters(geneSymbol,
					tissueTypeEnum.getParticipantTissueType()));
			allResults.addAll(snrnaGeneExpressionRepository.findByTissueAndGeneAllClusters(geneSymbol,
					tissueTypeEnum.getParticipantTissueType()));
			return allResults;

		}
		return results;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<? extends GeneExpressionSummary> getExpressionSummaryPerGeneByCellTypeAndTissueType(String dataType,
			String cellType, String tissueType) {
		List<? extends GeneExpressionSummary> results = new ArrayList<>();
		DataTypeEnum dataTypeEnum = DataTypeEnum.fromAbbreviation(dataType);
		TissueTypeEnum tissueTypeEnum = TissueTypeEnum.fromRequestType(tissueType);
		switch (dataTypeEnum) {
			case SINGLE_CELL:
				results = scrnaGeneExpressionRepository.findExpressionSummaryPerGeneByCellTypeAndTissueType(cellType,
						tissueTypeEnum.getParticipantTissueType());
				break;
			case SINGLE_NUCLEUS:
				results = snrnaGeneExpressionRepository.findExpressionSummaryPerGeneByCellTypeAndTissueType(cellType,
						tissueTypeEnum.getParticipantTissueType());
				break;
			case UNKNOWN:
				List allResults = new ArrayList<>();
				allResults.addAll(scrnaGeneExpressionRepository.findExpressionSummaryPerGeneByCellTypeAndTissueType(
						cellType, tissueTypeEnum.getParticipantTissueType()));
				allResults.addAll(snrnaGeneExpressionRepository.findExpressionSummaryPerGeneByCellTypeAndTissueType(
						cellType, tissueTypeEnum.getParticipantTissueType()));
				results = allResults;
		}
		return results;
	}

	public List<String> findDataTypesByGene(String gene) {
		List<String> dataTypes = new ArrayList<>();
		long scCountByGene = scrnaGeneExpressionRepository.getCountByGene(gene);
		if (scCountByGene != 0) {
			dataTypes.add(DataTypeEnum.SINGLE_CELL.getAbbreviation());
		}

		long snCountByGene = snrnaGeneExpressionRepository.getCountByGene(gene);
		if (snCountByGene != 0) {
			dataTypes.add(DataTypeEnum.SINGLE_NUCLEUS.getAbbreviation());
		}
		return dataTypes;
	}

	public List<DatasetSummary> getGeneDatasetInformation(String geneSymbol) {
		List<DatasetSummary> geneSummary = new ArrayList<>();

		geneSummary.add(new DatasetSummary(
			OmicsTypeEnum.TRANSCRIPTOMICS.getEnum(),
			FullDataTypeEnum.SINGLE_CELL_FULL.getFull(),
			DataTypeEnum.SINGLE_CELL.getAbbreviation(),
			scrnaGeneExpressionRepository.getCountByTissueAndGene(geneSymbol, TissueTypeEnum.AKI.getParticipantTissueType()),
			scrnaGeneExpressionRepository.getCountByTissueAndGene(geneSymbol, TissueTypeEnum.CKD.getParticipantTissueType()),
			scrnaGeneExpressionRepository.getCountByTissueAndGene(geneSymbol, TissueTypeEnum.HEALTHY_REFERENCE.getParticipantTissueType()),
			scrnaParticipantRepository.getParticipantCount()
		));
		geneSummary.add(new DatasetSummary(
			OmicsTypeEnum.NONE.getEnum(),
			FullDataTypeEnum.SINGLE_NUCLEUS_FULL.getFull(),
			DataTypeEnum.SINGLE_NUCLEUS.getAbbreviation(),
			snrnaGeneExpressionRepository.getCountByTissueAndGene(geneSymbol, TissueTypeEnum.AKI.getParticipantTissueType()),
			snrnaGeneExpressionRepository.getCountByTissueAndGene(geneSymbol, TissueTypeEnum.CKD.getParticipantTissueType()),
			snrnaGeneExpressionRepository.getCountByTissueAndGene(geneSymbol, TissueTypeEnum.HEALTHY_REFERENCE.getParticipantTissueType()),
			snrnaParticipantRepository.getParticipantCount()
		));
		return geneSummary;
	}
}
