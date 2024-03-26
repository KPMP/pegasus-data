package org.kpmp.participant;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantRepoDataRepository extends CrudRepository<ParticipantRepoData, String> {
    

    @Query(value = "select a.experimental_strategy, a.data_type, a.data_category, coalesce(count,0) as count "
        + "from (select distinct experimental_strategy, data_type, data_category from repo_file_v) as a "
        + "left join (select count(*) as count, experimental_strategy, data_type, data_category from repo_file_v where redcap_id= :redcap_id "
        + "group by experimental_strategy) as b on a.experimental_strategy=b.experimental_strategy", nativeQuery = true)
    public List<ParticipantRepoData> findFileCountsByParticipant(@Param("redcap_id") String redcapId);

}