package org.kpmp.participant;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

public interface SingleNucleusMetadataRepository2025 extends CrudRepository<SingleNucleusMetadata2025, String> {

	@Cacheable("snParticipantExistsByRedcap")

	boolean existsByRedcapId(String redcapId);

}
