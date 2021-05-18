package org.kpmp.geneExpressionSummary;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SNRNAParticipantRepository extends CrudRepository<SNRNAParticipantValue, ParticipantId> { 
	@Query(value = "SELECT COUNT(DISTINCT snp.redcap_id) FROM sn_participant_tissue_v snp", nativeQuery = true)
	Long getParticipantCount();
}
