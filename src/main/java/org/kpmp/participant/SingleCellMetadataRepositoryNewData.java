package org.kpmp.participant;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

interface SingleCellMetadataRepositoryNewData extends CrudRepository<SingleCellMetadataNewData, String> {

	@Cacheable("scParticipantExistsByRedcap")
	boolean existsByRedcapId(String redcapId);

}
