package org.kpmp;

public enum FullDataTypeEnum {

	SINGLE_CELL_FULL("Single-cell RNA-seq (scRNA-seq)"), SINGLE_NUCLEUS_FULL("Single-nucleus RNA-seq (snRNA-seq)"), UNKNOWN_FULL("");

	private String dataEnum;

	FullDataTypeEnum(String dataEnum) {
		this.dataEnum = dataEnum;
	}

	public String getFull() {
		return this.dataEnum;
	}

	public static FullDataTypeEnum fromLong(String dataEnum) {
		if (SINGLE_CELL_FULL.dataEnum.equals(dataEnum)) {
			return SINGLE_CELL_FULL;
		} else if (SINGLE_NUCLEUS_FULL.dataEnum.equals(dataEnum)) {
			return SINGLE_NUCLEUS_FULL;
		}
		return UNKNOWN_FULL;
	}
}
