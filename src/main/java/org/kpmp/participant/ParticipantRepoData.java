package org.kpmp.participant;

import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;

@Entity
@NamedNativeQuery (name="fileCountByExperimentAndParticipant",
    query="select a.experimental_strategy, b.data_type, b.data_category, coalesce(count,0) as count "
    + "from (select distinct experimental_strategy from repo_file_v) as a "
    + "left join (select count(*) as count, experimental_strategy, data_type, data_category from repo_file_v where redcap_id= :redcap_id "
    + "group by experimental_strategy) as b on a.experimental_strategy=b.experimental_strategy",
    resultSetMapping = "participantRepoData")


@SqlResultSetMapping(
    name = "participantRepoData",
    entities = {
        @EntityResult(
            entityClass=org.kpmp.participant.ParticipantRepoData.class,
            fields={
                @FieldResult(name="experimentalStrategy",column="experimental_strategy"),
                @FieldResult(name="dataType", column="data_type"),
                @FieldResult(name="dataCategory", column="data_category"),
                @FieldResult(name="count", column="count")
            }
        )
    }
)
public class ParticipantRepoData {

    @Id
    private String experimentalStrategy;
    private String dataType;
    private String dataCategory;
    private int count;

    public ParticipantRepoData(String experimentalStrategy, String dataType, String dataCategory, int count) {
        this.experimentalStrategy = experimentalStrategy;
        this.dataCategory = dataCategory;
        this.dataType = dataType;
        this.count = count;
    }


    public String getDataCategory() {
        return dataCategory;
    }

    public void setDataCategory(String dataCategory) {
        this.dataCategory = dataCategory;
    }

    public String getExperimentalStrategy() {
        return experimentalStrategy;
    }

    public void setExperimentalStrategy(String experimentalStrategy) {
        this.experimentalStrategy = experimentalStrategy;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}


