package org.kpmp.umap;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

interface SNMetadataRepositoryNewData extends CrudRepository<SNMetadataNewData, String> {

	@Cacheable("snMetadataAll")
	@Override
	List<SNMetadataNewData> findAll();

	@Cacheable("snMetadataLimited")
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
	List<SNMetadataNewData> findLimited(@Param("limit") int limit);

	@Cacheable("snMetadataCount")
	@Query(value = "SELECT COUNT(umap_x) FROM sn_umap_point_2025_v;", nativeQuery = true)
	int findCount();

	@Cacheable("snMetadataByEnrollment")
	List<SNMetadataNewData> findByEnrollmentCategory(String enrollmentCategory);
	
	@Cacheable("snMetadataWithEnrollment")
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
	List<SNMetadataNewData> findLimitedWithEnrollmentCategory(@Param("enrollmentCategory") String enrollmentCategory, @Param("limit") int limit);
}
