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


  @Cacheable("dataSummaryCount")
	@Query(value = "SELECT count(*) FROM sv_file_v WHERE tissue_type= :tissue_type and data_type= :data_type")
  Long getDataSummaryCount(@Param("tissue_type") String tissue_type, @Param("data_type") String data_type);

  @Cacheable("dataSummaryCount")
	@Query(value = "SELECT count(*) FROM sv_file_v WHERE data_type= :data_type")
  Long getParticipantSummaryCount(@Param("data_type") String data_type);
}