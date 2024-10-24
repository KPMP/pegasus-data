package org.kpmp.geneExpressionSummary.regionalProteomics;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RPParticipantRepository extends CrudRepository<RPParticipantValue, String> {
    @Cacheable("rpCounts")
    @Query(value = "SELECT COUNT(DISTINCT rp.redcap_id) FROM rp_participant_tissue_v rp", nativeQuery = true)
    Long getParticipantCount();

    @Cacheable("rpCountsByEnrollment")
    @Query(value = "SELECT COUNT(DISTINCT rp.redcap_id) FROM rp_participant_tissue_v rp WHERE rp.enrollment_category = :enrollmentCategory", nativeQuery = true)
    Long getCountByEnrollmentCategory(String enrollmentCategory);

    boolean existsByRedcapId(String redcapId);

}
