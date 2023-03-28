package org.kpmp.gene;

import java.io.Serializable;
import java.util.List;

public class MyGeneInfoResult implements Serializable {
	private static final long serialVersionUID = 2296351561705106315L;
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
