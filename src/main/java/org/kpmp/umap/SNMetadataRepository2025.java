package org.kpmp.umap;

import java.util.List;

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
						+ "umap_x, "
						+ "umap_y, "
						+ "cluster_abbreviation, "
						+ "cluster_name, "
						+ "cluster_color, "
						+ "barcode, "
						+ "enrollment_category "
					+ "FROM sn_umap_point_2025_v "
					+ "LIMIT :limit", nativeQuery = true)
	List<SNMetadata2025> findLimited(@Param("limit") int limit);

	@Cacheable("snMetadataCount2025")
	@Query(value = "SELECT COUNT(umap_x) FROM sn_umap_point_2025_v;", nativeQuery = true)
	int findCount();

	@Cacheable("snMetadataByEnrollment2025")
	List<SNMetadata2025> findByEnrollmentCategory(String enrollmentCategory);
	
	@Cacheable("snMetadataWithEnrollment2025")
	@Query(value = "SELECT "
						+ "umap_x, "
						+ "umap_y, "
						+ "cluster_abbreviation, "
						+ "cluster_name, "
						+ "cluster_color, "
						+ "barcode, "
						+ "enrollment_category "
					+ "FROM sn_umap_point_2025_v "
					+ "WHERE enrollment_category=:enrollmentCategory "
					+ "LIMIT :limit", nativeQuery = true)
	List<SNMetadata2025> findLimitedWithEnrollmentCategory(@Param("enrollmentCategory") String enrollmentCategory, @Param("limit") int limit);
}
