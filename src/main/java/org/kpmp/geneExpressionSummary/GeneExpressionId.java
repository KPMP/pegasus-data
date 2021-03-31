package org.kpmp.geneExpressionSummary;

import java.io.Serializable;

public class GeneExpressionId implements Serializable {

    private String cluster;
    private String gene;

    public GeneExpressionId() {
    }

    public GeneExpressionId(String cluster, String gene) {
        this.cluster = cluster;
        this.gene = gene;
    }

    public String getCluster() {
        return cluster;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
    }

    public String getGene() {
        if (gene == null)
            return "null gene";
        else
            return gene;
    }

    public void setGene(String gene) {
        this.gene = gene;
    }

}
