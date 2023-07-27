package org.kpmp.participant;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ParticipantRepoDataTypeSummary {
	private List<ParticipantRepoDataTypeInformation> repositoryDataTypes;

	public List<ParticipantRepoDataTypeInformation> getRepositoryDataTypes() {
		return repositoryDataTypes;
	}

	public void setRepositoryDataTypes(List<ParticipantRepoDataTypeInformation> repositoryDataTypes) {
		this.repositoryDataTypes = repositoryDataTypes;
	}
}
