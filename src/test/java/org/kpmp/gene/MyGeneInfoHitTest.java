package org.kpmp.gene;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MyGeneInfoHitTest {

    private MyGeneInfoHit myGeneInfoHit;

    @BeforeEach
    public void setUp() throws Exception {
        myGeneInfoHit = new MyGeneInfoHit();
    }

    @AfterEach
    public void tearDown() throws Exception {
        myGeneInfoHit = null;
    }

    @Test
    public void testSetId() throws Exception {
        myGeneInfoHit.setId("id");
        assertEquals("id", myGeneInfoHit.getId());
    }

    @Test
    public void testSetName() throws Exception {
        myGeneInfoHit.setName("name");
        assertEquals("name", myGeneInfoHit.getName());
    }

    @Test
    public void testSetSymbol() throws Exception {
        myGeneInfoHit.setSymbol("symbol");
        assertEquals("symbol", myGeneInfoHit.getSymbol());
    }

    @Test
    public void testSetEntrezgene() throws Exception {
        myGeneInfoHit.setEntrezgene("egene");
        assertEquals("egene", myGeneInfoHit.getEntrezgene());
    }

    @Test
    public void testSetAlias() throws Exception {
        List<String> aliases = Arrays.asList("one", "two");
        myGeneInfoHit.setAlias(aliases);
        assertEquals(aliases, myGeneInfoHit.getAlias());
    }


}
