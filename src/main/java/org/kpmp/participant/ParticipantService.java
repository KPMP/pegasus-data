package org.kpmp.participant; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParticipantService {
	private ParticipantSummaryDatasetRepository participantSummaryDatasetRepository;

	@Autowired
	public ParticipantService(
		ParticipantSummaryDatasetRepository participantSummaryDatasetRepository ) {
		this.participantSummaryDatasetRepository = participantSummaryDatasetRepository;
	}

	public ParticipantSummaryDataset getParticipantSummaryDataset(String redcapId) {
		return participantSummaryDatasetRepository.findByRedcapId(redcapId);
	}
}