package org.kpmp.geneExpression;

import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SCExpressionDataTest {

	private SCExpressionData data;

	@BeforeEach
	public void setUp() throws Exception {
		data = new SCExpressionData();
	}

	@AfterEach
	public void tearDown() throws Exception {
		data = null;
	}

	@Test
	public void testSetGeneSymbol() {
		data.setGeneSymbol("gene symbol");
		assertEquals("gene symbol", data.getGeneSymbol());
	}

	@Test
	public void testSetExpressionString() {
		data.setExpressionString("expression string");
		assertEquals("expression string", data.getExpressionString());
	}

	@Test
	public void testGetExpressionDataAsJson() throws Exception {
		data.setExpressionString("{'key': 'value'}");
		JSONObject expressionJson = data.getExpressionDataAsJson();

		assertEquals("value", expressionJson.get("key"));
	}

}
