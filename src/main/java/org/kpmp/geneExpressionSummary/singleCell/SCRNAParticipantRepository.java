package org.kpmp.geneExpressionSummary.singleCell;

import org.kpmp.geneExpressionSummary.ParticipantId;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SCRNAParticipantRepository extends CrudRepository<SCRNAParticipantValue, ParticipantId> {
	@Cacheable("scParticipantCount")
	@Query(value = "SELECT COUNT(DISTINCT scp.redcap_id) FROM sc_participant_tissue_v scp", nativeQuery = true)
	Long getParticipantCount();
}
