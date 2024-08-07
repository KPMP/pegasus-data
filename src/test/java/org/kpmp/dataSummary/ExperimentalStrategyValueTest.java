package org.kpmp.dataSummary;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ExperimentalStrategyValueTest {

	private ExperimentalStrategyValue value;

	@BeforeEach
	public void setUp() throws Exception {
		value = new ExperimentalStrategyValue();
	}

	@AfterEach
	public void tearDown() throws Exception {
		value = null;
	}

	@Test
	public void testSetCount() {
		value.setCount(23);
		assertEquals(23, value.getCount());
	}

	@Test
	public void testSetExperimentalStrategy() {
		value.setExperimentalStrategy("experimentalStrategy");
		assertEquals("experimentalStrategy", value.getExperimentalStrategy());
	}

	@Test
	public void testSetAccess() {
		value.setAccess("open");
		assertEquals("open", value.getAccess());
	}

	@Test
	public void testSetDataType() {
		value.setDataType("dataType");
		assertEquals("dataType", value.getDataType());
	}

	@Test
	public void testSetDataCategory() {
		value.setDataCategory("dataCategory");
		assertEquals("dataCategory", value.getDataCategory());
	}

}
