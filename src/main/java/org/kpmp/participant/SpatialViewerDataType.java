package org.kpmp.participant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "sv_data_type_v")
class SpatialViewerDataType implements Serializable {

	@Id
	@Column(name = "data_type")
	private String dataType;

	@Column(name = "table_name")
	private String tableName;

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

}
