package org.kpmp.geneExpressionSummary.singleCell;

import java.beans.Transient;
import java.io.Serializable;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.kpmp.FullDataTypeEnum;
import org.kpmp.geneExpressionSummary.GeneExpressionId;
import org.kpmp.geneExpressionSummary.GeneExpressionSummary;

@Entity
@Table(name = "sc_rnaseq_2025")
@IdClass(GeneExpressionId.class)
public class SCRNAGeneExpressionExpressionSummaryValue2025 implements GeneExpressionSummary, Serializable {

	private static final long serialVersionUID = 7265280085624504185L;
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

	@Column(name = "cluster_name")
	private String clusterName;

	@Column(name = "cell_count")
	private Integer cellCount;

	@Override
	@Transient
	public String getDataType() {
		return FullDataTypeEnum.SINGLE_CELL.getAbbreviation();
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
	public void setAvgExp(Double avgExp) {
		this.avgExp = avgExp;
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

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(gene).append(cluster).build();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof SCRNAGeneExpressionExpressionSummaryValue2025))
			return false;
		SCRNAGeneExpressionExpressionSummaryValue2025 that = (SCRNAGeneExpressionExpressionSummaryValue2025) obj;
		return new EqualsBuilder().append(this.gene, that.gene).append(this.cluster, that.cluster).build();
	}

}
