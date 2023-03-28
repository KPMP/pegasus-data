package org.kpmp.dataSummary;

import java.io.Serializable;

public class ExperimentalStrategyId implements Serializable {

	private static final long serialVersionUID = 938153099192129678L;

	private String experimentalStrategy;
	private String access;

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

}
