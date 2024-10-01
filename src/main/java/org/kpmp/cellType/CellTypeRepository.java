package org.kpmp.cellType;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CellTypeRepository extends CrudRepository<CellType, Integer> {

	@Cacheable("cellTypeContainingOrSynonymContaining")
	@Query(value = "SELECT ct.cell_type_id, ct.structure_region, ct.structure_subregion, ct.cell_type, ct.release_ver, ct.release_sunset, ct.cell_type_order, cs.cell_type_synonym " + "    FROM cell_type ct "
			+ "    JOIN celltype_synonym cs ON (ct.cell_type_id = cs.cell_type_id AND cs.cell_type_synonym LIKE %:searchTerm%) "
			+ "    UNION " + "    SELECT ct.cell_type_id, ct.structure_region, ct.structure_subregion, ct.cell_type, ct.release_ver, ct.release_sunset, ct.cell_type_order, cs.cell_type_synonym " + "    FROM cell_type ct "
			+ "    LEFT JOIN celltype_synonym cs ON ct.cell_type_id = cs.cell_type_id "
			+ "    WHERE ct.cell_type LIKE %:searchTerm% ", nativeQuery = true)
	List<CellType> findByCellTypeContainingOrSynonymContaining(@Param("searchTerm") String searchTerm);

	@Cacheable("cellTypeIsNotNullOrderByCellTypeOrdering")
	List<CellType> findAllByCellTypeIsNotNullOrderByCellTypeOrdering();

	@Cacheable("structureRegionContaining")
	List<CellType> findByStructureRegionContaining(String searchTerm);

	@Cacheable("structureSubregionContaining")
	List<CellType> findByStructureSubregionContaining(String searchTerm);

}
