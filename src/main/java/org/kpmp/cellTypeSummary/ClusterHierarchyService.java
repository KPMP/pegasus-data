package org.kpmp.cellTypeSummary;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClusterHierarchyService {

	private ClusterHiearchyRepository clusterHierarchyRepo;

	@Autowired
	public ClusterHierarchyService(ClusterHiearchyRepository clusterHierarchyRepo) {
		this.clusterHierarchyRepo = clusterHierarchyRepo;
	}

	public List<ClusterHierarchy> findClustersByCellType(String cellType) {
		return clusterHierarchyRepo.findByCellType(cellType);
	}

}
