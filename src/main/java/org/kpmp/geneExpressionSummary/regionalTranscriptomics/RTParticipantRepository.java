package org.kpmp.geneExpressionSummary.regionalTranscriptomics;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RTParticipantRepository extends CrudRepository<RTParticipantValue, String> {

	@Cacheable("rtCounts")
	@Query(value = "SELECT COUNT(DISTINCT rtp.redcap_id) FROM rt_participant_tissue_v rtp", nativeQuery = true)
	Long getParticipantCount();

	@Cacheable("rtCountsByEnrollment")
	@Query(value = "SELECT COUNT(DISTINCT rtp.redcap_id) FROM rt_participant_tissue_v rtp WHERE rtp.enrollment_category = :enrollmentCategory", nativeQuery = true)
	Long getCountByEnrollmentCategory(String enrollmentCategory);

	boolean existsByRedcapId(String redcapId);

}
