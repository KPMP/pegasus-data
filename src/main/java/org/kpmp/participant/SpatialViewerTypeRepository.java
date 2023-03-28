package org.kpmp.participant;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpatialViewerTypeRepository extends CrudRepository<SpatialViewerDataType, String> {

	@Override
	@Cacheable("svType")
	public List<SpatialViewerDataType> findAll();

}
