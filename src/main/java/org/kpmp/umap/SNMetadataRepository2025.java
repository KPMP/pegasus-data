package org.kpmp.umap;

import java.util.List;

import org.checkerframework.checker.units.qual.C;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

interface SNMetadataRepository2025 extends CrudRepository<SNMetadata2025, String> {

	@Cacheable("snMetadataAll2025")
	@Override
	List<SNMetadata2025> findAll();

	@Cacheable("snMetadataLimited2025")
	@Query(value = "SELECT "
			+ "umap_x, umap_y, cluster_abbreviation, cluster_name, cluster_color, barcode, enrollment_category "
			+ "FROM sn_umap_point_2025_v "
			+ "WHERE cluster_abbreviation in ( select cluster_abbreviation from sn_umap_point_2025_v "
			+ "group by cluster_abbreviation having count(cluster_abbreviation) < 1000) "
			+ "UNION ALL "
			+ "(SELECT "
			+ "umap_x, umap_y, cluster_abbreviation, cluster_name, cluster_color, barcode, enrollment_category "
			+ "FROM sn_umap_point_2025_v "
			+ "WHERE cluster_abbreviation in ( select cluster_abbreviation from sn_umap_point_2025_v "
			+ "group by cluster_abbreviation having count(cluster_abbreviation) BETWEEN 1000 AND 5000 ) "
			+ "limit :mediumClusterLimit) "
			+ "UNION ALL "
			+ "(SELECT "
			+ "umap_x, umap_y, cluster_abbreviation, cluster_name, cluster_color, barcode, enrollment_category "
			+ "FROM sn_umap_point_2025_v "
			+ "WHERE cluster_abbreviation in ( select cluster_abbreviation from sn_umap_point_2025_v "
			+ "group by cluster_abbreviation having count(cluster_abbreviation) > 5000 ) "
			+ "limit :largeClusterLimit) ", nativeQuery = true)
	List<SNMetadata2025> findLimited(@Param("mediumClusterLimit") int mediumClusterLimit, @Param("largeClusterLimit") int largeClusterLimit);

	@Cacheable("snMetadataCount2025")
	@Query(value = "SELECT COUNT(umap_x) FROM sn_umap_point_2025_v;", nativeQuery = true)
	int findCount();

	@Cacheable("snMetadataByEnrollment2025")
	List<SNMetadata2025> findByEnrollmentCategory(String enrollmentCategory);

	@Cacheable("snMetadataWithEnrollment2025")
	@Query(value = "SELECT "
			+ "umap_x, umap_y, cluster_abbreviation, cluster_name, cluster_color, barcode, enrollment_category "
			+ "FROM sn_umap_point_2025_v "
			+ "WHERE cluster_abbreviation in ( select cluster_abbreviation from sn_umap_point_2025_v "
			+ "group by cluster_abbreviation having count(cluster_abbreviation) < 1000) "
			+ "AND enrollment_category=:enrollmentCategory "
			+ "UNION ALL "
			+ "(SELECT "
			+ "umap_x, umap_y, cluster_abbreviation, cluster_name, cluster_color, barcode, enrollment_category "
			+ "FROM sn_umap_point_2025_v "
			+ "WHERE cluster_abbreviation in ( select cluster_abbreviation from sn_umap_point_2025_v "
			+ "group by cluster_abbreviation having count(cluster_abbreviation) BETWEEN 1000 AND 5000 ) "
			+ "AND enrollment_category=:enrollmentCategory "
			+ "limit :mediumClusterLimit) "
			+ "UNION ALL "
			+ "(SELECT "
			+ "umap_x, umap_y, cluster_abbreviation, cluster_name, cluster_color, barcode, enrollment_category "
			+ "FROM sn_umap_point_2025_v "
			+ "WHERE cluster_abbreviation in ( select cluster_abbreviation from sn_umap_point_2025_v "
			+ "group by cluster_abbreviation having count(cluster_abbreviation) > 5000 ) "
			+ "AND enrollment_category=:enrollmentCategory "
			+ "limit :largeClusterLimit) ", nativeQuery = true)
	List<SNMetadata2025> findLimitedWithEnrollmentCategory(@Param("enrollmentCategory") String enrollmentCategory,
														   @Param("mediumClusterLimit") int mediumClusterLimit,
														   @Param("largeClusterLimit") int largeClusterLimit);
}
