package org.kpmp.umap;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ReferenceCluster implements Serializable {

	private static final long serialVersionUID = -3963666320832242935L;
	private List<Double> xValues = new ArrayList<>();
	private List<Double> yValues = new ArrayList<>();
	private String color;
	private String clusterName;
	private String clusterAbbreviation;

	public ReferenceCluster(String clusterAbbreviation, String color) {
		this.clusterAbbreviation = clusterAbbreviation;
		this.color = color;
	}

	public List<Double> getXValues() {
		return xValues;
	}

	public void setXValues(List<Double> x) {
		this.xValues = x;
	}

	public void addXValue(Double xValue) {
		xValues.add(xValue);
	}

	public List<Double> getYValues() {
		return yValues;
	}

	public void setYValues(List<Double> y) {
		this.yValues = y;
	}

	public void addYValue(Double yValue) {
		yValues.add(yValue);
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getClusterName() {
		return clusterName;
	}

	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}

	public String getClusterAbbreviation() {
		return clusterAbbreviation;
	}

	public void setClusterAbbreviation(String clusterAbbreviation) {
		this.clusterAbbreviation = clusterAbbreviation;
	}
}
