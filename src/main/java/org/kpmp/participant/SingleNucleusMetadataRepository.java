package org.kpmp.participant;

import org.springframework.data.repository.CrudRepository;

public interface SingleNucleusMetadataRepository extends CrudRepository<SingleNucleusMetadata, String> {

	boolean existsByRedcapId(String redcapId);

}
