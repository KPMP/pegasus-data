package org.kpmp.datasetSummary;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DatasetSummaryRepository 
                extends CrudRepository<DatasetSummaryValue, DatasetSummaryId> {


  @Cacheable("datatypeCount")
	@Query(value = "SELECT tissue_type FROM sv_file_v WHERE data_type= :data_type")
  Long getCountByDataType(@Param("data_type") String data_type);
}