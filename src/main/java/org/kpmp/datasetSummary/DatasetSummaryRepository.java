package org.kpmp.datasetSummary;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DatasetSummaryRepository extends CrudRepository<DatasetSummaryValue, DatasetSummaryId> {

  @Cacheable("tissueTypeCount")
	@Query(value = "SELECT count(*) FROM sv_file_v WHERE tissue_type= "AKI" and data_type=: ")
  Long getCountByTissueType(@Param("tissue_type") String tissue_type, @Param("data_type") String data_type);
}