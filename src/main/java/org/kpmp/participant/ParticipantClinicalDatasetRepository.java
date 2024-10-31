package org.kpmp.participant;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantClinicalDatasetRepository extends CrudRepository<ParticipantClinicalDataset, Long> {

	@Cacheable("participantById")
    @Query(value = "SELECT " + 
                "pc.participant_clinical_id, " + 
                "pc.participant_id, " + 
                "pc.kdigo_stage," + 
                "pc.baseline_egfr, " + 
                "pc.proteinuria," + 
                "pc.a1c, " + 
                "pc.albuminuria, " + 
                "pc.diabetes_history," + 
                "pc.diabetes_duration, " + 
                "pc.hypertension_history," +
                "pc.hypertension_duration, " +
                "pc.on_raas_blockade," + 
                "pc.race, " + 
                "p.age_binned, " + 
                "p.sex," + 
                "p.tissue_source, " + 
                "p.protocol," + 
                "p.sample_type," + 
                "from participant_clinical pc join participant p on pc.participant_id = p.participant_id where pc.participant_id = :participantId ", nativeQuery = true)
	ParticipantClinicalDataset findByParticipantId(@Param("participantId") Integer participantId);

}
