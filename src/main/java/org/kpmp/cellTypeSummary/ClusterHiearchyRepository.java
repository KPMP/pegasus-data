package org.kpmp.cellTypeSummary;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

interface ClusterHiearchyRepository extends CrudRepository<ClusterHierarchy, ClusterHierarchyId> {

	@Override
	List<ClusterHierarchy> findAll();

	List<ClusterHierarchy> findByCellType(String cellType);

	ClusterHierarchy findOneByClusterName(String clusterName);
}
