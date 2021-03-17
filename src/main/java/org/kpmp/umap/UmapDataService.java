package org.kpmp.umap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.kpmp.cluster.ClusterService;
import org.springframework.beans.factory.annotation.Autowired;

public class UmapDataService {

	private SingleNucleusUmapPointRepository singleNucRepo;
	private SingleCellUmapPointRepository singleCellRepo;
	private ClusterService clusterService;

	@Autowired
	public UmapDataService(SingleCellUmapPointRepository singleCellRepo, SingleNucleusUmapPointRepository singleNucRepo,
			ClusterService clusterService) {
		this.singleCellRepo = singleCellRepo;
		this.singleNucRepo = singleNucRepo;
		this.clusterService = clusterService;
	}

	public List<UmapPoint> getSingleCellUmapPoints() {
		Map<String, String> clusterToColorMapping = clusterService.getClusterToColorMapping();
		List<UmapPoint> points = new ArrayList<>();
		List<SingleCellUmapPoint> allPoints = singleCellRepo.findAll();
		for (SingleCellUmapPoint point : allPoints) {
			UmapPoint newPoint = new UmapPoint();
			newPoint.setCluster(point.getCellTypeSubClassL2());
			newPoint.setColor(clusterToColorMapping.get(point.getCellTypeSubClassL2()));
			newPoint.setxCoordinate(point.getUmapX());
			newPoint.setyCoordinate(point.getUmapY());
			points.add(newPoint);
		}
		return points;
	}

	public List<UmapPoint> getSingleNucUmapPoints() {
		Map<String, String> clusterToColorMapping = clusterService.getClusterToColorMapping();
		List<UmapPoint> points = new ArrayList<>();
		List<SingleNucleusUmapPoint> allPoints = singleNucRepo.findAll();
		for (SingleNucleusUmapPoint point : allPoints) {
			UmapPoint newPoint = new UmapPoint();
			newPoint.setCluster(point.getCellTypeSubClassL2());
			newPoint.setColor(clusterToColorMapping.get(point.getCellTypeSubClassL2()));
			newPoint.setxCoordinate(point.getUmapX());
			newPoint.setyCoordinate(point.getUmapY());
			points.add(newPoint);
		}
		return points;
	}

}
