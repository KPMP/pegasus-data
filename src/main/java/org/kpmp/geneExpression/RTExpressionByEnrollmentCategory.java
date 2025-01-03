package org.kpmp.geneExpression;

import java.util.List;

public class RTExpressionByEnrollmentCategory {

	private List<? extends RTExpressionData> all;
	private List<? extends RTExpressionData> aki;
	private List<? extends RTExpressionData> ckd;
	private List<? extends RTExpressionData> hrt;
	private List<? extends RTExpressionData> dmr;

	public List<? extends RTExpressionData> getAll() {
		return all;
	}

	public void setAll(List<? extends RTExpressionData> all) {
		this.all = all;
	}

	public List<? extends RTExpressionData> getAki() {
		return aki;
	}

	public void setAki(List<? extends RTExpressionData> aki) {
		this.aki = aki;
	}

	public List<? extends RTExpressionData> getCkd() {
		return ckd;
	}

	public void setCkd(List<? extends RTExpressionData> ckd) {
		this.ckd = ckd;
	}

	public List<? extends RTExpressionData> getHrt() {
		return hrt;
	}

	public void setHrt(List<? extends RTExpressionData> hrt) {
		this.hrt = hrt;
	}

	public List<? extends RTExpressionData> getDmr() {
		return dmr;
	}

	public void setDmr(List<? extends RTExpressionData> dmr) {
		this.dmr = dmr;
	}
}
