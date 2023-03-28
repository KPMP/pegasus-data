package org.kpmp.participant;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

interface SingleCellMetadataRepository extends CrudRepository<SingleCellMetadata, String> {

	@Cacheable("scParticipantExistsByRedcap")
	boolean existsByRedcapId(String redcapId);

}
