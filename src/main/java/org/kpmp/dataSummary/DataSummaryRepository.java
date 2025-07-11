package org.kpmp.dataSummary;

import java.util.List;

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
			+ "where sv.config_type = :data_type " + "and p.enrollment_category = :enrollment_category " 
	       		+ "and sv.release_sunset_version is null", nativeQuery = true)
	Long getDataSummaryCount(@Param("enrollment_category") String enrollment_category, @Param("data_type") String data_type);

	@Cacheable("dataSummaryTotal")
	@Query(value = "select count(distinct(redcap_id)) from participant p "
			+ "join file_participant fp on p.participant_id = fp.participant_id "
			+ "join file f on f.file_id= fp.file_id " + "join sv_file_info sv on sv.file_id = f.file_id "
			+ "where sv.config_type = :data_type and sv.release_sunset_version is null", nativeQuery = true)
	Long getDataSummaryTotal(@Param("data_type") String data_type);
	
	@Cacheable("repoDataSummaryCount")
	@Query(value = "select count(distinct(dl_file_id)) from repo_file_v where experimental_strategy = :exp_strat and enrollment_category = :enrollment_category", nativeQuery = true)
	Long getRepoDataSummaryCount(@Param("enrollment_category") String enrollment_category, @Param("exp_strat") String exp_strat);

	@Cacheable("repoDataSummaryTotal")
	@Query(value = "select count(distinct(dl_file_id)) from repo_file_v where experimental_strategy = :exp_strat", nativeQuery = true)
	Long getRepoDataSummaryTotal(@Param("exp_strat") String exp_strat);
	
	@Cacheable("repoBiomarkerSummaryCount")
	@Query(value = "select count(distinct(dl_file_id)) from repo_file_v where data_category = 'Biomarker' and enrollment_category = :enrollment_category", nativeQuery = true)
	Long getRepoBiomarkerSummaryCount(@Param("enrollment_category") String enrollment_category);
	
	@Cacheable("repoBiomarkerSummaryTotal")
	@Query(value = "select count(distinct(dl_file_id)) from repo_file_v where data_category = 'Biomarker' ", nativeQuery = true)
	Long getRepoBiomarkerSummaryTotal();

	@Cacheable("dataSummaryLinkCount")
	@Query(value = "select count(distinct(redcap_id)) " + "from sv_link_v " + "where data_type = :data_type "
			+ "and enrollment_category = :enrollment_category", nativeQuery = true)
	Long getDataSummaryLinkCount(@Param("enrollment_category") String enrollment_category, @Param("data_type") String data_type);

	@Cacheable("dataSummaryLinkTotal")
	@Query(value = "select count(distinct(redcap_id)) " + "from sv_link_v " + "where data_type = :data_type ", nativeQuery = true)
	Long getDataSummaryLinkTotal(@Param("data_type") String data_type);

	@Cacheable("dataParticipantSummaryCount")
	@Query(value = "SELECT count(*) FROM sv_file_v WHERE data_type= :data_type", nativeQuery = true)
	Long getParticipantSummaryCount(@Param("data_type") String data_type);

	@Cacheable("dataParticipantSummaryCountByConfigType")
	@Query(value = "SELECT count(*) FROM sv_file_v WHERE config_type= :config_type", nativeQuery = true)
	Long getParticipantSummaryCountByConfigType(@Param("config_type") String config_type);

	@Cacheable("dataParticipantSummaryLinkCount")
	@Query(value = "SELECT count(*) FROM sv_link_v WHERE data_type= :data_type", nativeQuery = true)
	Long getParticipantSummaryLinkCount(@Param("data_type") String data_type);

	@Cacheable("dataParticipantSvFileDataTypeCount")
	@Query(value = "SELECT count(*) from sv_file_v WHERE data_type= :data_type AND redcap_id= :redcap_id", nativeQuery = true)
	Integer getParticipantSvFileDataTypeCount(@Param("redcap_id") String redcapId, @Param("data_type") String dataType);

	@Cacheable("dataParticipantSvLinkDataTypeCount")
	@Query(value = "SELECT count(*) from sv_link_v WHERE data_type= :data_type AND redcap_id= :redcap_id", nativeQuery = true)
	Integer getParticipantSvLinkDataTypeCount(@Param("redcap_id") String redcapId, @Param("data_type") String dataType);

	@Cacheable("dataParticipantRepoFileDataTypeCount")
	@Query(value = "SELECT count(*) from (SELECT distinct(dl_file_id) FROM repo_file_v WHERE data_type= :data_type AND redcap_id= :redcap_id) a", nativeQuery = true)
	Integer getParticipantRepoFileDataTypeCount(@Param("redcap_id") String redcapId, @Param("data_type") String dataType);

    @Cacheable("repoDataTypes")
	@Query(value = "SELECT distinct(data_type) FROM repository_summary_v ORDER BY data_type ASC", nativeQuery = true)
	List<String> getRepoDataTypes();

	@Cacheable("participantIDFromRedcapID")
	@Query(value = "SELECT participant_id FROM participant WHERE redcap_id= :redcap_id LIMIT 1", nativeQuery = true)
	String getParticipantIDString(@Param("redcap_id") String redcapId);

	@Cacheable("participantTotalFileCount")
	@Query(value = "SELECT count(DISTINCT fp.file_id) FROM file_participant fp JOIN ar_file_info ar ON fp.file_id = ar.file_id "
				+ "WHERE fp.participant_id= :participant_id AND ar.release_sunset_version IS NULL", nativeQuery = true)
	Integer getParticipantTotalFileCount(@Param("participant_id") String participantId);

	@Cacheable("sv_data_types")
	@Query(value="SELECT distinct(data_type) from sv_file_v", nativeQuery = true)
	List<String> getSpatialViewerDataTypes();

	@Cacheable("sv_link_data_types")
	@Query(value="select distinct(data_type) from sv_link_v", nativeQuery = true)
	List<String> getSpatialViewerLinkDataTypes();
}
