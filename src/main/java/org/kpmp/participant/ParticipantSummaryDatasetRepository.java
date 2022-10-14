package org.kpmp.participant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantSummaryDatasetRepository
    extends CrudRepository<ParticipantSummaryDataset, Integer> {

      ParticipantSummaryDataset findByRedcapId(
      @Param("redcap_id") String redcapId);
    
}
