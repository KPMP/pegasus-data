package org.kpmp.geneExpressionSummary.regionalTranscriptomics;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RTParticipantRepository extends CrudRepository<RTParticipantValue, String> {

	@Cacheable("rtCounts")
	@Query(value = "SELECT COUNT(DISTINCT rtp.redcap_id) FROM rt_participant_tissue_v rtp", nativeQuery = true)
	Long getParticipantCount();

	@Cacheable("rtCountsByTissue")
	@Query(value = "SELECT COUNT(DISTINCT rtp.redcap_id) FROM rt_participant_tissue_v rtp WHERE rtp.tissue_type = :tissueType", nativeQuery = true)
	Long getCountByTissueType(String tissueType);

	boolean existsByRedcapId(String redcapId);

}
