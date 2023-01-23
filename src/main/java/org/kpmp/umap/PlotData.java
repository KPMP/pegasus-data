package org.kpmp.umap;

import java.io.Serializable;
import java.util.List;

public class PlotData implements Serializable {

	private static final long serialVersionUID = 7957121452487372467L;
	private List<ReferenceCluster> referenceData;
	private List<FeatureData> featureData;

	public PlotData(List<ReferenceCluster> referenceData, List<FeatureData> featureData) {
		this.referenceData = referenceData;
		this.featureData = featureData;
	}

	public List<ReferenceCluster> getReferenceData() {
		return referenceData;
	}

	public void setReferenceData(List<ReferenceCluster> referenceData) {
		this.referenceData = referenceData;
	}

	public List<FeatureData> getFeatureData() {
		return featureData;
	}

	public void setFeatureData(List<FeatureData> featureData) {
		this.featureData = featureData;
	}

}
