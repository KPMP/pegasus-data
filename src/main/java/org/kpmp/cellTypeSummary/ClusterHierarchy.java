package org.kpmp.cellTypeSummary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "cluster_hierarchy_v")
@IdClass(ClusterHierarchyId.class)
public class ClusterHierarchy {

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
	@Column(name = "cell_type")
	private String cellType;

	public int getCellTypeId() {
		return cellTypeId;
	}

	public void setCellTypeId(int cellTypeId) {
		this.cellTypeId = cellTypeId;
	}

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

	public String getStructureSubregion() {
		return structureSubregion;
	}

	public void setStructureSubregion(String structureSubregion) {
		this.structureSubregion = structureSubregion;
	}

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

}
