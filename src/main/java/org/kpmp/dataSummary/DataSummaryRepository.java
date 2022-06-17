package org.kpmp.dataSummary;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DataSummaryRepository extends CrudRepository<DataSummaryValue, Integer> {
	@Cacheable("dataSummaryCount")
	@Query(value = "SELECT count(*) FROM sv_file_v WHERE tissue_type= :tissue_type and data_type= :data_type", nativeQuery = true)
	Long getDataSummaryCount(@Param("tissue_type") String tissue_type, @Param("data_type") String data_type);
	
	
	@Cacheable("dataParticipantSummaryCount")
	@Query(value = "SELECT count(*) FROM sv_file_v WHERE data_type= :data_type", nativeQuery = true)
	Long getParticipantSummaryCount(@Param("data_type") String data_type);
}
