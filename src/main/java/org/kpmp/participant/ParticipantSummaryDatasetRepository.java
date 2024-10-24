package org.kpmp.participant;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantSummaryDatasetRepository extends CrudRepository<ParticipantSummaryDataset, Long> {

	@Cacheable("participantByRedcap")
    @Query(value = "select * from participant where redcap_id= :redcapId", nativeQuery = true)
	ParticipantSummaryDataset findByRedcapId(@Param("redcapId") String redcapId);

	@Cacheable("partipantByEnrollmentCategory")
	@Query(value = "select count(*) from participant where enrollment_category= :enrollment_category", nativeQuery = true)
	Long getDataSummaryCount(@Param("enrollment_category") String enrollment_category);
}
