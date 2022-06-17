package org.kpmp;

public enum DataTypeEnum {

	SINGLE_CELL("sc"),
	SINGLE_NUCLEUS("sn"),
	REGIONAL_TRANSCRIPTOMICS("rt"),
	SPATIAL_TRANSCRIPTOMICS("st"),
	TISSUE_IMAGING_AND_CYTOMETRY_3D("3d"),
	LIGHT_MICROSCOPIC_WHOLE_SLIDE_IMAGES("wsi"),
	UNKNOWN("");

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
		} else if (SPATIAL_TRANSCRIPTOMICS.abbreviation.equals(abbreviation)) {
			return SPATIAL_TRANSCRIPTOMICS;
		} else if (TISSUE_IMAGING_AND_CYTOMETRY_3D.abbreviation.equals(abbreviation)) {
			return TISSUE_IMAGING_AND_CYTOMETRY_3D;
		} else if (LIGHT_MICROSCOPIC_WHOLE_SLIDE_IMAGES.abbreviation.equals(abbreviation)) {
			return LIGHT_MICROSCOPIC_WHOLE_SLIDE_IMAGES;
		}
		return UNKNOWN;
	}
}
