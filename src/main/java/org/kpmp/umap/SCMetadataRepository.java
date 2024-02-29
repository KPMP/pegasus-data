package org.kpmp.umap;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface SCMetadataRepository extends CrudRepository<SCMetadata, String> {

	@Cacheable("scMetadataAll")
	@Override
	List<SCMetadata> findAll();
	
	@Cacheable("scMetadataLimited")
	@Query(value = "SELECT "
						+ "umap_x, "
						+ "umap_y, "
						+ "cluster_abbreviation, "
						+ "cluster_name, "
						+ "cluster_color, "
						+ "barcode, "
						+ "tissue_type "
					+ "FROM sc_umap_point_v "
					+ "LIMIT :limit", nativeQuery = true)
	List<SCMetadata> findLimited(@Param("limit") int limit);

	@Cacheable("scMetadataCount")
	@Query(value = "SELECT COUNT(umap_x) FROM sc_umap_point_v;", nativeQuery = true)
	int findCount();

	List<SCMetadata> findByTissueType(String tissueType);

	SCMetadata findByBarcode(String barcode);

	@Cacheable("scMetadataWithTissue")
	@Query(value = "SELECT "
						+ "umap_x, "
						+ "umap_y, "
						+ "cluster_abbreviation, "
						+ "cluster_name, "
						+ "cluster_color, "
						+ "barcode, "
						+ "tissue_type "
					+ "FROM sc_umap_point_v "
					+ "WHERE tissue_type=:tissueType "
					+ "LIMIT :limit", nativeQuery = true)
	List<SCMetadata> findLimitedWithTissueType(@Param("tissueType") String tissueType, @Param("limit") int limit);
}
