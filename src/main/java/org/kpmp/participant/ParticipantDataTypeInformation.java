package org.kpmp.participant;

import org.springframework.stereotype.Component;

@Component
public class ParticipantDataTypeInformation {

	private String dataType;
	private Integer count;
	private Boolean isAggregatedData;

	public ParticipantDataTypeInformation() {
		// default constructor so spring does not complain
	}

	public ParticipantDataTypeInformation(String dataType, Integer count, boolean isAggregatedData) {
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

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public boolean isAggregatedData() {
		return isAggregatedData;
	}

	public void setAggregatedData(boolean isAggregatedData) {
		this.isAggregatedData = isAggregatedData;
	}

}
