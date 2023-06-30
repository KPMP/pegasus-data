package org.kpmp.file;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ar_file_info")
public class ARFileInfo {
    @Id
    private int arFileInfoId;
    private int fileId;
    private int releaseVersion;
    private int releaseSunsetVersion;
    private int metadataTypeId;

    public int getMetadataTypeId() {
        return metadataTypeId;
    }

    public void setMetadataTypeId(int metadataTypeId) {
        this.metadataTypeId = metadataTypeId;
    }

    public int getArFileInfoId() {
        return arFileInfoId;
    }

    public void setArFileInfoId(int arFileInfoId) {
        this.arFileInfoId = arFileInfoId;
    }
    
    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public int getReleaseVersion() {
        return releaseVersion;
    }

    public void setReleaseVersion(int releaseVersion) {
        this.releaseVersion = releaseVersion;
    }

    public int getReleaseSunsetVersion() {
        return releaseSunsetVersion;
    }
    
    public void setReleaseSunsetVersion(int releaseSunsetVersion) {
        this.releaseSunsetVersion = releaseSunsetVersion;
    }

}
