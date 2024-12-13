package org.kpmp.dataSummary;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.springframework.lang.Nullable;

@Entity
@Table(name = "sv_file_v")
public class DataSummaryValue implements Serializable {
	private static final long serialVersionUID = -1934662137113288596L;
	@Id
	@Column(name = "file_id")
	private Integer fileId;
	@Column(name = "config_type")
	private String configType;
	@Column(name = "image_type")
	private String imageType;
	@Column(name = "data_type")
	private String dataType;
	@Column(name = "dl_file_id")
	private String dlFileId;
	@Column(name = "file_name")
	private String fileName;
	@Column(name = "package_id")
	private String packageId;
	@Column(name = "file_size")
	private Long fileSize;
	@Column(name = "participant_id")
	private Integer participantId;
	@Column(name = "redcap_id")
	private String redcapId;
	@Column(name = "age")
	private String age;
	@Column(name = "protocol")
	private String protocol;
	@Column(name = "sample_type")
	private String sampleType;
	@Column(name = "sex")
	private String sex;
	@Column(name = "tissue_source")
	private String tissueSource;
	@Column(name = "enrollment_category")
	private String enrollmentCategory;
	@Column(name = "spectrack_sample_id")
	private String spectrackSampleId;

	@Nullable
	public Integer getFileId() {
		return fileId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	@Nullable
	public String getConfigType() {
		return configType;
	}

	public void setConfigType(String configType) {
		this.configType = configType;
	}

	@Nullable
	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	@Nullable
	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getDlFileId() {
		return dlFileId;
	}

	public void setDlFileIdd(String dlFileId) {
		this.dlFileId = dlFileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Nullable
	public String getPackageId() {
		return packageId;
	}

	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}

	@Nullable
	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	@Nullable
	public Integer getParticipantId() {
		return participantId;
	}

	public void setParticipantId(Integer participantId) {
		this.participantId = participantId;
	}

	@Nullable
	public String getRedcapId() {
		return redcapId;
	}

	public void setRedcapId(String redcapId) {
		this.redcapId = redcapId;
	}

	@Nullable
	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	@Nullable
	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	@Nullable
	public String getSampleType() {
		return sampleType;
	}

	public void setSampleType(String sampleType) {
		this.sampleType = sampleType;
	}

	@Nullable
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Nullable
	public String getTissueSource() {
		return tissueSource;
	}

	public void setTissueSource(String tissueSource) {
		this.tissueSource = tissueSource;
	}

	@Nullable
	public String getEnrollmentCategory() {
		return enrollmentCategory;
	}

	public void setEnrollmentCategory(String enrollmentCategory) {
		this.enrollmentCategory = enrollmentCategory;
	}

	@Nullable
	public String getSpectrackSampleId() {
		return spectrackSampleId;
	}

	public void setSpectrackSampleId(String spectrackSampleId) {
		this.spectrackSampleId = spectrackSampleId;
	}
}
