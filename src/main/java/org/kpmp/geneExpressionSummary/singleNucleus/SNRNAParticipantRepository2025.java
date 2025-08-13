package org.kpmp.geneExpressionSummary.singleNucleus;

import org.kpmp.geneExpressionSummary.ParticipantId;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SNRNAParticipantRepository2025 extends CrudRepository<SNRNAParticipantValue2025, ParticipantId> {

	@Cacheable("snParticipantCount")
	@Query(value = "SELECT COUNT(DISTINCT snp.redcap_id) FROM sn_participant_tissue_2025_v snp", nativeQuery = true)
	Long getParticipantCount();
}
