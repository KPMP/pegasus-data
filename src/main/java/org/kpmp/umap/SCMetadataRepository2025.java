package org.kpmp.umap;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface SCMetadataRepository2025 extends CrudRepository<SCMetadata2025, String> {

	@Cacheable("scMetadataAll")
	@Override
	List<SCMetadata2025> findAll();
	
	@Cacheable("scMetadataLimited")
	@Query(value = "SELECT "
			+ "umap_x, "
			+ "umap_y, "
			+ "cluster_abbreviation, "
			+ "cluster_name, "
			+ "cluster_color, "
			+ "barcode, "
			+ "enrollment_category "
			+ "FROM sc_umap_point_2025_v "
			+ "WHERE cluster_abbreviation in ( "
			+ "select cluster_abbreviation "
			+ "from sc_umap_point_2025_v "
			+ "group by cluster_abbreviation "
			+ "having count(cluster_abbreviation) < 1000 "
			+ "UNION ALL "
			+ "(SELECT "
			+ "umap_x, "
			+ "umap_y, "
			+ "cluster_abbreviation, "
			+ "cluster_name, "
			+ "cluster_color, "
			+ "barcode, "
			+ "enrollment_category "
			+ "FROM sc_umap_point_2025_v "
			+ "limit :limit)", nativeQuery = true)
	List<SCMetadata2025> findLimited(@Param("limit") int limit);

	@Cacheable("scMetadataCount")
	@Query(value = "SELECT COUNT(umap_x) FROM sc_umap_point_2025_v;", nativeQuery = true)
	int findCount();

	List<SCMetadata2025> findByEnrollmentCategory(String enrollmentCategory);

	SCMetadata findByBarcode(String barcode);

	@Cacheable("scMetadataWithEnrollment")
	@Query(value = "SELECT "
						+ "umap_x, "
						+ "umap_y, "
						+ "cluster_abbreviation, "
						+ "cluster_name, "
						+ "cluster_color, "
						+ "barcode, "
						+ "enrollment_category "
					+ "FROM sc_umap_point_2025_v "
					+ "WHERE enrollment_category=:enrollmentCategory "
					+ "LIMIT :limit", nativeQuery = true)
	List<SCMetadata2025> findLimitedWithEnrollmentCategory(@Param("enrollmentCategory") String enrollmentCategory, @Param("limit") int limit);
}
