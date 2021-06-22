package org.kpmp.geneExpressionSummary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class RTSummaryValue {

    @Id
    @Column(name = "segment")
    private String segment;
    @Column(name = "all_count")
    private Integer allCount;
    @Column(name = "hrt_count")
    private Integer hrtCount;
    @Column(name = "aki_count")
    private Integer akiCount;
    @Column(name = "ckd_count")
    private Integer ckdCount;

    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public Integer getAllCount() {
        return allCount;
    }

    public void setAllCount(Integer allCount) {
        this.allCount = allCount;
    }

    public Integer getHrtCount() {
        return hrtCount;
    }

    public void setHrtCount(Integer hrtCount) {
        this.hrtCount = hrtCount;
    }

    public Integer getAkiCount() {
        return akiCount;
    }

    public void setAkiCount(Integer akiCount) {
        this.akiCount = akiCount;
    }

    public Integer getCkdCount() {
        return ckdCount;
    }

    public void setCkdCount(Integer ckdCount) {
        this.ckdCount = ckdCount;
    }
}
