package org.kpmp.dataSummary;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ExperimentalStrategyIdTest {

	private ExperimentalStrategyId id;

	@Before
	public void setUp() throws Exception {
		id = new ExperimentalStrategyId();
	}

	@After
	public void tearDown() throws Exception {
		id = null;
	}

	@Test
	public void testSetExperimentalStrategy() {
		id.setExperimentalStrategy("strategy");
		assertEquals("strategy", id.getExperimentalStrategy());
	}

	@Test
	public void testSetAccess() {
		id.setAccess("open");
		assertEquals("open", id.getAccess());
	}

}
