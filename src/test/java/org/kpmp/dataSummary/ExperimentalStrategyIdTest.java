package org.kpmp.dataSummary;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ExperimentalStrategyIdTest {

	private ExperimentalStrategyId id;

	@BeforeEach
	public void setUp() throws Exception {
		id = new ExperimentalStrategyId();
	}

	@AfterEach
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
