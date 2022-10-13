package org.kpmp.participants;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpatialViewerTypeRepository extends CrudRepository<SpatialViewerDataType, String> {

	@Override
	public List<SpatialViewerDataType> findAll();

}
