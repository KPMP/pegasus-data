package org.kpmp.geneExpression;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "rt_gti")
public class RTExpressionDataGTI implements RTExpressionData, Serializable {

	private static final long serialVersionUID = 4789000526795024990L;
	@Id
	@Column(name = "id")
	private Integer id;
	@Column(name = "gene_symbol")
	private String geneSymbol;
	@Column(name = "segment")
	private String segment;
	@Column(name = "segment_name")
	private String segmentName;
	@Column(name = "fold_change")
	private Double foldChange;
	@Column(name = "p_val")
	private Double pVal;
	@Column(name = "std_dev")
	private Double stdDev;
	@Column(name = "p_val_log10")
	private Double pValLog10;
	@Column(name = "enrollment_category")
	private String enrollmentCategory;
	@Column(name = "adj_p_val")
	private Double adjPVal;
	@Column(name = "sample_count")
	private Integer sampleCount;

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String getGeneSymbol() {
		return geneSymbol;
	}

	@Override
	public void setGeneSymbol(String geneSymbol) {
		this.geneSymbol = geneSymbol;
	}

	@Override
	public String getSegment() {
		return segment;
	}

	@Override
	public void setSegment(String segment) {
		this.segment = segment;
	}

	@Override
	public Double getFoldChange() {
		return foldChange;
	}

	@Override
	public void setFoldChange(Double foldChange) {
		this.foldChange = foldChange;
	}

	@Override
	public Double getPVal() {
		return pVal;
	}

	@Override
	public void setPVal(Double pVal) {
		this.pVal = pVal;
	}

	@Override
	public Double getStdDev() {
		return stdDev;
	}

	@Override
	public void setStdDev(Double stdDev) {
		this.stdDev = stdDev;
	}

	@Override
	public Double getPValLog10() {
		return pValLog10;
	}

	@Override
	public void setPValLog10(Double pValLog10) {
		this.pValLog10 = pValLog10;
	}

	@Override
	public String getEnrollmentCategory() {
		return enrollmentCategory;
	}

	@Override
	public void setEnrollmentCategory(String enrollmentCategory) {
		this.enrollmentCategory = enrollmentCategory;
	}

	@Override
	public Integer getSampleCount() {
		return sampleCount;
	}

	@Override
	public void setSampleCount(Integer sampleCount) {
		this.sampleCount = sampleCount;
	}

	@Override
	public String getSegmentName() {
		return segmentName;
	}

	@Override
	public void setSegmentName(String segmentName) {
		this.segmentName = segmentName;
	}

	@Override
	public Double getAdjPVal() {
		return adjPVal;
	}

	@Override
	public void setAdjPVal(Double adjPVal) {
		this.adjPVal = adjPVal;
	}
}
