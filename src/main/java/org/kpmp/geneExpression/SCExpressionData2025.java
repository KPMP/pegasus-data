package org.kpmp.geneExpression;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "sc_feature_data_2025")
class SCExpressionData2025 implements Serializable {

	private static final long serialVersionUID = 1079445825708102694L;
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
