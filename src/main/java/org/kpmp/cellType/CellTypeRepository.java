package org.kpmp.cellType;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CellTypeRepository extends CrudRepository<CellType, Integer> {

    List<CellType> findByCellTypeContainingOrCellTypeSynonymContaining(String searchTerm);
    
    List<CellType> findAll();

}