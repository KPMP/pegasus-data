package org.kpmp.participant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantSummaryDatasetRepository
    extends CrudRepository<ParticipantSummaryDataset, Integer> {

      @Query(value = "SELECT * FROM participant WHERE redcap_id = :participant_id", nativeQuery = true)
      ParticipantSummaryDataset findByParticipantSummaryDataset(
      @Param("participant_id") String participant_id);
    
}
