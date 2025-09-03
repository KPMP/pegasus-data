package org.kpmp.participant;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

interface SingleCellMetadataRepository2025 extends CrudRepository<SingleCellMetadata2025, String> {

	@Cacheable("scParticipantExistsByRedcap")
	boolean existsByRedcapId(String redcapId);

}
