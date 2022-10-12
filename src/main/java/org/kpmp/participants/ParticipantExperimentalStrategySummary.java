package org.kpmp.participants;

import java.util.List;

class ParticipantExperimentalStrategySummary {

	private List<ParticipantExperimentalStragegyInformation> spatialViewerExperiments;
	private List<ParticipantExperimentalStragegyInformation> explorerExperiments;

	public List<ParticipantExperimentalStragegyInformation> getSpatialViewerExperiments() {
		return spatialViewerExperiments;
	}

	public void setSpatialViewerExperiments(List<ParticipantExperimentalStragegyInformation> spatialViewerExperiments) {
		this.spatialViewerExperiments = spatialViewerExperiments;
	}

	public List<ParticipantExperimentalStragegyInformation> getExplorerExperiments() {
		return explorerExperiments;
	}

	public void setExplorerExperiments(List<ParticipantExperimentalStragegyInformation> explorerExperiments) {
		this.explorerExperiments = explorerExperiments;
	}

}
