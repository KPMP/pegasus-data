package org.kpmp.cluster;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

public interface ClusterRepository extends CrudRepository<Cluster, Integer> {

	@Override
	@Cacheable("cluster")
	List<Cluster> findAll();

	@Cacheable("clusterAbbrev")
	List<Cluster> findByAbbreviation(String abbreviation);

}
