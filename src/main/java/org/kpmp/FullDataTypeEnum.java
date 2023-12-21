package org.kpmp;

public enum FullDataTypeEnum {

	SINGLE_CELL_FULL("Single-cell RNA-seq (scRNA-seq)"),
	SINGLE_NUCLEUS_FULL("Single-nucleus RNA-seq (snRNA-seq)"),
	REGIONAL_TRANSCRIPTOMICS_FULL("Regional transcriptomics"),

	REGIONAL_PROTEOMICS_FULL("Regional proteomics"),
	SPATIAL_TRANSCRIPTOMICS_FULL("Spatial Transcriptomics"),
	TISSUE_IMAGING_AND_CYTOMETRY_3D_FULL("3D Tissue Imaging and Cytometry"),
	LIGHT_MICROSCOPIC_WHOLE_SLIDE_IMAGES_FULL("Light Microscopic Whole Slide Images"),
	CODEX_FULL("CODEX"),
	SPATIAL_LIPIDOMICS_FULL("Spatial Lipidomics"),
	SPATIAL_METABOLOMICS_FULL("Spatial Metabolomics"),
	SPATIAL_NGLYCOMICS_FULL("Spatial N-glycomics"),
	UNKNOWN_FULL("");

	private String dataEnum;

	FullDataTypeEnum(String dataEnum) {
		this.dataEnum = dataEnum;
	}

	public String getFullName() {
		return this.dataEnum;
	}

	public static FullDataTypeEnum fromLong(String dataEnum) {
		if (SINGLE_CELL_FULL.dataEnum.equals(dataEnum)) {
			return SINGLE_CELL_FULL;
		} else if (SINGLE_NUCLEUS_FULL.dataEnum.equals(dataEnum)) {
			return SINGLE_NUCLEUS_FULL;
		} else if (REGIONAL_TRANSCRIPTOMICS_FULL.dataEnum.equals(dataEnum)) {
			return REGIONAL_TRANSCRIPTOMICS_FULL;
		} else if (REGIONAL_PROTEOMICS_FULL.dataEnum.equals(dataEnum)) {
			return REGIONAL_PROTEOMICS_FULL;
		} else if (SPATIAL_TRANSCRIPTOMICS_FULL.dataEnum.equals(dataEnum)) {
			return SPATIAL_TRANSCRIPTOMICS_FULL;
		} else if (TISSUE_IMAGING_AND_CYTOMETRY_3D_FULL.dataEnum.equals(dataEnum)) {
			return TISSUE_IMAGING_AND_CYTOMETRY_3D_FULL;
		} else if (LIGHT_MICROSCOPIC_WHOLE_SLIDE_IMAGES_FULL.dataEnum.equals(dataEnum)) {
			return LIGHT_MICROSCOPIC_WHOLE_SLIDE_IMAGES_FULL;
		} else if (CODEX_FULL.dataEnum.equals(dataEnum)) {
			return CODEX_FULL;
		} else if (SPATIAL_LIPIDOMICS_FULL.dataEnum.equals(dataEnum)) {
			return SPATIAL_LIPIDOMICS_FULL;
		} else if (SPATIAL_METABOLOMICS_FULL.dataEnum.equals(dataEnum)) {
			return SPATIAL_METABOLOMICS_FULL;
		} else if (SPATIAL_NGLYCOMICS_FULL.dataEnum.equals(dataEnum)) {
			return SPATIAL_NGLYCOMICS_FULL;
		}
		return UNKNOWN_FULL;
	}
}
