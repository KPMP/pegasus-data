package org.kpmp.cluster;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClusterService {

	private ClusterRepository clusterRepo;

	@Autowired
	public ClusterService(ClusterRepository clusterRepo) {
		this.clusterRepo = clusterRepo;
	}

	public Map<String, String> getClusterToColorMapping() {
		Map<String, String> clusterToColorMap = new HashMap<>();
		List<Cluster> clusters = clusterRepo.findAll();
		for (Cluster cluster : clusters) {
			clusterToColorMap.put(cluster.getAbbreviation(), cluster.getClusterColor());
		}
		return clusterToColorMap;
	}

}
