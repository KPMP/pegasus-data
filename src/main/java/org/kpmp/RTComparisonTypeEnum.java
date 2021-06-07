package org.kpmp;

public enum RTComparisonTypeEnum {

	ALL_SEGMENTS("all_segments"), GLOM_V_TI("glom_tub"), UNKNOWN("");

	private String abbreviation;

	RTComparisonTypeEnum(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getAbbreviation() {
		return this.abbreviation;
	}

	public static RTComparisonTypeEnum fromAbbreviation(String abbreviation) {
		if (ALL_SEGMENTS.abbreviation.equals(abbreviation)) {
			return ALL_SEGMENTS;
		} else if (GLOM_V_TI.abbreviation.equals(abbreviation)) {
			return GLOM_V_TI;
		}
		return UNKNOWN;
	}
}
