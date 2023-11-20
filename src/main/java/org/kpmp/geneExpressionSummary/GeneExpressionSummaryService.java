package org.kpmp.geneExpressionSummary;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.kpmp.DataTypeEnum;
import org.kpmp.FullDataTypeEnum;
import org.kpmp.OmicsTypeEnum;
import org.kpmp.TissueTypeEnum;
import org.kpmp.dataSummary.DataTypeSummary;
import org.kpmp.geneExpression.RPExpressionDataRepository;
import org.kpmp.geneExpression.RTExpressionDataAllSegmentsRepository;
import org.kpmp.geneExpressionSummary.regionalProteomics.RPParticipantRepository;
import org.kpmp.geneExpressionSummary.regionalTranscriptomics.RTParticipantRepository;
import org.kpmp.geneExpressionSummary.singleCell.SCRNAGeneExpressionSummaryRepository;
import org.kpmp.geneExpressionSummary.singleCell.SCRNAParticipantRepository;
import org.kpmp.geneExpressionSummary.singleNucleus.SNRNAGeneExpressionSummaryRepository;
import org.kpmp.geneExpressionSummary.singleNucleus.SNRNAParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeneExpressionSummaryService {
	private SCRNAGeneExpressionSummaryRepository scrnaGeneExpressionRepository;
	private SNRNAGeneExpressionSummaryRepository snrnaGeneExpressionRepository;
	private SCRNAParticipantRepository scrnaParticipantRepository;
	private SNRNAParticipantRepository snrnaParticipantRepository;
	private RTParticipantRepository rtParticipantRepository;

	private RPParticipantRepository rpParticipantRepository;
	private RPExpressionDataRepository rpExpressionDataRepository;
	RTExpressionDataAllSegmentsRepository rtExpressionDataAllSegmentsRepository;

	@Autowired
	public GeneExpressionSummaryService(SCRNAGeneExpressionSummaryRepository scrnaGeneExpressionRepository,
			SNRNAGeneExpressionSummaryRepository snrnaGeneExpressionRepository,
			SCRNAParticipantRepository scrnaParticipantRepository,
			SNRNAParticipantRepository snrnaParticipantRepository, RTParticipantRepository rtParticipantRepository,
			RTExpressionDataAllSegmentsRepository rtExpressionDataAllSegmentsRepository, RPExpressionDataRepository rpExpressionDataRepository, RPParticipantRepository rpParticipantRepository) {
		this.scrnaGeneExpressionRepository = scrnaGeneExpressionRepository;
		this.snrnaGeneExpressionRepository = snrnaGeneExpressionRepository;
		this.scrnaParticipantRepository = scrnaParticipantRepository;
		this.snrnaParticipantRepository = snrnaParticipantRepository;
		this.rtParticipantRepository = rtParticipantRepository;
		this.rpExpressionDataRepository = rpExpressionDataRepository;
		this.rpParticipantRepository = rpParticipantRepository;
		this.rtExpressionDataAllSegmentsRepository = rtExpressionDataAllSegmentsRepository;
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
		default:
			break;

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
		default:
			break;
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
		long rtCountByGene = rtExpressionDataAllSegmentsRepository.getCountByGene(gene);
		if (rtCountByGene != 0) {
			dataTypes.add(DataTypeEnum.REGIONAL_TRANSCRIPTOMICS.getAbbreviation());
		}
		long rpCountByGene = rpExpressionDataRepository.getCountByGene(gene);
		if (rpCountByGene != 0) {
			dataTypes.add(DataTypeEnum.REGIONAL_PROTEOMICS.getAbbreviation());
		}
		return dataTypes;
	}

	public List<DataTypeSummary> getDataTypeSummaryInformation() {
		List<DataTypeSummary> dataTypeSummary = new ArrayList<>();
		dataTypeSummary.add(new DataTypeSummary(OmicsTypeEnum.TRANSCRIPTOMICS.getEnum(),
				FullDataTypeEnum.SINGLE_CELL_FULL.getFullName(), DataTypeEnum.SINGLE_CELL.getAbbreviation(),
				scrnaGeneExpressionRepository.getCountByTissue(TissueTypeEnum.AKI.getParticipantTissueType()),
				scrnaGeneExpressionRepository.getCountByTissue(TissueTypeEnum.CKD.getParticipantTissueType()),
				scrnaGeneExpressionRepository
						.getCountByTissue(TissueTypeEnum.HEALTHY_REFERENCE.getParticipantTissueType()),
				scrnaGeneExpressionRepository.getCountByTissue(TissueTypeEnum.DMR.getParticipantTissueType()),
				scrnaParticipantRepository.getParticipantCount()));
		dataTypeSummary.add(new DataTypeSummary(OmicsTypeEnum.NONE.getEnum(),
				FullDataTypeEnum.SINGLE_NUCLEUS_FULL.getFullName(), DataTypeEnum.SINGLE_NUCLEUS.getAbbreviation(),
				snrnaGeneExpressionRepository.getCountByTissue(TissueTypeEnum.AKI.getParticipantTissueType()),
				snrnaGeneExpressionRepository.getCountByTissue(TissueTypeEnum.CKD.getParticipantTissueType()),
				snrnaGeneExpressionRepository
						.getCountByTissue(TissueTypeEnum.HEALTHY_REFERENCE.getParticipantTissueType()),
				snrnaGeneExpressionRepository.getCountByTissue(TissueTypeEnum.DMR.getParticipantTissueType()),
				snrnaParticipantRepository.getParticipantCount()));
		dataTypeSummary.add(new DataTypeSummary(OmicsTypeEnum.NONE.getEnum(),
				FullDataTypeEnum.REGIONAL_TRANSCRIPTOMICS_FULL.getFullName(),
				DataTypeEnum.REGIONAL_TRANSCRIPTOMICS.getAbbreviation(),
				rtParticipantRepository.getCountByTissueType(TissueTypeEnum.AKI.getParticipantTissueType()),
				rtParticipantRepository.getCountByTissueType(TissueTypeEnum.CKD.getParticipantTissueType()),
				rtParticipantRepository
						.getCountByTissueType(TissueTypeEnum.HEALTHY_REFERENCE.getParticipantTissueType()),
				rtParticipantRepository.getCountByTissueType(TissueTypeEnum.DMR.getParticipantTissueType()),
				rtParticipantRepository.getParticipantCount()));
		dataTypeSummary.add(new DataTypeSummary(OmicsTypeEnum.NONE.getEnum(),
				FullDataTypeEnum.REGIONAL_PROTEOMICS_FULL.getFullName(),
				DataTypeEnum.REGIONAL_PROTEOMICS.getAbbreviation(),
				rpParticipantRepository.getCountByTissueType(TissueTypeEnum.AKI.getParticipantTissueType()),
				rpParticipantRepository.getCountByTissueType(TissueTypeEnum.CKD.getParticipantTissueType()),
				rpParticipantRepository
						.getCountByTissueType(TissueTypeEnum.HEALTHY_REFERENCE.getParticipantTissueType()),
				rpParticipantRepository.getCountByTissueType(TissueTypeEnum.DMR.getParticipantTissueType()),
				rpParticipantRepository.getParticipantCount()));
		return dataTypeSummary;
	}

}
