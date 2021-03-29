package org.kpmp.umap;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface UmapPointRepository extends CrudRepository<UmapPoint, UmapPointId> {

	List<UmapPoint> findByDataType(String dataType);

	@Override
	List<UmapPoint> findAll();

}
