package org.kpmp.cellTypeSummary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kpmp.FullDataTypeEnum;
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
		if (clusterName.equals("Tubulo-interstitium")) {
			dataTypesRepresented.add(FullDataTypeEnum.REGIONAL_PROTEOMICS.getAbbreviation());
			dataTypesRepresented.add(FullDataTypeEnum.REGIONAL_TRANSCRIPTOMICS.getAbbreviation());
		} else {
			ClusterHierarchy clustersInDataTypes = clusterHierarchyRepo.findFirstByClusterOrRegion(clusterName);
			if (clustersInDataTypes.getIsSingleCellCluster().equalsIgnoreCase("Y")) {
				dataTypesRepresented.add(FullDataTypeEnum.SINGLE_CELL.getAbbreviation());
			}
			if (clustersInDataTypes.getIsSingleNucCluster().equalsIgnoreCase("Y")) {
				dataTypesRepresented.add(FullDataTypeEnum.SINGLE_NUCLEUS.getAbbreviation());
			}
			if (clustersInDataTypes.getIsRegionalTranscriptomics().equalsIgnoreCase("Y")) {
				dataTypesRepresented.add(FullDataTypeEnum.REGIONAL_TRANSCRIPTOMICS.getAbbreviation());
			}
			if (clustersInDataTypes.getIsRegionalProteomics().equalsIgnoreCase("Y")) {
				dataTypesRepresented.add(FullDataTypeEnum.REGIONAL_PROTEOMICS.getAbbreviation());
			}
		}
		return dataTypesRepresented;
	}

    public List<ClusterHierarchy> findClustersByCellType2025(String cellType) {

        ArrayList<ClusterHierarchy> result = new ArrayList<>();
        Map<String, ClusterHierarchy> clusterToHierarchy = new HashMap<>();
        List<ClusterHierarchy> clusterHierarchiesRNASeq = clusterHierarchyRepo.findRnaSeqByCellTypeOrRegion(cellType);
        List<ClusterHierarchy> clusterHierarchiesRegional = clusterHierarchyRepo.findRTRPByCellTypeOrRegion(cellType);
        List<ClusterHierarchy> clusterHierarchiesParentRegions = clusterHierarchyRepo.findRTRPParentRegions(cellType);
        clusterHierarchiesRNASeq.addAll(clusterHierarchiesRegional);
        clusterHierarchiesRNASeq.addAll(clusterHierarchiesParentRegions);

        for (ClusterHierarchy clusterHierarchy : clusterHierarchiesRNASeq) {
            String clusterName = clusterHierarchy.getClusterName();
            if (clusterToHierarchy.containsKey(clusterName)) {
                if (clusterName == null) {
                    result.add(clusterHierarchy);
                } else if (clusterName.equals(clusterHierarchy.getCellType())) {
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

    public List<String> findDataTypesByClusterName2025(String clusterName) {
        List<String> dataTypesRepresented = new ArrayList<>();
        if (clusterName.equals("Tubulo-interstitium")) {
            dataTypesRepresented.add(FullDataTypeEnum.REGIONAL_PROTEOMICS.getAbbreviation());
            dataTypesRepresented.add(FullDataTypeEnum.REGIONAL_TRANSCRIPTOMICS.getAbbreviation());
        } else {
            ClusterHierarchy clustersInDataTypes = clusterHierarchyRepo.findFirstByClusterOrRegion2025(clusterName);
            if (clustersInDataTypes.getIsSingleCellCluster().equalsIgnoreCase("Y")) {
                dataTypesRepresented.add(FullDataTypeEnum.SINGLE_CELL.getAbbreviation());
            }
            if (clustersInDataTypes.getIsSingleNucCluster().equalsIgnoreCase("Y")) {
                dataTypesRepresented.add(FullDataTypeEnum.SINGLE_NUCLEUS.getAbbreviation());
            }
            if (clustersInDataTypes.getIsRegionalTranscriptomics().equalsIgnoreCase("Y")) {
                dataTypesRepresented.add(FullDataTypeEnum.REGIONAL_TRANSCRIPTOMICS.getAbbreviation());
            }
            if (clustersInDataTypes.getIsRegionalProteomics().equalsIgnoreCase("Y")) {
                dataTypesRepresented.add(FullDataTypeEnum.REGIONAL_PROTEOMICS.getAbbreviation());
            }
        }
        return dataTypesRepresented;
    }
}
