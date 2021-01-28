package org.kpmp.autocomplete;

import java.util.List;

public class AutocompleteResult {

    private String value;
    private String id;
    private String type;
    private List<String> aliases;

    public AutocompleteResult() {
    }

    public AutocompleteResult(String value, String id, String type, List<String> aliases) {
        this.value = value;
        this.id = id;
        this.type = type;
        this.aliases = aliases;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getAliases() {
        return aliases;
    }

    public void setAliases(List<String> aliases) {
        this.aliases = aliases;
    }
}
