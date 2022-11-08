package org.kpmp.participant;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantSummaryDatasetRepository extends CrudRepository<ParticipantSummaryDataset, Integer> {

	@Cacheable("participantByRedcap")
	ParticipantSummaryDataset findByRedcapId(@Param("redcap_id") String redcapId);

}
