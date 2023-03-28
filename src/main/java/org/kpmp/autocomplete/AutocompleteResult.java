package org.kpmp.autocomplete;

import java.io.Serializable;
import java.util.List;

public class AutocompleteResult implements Serializable {

	private static final long serialVersionUID = 762733408490639258L;
	private String value;
	private String name;
	private String id;
	private String type;
	private List<String> aliases;

	public AutocompleteResult() {
	}

	public AutocompleteResult(String value, String name, String id, String type, List<String> aliases) {
		this.value = value;
		this.name = name;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getAliases() {
		return aliases;
	}

	public void setAliases(List<String> aliases) {
		this.aliases = aliases;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof AutocompleteResult) {
			AutocompleteResult that = (AutocompleteResult) obj;
			if (this.id == that.id || this.value.equalsIgnoreCase(that.value)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
