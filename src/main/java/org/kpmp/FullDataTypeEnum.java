package org.kpmp;

public enum FullDataTypeEnum {

	SINGLE_CELL("Single-cell RNA-seq (scRNA-seq)", "sc"),
	SINGLE_NUCLEUS("Single-nucleus RNA-seq (snRNA-seq)", "sn"),
	REGIONAL_TRANSCRIPTOMICS("Regional transcriptomics", "rt"),

	REGIONAL_PROTEOMICS("Regional proteomics", "rp"),
	SPATIAL_TRANSCRIPTOMICS("Spatial Transcriptomics", "st"),
	TISSUE_IMAGING_AND_CYTOMETRY_3D("3D Tissue Imaging and Cytometry", "3d"),
	LIGHT_MICROSCOPIC_WHOLE_SLIDE_IMAGES("Light Microscopic Whole Slide Images", "wsi"),
	CODEX("CODEX", "codex"),
	SPATIAL_LIPIDOMICS("Spatial Lipidomics", "sl"),
	SPATIAL_METABOLOMICS("Spatial Metabolomics", "sm"),
	SPATIAL_NGLYCOMICS("Spatial N-glycomics", "sng"),
    IMAGING_MASS_CYTOMETRY("Imaging Mass Cytometry", "imc"),
	UNKNOWN("", "");

	private final String fullName;
	private final String abbreviation;

	FullDataTypeEnum(String fullName, String abbreviation) {
		this.fullName = fullName;
		this.abbreviation = abbreviation;
	}

	public String getFullName() {
		return this.fullName;
	}

	public String getAbbreviation() {
		return this.abbreviation;
	}

	public static FullDataTypeEnum fromLong(String dataEnum) {
		if (SINGLE_CELL.fullName.equals(dataEnum)) {
			return SINGLE_CELL;
		} else if (SINGLE_NUCLEUS.fullName.equals(dataEnum)) {
			return SINGLE_NUCLEUS;
		} else if (REGIONAL_TRANSCRIPTOMICS.fullName.equals(dataEnum)) {
			return REGIONAL_TRANSCRIPTOMICS;
		} else if (REGIONAL_PROTEOMICS.fullName.equals(dataEnum)) {
			return REGIONAL_PROTEOMICS;
		} else if (SPATIAL_TRANSCRIPTOMICS.fullName.equals(dataEnum)) {
			return SPATIAL_TRANSCRIPTOMICS;
		} else if (TISSUE_IMAGING_AND_CYTOMETRY_3D.fullName.equals(dataEnum)) {
			return TISSUE_IMAGING_AND_CYTOMETRY_3D;
		} else if (LIGHT_MICROSCOPIC_WHOLE_SLIDE_IMAGES.fullName.equals(dataEnum)) {
			return LIGHT_MICROSCOPIC_WHOLE_SLIDE_IMAGES;
		} else if (CODEX.fullName.equals(dataEnum)) {
			return CODEX;
		} else if (SPATIAL_LIPIDOMICS.fullName.equals(dataEnum)) {
			return SPATIAL_LIPIDOMICS;
		} else if (SPATIAL_METABOLOMICS.fullName.equals(dataEnum)) {
			return SPATIAL_METABOLOMICS;
		} else if (SPATIAL_NGLYCOMICS.fullName.equals(dataEnum)) {
			return SPATIAL_NGLYCOMICS;
		}else if (IMAGING_MASS_CYTOMETRY.fullName.equals(dataEnum)){
            return IMAGING_MASS_CYTOMETRY;
        }
		return UNKNOWN;
	}

	public static FullDataTypeEnum fromAbbreviation(String abbreviation) {
		if (SINGLE_CELL.abbreviation.equals(abbreviation)) {
			return SINGLE_CELL;
		} else if (SINGLE_NUCLEUS.abbreviation.equals(abbreviation)) {
			return SINGLE_NUCLEUS;
		} else if (REGIONAL_TRANSCRIPTOMICS.abbreviation.equals(abbreviation)) {
			return REGIONAL_TRANSCRIPTOMICS;
		} else if (REGIONAL_PROTEOMICS.abbreviation.equals(abbreviation)) {
			return REGIONAL_PROTEOMICS;
		} else if (SPATIAL_TRANSCRIPTOMICS.abbreviation.equals(abbreviation)) {
			return SPATIAL_TRANSCRIPTOMICS;
		} else if (TISSUE_IMAGING_AND_CYTOMETRY_3D.abbreviation.equals(abbreviation)) {
			return TISSUE_IMAGING_AND_CYTOMETRY_3D;
		} else if (LIGHT_MICROSCOPIC_WHOLE_SLIDE_IMAGES.abbreviation.equals(abbreviation)) {
			return LIGHT_MICROSCOPIC_WHOLE_SLIDE_IMAGES;
		} else if (CODEX.abbreviation.equals(abbreviation)) {
			return CODEX;
		} else if (SPATIAL_LIPIDOMICS.abbreviation.equals(abbreviation)) {
			return SPATIAL_LIPIDOMICS;
		} else if (SPATIAL_METABOLOMICS.abbreviation.equals(abbreviation)) {
			return SPATIAL_METABOLOMICS;
		} else if (SPATIAL_NGLYCOMICS.abbreviation.equals(abbreviation)) {
			return SPATIAL_NGLYCOMICS;
		}else if (IMAGING_MASS_CYTOMETRY.abbreviation.equals(abbreviation)){
			return IMAGING_MASS_CYTOMETRY;
		}
		return UNKNOWN;
	}
}
