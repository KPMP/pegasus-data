package org.kpmp.participants;

import org.springframework.data.repository.CrudRepository;

public interface SingleNucleusMetadataRepository extends CrudRepository<SingleNucleusMetadata, String> {

	boolean existsByRedcapId(String redcapId);

}
