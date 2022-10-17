package org.kpmp.participant;

import org.springframework.data.repository.CrudRepository;

interface SingleCellMetadataRepository extends CrudRepository<SingleCellMetadata, String> {

	boolean existsByRedcapId(String redcapId);

}
