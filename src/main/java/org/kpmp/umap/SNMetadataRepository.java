package org.kpmp.umap;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

interface SNMetadataRepository extends CrudRepository<SNMetadata, String> {

	@Cacheable("snMetadata")
	@Override
	List<SNMetadata> findAll();

}
