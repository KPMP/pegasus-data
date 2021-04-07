package org.kpmp.umap;

import java.util.List;

public class PlotData {

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
