package org.kpmp.gene;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MyGeneInfoHit {
    private String id;
    private String symbol;
    private String name;
    private String entrezgene;

    @JsonIgnoreProperties(value={ "_score" }, allowGetters=true)

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

}
