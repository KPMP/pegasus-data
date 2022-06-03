package org.kpmp.datasetSummary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "sv_file_v")
@IdClass(DatasetSummaryId.class)
public class DatasetSummaryValue {
	@Id
	@Column(name = "file_id")
	private Integer file_id;
	@Column(name = "config_type")
	private String config_type;
	@Column(name = "image_type")
	private String image_type;
	@Column(name = "data_type")
	private String data_type;
	@Column(name = "dl_file_id")
	private String dl_file_id;
	@Column(name = "file_name")
	private String file_name;
	@Column(name = "package_id")
	private String package_id;
    @Column(name = "file_size")
	private Long file_size;
    @Column(name = "participant_id")
	private Integer participant_id;
	@Column(name = "redcap_id")
	private String redcap_id;
	@Column(name = "age")
	private String age;
	@Column(name = "protocol")
	private String protocol;
	@Column(name = "sample_type")
	private String sample_type;
    @Column(name = "sex")
	private String sex;
    @Column(name = "tissue_source")
	private String tissue_source;
    @Column(name = "tissue_type")
	private String tissue_type;
    @Column(name = "spectrack_sample_id")
	private String spectrack_sample_id;
}
