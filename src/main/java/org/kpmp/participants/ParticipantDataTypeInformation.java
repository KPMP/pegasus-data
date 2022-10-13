package org.kpmp.participants;

import org.springframework.stereotype.Component;

@Component
public class ParticipantDataTypeInformation {

	private String dataType;
	private Long count;
	private Boolean isAggregatedData;

	public ParticipantDataTypeInformation() {
		// default constructor so spring does not complain
	}

	public ParticipantDataTypeInformation(String dataType, Long count, boolean isAggregatedData) {
		this.dataType = dataType;
		this.count = count;
		this.isAggregatedData = isAggregatedData;

	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public boolean isAggregatedData() {
		return isAggregatedData;
	}

	public void setAggregatedData(boolean isAggregatedData) {
		this.isAggregatedData = isAggregatedData;
	}

}
