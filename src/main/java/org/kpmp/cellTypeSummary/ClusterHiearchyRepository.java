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
    @Query(value = "SELECT ch.*, 'N' AS is_rt, 'N' AS is_rp, 'Y' AS is_single_cell, 'Y' as is_single_nuc FROM cluster_hierarchy_2025_v ch WHERE ch.cell_type = :cell_type OR ch.structure_region = :cell_type OR ch.structure_subregion = :cell_type", nativeQuery = true)
    List<ClusterHierarchy> findRnaSeqByCellTypeOrRegion(@Param("cell_type") String cell_type);

    @Cacheable("clusterHierarchyRT2025ByCellType")
    @Query(value = "SELECT rt.*, 0 AS cluster_id, NULL AS `cluster_name`, 'Y' AS is_rt, 'N' AS is_rp, 'N' AS is_single_cell, 'N' as is_single_nuc FROM rt_segment_hierarchy_2025_v rt " +
            "WHERE rt.abbreviation <> 'Ti' AND (rt.cell_type = :cell_type OR rt.structure_region = :cell_type OR rt.structure_subregion = :cell_type)", nativeQuery = true)
    List<ClusterHierarchy> findRTByCellTypeOrRegion(@Param("cell_type") String cell_type);

    @Cacheable("clusterHierarchyRP2025ByCellType")
    @Query(value = "SELECT rt.*, 0 AS cluster_id, rt.cell_type AS cluster_name, 'Y' AS is_rt, 'Y' AS is_rp, 'N' AS is_single_cell, 'N' as is_single_nuc FROM rt_segment_hierarchy_2025_v rt " +
            "WHERE rt.abbreviation <> 'Ti' AND rt.abbreviation <> 'INT' AND rt.structure_subregion IS NULL AND (rt.cell_type = :cell_type OR rt.structure_region = :cell_type OR rt.structure_subregion = :cell_type) " +
            "UNION ALL " +
            "SELECT rt.*, 0 AS cluster_id, NULL AS `cluster_name`, 'Y' AS is_rt, 'N' AS is_rp, 'N' AS is_single_cell, 'N' as is_single_nuc FROM rt_segment_hierarchy_2025_v rt " +
            "WHERE rt.abbreviation <> 'Ti' AND (rt.cell_type = :cell_type OR rt.structure_region = :cell_type OR rt.structure_subregion = :cell_type)", nativeQuery = true)
    List<ClusterHierarchy> findRTRPByCellTypeOrRegion(@Param("cell_type") String cell_type);

    @Cacheable("clusterHierarchyRNA2025ByCluster")
    @Query(value = "SELECT * FROM cluster_hierarchy_2025_v WHERE cluster_name = :cluster_name", nativeQuery = true)
    List<ClusterHierarchy> findRnaSeqByCluster(@Param("cluster_name") String cell_type);

    @Cacheable("clusterHierarchyRegional2025ByCluster")
    @Query(value = "SELECT * FROM rt_segment_hierarchy_2025_v WHERE (cell_type IS NULL AND (structure_subregion = :cluster_name OR structure_region = :cluster_name)) OR cell_type = :cluster_name LIMIT 1", nativeQuery = true)
    List<ClusterHierarchy> findRegionalByCluster(@Param("cluster_name") String cell_type);

	@Cacheable("clusterHierarchyCt")
	@Query(value = "CALL cluster_hierarchy_sp(:cell_type);", nativeQuery = true)
	List<ClusterHierarchy> findByCellType(@Param("cell_type") String cellType);

	@Cacheable("clusterHierarchyCluster")
	@Query(value = "CALL cluster_hierarchy_by_cluster_sp(:cluster);", nativeQuery = true)
    ClusterHierarchy findFirstByClusterOrRegion(String cluster);

}
