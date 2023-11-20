package org.kpmp.geneExpressionSummary.regionalProteomics;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RPParticipantRepository extends CrudRepository<RPParticipantValue, String> {
    @Cacheable("rpCounts")
    @Query(value = "SELECT COUNT(DISTINCT rp.redcap_id) FROM rp_participant_tissue_v rp", nativeQuery = true)
    Long getParticipantCount();

    @Cacheable("rpCountsByTissue")
    @Query(value = "SELECT COUNT(DISTINCT rp.redcap_id) FROM rp_participant_tissue_v rp WHERE rp.tissue_type = :tissueType", nativeQuery = true)
    Long getCountByTissueType(String tissueType);

}
