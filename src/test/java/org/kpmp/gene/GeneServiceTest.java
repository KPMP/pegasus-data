package org.kpmp.gene;

import static org.mockito.Mockito.mock;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GeneServiceTest {

    private GeneService geneService;

    @Before
    public void setUp() throws Exception {
        geneService = new GeneService();
    }

    @After
    public void tearDown() throws Exception {
        geneService = null;
    }

}
