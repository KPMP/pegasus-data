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
import org.kpmp.geneExpressionSummary.singleCell.SCRNAGeneExpressionSummaryRepository;
import org.kpmp.geneExpressionSummary.singleCell.SCRNAParticipantRepository;
import org.kpmp.geneExpressionSummary.singleCell.SCRNAParticipantRepositoryNewData;
import org.kpmp.geneExpressionSummary.singleNucleus.SNRNAGeneExpressionSummaryRepository;
import org.kpmp.geneExpressionSummary.singleNucleus.SNRNAGeneExpressionSummaryRepositoryNewData;
import org.kpmp.geneExpressionSummary.singleNucleus.SNRNAParticipantRepository;
import org.kpmp.geneExpressionSummary.singleNucleus.SNRNAParticipantRepositoryNewData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeneExpressionSummaryService {
	private SCRNAGeneExpressionSummaryRepository scrnaGeneExpressionRepository;
	private SNRNAGeneExpressionSummaryRepository snrnaGeneExpressionRepository;
	private SCRNAParticipantRepository scrnaParticipantRepository;
	private SNRNAParticipantRepository snrnaParticipantRepository;
    private SNRNAParticipantRepositoryNewData snrnaParticipantRepositoryNewData;
	private RTParticipantRepository rtParticipantRepository;
    private SNRNAGeneExpressionSummaryRepositoryNewData snrnaGeneExpressionRepositoryNewData;
    private SCRNAParticipantRepositoryNewData scrnaParticipantRepositoryNewData;

	private RPParticipantRepository rpParticipantRepository;
	private RPExpressionDataRepository rpExpressionDataRepository;
	RTExpressionDataAllSegmentsRepository rtExpressionDataAllSegmentsRepository;

	@Autowired
	public GeneExpressionSummaryService(SCRNAGeneExpressionSummaryRepository scrnaGeneExpressionRepository,
			SNRNAGeneExpressionSummaryRepository snrnaGeneExpressionRepository, 
            SNRNAGeneExpressionSummaryRepositoryNewData snrnaGeneExpressionRepositoryNewData,
			SCRNAParticipantRepository scrnaParticipantRepository,
            SCRNAParticipantRepositoryNewData scrnaParticipantRepositoryNewData,
			SNRNAParticipantRepository snrnaParticipantRepository, SNRNAParticipantRepositoryNewData snrnaParticipantRepositoryNewData, RTParticipantRepository rtParticipantRepository,
			RTExpressionDataAllSegmentsRepository rtExpressionDataAllSegmentsRepository, RPExpressionDataRepository rpExpressionDataRepository, RPParticipantRepository rpParticipantRepository) {
		this.scrnaGeneExpressionRepository = scrnaGeneExpressionRepository;
		this.snrnaGeneExpressionRepository = snrnaGeneExpressionRepository;
        this.snrnaGeneExpressionRepositoryNewData = snrnaGeneExpressionRepositoryNewData;
		this.scrnaParticipantRepository = scrnaParticipantRepository;
		this.snrnaParticipantRepository = snrnaParticipantRepository;
		this.rtParticipantRepository = rtParticipantRepository;
		this.rpExpressionDataRepository = rpExpressionDataRepository;
		this.rpParticipantRepository = rpParticipantRepository;
		this.rtExpressionDataAllSegmentsRepository = rtExpressionDataAllSegmentsRepository;
        this.scrnaParticipantRepositoryNewData = scrnaParticipantRepositoryNewData;
	}

	public List<? extends GeneExpressionSummary> getByDataTypeEnrollmentCategoryAndGene(String dataType, String geneSymbol,
			String enrollmentCategory, Boolean newData) {
		List<? extends GeneExpressionSummary> results = new ArrayList<>();
		FullDataTypeEnum dataTypeEnum = FullDataTypeEnum.fromAbbreviation(dataType);
		EnrollmentCategoryEnum enrollmentCategoryEnum = EnrollmentCategoryEnum.fromRequestType(enrollmentCategory);
		switch (dataTypeEnum) {
		case SINGLE_CELL:
            if (newData != null && newData) {
                
            }
			return scrnaGeneExpressionRepository
					.findByEnrollmentAndGeneAllClusters(geneSymbol, enrollmentCategoryEnum.getParticipantEnrollmentCategory()).stream()
					.distinct().collect(Collectors.toList());
		case SINGLE_NUCLEUS:
            if(newData != null && newData){
                return snrnaGeneExpressionRepositoryNewData
                        .findByEnrollmentAndGeneAllClusters(geneSymbol, enrollmentCategoryEnum.getParticipantEnrollmentCategory()).stream()
                        .distinct().collect(Collectors.toList());
            }
            else{
                return snrnaGeneExpressionRepository
                .findByEnrollmentAndGeneAllClusters(geneSymbol, enrollmentCategoryEnum.getParticipantEnrollmentCategory()).stream()
                .distinct().collect(Collectors.toList());
            }
		case UNKNOWN:
            if (newData != null && newData) {
                List<GeneExpressionSummary> allResults = new ArrayList<>();
                allResults.addAll(scrnaGeneExpressionRepository.findByEnrollmentAndGeneAllClusters(geneSymbol,
                        enrollmentCategoryEnum.getParticipantEnrollmentCategory()));
                allResults.addAll(snrnaGeneExpressionRepositoryNewData.findByEnrollmentAndGeneAllClusters(geneSymbol,
                        enrollmentCategoryEnum.getParticipantEnrollmentCategory()));
                return allResults;
            }else {
                List<GeneExpressionSummary> allResults = new ArrayList<>();
                allResults.addAll(scrnaGeneExpressionRepository.findByEnrollmentAndGeneAllClusters(geneSymbol,
                        enrollmentCategoryEnum.getParticipantEnrollmentCategory()));
                allResults.addAll(snrnaGeneExpressionRepository.findByEnrollmentAndGeneAllClusters(geneSymbol,
                        enrollmentCategoryEnum.getParticipantEnrollmentCategory()));
                return allResults;

            }
		default:
			break;

		}
		return results;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<? extends GeneExpressionSummary> getExpressionSummaryPerGeneByCellTypeAndEnrollmentCategory(String dataType,
			String cellType, String enrollmentCategory, Boolean newData) {
		List<? extends GeneExpressionSummary> results = new ArrayList<>();
		FullDataTypeEnum dataTypeEnum = FullDataTypeEnum.fromAbbreviation(dataType);
		EnrollmentCategoryEnum enrollmentCategoryEnum = EnrollmentCategoryEnum.fromRequestType(enrollmentCategory);
		switch (dataTypeEnum) {
		case SINGLE_CELL:
			results = scrnaGeneExpressionRepository.findExpressionSummaryPerGeneByCellTypeAndEnrollmentCategory(cellType,
					enrollmentCategoryEnum.getParticipantEnrollmentCategory());
			break;
		case SINGLE_NUCLEUS:
            if(newData != null && newData){
                results = snrnaGeneExpressionRepositoryNewData.findExpressionSummaryPerGeneByCellTypeAndEnrollmentCategory(cellType,
                        enrollmentCategoryEnum.getParticipantEnrollmentCategory());
            }else{
                results = snrnaGeneExpressionRepository.findExpressionSummaryPerGeneByCellTypeAndEnrollmentCategory(cellType,
                        enrollmentCategoryEnum.getParticipantEnrollmentCategory());
            }
			break;
		case UNKNOWN:
            if(newData != null && newData){
                List allResults = new ArrayList<>();
                allResults.addAll(scrnaGeneExpressionRepository.findExpressionSummaryPerGeneByCellTypeAndEnrollmentCategory(
                        cellType, enrollmentCategoryEnum.getParticipantEnrollmentCategory()));
                allResults.addAll(snrnaGeneExpressionRepository.findExpressionSummaryPerGeneByCellTypeAndEnrollmentCategory(
                        cellType, enrollmentCategoryEnum.getParticipantEnrollmentCategory()));
                results = allResults;
            }
            else{
                List allResults = new ArrayList<>();
                allResults.addAll(scrnaGeneExpressionRepository.findExpressionSummaryPerGeneByCellTypeAndEnrollmentCategory(
                        cellType, enrollmentCategoryEnum.getParticipantEnrollmentCategory()));
                allResults.addAll(snrnaGeneExpressionRepository.findExpressionSummaryPerGeneByCellTypeAndEnrollmentCategory(
                        cellType, enrollmentCategoryEnum.getParticipantEnrollmentCategory()));
                results = allResults;   
            }
			
		default:
			break;
		}
		return results;
	}

	public List<String> findDataTypesByGene(String gene, Boolean newData) {
		List<String> dataTypes = new ArrayList<>();
		long scCountByGene = scrnaGeneExpressionRepository.getCountByGene(gene);
		if (scCountByGene != 0) {
			dataTypes.add(FullDataTypeEnum.SINGLE_CELL.getAbbreviation());
		}
		long snCountByGene;
		if (newData != null && newData) {
			snCountByGene = snrnaGeneExpressionRepositoryNewData.getCountByGene(gene);
		} else {
			snCountByGene = snrnaGeneExpressionRepository.getCountByGene(gene);
		}
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

	public List<DataTypeSummary> getDataTypeSummaryInformation(Boolean newData) {
		List<DataTypeSummary> dataTypeSummary = new ArrayList<>();
		dataTypeSummary.add(new DataTypeSummary(OmicsTypeEnum.TRANSCRIPTOMICS.getEnum(),
				FullDataTypeEnum.SINGLE_CELL.getFullName(), FullDataTypeEnum.SINGLE_CELL.getAbbreviation(),
				scrnaGeneExpressionRepository.getCountByEnrollment(EnrollmentCategoryEnum.AKI.getParticipantEnrollmentCategory()),
				scrnaGeneExpressionRepository.getCountByEnrollment(EnrollmentCategoryEnum.CKD.getParticipantEnrollmentCategory()),
				scrnaGeneExpressionRepository
						.getCountByEnrollment(EnrollmentCategoryEnum.HEALTHY_REFERENCE.getParticipantEnrollmentCategory()),
				scrnaGeneExpressionRepository.getCountByEnrollment(EnrollmentCategoryEnum.DMR.getParticipantEnrollmentCategory()),
				scrnaParticipantRepository.getParticipantCount(),
				scrnaParticipantRepository.getParticipantCount()));
                if (newData != null && newData) {
                    dataTypeSummary.add(new DataTypeSummary(OmicsTypeEnum.NONE.getEnum(),
                        FullDataTypeEnum.SINGLE_NUCLEUS.getFullName(), FullDataTypeEnum.SINGLE_NUCLEUS.getAbbreviation(),
                        snrnaGeneExpressionRepositoryNewData.getCountByEnrollment(EnrollmentCategoryEnum.AKI.getParticipantEnrollmentCategory()),
                        snrnaGeneExpressionRepositoryNewData.getCountByEnrollment(EnrollmentCategoryEnum.CKD.getParticipantEnrollmentCategory()),
                        snrnaGeneExpressionRepositoryNewData
                                .getCountByEnrollment(EnrollmentCategoryEnum.HEALTHY_REFERENCE.getParticipantEnrollmentCategory()),
                        snrnaGeneExpressionRepositoryNewData.getCountByEnrollment(EnrollmentCategoryEnum.DMR.getParticipantEnrollmentCategory()),
                        snrnaParticipantRepositoryNewData.getParticipantCount(),
                        snrnaParticipantRepositoryNewData.getParticipantCount()));
                }else{
                    dataTypeSummary.add(new DataTypeSummary(OmicsTypeEnum.NONE.getEnum(),
                        FullDataTypeEnum.SINGLE_NUCLEUS.getFullName(), FullDataTypeEnum.SINGLE_NUCLEUS.getAbbreviation(),
                        snrnaGeneExpressionRepository.getCountByEnrollment(EnrollmentCategoryEnum.AKI.getParticipantEnrollmentCategory()),
                        snrnaGeneExpressionRepository.getCountByEnrollment(EnrollmentCategoryEnum.CKD.getParticipantEnrollmentCategory()),
                        snrnaGeneExpressionRepository
                                .getCountByEnrollment(EnrollmentCategoryEnum.HEALTHY_REFERENCE.getParticipantEnrollmentCategory()),
                        snrnaGeneExpressionRepository.getCountByEnrollment(EnrollmentCategoryEnum.DMR.getParticipantEnrollmentCategory()),
                        snrnaParticipantRepository.getParticipantCount(),
                        snrnaParticipantRepository.getParticipantCount()));
                }
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
