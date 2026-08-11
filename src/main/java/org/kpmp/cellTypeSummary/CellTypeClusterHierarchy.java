package org.kpmp.cellTypeSummary;

import jakarta.persistence.*;
import org.jspecify.annotations.Nullable;

import java.io.Serializable;


@Entity
@Table(name = "cell_type_cluster_hierarchy")
public class CellTypeClusterHierarchy implements Serializable {

	private static final long serialVersionUID = -7707637379989017634L;
	@Id
	@Column(name = "cell_type_cluster_hierarchy_id")
	private int cellTypeClusterHierarchyId;
	@Column(name = "structure_region")
	private String structureRegion;
    @jakarta.annotation.Nullable
    @Column(name = "structure_subregion")
	private String structureSubregion;
    @jakarta.annotation.Nullable
    @Column(name = "cell_type")
    private String cellType;
    @Column(name = "release_ver")
    private Double releaseVer;
    @Column(name = "release_sunset")
    private Double releaseSunset;
    @Column(name = "cell_type_order")
    private Double cellTypeOrder;
	@Column(name = "cluster_name")
	private String clusterName;
    @Column(name = "cluster_abbreviation")
    private String clusterAbbreviation;
    @Column(name = "rt_segment_abbreviation")
    private String rtSegmentAbbreviation;
	@Column(name = "is_single_cell")
	private String isSingleCellCluster;
	@Column(name = "is_single_nuc")
	private String isSingleNucCluster;
	@Column(name = "is_rt")
	private String isRegionalTranscriptomics;
	@Column(name = "is_rp")
	private String isRegionalProteomics;
    @Column(name = "is_st")
    private String isSpatialTranscriptomics;

    @Nullable
    public String getClusterAbbreviation() {
        return clusterAbbreviation;
    }

    public void setClusterAbbreviation(String clusterAbbreviation) {
        this.clusterAbbreviation = clusterAbbreviation;
    }

    @Nullable
    public String getRtSegmentAbbreviation() {
        return rtSegmentAbbreviation;
    }

    public void setRtSegmentAbbreviation(String rtSegmentAbbreviation) {
        this.rtSegmentAbbreviation = rtSegmentAbbreviation;
    }

    public String getIsSpatialTranscriptomics() {
        return isSpatialTranscriptomics;
    }

    public void setIsSpatialTranscriptomics(String isSpatialTranscriptomics) {
        this.isSpatialTranscriptomics = isSpatialTranscriptomics;
    }

	public int getCellTypeClusterHierarchyId() {
		return cellTypeClusterHierarchyId;
	}

	public void setCellTypeClusterHierarchyId(int cellTypeId) {
		this.cellTypeClusterHierarchyId = cellTypeId;
	}
    

	public String getStructureRegion() {
		return structureRegion;
	}

	public void setStructureRegion(String structureRegion) {
		this.structureRegion = structureRegion;
	}

	@Nullable
	public String getStructureSubregion() {
		return structureSubregion;
	}

	public void setStructureSubregion(String structureSubregion) {
		this.structureSubregion = structureSubregion;
	}

	@Nullable
	public String getClusterName() {
		return clusterName;
	}

	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}

	public String getIsSingleCellCluster() {
		return isSingleCellCluster;
	}

	public void setIsSingleCellCluster(String isSingleCellCluster) {
		this.isSingleCellCluster = isSingleCellCluster;
	}

	public String getIsSingleNucCluster() {
		return isSingleNucCluster;
	}

	public void setIsSingleNucCluster(String isSingleNucCluster) {
		this.isSingleNucCluster = isSingleNucCluster;
	}

	public String getIsRegionalTranscriptomics() {
		return isRegionalTranscriptomics;
	}

	public void setIsRegionalTranscriptomics(String isRegionalTranscriptomics) {
		this.isRegionalTranscriptomics = isRegionalTranscriptomics;
	}

	public String getIsRegionalProteomics() {
		return isRegionalProteomics;
	}

	public void setIsRegionalProteomics(String isRegionalProteomics) {
		this.isRegionalProteomics = isRegionalProteomics;
	}

	@Nullable
	public Double getCellTypeOrder() {
		return cellTypeOrder;
	}

	public void setCellTypeOrder(Double cellTypeOrder) {
		this.cellTypeOrder = cellTypeOrder;
	}

	@Nullable
	public String getCellType() {
		return cellType;
	}

	public void setCellType(String cellType) {
		this.cellType = cellType;
	}

    public Double getReleaseVer() {
        return releaseVer;
    }

    public void setReleaseVer(Double releaseVer) {
        this.releaseVer = releaseVer;
    }

    public Double getReleaseSunset() {
        return releaseSunset;
    }

    public void setReleaseSunset(Double releaseSunset) {
        this.releaseSunset = releaseSunset;
    }
    
    
    @Override
    public String toString() {
        return "CellTypeClusterHierarchy{" +
                "cellTypeClusterHierarchyId=" + cellTypeClusterHierarchyId +
                ", structureRegion='" + structureRegion + '\'' +
                ", structureSubregion='" + structureSubregion + '\'' +
                ", cellType='" + cellType + '\'' +
                ", releaseVer=" + releaseVer +
                ", releaseSunset=" + releaseSunset +
                ", cellTypeOrder=" + cellTypeOrder +
                ", clusterName='" + clusterName + '\'' +
                ", clusterAbbreviation='" + clusterAbbreviation + '\'' +
                ", rtSegmentAbbreviation='" + rtSegmentAbbreviation + '\'' +
                ", isSingleCellCluster='" + isSingleCellCluster + '\'' +
                ", isSingleNucCluster='" + isSingleNucCluster + '\'' +
                ", isRegionalTranscriptomics='" + isRegionalTranscriptomics + '\'' +
                ", isRegionalProteomics='" + isRegionalProteomics + '\'' +
                ", isSpatialTranscriptomics='" + isSpatialTranscriptomics + '\'' +
                '}';
    }
}
