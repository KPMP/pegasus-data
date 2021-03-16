package org.kpmp.umap;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface SingleNucleusUMAPPointRepository extends CrudRepository<SingleNucleusUMAPPoint, String> {

	@Override
	List<SingleNucleusUMAPPoint> findAll();
}
