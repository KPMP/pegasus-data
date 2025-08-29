package org.kpmp.umap;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "sc_umap_point_2025_v")
public class SCMetadataNewData implements UmapPoint, Serializable {

	private static final long serialVersionUID = 7947867645080936879L;
	@Column(name = "umap_x")
	private double umapX;
	@Column(name = "umap_y")
	private double umapY;
	@Column(name = "cluster_abbreviation")
	private String clusterAbbreviation;
	@Column(name = "cluster_name")
	private String clusterName;
	@Column(name = "cluster_color")
	private String clusterColor;
	@Id
	@Column(name = "barcode")
	private String barcode;
	@Column(name = "enrollment_category")
	private String enrollmentCategory;
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
	public String getClusterAbbreviation() {
		return clusterAbbreviation;
	}

	@Override
	public void setClusterAbbreviation(String clusterAbbreviation) {
		this.clusterAbbreviation = clusterAbbreviation;
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

	public String getEnrollmentCategory() {
		return enrollmentCategory;
	}

	public void setEnrollmentCategory(String enrollmentCategory) {
		this.enrollmentCategory = enrollmentCategory;
	}

}
