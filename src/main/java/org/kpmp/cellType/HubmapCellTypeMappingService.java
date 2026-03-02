package org.kpmp.cellType;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class HubmapCellTypeMappingService {

    private final HubmapOnotologyCellTypeRepository repo;

    public HubmapCellTypeMappingService(HubmapOnotologyCellTypeRepository repo) {
        this.repo = repo;
    }

    public List<HubmapOntologyCellType> buildHubmapIdToCellTypeMap() {
        return repo.findAll();
    }
}
