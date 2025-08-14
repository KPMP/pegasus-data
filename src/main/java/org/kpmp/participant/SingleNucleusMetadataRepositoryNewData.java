package org.kpmp.participant;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

public interface SingleNucleusMetadataRepositoryNewData extends CrudRepository<SingleNucleusMetadataNewData, String> {

	@Cacheable("snParticipantExistsByRedcap")

	boolean existsByRedcapId(String redcapId);

}
