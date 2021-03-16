package org.kpmp.umap;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface SingleCellUMAPPointRepository extends CrudRepository<SingleCellUMAPPoint, String> {

	@Override
	List<SingleCellUMAPPoint> findAll();

}
