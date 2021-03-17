package org.kpmp.umap;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface SingleCellUmapPointRepository extends CrudRepository<SingleCellUmapPoint, String> {

	@Override
	List<SingleCellUmapPoint> findAll();

}
