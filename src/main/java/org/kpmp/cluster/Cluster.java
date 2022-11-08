package org.kpmp.cluster;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cluster")
public class Cluster {

	@Id
	private int clusterId;
	private String clusterName;
	private String abbreviation;
	private String clusterColor;

	public int getClusterId() {
		return clusterId;
	}

	public void setClusterId(int clusterId) {
		this.clusterId = clusterId;
	}

	public String getClusterName() {
		return clusterName;
	}

	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getClusterColor() {
		return clusterColor;
	}

	public void setClusterColor(String clusterColor) {
		this.clusterColor = clusterColor;
	}
}
