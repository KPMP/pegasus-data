package org.kpmp.umap;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface SingleNucleusUmapPointRepository extends CrudRepository<SingleNucleusUmapPoint, String> {

	@Override
	List<SingleNucleusUmapPoint> findAll();
}
