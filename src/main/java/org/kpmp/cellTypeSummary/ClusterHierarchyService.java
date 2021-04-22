package org.kpmp.cellTypeSummary;

import java.util.ArrayList;
import java.util.List;

import org.kpmp.DataTypeEnum;
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

	public List<String> findDataTypesByClusterName(String clusterName) {
		List<String> dataTypesRepresented = new ArrayList<>();
		ClusterHierarchy clustersInDataTypes = clusterHierarchyRepo.findFirstByClusterName(clusterName);
		if (clustersInDataTypes.getIsSingleCellCluster().equalsIgnoreCase("Y")) {
			dataTypesRepresented.add(DataTypeEnum.SINGLE_CELL.getAbbreviation());
		}
		if (clustersInDataTypes.getIsSingleNucCluster().equalsIgnoreCase("Y")) {
			dataTypesRepresented.add(DataTypeEnum.SINGLE_NUCLEUS.getAbbreviation());
		}
		return dataTypesRepresented;
	}
}
