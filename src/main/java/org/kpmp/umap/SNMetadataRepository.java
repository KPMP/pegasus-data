package org.kpmp.umap;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

interface SNMetadataRepository extends CrudRepository<SNMetadata, String> {

	@Cacheable("snMetadata")
	@Override
	List<SNMetadata> findAll();

	@Query(value = "SELECT "
						+ "umap_x, "
						+ "umap_y, "
						+ "cluster_abbreviation, "
						+ "cluster_name, "
						+ "cluster_color, "
						+ "barcode, "
						+ "tissue_type "
					+ "FROM sn_umap_point_v "
					+ "LIMIT :limit", nativeQuery = true)
	List<SNMetadata> findLimited(@Param("limit") int limit);

	@Query(value = "SELECT COUNT(umap_x) FROM sn_umap_point_v;", nativeQuery = true)
	int findCount();

	List<SNMetadata> findByTissueType(String tissueType);
	
	@Cacheable("snMetadata")
	@Query(value = "SELECT "
						+ "umap_x, "
						+ "umap_y, "
						+ "cluster_abbreviation, "
						+ "cluster_name, "
						+ "cluster_color, "
						+ "barcode, "
						+ "tissue_type "
					+ "FROM sn_umap_point_v "
					+ "WHERE tissue_type=:tissueType "
					+ "LIMIT :limit", nativeQuery = true)
	List<SNMetadata> findLimitedWithTissueType(@Param("tissueType") String tissueType, @Param("limit") int limit);
}
