package org.kpmp.gene;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MyGeneInfoHit implements Comparable<MyGeneInfoHit>, Serializable {
	private static final long serialVersionUID = 7498017467676968095L;
	private String id;
	private String symbol;
	private String name;
	private String entrezgene;
	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	private List<String> alias;

	public String getId() {
		return id;
	}

	@JsonProperty("_id")
	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getEntrezgene() {
		return entrezgene;
	}

	public void setEntrezgene(String entrezgene) {
		this.entrezgene = entrezgene;
	}

	public List<String> getAlias() {
		return alias;
	}

	public void setAlias(List<String> alias) {
		this.alias = alias;
	}

	@Override
	public int compareTo(MyGeneInfoHit hit) {
		return this.getSymbol().compareTo(hit.getSymbol());
	}
}
