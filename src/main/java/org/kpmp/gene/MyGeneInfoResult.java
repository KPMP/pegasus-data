package org.kpmp.gene;

import java.util.List;

public class MyGeneInfoResult {
    private int total;
    private List<MyGeneInfoHit> hits;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<MyGeneInfoHit> getHits() {
        return hits;
    }

    public void setHits(List<MyGeneInfoHit> hits) {
        this.hits = hits;
    }
}
