package org.kpmp.geneExpressionSummary;

public interface GeneExpressionSummary {

    Integer getId();

    void setId(Integer id);

    String getTissueType();

    void setTissueType(String tissueType);

    String getGene();

    void setGene(String gene);

    Double getPVal();

    void setPVal(Double pVal);

    Double getPValAdj();

    void setPValAdj(Double pValAdj);

    Double getFoldChange();

    void setFoldChange(Double foldChange);

    Double getPct1();

    void setPct1(Double pct1);

    Double getPct2();

    void setPct2(Double pct2);

    Double getAvgExp();

    void setAvgExp(Double avgExp);

    String getCluster();

    void setCluster(String cluster);

    String getDataType();

    void setCellCount(Integer cellCount);

    Integer getCellCount();

    void setClusterName(String clusterName);

    String getClusterName();
}
