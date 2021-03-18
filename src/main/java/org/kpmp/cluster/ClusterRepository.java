package org.kpmp.cluster;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ClusterRepository extends CrudRepository<Cluster, Integer> {

	@Override
	List<Cluster> findAll();

	List<Cluster> findByAbbreviation(String abbreviation);

}
