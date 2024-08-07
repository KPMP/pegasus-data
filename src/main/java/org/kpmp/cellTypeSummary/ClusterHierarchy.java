package org.kpmp.cellTypeSummary;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

import org.springframework.lang.Nullable;

@Entity
@Table(name = "cluster_hierarchy_v")
@IdClass(ClusterHierarchyId.class)
public class ClusterHierarchy implements Serializable {

	private static final long serialVersionUID = -7707637379989017634L;
	@Id
	@Column(name = "cell_type_id")
	private int cellTypeId;
	@Id
	@Column(name = "cluster_id")
	private int clusterId;
	@Column(name = "structure_region")
	private String structureRegion;
	@Column(name = "structure_subregion")
	private String structureSubregion;
	@Column(name = "cluster_name")
	private String clusterName;
	@Column(name = "is_single_cell")
	private String isSingleCellCluster;
	@Column(name = "is_single_nuc")
	private String isSingleNucCluster;
	@Column(name = "is_rt")
	private String isRegionalTranscriptomics;

	@Column(name = "is_rp")
	private String isRegionalProteomics;
	@Column(name = "cell_type")
	private String cellType;
	@Column(name = "cell_type_order")
	private Double cellTypeOrder;

	public int getCellTypeId() {
		return cellTypeId;
	}

	public void setCellTypeId(int cellTypeId) {
		this.cellTypeId = cellTypeId;
	}

	@Nullable
	public int getClusterId() {
		return clusterId;
	}

	public void setClusterId(int clusterId) {
		this.clusterId = clusterId;
	}

	public String getStructureRegion() {
		return structureRegion;
	}

	public void setStructureRegion(String structureRegion) {
		this.structureRegion = structureRegion;
	}

	@Nullable
	public String getStructureSubregion() {
		return structureSubregion;
	}

	public void setStructureSubregion(String structureSubregion) {
		this.structureSubregion = structureSubregion;
	}

	@Nullable
	public String getClusterName() {
		return clusterName;
	}

	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}

	public String getIsSingleCellCluster() {
		return isSingleCellCluster;
	}

	public void setIsSingleCellCluster(String isSingleCellCluster) {
		this.isSingleCellCluster = isSingleCellCluster;
	}

	public String getIsSingleNucCluster() {
		return isSingleNucCluster;
	}

	public void setIsSingleNucCluster(String isSingleNucCluster) {
		this.isSingleNucCluster = isSingleNucCluster;
	}

	public String getIsRegionalTranscriptomics() {
		return isRegionalTranscriptomics;
	}

	public void setIsRegionalTranscriptomics(String isRegionalTranscriptomics) {
		this.isRegionalTranscriptomics = isRegionalTranscriptomics;
	}

	public String getIsRegionalProteomics() {
		return isRegionalProteomics;
	}

	public void setIsRegionalProteomics(String isRegionalProteomics) {
		this.isRegionalProteomics = isRegionalProteomics;
	}
	@Nullable
	public Double getCellTypeOrder() {
		return cellTypeOrder;
	}

	public void setCellTypeOrder(Double cellTypeOrder) {
		this.cellTypeOrder = cellTypeOrder;
	}

	@Nullable
	public String getCellType() {
		return cellType;
	}

	public void setCellType(String cellType) {
		this.cellType = cellType;
	}
}
