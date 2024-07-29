package org.kpmp.participant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class ParticipantRepoData {

    @Id
    @Column(name = "experimental_strategy")
    private String experimentalStrategy;
    @Column(name = "data_type")
    private String dataType;
    @Column(name = "data_category")
    private String dataCategory;
    @Column(name = "count")
    private int count;

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


