package org.kpmp.datasetSummary;

import java.io.Serializable;

public class DatasetSummaryId implements Serializable {

	private static final long serialVersionUID = -1481683814741343901L;
	private Integer file_id;

	public DatasetSummaryId() {
	}

	public DatasetSummaryId(Integer file_id) {

		this.file_id = file_id;
	}

	public Integer getFileId() {
		if (file_id == null)
			return -1;
		else
			return file_id;
	}

	public void setFileId(Integer file_id) {
		this.file_id = file_id;
	}

}
