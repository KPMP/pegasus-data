package org.kpmp.cellTypeSummary;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

interface CellTypeClusterHierarchyRepository extends CrudRepository<CellTypeClusterHierarchy, Integer> {

    @Cacheable("cellTypeclusterHierarchyFindByCellTypeOrRegion")
    @Query(value = "SELECT ch.* " +
            "FROM cell_type_cluster_hierarchy ch " +
            "WHERE ch.cell_type = :cell_type or ch.structure_region = :cell_type  or ch.structure_subregion = :cell_type " +
            "UNION "+
            "SELECT ch2.* " +
            "FROM cell_type_cluster_hierarchy ch " +
            "JOIN cell_type_cluster_hierarchy ch2 on ch.structure_region = ch2.structure_region "+
            "WHERE (ch.cell_type = :cell_type  or ch.structure_region = :cell_type  or ch.structure_subregion = :cell_type ) AND (ch2.cell_type IS NULL AND ch2.structure_subregion IS NULL AND ch2.structure_superregion IS NULL)", nativeQuery = true)
    List<CellTypeClusterHierarchy> findByCellTypeOrRegion(@Param("cell_type") String cell_type);

    @Cacheable("cellTypeclusterHierarchyFindOneByCellTypeOrRegion")
    @Query(value = "SELECT ch.* " +
            "FROM cell_type_cluster_hierarchy ch " +
            "where ch.structure_region = :cell_type and ch.structure_subregion IS NULL " +
            "UNION " +
            "SELECT ch.* " +
            "FROM cell_type_cluster_hierarchy ch " +
            "where ch.cell_type = :cell_type " +
            "UNION " +
            "SELECT ch.* " +
            "FROM cell_type_cluster_hierarchy ch " +
            "where ch.structure_subregion = :cell_type and ch.cell_type IS NULL LIMIT 1", nativeQuery = true)
    CellTypeClusterHierarchy findOneByCellTypeOrRegion(@Param("cell_type") String cell_type);

}
