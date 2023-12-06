package org.kpmp.geneExpression;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RPExpressionDataRepository extends CrudRepository<RPExpressionData, Integer> {


    @Query(value = "SELECT rpe.*, count(*) as sample_count FROM rp_expression rpe " +
            "JOIN rp_metadata rpm ON LOWER(rpe.region) = LOWER(rpm.tissue_region) " +
            "WHERE rpe.gene_symbol = :geneSymbol AND rpe.tissue_type = :tissueType " +
            "GROUP BY rpe.region", nativeQuery = true)
    List<RPExpressionData> findByGeneSymbolAndTissueTypeWithCounts(String geneSymbol, String tissueType);

    @Cacheable("rpExpCountByGene")
    @Query(value = "SELECT COUNT(*) FROM rp_expression WHERE gene_symbol = :gene", nativeQuery = true)
    long getCountByGene(@Param("gene") String gene);

    @Query(value = "SELECT rpe.*, count(*) as sample_count FROM rp_expression rpe " +
            "JOIN rp_metadata rpm ON LOWER(rpe.region) = LOWER(rpm.tissue_region) " +
            "WHERE rpe.gene_symbol = :geneSymbol AND rpe.tissue_type = :tissueType " +
            "AND rpe.accession = :protein " +
            "GROUP BY rpe.region", nativeQuery = true)
    List<RPExpressionData> findByGeneSymbolAndTissueTypeAndProteinWithCounts(String geneSymbol, String tissueType, String protein);
}
