package org.kpmp.participant;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ParticipantDataTypeSummary2025 {
	private List<ParticipantDataTypeInformation2025> spatialViewerDataTypes2025;
	private List<ParticipantDataTypeInformation2025> explorerDataTypes2025;

	public List<ParticipantDataTypeInformation2025> getSpatialViewerDataTypes() {
		return spatialViewerDataTypes2025;
	}

	public void setSpatialViewerDataTypes(List<ParticipantDataTypeInformation2025> spatialViewerDataTypes2025) {
		this.spatialViewerDataTypes2025 = spatialViewerDataTypes2025;
	}

	public List<ParticipantDataTypeInformation2025> getExplorerDataTypes() {
		return explorerDataTypes2025;
	}

	public void setExplorerDataTypes(List<ParticipantDataTypeInformation2025> explorerDataTypes2025) {
		this.explorerDataTypes2025 = explorerDataTypes2025;
	}
}
