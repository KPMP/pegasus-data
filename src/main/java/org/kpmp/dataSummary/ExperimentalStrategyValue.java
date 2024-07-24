package org.kpmp.dataSummary;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name = "repository_summary_v")
@IdClass(ExperimentalStrategyId.class)
public class ExperimentalStrategyValue implements Serializable {

	private static final long serialVersionUID = 8063582327279878125L;
	private int count;
	@Id
	private String experimentalStrategy;
	@Id
	private String access;
	private String dataType;
	private String dataCategory;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getExperimentalStrategy() {
		return experimentalStrategy;
	}

	public void setExperimentalStrategy(String experimentalStrategy) {
		this.experimentalStrategy = experimentalStrategy;
	}

	public String getAccess() {
		return access;
	}

	public void setAccess(String access) {
		this.access = access;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getDataCategory() {
		return dataCategory;
	}

	public void setDataCategory(String dataCategory) {
		this.dataCategory = dataCategory;
	}

}
