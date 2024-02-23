package org.kpmp.geneExpression;

public interface RTExpressionData {

	Integer getId();

	void setId(Integer id);

	String getGeneSymbol();

	void setGeneSymbol(String geneSymbol);

	String getSegment();

	void setSegment(String segment);

	String getSegmentName();

	void setSegmentName(String segmentName);

	String getFoldChangeString();

	Double getFoldChange();

	void setFoldChange(Double foldChange);

	String getPValString();

	Double getPVal();

	void setPVal(Double pVal);

	Double getStdDev();

	void setStdDev(Double stdDev);

	String getPValLog10String();

	Double getPValLog10();

	void setPValLog10(Double pValLog10);

	String getTissueType();

	void setTissueType(String tissueType);

	Integer getSampleCount();

	void setSampleCount(Integer sampleCount);
}
