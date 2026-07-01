package org.kpmp.cellType;

import org.jspecify.annotations.NonNull;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface HubmapOnotologyCellTypeRepository extends CrudRepository<HubmapOntologyCellType, String> {

    @NonNull
    @Cacheable("hubmapOntologyCellTypeMap")
    List<HubmapOntologyCellType> findAll();
}
