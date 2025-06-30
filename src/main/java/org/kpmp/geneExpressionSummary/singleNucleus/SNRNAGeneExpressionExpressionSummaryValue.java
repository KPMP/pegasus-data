package org.kpmp.geneExpressionSummary.singleNucleus;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import org.kpmp.FullDataTypeEnum;
import org.kpmp.geneExpressionSummary.GeneExpressionId;
import org.kpmp.geneExpressionSummary.GeneExpressionSummary;
import org.springframework.lang.Nullable;

@Entity
@Table(name = "sn_rnaseq")
@IdClass(GeneExpressionId.class)
public class SNRNAGeneExpressionExpressionSummaryValue implements GeneExpressionSummary, Serializable {

	private static final long serialVersionUID = -443713903175981503L;

	@Column(name = "id")
	private Integer id;

	@Column(name = "enrollment_category")
	private String enrollmentCategory;

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
		return FullDataTypeEnum.SINGLE_NUCLEUS.getAbbreviation();
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
	public String getEnrollmentCategory() {
		return enrollmentCategory;
	}

	@Override
	public void setEnrollmentCategory(String enrollmentCategory) {
		this.enrollmentCategory = enrollmentCategory;
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
		return (snrnaGeneExpressionExpressionSummaryValue.getGene().equals(this.getGene())
				&& snrnaGeneExpressionExpressionSummaryValue.getCluster().equals(this.getCluster()));
	}
}
