package org.kpmp.geneExpression;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.json.JSONException;
import org.json.JSONObject;

@Entity
@Table(name = "sn_feature_data_2025")
public class SNExpressionData2025 implements Serializable {

	private static final long serialVersionUID = -3363426907715685587L;
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
