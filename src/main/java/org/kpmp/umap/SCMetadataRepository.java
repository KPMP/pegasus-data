package org.kpmp.umap;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

public interface SCMetadataRepository extends CrudRepository<SCMetadata, String> {

	@Cacheable("scMetadata")
	@Override
	List<SCMetadata> findAll();

}
