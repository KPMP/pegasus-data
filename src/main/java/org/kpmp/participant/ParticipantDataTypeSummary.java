package org.kpmp.participant;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ParticipantDataTypeSummary {
	private List<ParticipantDataTypeInformation> spatialViewerDataTypes;
	private List<ParticipantDataTypeInformation> explorerDataTypes;
	private List<ParticipantDataTypeInformation> repositoryDataTypes;
	public List<ParticipantDataTypeInformation> getSpatialViewerDataTypes() {
		return spatialViewerDataTypes;
	}

	public void setSpatialViewerDataTypes(List<ParticipantDataTypeInformation> spatialViewerDataTypes) {
		this.spatialViewerDataTypes = spatialViewerDataTypes;
	}

	public List<ParticipantDataTypeInformation> getExplorerDataTypes() {
		return explorerDataTypes;
	}

	public void setExplorerDataTypes(List<ParticipantDataTypeInformation> explorerDataTypes) {
		this.explorerDataTypes = explorerDataTypes;
	}

	public List<ParticipantDataTypeInformation> getRepositoryDataTypes() {
		return repositoryDataTypes;
	}

	public void setRepositoryDataTypes(List<ParticipantDataTypeInformation> repositoryDataTypes) {
		this.repositoryDataTypes = repositoryDataTypes;
	}
}
