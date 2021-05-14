package org.kpmp.geneExpressionSummary;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SCRNAParticipantRepository extends CrudRepository<SCRNAParticipantValue, ParticipantId> { 
	@Query(value = "SELECT COUNT(DISTINCT scp.redcap_id) FROM sc_participant_tissue_v scp", nativeQuery = true)
	Long getParticipantCount();
}
