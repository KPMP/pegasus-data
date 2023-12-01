package org.kpmp.geneExpression;

import java.util.List;
import java.util.Map;

public class RPExpressionByTissueType {

    private class AccessionGroup {
        private accession String;
        private List<RPExpressionData> expressionDataList;
    }

    private Map<String, List<RPExpressionData>> all;

    private Map<String, List<RPExpressionData>> aki;

    private Map<String, List<RPExpressionData>> ckd;

    private Map<String, List<RPExpressionData>> hrt;

    private Map<String, List<RPExpressionData>> dmr;

    public Map<String, List<RPExpressionData>> getAll() {
        return all;
    }

    public void setAll(Map<String, List<RPExpressionData>> all) {
        this.all = all;
    }

    public Map<String, List<RPExpressionData>> getAki() {
        return aki;
    }

    public void setAki(Map<String, List<RPExpressionData>> aki) {
        this.aki = aki;
    }

    public Map<String, List<RPExpressionData>> getCkd() {
        return ckd;
    }

    public void setCkd(Map<String, List<RPExpressionData>> ckd) {
        this.ckd = ckd;
    }

    public Map<String, List<RPExpressionData>> getHrt() {
        return hrt;
    }

    public void setHrt(Map<String, List<RPExpressionData>> hrt) {
        this.hrt = hrt;
    }

    public Map<String, List<RPExpressionData>> getDmr() {
        return dmr;
    }

    public void setDmr(Map<String, List<RPExpressionData>> dmr) {
        this.dmr = dmr;
    }
}

