package org.kpmp.cellType;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CellTypeRepository extends CrudRepository<CellType, Integer> {

	@Query(value = "SELECT ct.*, cs.* " + "    FROM cell_type ct "
			+ "    JOIN celltype_synonym cs ON (ct.cell_type_id = cs.cell_type_id AND cs.cell_type_synonym LIKE %:searchTerm%) "
			+ "    UNION " + "    SELECT ct.*, cs.* " + "    FROM cell_type ct "
			+ "    LEFT JOIN celltype_synonym cs ON ct.cell_type_id = cs.cell_type_id "
			+ "    WHERE ct.cell_type LIKE %:searchTerm% OR ct.structure_region LIKE %:searchTerm% OR ct.structure_subregion LIKE %:searchTerm%", nativeQuery = true)
	List<CellType> findByCellTypeContainingOrSynonymContaining(@Param("searchTerm") String searchTerm);

	List<CellType> findAllByOrderByCellTypeOrderingAsc();

}