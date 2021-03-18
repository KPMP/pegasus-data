package org.kpmp.umap;

import java.io.Serializable;

public class UmapPointId implements Serializable {

	private static final long serialVersionUID = 6207289752569815225L;

	private double umapX;
	private double umapY;

	public double getUmapX() {
		return umapX;
	}

	public void setUmapX(double umapX) {
		this.umapX = umapX;
	}

	public double getUmapY() {
		return umapY;
	}

	public void setUmapY(double umapY) {
		this.umapY = umapY;
	}

}
