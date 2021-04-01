package org.kpmp.umap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "sn_umap_point_v")
public class SNMetadata implements UmapPoint {

	@Column(name = "umap_x")
	private double umapX;
	@Column(name = "umap_y")
	private double umapY;
	@Column(name = "cluster_name")
	private String clusterName;
	@Column(name = "cluster_color")
	private String clusterColor;
	@Id
	@Column(name = "barcode")
	private String barcode;
	@Column(name = "tissue_type")
	private String tissueType;
	@Transient
	private double expressionValue;

	@Override
	public double getUmapX() {
		return umapX;
	}

	@Override
	public void setUmapX(double umapX) {
		this.umapX = umapX;
	}

	@Override
	public double getUmapY() {
		return umapY;
	}

	@Override
	public void setUmapY(double umapY) {
		this.umapY = umapY;
	}

	@Override
	public String getClusterName() {
		return clusterName;
	}

	@Override
	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}

	@Override
	public String getClusterColor() {
		return clusterColor;
	}

	@Override
	public void setClusterColor(String clusterColor) {
		this.clusterColor = clusterColor;
	}

	@Override
	public double getExpressionValue() {
		return this.expressionValue;
	}

	@Override
	public void setExpressionValue(double expressionValue) {
		this.expressionValue = expressionValue;
	}

	@Override
	public String getBarcode() {
		return this.barcode;
	}

	@Override
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getTissueType() {
		return tissueType;
	}

	public void setTissueType(String tissueType) {
		this.tissueType = tissueType;
	}

}
