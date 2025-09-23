package org.kpmp.cellTypeSummary;

import java.util.List;

import org.kpmp.cluster.Cluster;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

interface ClusterHiearchyRepository extends CrudRepository<ClusterHierarchy, ClusterHierarchyId> {

	@Override
	@Cacheable("clusterHierarchy")
	List<ClusterHierarchy> findAll();

    @Cacheable("clusterHierarchyRNA2025ByCellType")
    @Query(value = "SELECT ch.*, 'N' AS is_rt, 'N' AS is_rp, 'Y' AS is_single_cell, 'Y' as is_single_nuc FROM cluster_hierarchy_2025_v ch WHERE ch.cell_type = :cell_type OR ch.structure_region = :cell_type OR ch.structure_subregion = :cell_type " +
            "ORDER BY ch.cell_type_order ASC", nativeQuery = true)
    List<ClusterHierarchy> findRnaSeqByCellTypeOrRegion(@Param("cell_type") String cell_type);

    @Cacheable("clusterHierarchyRP2025ByCellType")
    @Query(value = "SELECT * FROM (SELECT rt.*, 0 AS cluster_id, rt.cell_type AS cluster_name, 'Y' AS is_rt, 'Y' AS is_rp, 'N' AS is_single_cell, 'N' as is_single_nuc FROM rt_segment_hierarchy_2025_v rt " +
            "WHERE rt.abbreviation <> 'Ti' AND rt.abbreviation <> 'INT' AND rt.structure_subregion IS NULL AND (rt.cell_type = :cell_type OR rt.structure_region = :cell_type OR rt.structure_subregion = :cell_type) " +
            "UNION ALL " +
            "SELECT rt.*, 0 AS cluster_id, NULL AS `cluster_name`, 'Y' AS is_rt, 'N' AS is_rp, 'N' AS is_single_cell, 'N' as is_single_nuc FROM rt_segment_hierarchy_2025_v rt " +
            "WHERE rt.abbreviation <> 'Ti' AND (rt.cell_type = :cell_type OR rt.structure_region = :cell_type OR rt.structure_subregion = :cell_type)) x ORDER BY x.cell_type_order ASC", nativeQuery = true)
    List<ClusterHierarchy> findRTRPByCellTypeOrRegion(@Param("cell_type") String cell_type);

    // This query gets the regions or subregions that RT/RP data are in given a more specific cell type.
    @Cacheable("clusterHierarchy2025ByCellTypeRegionsSubregions")
    @Query(value = "SELECT * FROM (SELECT v1.*, 0 AS cluster_id, v1.cell_type AS cluster_name, 'Y' AS is_rt, 'Y' AS is_rp, 'N' AS is_single_cell, 'N' as is_single_nuc FROM rt_segment_hierarchy_2025_v v1 " +
    "WHERE v1.cell_type IS NULL AND v1.structure_subregion IS NULL AND v1.structure_region IN (" +
            "SELECT v2.structure_region FROM cell_type_2025 v2 " +
                    "WHERE v2.cell_type = :cell_type OR " +
                    "v2.structure_subregion = :cell_type) " +
    "UNION ALL " +
    "SELECT v1.*, 0 AS cluster_id, v1.cell_type AS cluster_name, 'Y' AS is_rt, 'Y' AS is_rp, 'N' AS is_single_cell, 'N' as is_single_nuc FROM knowledge_environment.rt_segment_hierarchy_2025_v v1 " +
    "WHERE v1.cell_type IS NULL AND v1.structure_subregion IN ( " +
            "SELECT v2.structure_subregion FROM cell_type_2025 v2 " +
                    "WHERE v2.cell_type = :cell_type OR " +
                    "v2.structure_subregion = :cell_type)) x GROUP BY x.cluster_name, x.structure_subregion, x.structure_region ORDER BY x.cell_type_order ASC", nativeQuery = true)
    List<ClusterHierarchy> findRTRPParentRegions(@Param("cell_type") String cell_type);

//    @Query(value = "SELECT v1.*, 'N' AS is_rt, 'N' AS is_rp, 'Y' AS is_single_cell, 'Y' as is_single_nuc FROM cluster_hierarchy_2025_v v1 " +
//    "WHERE v1.cluster_name = :cluster_name " +
//    "UNION ALL " +
//    "SELECT v1.cell_type, v1.structure_region, v1.structure_subregion, v1.cell_type_id, v1.cell_type_order, 0 as cluster_id, v1.cell_type AS cluster_name, 'Y' AS is_rt, 'Y' AS is_rp, 'N' AS is_single_cell, 'N' as is_single_nuc " +
//            "FROM rt_segment_hierarchy_2025_v v1 " +
//    "WHERE v1.cell_type IS NULL AND (v1.structure_subregion = :cluster_name OR v1.structure_region = :cluster_name) LIMIT 1", nativeQuery = true)

    @Cacheable("clusterHierarchyCluster2025")
    @Query(value =
            "SELECT v1.*, 'N' AS is_rt, 'N' AS is_rp, 'Y' AS is_single_cell, 'Y' as is_single_nuc FROM cluster_hierarchy_2025_v v1 " +
            "WHERE v1.cluster_name = :cluster_name " +
            "UNION ALL " +
            "SELECT rt.cell_type, rt.structure_region, rt.structure_subregion, rt.cell_type_id, rt.cell_type_order, 0 as cluster_id, v1.cell_type AS cluster_name, 'Y' AS is_rt, 'Y' AS is_rp, 'N' AS is_single_cell, 'N' as is_single_nuc FROM rt_segment_hierarchy_2025_v rt " +
            "WHERE rt.abbreviation <> 'Ti' AND rt.abbreviation <> 'INT' AND rt.structure_subregion IS NULL AND rt.structure_region = :cluster_name " +
            "UNION ALL " +
            "SELECT rt.cell_type, rt.structure_region, rt.structure_subregion, rt.cell_type_id, rt.cell_type_order, 0 as cluster_id, v1.cell_type AS cluster_name, 'Y' AS is_rt, 'N' AS is_rp, 'N' AS is_single_cell, 'N' as is_single_nuc FROM rt_segment_hierarchy_2025_v rt " +
            "WHERE rt.abbreviation <> 'Ti' AND rt.structure_subregion = :cluster_name)) x ORDER BY x.cell_type_order ASC", nativeQuery = true)
    ClusterHierarchy findFirstByClusterOrRegion2025(@Param("cluster_name") String cell_type);

    @Cacheable("clusterHierarchyCt")
	@Query(value = "CALL cluster_hierarchy_sp(:cell_type);", nativeQuery = true)
	List<ClusterHierarchy> findByCellType(@Param("cell_type") String cellType);

	@Cacheable("clusterHierarchyCluster")
	@Query(value = "CALL cluster_hierarchy_by_cluster_sp(:cluster);", nativeQuery = true)
    ClusterHierarchy findFirstByClusterOrRegion(String cluster);

}
