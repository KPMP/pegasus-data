package org.kpmp.cellType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class HubmapCellTypeMappingService {

    private final HubmapOnotologyCellTypeRepository repo;

    public HubmapCellTypeMappingService(HubmapOnotologyCellTypeRepository repo) {
        this.repo = repo;
    }

    public Map<String, String> buildHubmapIdToCellTypeMap() {
        List<HubmapOntologyCellType> idToCellTypes = repo.findAll();
        Map<String, String> mapping = new HashMap<>();
        for (HubmapOntologyCellType idToCellType : idToCellTypes) {
            mapping.put(idToCellType.getHubmapOntologyId(), idToCellType.getCell_type());
        }
        return mapping;
    }
}
