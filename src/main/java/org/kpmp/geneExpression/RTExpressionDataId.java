package org.kpmp.geneExpression;

import java.io.Serializable;
import java.util.Objects;

public class RTExpressionDataId implements Serializable {

    private Integer id;
    private String geneSymbol;
    private Double pVal;

    public RTExpressionDataId() {}

    public RTExpressionDataId(Integer id, String geneSymbol, Double pVal) {
        this.id = id;
        this.geneSymbol = geneSymbol;
        this.pVal = pVal;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getGeneSymbol() { return geneSymbol; }
    public void setGeneSymbol(String geneSymbol) { this.geneSymbol = geneSymbol; }
    public Double getPVal() { return pVal; }
    public void setPVal(Double pVal) { this.pVal = pVal; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RTExpressionDataId that = (RTExpressionDataId) o;
        return Objects.equals(id, that.id) && Objects.equals(geneSymbol, that.geneSymbol) && Objects.equals(pVal, that.pVal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, geneSymbol, pVal);
    }
}
