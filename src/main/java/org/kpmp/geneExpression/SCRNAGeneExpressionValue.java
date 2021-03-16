package org.kpmp.geneExpression;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sc_rnaseq")
public class SCRNAGeneExpressionValue {

    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "tissue_type")
    private String tissueType;
    @Column(name = "gene")
    private String gene;
    @Column(name = "p_val")
    private double pVal;
    @Column(name = "p_val_adj")
    private double pValAdj;
    @Column(name = "fold_change")
    private double foldChanges;
    @Column(name = "pct_1")
    private double pct1;
    @Column(name = "pct_2")
    private double pct2;
    @Column(name = "specificity")
    private double specificity;
    @Column(name = "avg_exp")
    private double avgExpression;
    @Column(name = "cluster")
    private String cluster;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTissueType() {
        return tissueType;
    }

    public void setTissueType(String tissueType) {
        this.tissueType = tissueType;
    }

    public String getGene() {
        return gene;
    }

    public void setGene(String gene) {
        this.gene = gene;
    }

    public double getpVal() {
        return pVal;
    }

    public void setpVal(double pVal) {
        this.pVal = pVal;
    }

    public double getpValAdj() {
        return pValAdj;
    }

    public void setpValAdj(double pValAdj) {
        this.pValAdj = pValAdj;
    }

    public double getFoldChanges() {
        return foldChanges;
    }

    public void setFoldChanges(double foldChanges) {
        this.foldChanges = foldChanges;
    }

    public double getPct1() {
        return pct1;
    }

    public void setPct1(double pct1) {
        this.pct1 = pct1;
    }

    public double getPct2() {
        return pct2;
    }

    public void setPct2(double pct2) {
        this.pct2 = pct2;
    }

    public double getSpecificity() {
        return specificity;
    }

    public void setSpecificity(double specificity) {
        this.specificity = specificity;
    }

    public double getAvgExpression() {
        return avgExpression;
    }

    public void setAvgExpression(double avgExpression) {
        this.avgExpression = avgExpression;
    }

    public String getCluster() {
        return cluster;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
    }
}
