package org.kpmp.cellTypeSummary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

		Map<String, ClusterHierarchy> clusterToHierarchy = new HashMap<>();
		List<ClusterHierarchy> clusterHierarchies = clusterHierarchyRepo.findByCellType(cellType);
		for (ClusterHierarchy clusterHierarchy : clusterHierarchies) {
			String clusterName = clusterHierarchy.getClusterName();
			if (clusterToHierarchy.containsKey(clusterName)) {
				if (clusterName == null
						|| (clusterName != null && clusterName.equals(clusterHierarchy.getCellType()))) {
					clusterToHierarchy.put(clusterName, clusterHierarchy);
				}
			} else {
				clusterToHierarchy.put(clusterName, clusterHierarchy);
			}
		}
		ArrayList<ClusterHierarchy> result = new ArrayList<>(clusterToHierarchy.values());
		Collections.sort(result, new Comparator<ClusterHierarchy>() {

			@Override
			public int compare(ClusterHierarchy a, ClusterHierarchy b) {

				return a.getStructureSubregion().compareTo(b.getStructureSubregion());
			}

		});
		return result;
	}

	public List<String> findDataTypesByClusterName(String clusterName) {
		List<String> dataTypesRepresented = new ArrayList<>();
		ClusterHierarchy clustersInDataTypes = clusterHierarchyRepo.findFirstByClusterOrRegion(clusterName);
		if (clustersInDataTypes.getIsSingleCellCluster().equalsIgnoreCase("Y")) {
			dataTypesRepresented.add(DataTypeEnum.SINGLE_CELL.getAbbreviation());
		}
		if (clustersInDataTypes.getIsSingleNucCluster().equalsIgnoreCase("Y")) {
			dataTypesRepresented.add(DataTypeEnum.SINGLE_NUCLEUS.getAbbreviation());
		}
		if (clustersInDataTypes.getIsRegionalTranscriptomics().equalsIgnoreCase("Y")) {
			dataTypesRepresented.add(DataTypeEnum.REGIONAL_TRANSCRIPTOMICS.getAbbreviation());
		}
		return dataTypesRepresented;
	}
}
