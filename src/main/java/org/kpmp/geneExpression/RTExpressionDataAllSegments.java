package org.kpmp.geneExpression;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rt_segments")
public class RTExpressionDataAllSegments implements RTExpressionData {

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
	@Column(name = "tissue_type")
	private String tissueType;
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
	public String getTissueType() {
		return tissueType;
	}

	@Override
	public void setTissueType(String tissueType) {
		this.tissueType = tissueType;
	}

	@Override
	public Integer getSampleCount() {
		return sampleCount;
	}

	@Override
	public void setSampleCount(Integer sampleCount) {
		this.sampleCount = sampleCount;
	}

	public String getSegmentName() {
		return segmentName;
	}

	public void setSegmentName(String segmentName) {
		this.segmentName = segmentName;
	}
}
