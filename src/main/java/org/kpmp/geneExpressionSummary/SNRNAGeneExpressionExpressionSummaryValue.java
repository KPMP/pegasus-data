package org.kpmp.geneExpressionSummary;

import org.kpmp.DataTypeEnum;
import org.springframework.lang.Nullable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;

@Entity
@Table(name = "sn_rnaseq")
@IdClass(GeneExpressionId.class)
public class SNRNAGeneExpressionExpressionSummaryValue implements GeneExpressionSummary, Serializable {

	@Column(name = "id")
	private Integer id;

	@Column(name = "tissue_type")
	private String tissueType;

	@Id
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

	@Column(name = "avg_exp")
	private Double avgExp;

	@Id
	@Column(name = "cluster")
	private String cluster;

	@Column(name = "cell_type")
	private String cellType;

	@Override
	@Transient
	public String getDataType() {
		return DataTypeEnum.SINGLE_NUCLEUS.getAbbreviation();
	}

	@Column(name = "cluster_name")
	private String clusterName;

	@Column(name = "cell_count")
	private Integer cellCount;

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

	@Nullable
	public Double getPVal() {
		return pVal;
	}

	public void setPVal(Double pVal) {
		this.pVal = pVal;
	}

	@Nullable
	public Double getPValAdj() {
		return pValAdj;
	}

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

	@Override
	@Nullable
	public Double getAvgExp() {
		return avgExp;
	}

	@Override
	@Nullable
	public void setAvgExp(Double avgExp) {
		this.avgExp = avgExp;
	}

	@Id
	@Override
	public String getCluster() {
		return cluster;
	}

	@Override
	public void setCluster(String cluster) {
		this.cluster = cluster;
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
	public int hashCode() {
		return (gene + cluster).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof SNRNAGeneExpressionExpressionSummaryValue))
			return false;
		SNRNAGeneExpressionExpressionSummaryValue snrnaGeneExpressionExpressionSummaryValue = (SNRNAGeneExpressionExpressionSummaryValue) obj;
		return (snrnaGeneExpressionExpressionSummaryValue.getGene().equals(this.getGene()) && snrnaGeneExpressionExpressionSummaryValue.getCluster().equals(this.getCluster()));
	}
}
