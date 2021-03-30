package org.kpmp.geneExpression;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.json.JSONException;
import org.json.JSONObject;

@Entity
@Table(name = "sc_feature_data")
class SCExpressionData {

	@Id
	@Column(name = "gene_symbol")
	private String geneSymbol;
	@Column(name = "expression_data")
	private String expressionString;

	public String getGeneSymbol() {
		return geneSymbol;
	}

	public void setGeneSymbol(String geneSymbol) {
		this.geneSymbol = geneSymbol;
	}

	public String getExpressionString() {
		return expressionString;
	}

	public void setExpressionString(String expressionString) {
		this.expressionString = expressionString;
	}

	public JSONObject getExpressionDataAsJson() throws JSONException {
		return new JSONObject(expressionString);
	}

}
