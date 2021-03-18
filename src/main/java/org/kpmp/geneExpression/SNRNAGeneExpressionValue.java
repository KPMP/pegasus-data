package org.kpmp.geneExpression;

import org.springframework.lang.Nullable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "sn_rnaseq")
public class SNRNAGeneExpressionValue implements GeneExpressionValue {

    @Column(name = "id")
    private Integer id;

    @Column(name = "tissue_type")
    private String tissueType;

    @Column(name = "gene")
    private String gene;

    @Column(name = "p_val")
    private Double PVal;

    @Column(name = "p_val_adj")
    private Double pValAdj;

    @Column(name = "fold_change")
    private Double foldChange;

    @Column(name = "pct_1")
    private Double pct1;

    @Column(name = "pct_2")
    private Double pct2;

    @Column(name = "avg_exp")
    private Double avgExpression;

    @Id
    @Column(name = "cluster")
    private String cluster;

    @Column(name = "cell_type")
    private String cellType;

    @Transient
    public String getDataType() {
        return "sn";
    }

    @Override
    @Nullable
    public String getCellType() {
        return cellType;
    }

    @Override
    public void setCellType(String cellType) {
        this.cellType = cellType;
    }

    @Override
    @Nullable
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    @Nullable
    public String getTissueType() {
        return tissueType;
    }

    @Override
    public void setTissueType(String tissueType) {
        this.tissueType = tissueType;
    }

    @Override
    @Nullable
    public String getGene() {
        return gene;
    }

    @Override
    public void setGene(String gene) {
        this.gene = gene;
    }

    @Nullable
    public Double getPVal() {
        return PVal;
    }

    public void setPVal(Double PVal) {
        this.PVal = PVal;
    }

    @Nullable
    public Double getPValAdj() {
        return pValAdj;
    }

    public void setPValAdj(Double pValAdj) {
        this.pValAdj = pValAdj;
    }

    @Override
    @Nullable
    public Double getFoldChange() {
        return foldChange;
    }

    @Override
    public void setFoldChange(Double foldChange) {
        this.foldChange = foldChange;
    }

    @Override
    @Nullable
    public Double getPct1() {
        return pct1;
    }

    @Override
    public void setPct1(Double pct1) {
        this.pct1 = pct1;
    }

    @Override
    @Nullable
    public Double getPct2() {
        return pct2;
    }

    @Override
    public void setPct2(Double pct2) {
        this.pct2 = pct2;
    }

    @Override
    @Nullable
    public Double getAvgExpression() {
        return avgExpression;
    }

    @Override
    @Nullable
    public void setAvgExpression(Double avgExpression) {
        this.avgExpression = avgExpression;
    }

    @Id
    @Override
    public String getCluster() {
        return cluster;
    }

    @Override
    public void setCluster(String cluster) {
        this.cluster = cluster;
    }
}
