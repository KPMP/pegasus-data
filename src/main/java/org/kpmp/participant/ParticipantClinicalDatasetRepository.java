package org.kpmp.participant;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantClinicalDatasetRepository extends CrudRepository<ParticipantClinicalDataset, Long> {

	@Cacheable("participantById")
    @Query(value = "select * from participant_clinical where participant_id= :participantId", nativeQuery = true)
	ParticipantClinicalDataset findByParticipantId(@Param("participantId") Integer participantId);
}
