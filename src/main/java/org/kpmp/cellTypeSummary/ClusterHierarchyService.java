package org.kpmp.cellTypeSummary;

import java.util.*;

import org.kpmp.DataTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTypeExcludeFilter;
import org.springframework.stereotype.Service;

@Service
public class ClusterHierarchyService {

	private ClusterHiearchyRepository clusterHierarchyRepo;

	@Autowired
	public ClusterHierarchyService(ClusterHiearchyRepository clusterHierarchyRepo) {
		this.clusterHierarchyRepo = clusterHierarchyRepo;
	}

	public List<ClusterHierarchy> findClustersByCellType(String cellType) {

		ArrayList<ClusterHierarchy> result = new ArrayList<>();
		Map<String, ClusterHierarchy> clusterToHierarchy = new HashMap<>();
		List<ClusterHierarchy> clusterHierarchies = clusterHierarchyRepo.findByCellType(cellType);
		for (ClusterHierarchy clusterHierarchy : clusterHierarchies) {
			String clusterName = clusterHierarchy.getClusterName();
			if (clusterToHierarchy.containsKey(clusterName)) {
				if (clusterName == null) {
					result.add(clusterHierarchy);
				} else if (clusterName != null && clusterName.equals(clusterHierarchy.getCellType())) {
					clusterToHierarchy.put(clusterName, clusterHierarchy);
				}
			} else {
				clusterToHierarchy.put(clusterName, clusterHierarchy);
			}
		}
		if (cellType.equals("Tubules") || cellType.equals("Interstitium")) {
			ClusterHierarchy tiCluster = new ClusterHierarchy();
			tiCluster.setStructureRegion("Tubulo-interstitium");
			tiCluster.setIsSingleCellCluster("N");
			tiCluster.setIsSingleNucCluster("N");
			tiCluster.setIsRegionalProteomics("Y");
			tiCluster.setIsRegionalTranscriptomics("Y");
			tiCluster.setCellTypeOrder(0.01);
			result.add(tiCluster);
		}
		result.addAll(clusterToHierarchy.values());
        Collections.sort(result, new Comparator<ClusterHierarchy>() {
            @Override
            public int compare(ClusterHierarchy a, ClusterHierarchy b) {
                return a.getCellTypeOrder().compareTo(b.getCellTypeOrder());
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
		if (clustersInDataTypes.getIsRegionalProteomics().equalsIgnoreCase("Y")) {
			dataTypesRepresented.add(DataTypeEnum.REGIONAL_PROTEOMICS.getAbbreviation());
		}
		if (clusterName.equals("Tubulo-interstitium")) {
			dataTypesRepresented.add(DataTypeEnum.REGIONAL_PROTEOMICS.getAbbreviation());
			dataTypesRepresented.add(DataTypeEnum.REGIONAL_TRANSCRIPTOMICS.getAbbreviation());
		}
		return dataTypesRepresented;
	}
}
