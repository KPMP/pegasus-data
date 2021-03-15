package org.kpmp.umap;

import javax.persistence.Column;
import javax.persistence.Id;

public class SingleNucleusUMAPPoint {

    @Id
    @Column(name = "barcode")
    private String barcode;
    @Column(name = "library_id")
    private String libraryId;
    @Column(name = "nCount_RNA")
    private int nCountRNA;
    @Column(name = "nFeature_RNA")
    private int nFeatureRNA;
    @Column(name = "percent.er")
    private double percentER;
    @Column(name = "percent.mt")
    private double percentMT;
    @Column(name = "experiment")
    private String experiment;
    @Column(name = "specimen_id")
    private String specimenId;
    @Column(name = "region")
    private String region;
    @Column(name = "percent.cortex")
    private int percentCortex;
    @Column(name = "percent.medulla")
    private int percentMedulla;
    @Column(name = "cluster_id")
    private int clusterId;
    @Column(name = "subclass.l2")
    private String cellTypesubClassL1;
    @Column(name = "subclass.l1")
    private String cellTypeSubClassL2;
    @Column(name = "class")
    private String cellTypeClass;
    @Column(name = "UMAP_1")
    private double umapX;
    @Column(name = "UMAP_2")
    private double umapY;

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(String libraryId) {
        this.libraryId = libraryId;
    }

    public int getnCountRNA() {
        return nCountRNA;
    }

    public void setnCountRNA(int nCountRNA) {
        this.nCountRNA = nCountRNA;
    }

    public int getnFeatureRNA() {
        return nFeatureRNA;
    }

    public void setnFeatureRNA(int nFeatureRNA) {
        this.nFeatureRNA = nFeatureRNA;
    }

    public double getPercentER() {
        return percentER;
    }

    public void setPercentER(double percentER) {
        this.percentER = percentER;
    }

    public double getPercentMT() {
        return percentMT;
    }

    public void setPercentMT(double percentMT) {
        this.percentMT = percentMT;
    }

    public String getExperiment() {
        return experiment;
    }

    public void setExperiment(String experiment) {
        this.experiment = experiment;
    }

    public String getSpecimenId() {
        return specimenId;
    }

    public void setSpecimenId(String specimenId) {
        this.specimenId = specimenId;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getPercentCortex() {
        return percentCortex;
    }

    public void setPercentCortex(int percentCortex) {
        this.percentCortex = percentCortex;
    }

    public int getPercentMedulla() {
        return percentMedulla;
    }

    public void setPercentMedulla(int percentMedulla) {
        this.percentMedulla = percentMedulla;
    }

    public int getClusterId() {
        return clusterId;
    }

    public void setClusterId(int clusterId) {
        this.clusterId = clusterId;
    }

    public String getCellTypesubClassL1() {
        return cellTypesubClassL1;
    }

    public void setCellTypesubClassL1(String cellTypesubClassL1) {
        this.cellTypesubClassL1 = cellTypesubClassL1;
    }

    public String getCellTypeSubClassL2() {
        return cellTypeSubClassL2;
    }

    public void setCellTypeSubClassL2(String cellTypeSubClassL2) {
        this.cellTypeSubClassL2 = cellTypeSubClassL2;
    }

    public String getCellTypeClass() {
        return cellTypeClass;
    }

    public void setCellTypeClass(String cellTypeClass) {
        this.cellTypeClass = cellTypeClass;
    }

    public double getUmapX() {
        return umapX;
    }

    public void setUmapX(double umapX) {
        this.umapX = umapX;
    }

    public double getUmapY() {
        return umapY;
    }

    public void setUmapY(double umapY) {
        this.umapY = umapY;
    }
}
