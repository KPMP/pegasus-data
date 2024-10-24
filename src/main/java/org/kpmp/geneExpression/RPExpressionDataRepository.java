package org.kpmp.geneExpression;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RPExpressionDataRepository extends CrudRepository<RPExpressionData, Integer> {


    @Query(value = "SELECT rpe.*, count(*) as sample_count FROM rp_expression rpe " +
            "JOIN rp_metadata rpm ON LOWER(rpe.region) = LOWER(rpm.tissue_region) " +
            "WHERE rpe.gene_symbol = :geneSymbol AND rpe.enrollment_category = :enrollmentCategory " +
            "GROUP BY rpe.region, rpe.accession", nativeQuery = true)
    List<RPExpressionData> findByGeneSymbolAndEnrollmentCategoryWithCounts(String geneSymbol, String enrollmentCategory);

    @Query(value = "SELECT rpe.accession from rp_expression rpe " +
            "WHERE rpe.gene_symbol = :geneSymbol " +
            "GROUP BY rpe.accession", nativeQuery = true)
     List<String> findAccessionByGeneSymbol(String geneSymbol);

    @Cacheable("rpExpCountByGene")
    @Query(value = "SELECT COUNT(*) FROM rp_expression WHERE gene_symbol = :gene", nativeQuery = true)
    long getCountByGene(@Param("gene") String gene);

    @Query(value = "CALL rp_diffex_sp(:structure);", nativeQuery = true)
    List<RPExpressionData> findByStructure(@Param("structure") String structure);
    @Query(value = "SELECT rpe.*, count(*) as sample_count FROM rp_expression rpe " +
            "JOIN rp_metadata rpm ON LOWER(rpe.region) = LOWER(rpm.tissue_region) " +
            "WHERE rpe.gene_symbol = :geneSymbol AND rpe.enrollment_category = :enrollmentCategory " +
            "AND rpe.accession = :protein " +
            "GROUP BY rpe.region", nativeQuery = true)
    List<RPExpressionData> findByGeneSymbolAndEnrollmentCategoryAndProteinWithCounts(String geneSymbol, String enrollmentCategory, String protein);
}
