package org.kpmp.cellTypeSummary;

import org.kpmp.FullDataTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CellTypeClusterHierarchyService {

	private CellTypeClusterHierarchyRepository cellTypeClusterHierarchyRepository;

	@Autowired
	public CellTypeClusterHierarchyService(ClusterHiearchyRepository clusterHierarchyRepo) {
		this.cellTypeClusterHierarchyRepository = cellTypeClusterHierarchyRepository;
	}

    public List<CellTypeClusterHierarchy> findClustersByCellType2026(String cellType) {

        ArrayList<CellTypeClusterHierarchy> result = new ArrayList<>();
        Map<String, CellTypeClusterHierarchy> clusterToHierarchy = new LinkedHashMap<>();
        Set<CellTypeClusterHierarchy> cellTypeClusterHierarchySet = new LinkedHashSet<>();
        List<CellTypeClusterHierarchy> cellTypeClusterHierarchies = cellTypeClusterHierarchyRepository.findByCellTypeOrRegion(cellType);
        cellTypeClusterHierarchySet.addAll(cellTypeClusterHierarchies);

        boolean hasTubulesOrInterstitium = cellTypeClusterHierarchySet.stream()
            .anyMatch(ch -> "Tubules".equals(ch.getStructureRegion()) 
                    || "Interstitium".equals(ch.getStructureRegion()));

        if (hasTubulesOrInterstitium || cellType.equals("Tubules") || cellType.equals("Interstitium")) {
            CellTypeClusterHierarchy tiCluster = new CellTypeClusterHierarchy();
            tiCluster.setStructureRegion("Tubulo-interstitium");
            tiCluster.setIsSingleCellCluster("N");
            tiCluster.setIsSingleNucCluster("N");
            tiCluster.setIsRegionalProteomics("Y");
            tiCluster.setIsRegionalTranscriptomics("Y");
            tiCluster.setCellTypeOrder(0.01);
            result.add(tiCluster);
        }
        result.addAll(clusterToHierarchy.values());
        Collections.sort(result, new Comparator<CellTypeClusterHierarchy>() {
            @Override
            public int compare(CellTypeClusterHierarchy a, CellTypeClusterHierarchy b) {
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
            CellTypeClusterHierarchy clustersInDataTypes = cellTypeClusterHierarchyRepository.findOneByCellTypeOrRegion(clusterName);
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
