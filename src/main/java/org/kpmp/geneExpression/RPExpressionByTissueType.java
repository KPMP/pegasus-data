package org.kpmp.geneExpression;

import java.util.List;

public class RPExpressionByTissueType {

	private List<? extends RPExpressionData> all;
	private List<? extends RPExpressionData> aki;
	private List<? extends RPExpressionData> ckd;
	private List<? extends RPExpressionData> hrt;
	private List<? extends RPExpressionData> dmr;

	public List<? extends RPExpressionData> getAll() {
		return all;
	}

	public void setAll(List<? extends RPExpressionData> all) {
		this.all = all;
	}

	public List<? extends RPExpressionData> getAki() {
		return aki;
	}

	public void setAki(List<? extends RPExpressionData> aki) {
		this.aki = aki;
	}

	public List<? extends RPExpressionData> getCkd() {
		return ckd;
	}

	public void setCkd(List<? extends RPExpressionData> ckd) {
		this.ckd = ckd;
	}

	public List<? extends RPExpressionData> getHrt() {
		return hrt;
	}

	public void setHrt(List<? extends RPExpressionData> hrt) {
		this.hrt = hrt;
	}

	public List<? extends RPExpressionData> getDmr() {
		return dmr;
	}

	public void setDmr(List<? extends RPExpressionData> dmr) {
		this.dmr = dmr;
	}

}
