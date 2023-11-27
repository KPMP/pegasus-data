package org.kpmp.geneExpression;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rp_expression")
public class RPExpressionData {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "fdr_confidence")
    private String fdrConfidence;

    @Column(name = "accession")
    private String accession;

    @Column(name = "description")
    private String description;

    @Column(name = "coverage_pct")
    private int coveragePct;

    @Column(name = "num_peptides")
    private int numPeptides;

    @Column(name = "num_unique_peptides")
    private int numUniquePeptides;

    @Column(name = "gene_symbol")
    private String geneSymbol;

    @Column(name = "comparison")
    private String comparison;

    @Column(name = "adj_p_val")
    private Double adjPVal;

    @Column(name = "fold_chane")
    private Double foldChange;

    @Column(name = "tissue_type")
    private String tissueType;

    @Column(name = "region")
    private String region;

    @Column(name = "sample_count")
    private Integer sampleCount;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFdrConfidence() {
        return fdrConfidence;
    }

    public void setFdrConfidence(String fdrConfidence) {
        this.fdrConfidence = fdrConfidence;
    }

    public String getAccession() {
        return accession;
    }

    public void setAccession(String accession) {
        this.accession = accession;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCoveragePct() {
        return coveragePct;
    }

    public void setCoveragePct(int coveragePct) {
        this.coveragePct = coveragePct;
    }

    public int getNumPeptides() {
        return numPeptides;
    }

    public void setNumPeptides(int numPeptides) {
        this.numPeptides = numPeptides;
    }

    public int getNumUniquePeptides() {
        return numUniquePeptides;
    }

    public void setNumUniquePeptides(int numUniquePeptides) {
        this.numUniquePeptides = numUniquePeptides;
    }

    public String getGeneSymbol() {
        return geneSymbol;
    }

    public void setGeneSymbol(String geneSymbol) {
        this.geneSymbol = geneSymbol;
    }

    public String getComparison() {
        return comparison;
    }

    public void setComparison(String comparison) {
        this.comparison = comparison;
    }

    public Double getAdjPVal() {
        return adjPVal;
    }

    public void setAdjPVal(Double adjPVal) {
        this.adjPVal = adjPVal;
    }

    public Double getFoldChange() {
        return foldChange;
    }

    public void setFoldChange(Double foldChange) {
        this.foldChange = foldChange;
    }

    public String getTissueType() {
        return tissueType;
    }

    public void setTissueType(String tissueType) {
        this.tissueType = tissueType;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Integer getSampleCount() {
        return sampleCount;
    }

    public void setSampleCount(Integer sampleCount) {
        this.sampleCount = sampleCount;
    }
}
