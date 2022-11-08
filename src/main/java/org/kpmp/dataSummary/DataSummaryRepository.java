package org.kpmp.dataSummary;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DataSummaryRepository extends CrudRepository<DataSummaryValue, Integer> {
	@Cacheable("dataSummaryCount")
	@Query(value = "select count(distinct(redcap_id)) from participant p "
			+ "join file_participant fp on p.participant_id = fp.participant_id "
			+ "join file f on f.file_id= fp.file_id " + "join sv_file_info sv on sv.file_id = f.file_id "
			+ "where sv.config_type = :data_type " + "and p.tissue_type = :tissue_type", nativeQuery = true)
	Long getDataSummaryCount(@Param("tissue_type") String tissue_type, @Param("data_type") String data_type);

	@Cacheable("dataSummaryLinkCount")
	@Query(value = "select count(distinct(redcap_id)) " + "from sv_link_v " + "where data_type = :data_type "
			+ "and tissue_type = :tissue_type", nativeQuery = true)
	Long getDataSummaryLinkCount(@Param("tissue_type") String tissue_type, @Param("data_type") String data_type);

	@Cacheable("dataParticipantSummaryCount")
	@Query(value = "SELECT count(*) FROM sv_file_v WHERE data_type= :data_type", nativeQuery = true)
	Long getParticipantSummaryCount(@Param("data_type") String data_type);

	@Cacheable("dataParticipantSummaryLinkCount")
	@Query(value = "SELECT count(*) FROM sv_link_v WHERE data_type= :data_type", nativeQuery = true)
	Long getParticipantSummaryLinkCount(@Param("data_type") String data_type);

	@Cacheable("dataParticipantSvFileDataTypeCount")
	@Query(value = "SELECT count(*) from sv_file_v WHERE data_type= :data_type AND redcap_id= :redcap_id", nativeQuery = true)
	Integer getParticipantSvFileDataTypeCount(@Param("redcap_id") String redcapId, @Param("data_type") String dataType);

	@Cacheable("dataParticipantSvLinkDataTypeCount")
	@Query(value = "SELECT count(*) from sv_link_v WHERE data_type= :data_type AND redcap_id= :redcap_id", nativeQuery = true)
	Integer getParticipantSvLinkDataTypeCount(@Param("redcap_id") String redcapId, @Param("data_type") String dataType);
}
