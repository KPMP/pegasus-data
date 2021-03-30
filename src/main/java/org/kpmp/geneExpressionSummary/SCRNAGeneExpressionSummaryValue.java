package org.kpmp.geneExpressionSummary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.kpmp.DataTypeEnum;
import org.springframework.lang.Nullable;

@Entity
@Table(name = "sc_rnaseq")
public class SCRNAGeneExpressionSummaryValue implements GeneSummaryPerCluster {

	@Column(name = "id")
	private Integer id;
	@Column(name = "tissue_type")
	private String tissueType;
	@Column(name = "gene")
	private String gene;
	@Column(name = "p_val")
	private Double pVal;
	@Column(name = "p_val_adj")
	private Double pValAdj;
	@Column(name = "fold_change")
	private Double foldChange;
	@Column(name = "pct_1")
	private Double pct1;
	@Column(name = "pct_2")
	private Double pct2;
	@Column(name = "specificity")
	private Double specificity;
	@Column(name = "avg_exp")
	private Double avgExpression;
	@Id
	@Column(name = "cluster")
	private String cluster;

	@Column(name = "cluster_name")
	private String clusterName;

	@Column(name = "cell_count")
	private Integer cellCount;

	@Override
	@Transient
	public String getDataType() {
		return DataTypeEnum.SINGLE_CELL.getAbbreviation();
	}

	@Override
	public void setCellCount(Integer cellCount) {
		this.cellCount = cellCount;
	}

	@Override
	public Integer getCellCount() {
		return this.cellCount;
	}

	@Override
	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}

	@Nullable
	@Override
	public String getClusterName() {
		return this.clusterName;
	}

	@Override
	@Nullable
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	@Nullable
	public String getTissueType() {
		return tissueType;
	}

	@Override
	public void setTissueType(String tissueType) {
		this.tissueType = tissueType;
	}

	@Override
	@Nullable
	public String getGene() {
		return gene;
	}

	@Override
	public void setGene(String gene) {
		this.gene = gene;
	}

	@Override
	@Nullable
	public Double getPVal() {
		return pVal;
	}

	@Override
	public void setPVal(Double pVal) {
		this.pVal = pVal;
	}

	@Override
	@Nullable
	public Double getPValAdj() {
		return pValAdj;
	}

	@Override
	public void setPValAdj(Double pValAdj) {
		this.pValAdj = pValAdj;
	}

	@Override
	@Nullable
	public Double getFoldChange() {
		return foldChange;
	}

	@Override
	public void setFoldChange(Double foldChange) {
		this.foldChange = foldChange;
	}

	@Override
	@Nullable
	public Double getPct1() {
		return pct1;
	}

	@Override
	public void setPct1(Double pct1) {
		this.pct1 = pct1;
	}

	@Override
	@Nullable
	public Double getPct2() {
		return pct2;
	}

	@Override
	public void setPct2(Double pct2) {
		this.pct2 = pct2;
	}

	@Nullable
	public Double getSpecificity() {
		return specificity;
	}

	public void setSpecificity(Double specificity) {
		this.specificity = specificity;
	}

	@Override
	@Nullable
	public Double getAvgExpression() {
		return avgExpression;
	}

	@Override
	public void setAvgExpression(Double avgExpression) {
		this.avgExpression = avgExpression;
	}

	@Override
	@Nullable
	public String getCluster() {
		return cluster;
	}

	@Override
	public void setCluster(String cluster) {
		this.cluster = cluster;
	}

}
