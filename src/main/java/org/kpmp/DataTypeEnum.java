package org.kpmp;

public enum DataTypeEnum {

	SINGLE_CELL("sc"), SINGLE_NUCLEUS("sn"), REGIONAL_TRANSCRIPTOMICS("rt"), UNKNOWN("");

	private String abbreviation;

	DataTypeEnum(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getAbbreviation() {
		return this.abbreviation;
	}

	public static DataTypeEnum fromAbbreviation(String abbreviation) {
		if (SINGLE_CELL.abbreviation.equals(abbreviation)) {
			return SINGLE_CELL;
		} else if (SINGLE_NUCLEUS.abbreviation.equals(abbreviation)) {
			return SINGLE_NUCLEUS;
		} else if (REGIONAL_TRANSCRIPTOMICS.abbreviation.equals(abbreviation)) {
			return REGIONAL_TRANSCRIPTOMICS;
		}
		return UNKNOWN;
	}
}
