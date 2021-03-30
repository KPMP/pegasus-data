package org.kpmp.umap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "umap_point_v")
@IdClass(UmapPointId.class)
public class UmapPoint {

	@Id
	@Column(name = "umap_x")
	private double umapX;
	@Id
	@Column(name = "umap_y")
	private double umapY;
	@Column(name = "cluster_name")
	private String clusterName;
	@Column(name = "cluster_color")
	private String clusterColor;
	@Column(name = "data_type")
	private String dataType;
	@Column(name = "barcode")
	private String barcode;
	@Transient
	private double expressionValue;

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

	public String getClusterName() {
		return clusterName;
	}

	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}

	public String getClusterColor() {
		return clusterColor;
	}

	public void setClusterColor(String clusterColor) {
		this.clusterColor = clusterColor;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public double getExpressionValue() {
		return expressionValue;
	}

	public void setExpressionValue(double expressionValue) {
		this.expressionValue = expressionValue;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

}
