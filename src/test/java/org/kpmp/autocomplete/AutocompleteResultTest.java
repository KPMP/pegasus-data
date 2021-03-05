package org.kpmp.autocomplete;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AutocompleteResultTest {

    private AutocompleteResult autocompleteResult;

    @BeforeEach
    void setUp() {
        autocompleteResult = new AutocompleteResult();
    }

    @AfterEach
    void tearDown() {
        autocompleteResult = null;
    }

    @Test
    void setValue() {
        autocompleteResult.setValue("value");
        assertEquals("value", autocompleteResult.getValue());
    }

    @Test
    void setId() {
        autocompleteResult.setId("id");
        assertEquals("id", autocompleteResult.getId());
    }

    @Test
    void setType() {
        autocompleteResult.setType("type");
        assertEquals("type", autocompleteResult.getType());
    }

    @Test
    void setAliases() {
        List<String> aliases = new ArrayList<String>();
        aliases.add("alias");
        autocompleteResult.setAliases(aliases);
        assertEquals(aliases, autocompleteResult.getAliases());
    }

    @Test
    void setName() {
        autocompleteResult.setName("name");
        assertEquals("name", autocompleteResult.getName());
    }
}