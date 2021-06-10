package org.kpmp.geneExpression;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rt_gti")
public class RTExpressionDataGTI implements RTExpressionData {

    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "gene_symbol")
    private String geneSymbol;
    @Column(name = "segment")
    private String segment;
    @Column(name = "fold_change")
    private Double foldChange;
    @Column(name = "p_val")
    private Double pVal;
    @Column(name = "std_dev")
    private Double stdDev;
    @Column(name = "p_val_log10")
    private Double pValLog10;
    @Column(name = "tissue_type")
    private String tissueType;
    @Column(name = "sample_count")
    private Integer sampleCount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGeneSymbol() {
        return geneSymbol;
    }

    public void setGeneSymbol(String geneSymbol) {
        this.geneSymbol = geneSymbol;
    }

    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public Double getFoldChange() {
        return foldChange;
    }

    public void setFoldChange(Double foldChange) {
        this.foldChange = foldChange;
    }

    public Double getPVal() {
        return pVal;
    }

    public void setPVal(Double pVal) {
        this.pVal = pVal;
    }

    public Double getStdDev() {
        return stdDev;
    }

    public void setStdDev(Double stdDev) {
        this.stdDev = stdDev;
    }

    public Double getPValLog10() {
        return pValLog10;
    }

    public void setPValLog10(Double pValLog10) {
        this.pValLog10 = pValLog10;
    }

    public String getTissueType() {
        return tissueType;
    }

    public void setTissueType(String tissueType) {
        this.tissueType = tissueType;
    }

    public Integer getSampleCount() {
        return sampleCount;
    }

    public void setSampleCount(Integer sampleCount) {
        this.sampleCount = sampleCount;
    }
}


