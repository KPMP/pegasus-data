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

	Double getFoldChange();

	void setFoldChange(Double foldChange);

	Double getPVal();

	void setPVal(Double pVal);

	Double getStdDev();

	void setStdDev(Double stdDev);

	Double getPValLog10();

	void setPValLog10(Double pValLog10);

	String getEnrollmentCategory();

	void setEnrollmentCategory(String enrollmentCategory);

	Integer getSampleCount();

	void setSampleCount(Integer sampleCount);

	Double getAdjPVal();

	void setAdjPVal(Double adjPVal);

}
