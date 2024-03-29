package org.kpmp.geneExpressionSummary.singleNucleus;

import org.kpmp.geneExpressionSummary.ParticipantId;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SNRNAParticipantRepository extends CrudRepository<SNRNAParticipantValue, ParticipantId> {

	@Cacheable("snParticipantCount")
	@Query(value = "SELECT COUNT(DISTINCT snp.redcap_id) FROM sn_participant_tissue_v snp", nativeQuery = true)
	Long getParticipantCount();
}
