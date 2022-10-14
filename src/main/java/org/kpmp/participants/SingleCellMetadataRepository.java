package org.kpmp.participants;

import org.springframework.data.repository.CrudRepository;

interface SingleCellMetadataRepository extends CrudRepository<SingleCellMetadata, String> {

	boolean existsByRedcapId(String redcapId);

}
