package org.kpmp.umap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sc_metadata")
public class SingleCellUMAPPoint {

    @Id
    @Column(name = "barcode")
    private String barcode;
    @Column(name = "orig.ident")
    private String origIdent;
    @Column(name = "nCount_RNA")
    private int nCountRNA;
    @Column(name = "nFeature_RNA")
    private int nFeatureRNA;
    @Column(name = "percent.mt")
    private double percentMT;
    @Column(name = "subclass.l2")
    private String cellTypeSubClassL2;
    @Column(name = "subclass.l1")
    private String cellTypesubClassL1;
    @Column(name = "dataSource")
    private String dataSource;
    @Column(name = "sampletype")
    private String sampleType;
    @Column(name = "UMAP_1")
    private double umapX;
    @Column(name = "UMAP_2")
    private double umapY;
    @Column(name = "specimen_id")
    private String specimenId;
    @Column(name = "diseasetype")
    private String diseaseType;
    @Column(name = "cluster_id")
    private int clusterId;
    @Column(name = "class")
    private String cellTypeClass;
    @Column(name = "library_id")
    private String libraryId;
    @Column(name = "age")
    private int age;
    @Column(name = "sex")
    private String sex;

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getOrigIdent() {
        return origIdent;
    }

    public void setOrigIdent(String origIdent) {
        this.origIdent = origIdent;
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

    public double getPercentMT() {
        return percentMT;
    }

    public void setPercentMT(double percentMT) {
        this.percentMT = percentMT;
    }

    public String getCellTypeSubClassL2() {
        return cellTypeSubClassL2;
    }

    public void setCellTypeSubClassL2(String cellTypeSubClassL2) {
        this.cellTypeSubClassL2 = cellTypeSubClassL2;
    }

    public String getCellTypesubClassL1() {
        return cellTypesubClassL1;
    }

    public void setCellTypesubClassL1(String cellTypesubClassL1) {
        this.cellTypesubClassL1 = cellTypesubClassL1;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getSampleType() {
        return sampleType;
    }

    public void setSampleType(String sampleType) {
        this.sampleType = sampleType;
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

    public String getSpecimenId() {
        return specimenId;
    }

    public void setSpecimenId(String specimenId) {
        this.specimenId = specimenId;
    }

    public String getDiseaseType() {
        return diseaseType;
    }

    public void setDiseaseType(String diseaseType) {
        this.diseaseType = diseaseType;
    }

    public int getClusterId() {
        return clusterId;
    }

    public void setClusterId(int clusterId) {
        this.clusterId = clusterId;
    }

    public String getCellTypeClass() {
        return cellTypeClass;
    }

    public void setCellTypeClass(String cellTypeClass) {
        this.cellTypeClass = cellTypeClass;
    }

    public String getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(String libraryId) {
        this.libraryId = libraryId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
