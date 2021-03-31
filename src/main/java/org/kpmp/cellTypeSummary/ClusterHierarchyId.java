package org.kpmp.cellTypeSummary;

import java.io.Serializable;

public class ClusterHierarchyId implements Serializable {

	private static final long serialVersionUID = -1586116837812306236L;

	private int cellTypeId;

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

	private int clusterId;

}
