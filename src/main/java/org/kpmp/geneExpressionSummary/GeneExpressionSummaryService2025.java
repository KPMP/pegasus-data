package org.kpmp.geneExpressionSummary;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.kpmp.FullDataTypeEnum;
import org.kpmp.OmicsTypeEnum;
import org.kpmp.EnrollmentCategoryEnum;
import org.kpmp.dataSummary.DataTypeSummary;
import org.kpmp.geneExpression.RPExpressionDataRepository;
import org.kpmp.geneExpression.RTExpressionDataAllSegmentsRepository;
import org.kpmp.geneExpressionSummary.regionalProteomics.RPParticipantRepository;
import org.kpmp.geneExpressionSummary.regionalTranscriptomics.RTParticipantRepository;
import org.kpmp.geneExpressionSummary.singleCell.SCRNAGeneExpressionSummaryRepository2025;
import org.kpmp.geneExpressionSummary.singleCell.SCRNAParticipantRepository2025;
import org.kpmp.geneExpressionSummary.singleNucleus.SNRNAGeneExpressionSummaryRepository2025;
import org.kpmp.geneExpressionSummary.singleNucleus.SNRNAParticipantRepository2025;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeneExpressionSummaryService2025 {
	private SCRNAGeneExpressionSummaryRepository2025 scrnaGeneExpressionRepository2025;
	private SNRNAGeneExpressionSummaryRepository2025 snrnaGeneExpressionRepository2025;
	private SNRNAParticipantRepository2025 snrnaParticipantRepository2025;
    private SCRNAParticipantRepository2025 scrnaParticipantRepository2025;
	private RTParticipantRepository rtParticipantRepository;

	private RPParticipantRepository rpParticipantRepository;
	private RPExpressionDataRepository rpExpressionDataRepository;
	RTExpressionDataAllSegmentsRepository rtExpressionDataAllSegmentsRepository;

	@Autowired
	public GeneExpressionSummaryService2025(SCRNAGeneExpressionSummaryRepository2025 scrnaGeneExpressionRepository2025,
			SNRNAGeneExpressionSummaryRepository2025 snrnaGeneExpressionRepository2025,
            SCRNAParticipantRepository2025 scrnaParticipantRepository2025,
			SNRNAParticipantRepository2025 snrnaParticipantRepository2025, RTParticipantRepository rtParticipantRepository,
			RTExpressionDataAllSegmentsRepository rtExpressionDataAllSegmentsRepository, RPExpressionDataRepository rpExpressionDataRepository, RPParticipantRepository rpParticipantRepository) {
		this.scrnaGeneExpressionRepository2025 = scrnaGeneExpressionRepository2025;
		this.snrnaGeneExpressionRepository2025 = snrnaGeneExpressionRepository2025;
		this.scrnaParticipantRepository2025 = scrnaParticipantRepository2025;
		this.snrnaParticipantRepository2025 = snrnaParticipantRepository2025;
		this.rtParticipantRepository = rtParticipantRepository;
		this.rpExpressionDataRepository = rpExpressionDataRepository;
		this.rpParticipantRepository = rpParticipantRepository;
		this.rtExpressionDataAllSegmentsRepository = rtExpressionDataAllSegmentsRepository;
	}

	public List<? extends GeneExpressionSummary> getByDataTypeEnrollmentCategoryAndGene(String dataType, String geneSymbol,
			String enrollmentCategory) {
		List<? extends GeneExpressionSummary> results = new ArrayList<>();
		FullDataTypeEnum dataTypeEnum = FullDataTypeEnum.fromAbbreviation(dataType);
		EnrollmentCategoryEnum enrollmentCategoryEnum = EnrollmentCategoryEnum.fromRequestType(enrollmentCategory);
		switch (dataTypeEnum) {
		case SINGLE_CELL:
			return scrnaGeneExpressionRepository2025
					.findByEnrollmentAndGeneAllClusters(geneSymbol, enrollmentCategoryEnum.getParticipantEnrollmentCategory()).stream()
					.distinct().collect(Collectors.toList());
		case SINGLE_NUCLEUS:
			return snrnaGeneExpressionRepository2025
					.findByEnrollmentAndGeneAllClusters(geneSymbol, enrollmentCategoryEnum.getParticipantEnrollmentCategory()).stream()
					.distinct().collect(Collectors.toList());
		case UNKNOWN:
			List<GeneExpressionSummary> allResults = new ArrayList<>();
			allResults.addAll(scrnaGeneExpressionRepository2025.findByEnrollmentAndGeneAllClusters(geneSymbol,
					enrollmentCategoryEnum.getParticipantEnrollmentCategory()));
			allResults.addAll(snrnaGeneExpressionRepository2025.findByEnrollmentAndGeneAllClusters(geneSymbol,
					enrollmentCategoryEnum.getParticipantEnrollmentCategory()));
			return allResults;
		default:
			break;

		}
		return results;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<? extends GeneExpressionSummary> getExpressionSummaryPerGeneByCellTypeAndEnrollmentCategory(String dataType,
			String cellType, String enrollmentCategory) {
		List<? extends GeneExpressionSummary> results = new ArrayList<>();
		FullDataTypeEnum dataTypeEnum = FullDataTypeEnum.fromAbbreviation(dataType);
		EnrollmentCategoryEnum enrollmentCategoryEnum = EnrollmentCategoryEnum.fromRequestType(enrollmentCategory);
		switch (dataTypeEnum) {
		case SINGLE_CELL:
			results = scrnaGeneExpressionRepository2025.findExpressionSummaryPerGeneByCellTypeAndEnrollmentCategory(cellType,
					enrollmentCategoryEnum.getParticipantEnrollmentCategory());
			break;
		case SINGLE_NUCLEUS:
			results = snrnaGeneExpressionRepository2025.findExpressionSummaryPerGeneByCellTypeAndEnrollmentCategory(cellType,
					enrollmentCategoryEnum.getParticipantEnrollmentCategory());
			break;
		case UNKNOWN:
			List allResults = new ArrayList<>();
			allResults.addAll(scrnaGeneExpressionRepository2025.findExpressionSummaryPerGeneByCellTypeAndEnrollmentCategory(
					cellType, enrollmentCategoryEnum.getParticipantEnrollmentCategory()));
			allResults.addAll(snrnaGeneExpressionRepository2025.findExpressionSummaryPerGeneByCellTypeAndEnrollmentCategory(
					cellType, enrollmentCategoryEnum.getParticipantEnrollmentCategory()));
			results = allResults;
		default:
			break;
		}
		return results;
	}

	public List<String> findDataTypesByGene(String gene) {
		List<String> dataTypes = new ArrayList<>();
		long scCountByGene = scrnaGeneExpressionRepository2025.getCountByGene(gene);
		if (scCountByGene != 0) {
			dataTypes.add(FullDataTypeEnum.SINGLE_CELL.getAbbreviation());
		}

		long snCountByGene = snrnaGeneExpressionRepository2025.getCountByGene(gene);
		if (snCountByGene != 0) {
			dataTypes.add(FullDataTypeEnum.SINGLE_NUCLEUS.getAbbreviation());
		}
		long rtCountByGene = rtExpressionDataAllSegmentsRepository.getCountByGene(gene);
		if (rtCountByGene != 0) {
			dataTypes.add(FullDataTypeEnum.REGIONAL_TRANSCRIPTOMICS.getAbbreviation());
		}
		long rpCountByGene = rpExpressionDataRepository.getCountByGene(gene);
		if (rpCountByGene != 0) {
			dataTypes.add(FullDataTypeEnum.REGIONAL_PROTEOMICS.getAbbreviation());
		}
		return dataTypes;
	}

	public List<DataTypeSummary> getDataTypeSummaryInformation() {
		List<DataTypeSummary> dataTypeSummary = new ArrayList<>();
		dataTypeSummary.add(new DataTypeSummary(OmicsTypeEnum.TRANSCRIPTOMICS.getEnum(),
				FullDataTypeEnum.SINGLE_CELL.getFullName(), FullDataTypeEnum.SINGLE_CELL.getAbbreviation(),
				scrnaGeneExpressionRepository2025.getCountByEnrollment(EnrollmentCategoryEnum.AKI.getParticipantEnrollmentCategory()),
				scrnaGeneExpressionRepository2025.getCountByEnrollment(EnrollmentCategoryEnum.CKD.getParticipantEnrollmentCategory()),
				scrnaGeneExpressionRepository2025
						.getCountByEnrollment(EnrollmentCategoryEnum.HEALTHY_REFERENCE.getParticipantEnrollmentCategory()),
				scrnaGeneExpressionRepository2025.getCountByEnrollment(EnrollmentCategoryEnum.DMR.getParticipantEnrollmentCategory()),
				scrnaParticipantRepository2025.getParticipantCount(),
				scrnaParticipantRepository2025.getParticipantCount()));
		dataTypeSummary.add(new DataTypeSummary(OmicsTypeEnum.NONE.getEnum(),
				FullDataTypeEnum.SINGLE_NUCLEUS.getFullName(), FullDataTypeEnum.SINGLE_NUCLEUS.getAbbreviation(),
				snrnaGeneExpressionRepository2025.getCountByEnrollment(EnrollmentCategoryEnum.AKI.getParticipantEnrollmentCategory()),
				snrnaGeneExpressionRepository2025.getCountByEnrollment(EnrollmentCategoryEnum.CKD.getParticipantEnrollmentCategory()),
				snrnaGeneExpressionRepository2025
						.getCountByEnrollment(EnrollmentCategoryEnum.HEALTHY_REFERENCE.getParticipantEnrollmentCategory()),
				snrnaGeneExpressionRepository2025.getCountByEnrollment(EnrollmentCategoryEnum.DMR.getParticipantEnrollmentCategory()),
				snrnaParticipantRepository2025.getParticipantCount(),
				snrnaParticipantRepository2025.getParticipantCount()));
		dataTypeSummary.add(new DataTypeSummary(OmicsTypeEnum.NONE.getEnum(),
				FullDataTypeEnum.REGIONAL_TRANSCRIPTOMICS.getFullName(),
				FullDataTypeEnum.REGIONAL_TRANSCRIPTOMICS.getAbbreviation(),
				rtParticipantRepository.getCountByEnrollmentCategory(EnrollmentCategoryEnum.AKI.getParticipantEnrollmentCategory()),
				rtParticipantRepository.getCountByEnrollmentCategory(EnrollmentCategoryEnum.CKD.getParticipantEnrollmentCategory()),
				rtParticipantRepository
						.getCountByEnrollmentCategory(EnrollmentCategoryEnum.HEALTHY_REFERENCE.getParticipantEnrollmentCategory()),
				rtParticipantRepository.getCountByEnrollmentCategory(EnrollmentCategoryEnum.DMR.getParticipantEnrollmentCategory()),
				rtParticipantRepository.getParticipantCount(),
				rtParticipantRepository.getParticipantCount()));
		dataTypeSummary.add(new DataTypeSummary(OmicsTypeEnum.NONE.getEnum(),
				FullDataTypeEnum.REGIONAL_PROTEOMICS.getFullName(),
				FullDataTypeEnum.REGIONAL_PROTEOMICS.getAbbreviation(),
				rpParticipantRepository.getCountByEnrollmentCategory(EnrollmentCategoryEnum.AKI.getParticipantEnrollmentCategory()),
				rpParticipantRepository.getCountByEnrollmentCategory(EnrollmentCategoryEnum.CKD.getParticipantEnrollmentCategory()),
				rpParticipantRepository
						.getCountByEnrollmentCategory(EnrollmentCategoryEnum.HEALTHY_REFERENCE.getParticipantEnrollmentCategory()),
				rpParticipantRepository.getCountByEnrollmentCategory(EnrollmentCategoryEnum.DMR.getParticipantEnrollmentCategory()),
				rpParticipantRepository.getParticipantCount(),
				rpParticipantRepository.getParticipantCount()));
		return dataTypeSummary;
	}

}