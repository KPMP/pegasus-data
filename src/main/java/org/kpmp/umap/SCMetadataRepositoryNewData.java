package org.kpmp.umap;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface SCMetadataRepositoryNewData extends CrudRepository<SCMetadataNewData, String> {

	@Cacheable("scMetadataAll")
	@Override
	List<SCMetadataNewData> findAll();
	
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
					+ "LIMIT :limit", nativeQuery = true)
	List<SCMetadataNewData> findLimited(@Param("limit") int limit);

	@Cacheable("scMetadataCount")
	@Query(value = "SELECT COUNT(umap_x) FROM sc_umap_point_2025v;", nativeQuery = true)
	int findCount();

	List<SCMetadataNewData> findByEnrollmentCategory(String enrollmentCategory);

	SCMetadataNewData findByBarcode(String barcode);

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
	List<SCMetadataNewData> findLimitedWithEnrollmentCategory(@Param("enrollmentCategory") String enrollmentCategory, @Param("limit") int limit);
}
