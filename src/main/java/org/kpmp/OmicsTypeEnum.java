package org.kpmp;

public enum OmicsTypeEnum {

	TRANSCRIPTOMICS("TRANSCRIPTOMICS"), NONE("");

	private String dataEmum;

	OmicsTypeEnum(String dataEmum) {
		this.dataEmum = dataEmum;
	}

	public String getEnum() {
		return this.dataEmum;
	}

	public static OmicsTypeEnum fromEnum(String dataEmum) {
		if (TRANSCRIPTOMICS.dataEmum.equals(dataEmum)) {
			return TRANSCRIPTOMICS;
		} else if (NONE.dataEmum.equals(dataEmum)) {
			return NONE;
		}
		return NONE;
	}
}
