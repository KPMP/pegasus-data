package org.kpmp.gene;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MyGeneInfoHitTest {

    private MyGeneInfoHit myGeneInfoHit;

    @Before
    public void setUp() throws Exception {
        myGeneInfoHit = new MyGeneInfoHit();
    }

    @After
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

}
