package org.kpmp.cellTypeSummary;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

interface ClusterHiearchyRepository extends CrudRepository<ClusterHierarchy, ClusterHierarchyId> {

	@Override
	@Cacheable("clusterHierarchy")
	List<ClusterHierarchy> findAll();

	@Cacheable("clusterHierarchyCt")
	@Query(value = "CALL cluster_hierarchy_sp(:cell_type);", nativeQuery = true)
	List<ClusterHierarchy> findByCellType(@Param("cell_type") String cellType);

	@Cacheable("clusterHierarchyCluster")
	@Query(value = "CALL cluster_hierarchy_by_cluster_sp(:cluster);", nativeQuery = true)
    ClusterHierarchy findFirstByClusterOrRegion(String cluster);
}
