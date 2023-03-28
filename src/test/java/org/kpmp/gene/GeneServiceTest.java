package org.kpmp.gene;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class GeneServiceTest {

	private GeneService geneService;

	@Mock
	HttpURLConnection connection;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		geneService = new GeneService();
	}

	@After
	public void tearDown() throws Exception {
		MockitoAnnotations.openMocks(this).close();
		geneService = null;
	}

	@Test
	public void testQuerySymbolAndAlias() throws Exception {
		MyGeneInfoResult result1 = new MyGeneInfoResult();
		List<MyGeneInfoHit> hits1 = new ArrayList<>();
		MyGeneInfoHit hit1a = new MyGeneInfoHit();
		hit1a.setSymbol("a");
		MyGeneInfoHit hit1b = new MyGeneInfoHit();
		hit1b.setSymbol("b");
		MyGeneInfoHit hit1c = new MyGeneInfoHit();
		hit1c.setSymbol("z");
		hits1.add(hit1b);
		hits1.add(hit1a);
		hits1.add(hit1c);
		hits1.add(hit1c);
		hits1.add(hit1c);
		hits1.add(hit1c);
		result1.setHits(hits1);
		MyGeneInfoResult result2 = new MyGeneInfoResult();
		List<MyGeneInfoHit> hits2 = new ArrayList<>();
		MyGeneInfoHit hit2 = new MyGeneInfoHit();
		hit2.setSymbol("z");
		hits2.add(hit2);
		hits2.add(hit2);
		hits2.add(hit2);
		hits2.add(hit2);
		hits2.add(hit2);
		hits2.add(hit2);
		hits2.add(hit2);
		hits2.add(hit2);
		hits2.add(hit2);
		hits2.add(hit2);
		hits2.add(hit2);
		hits2.add(hit2);
		hits2.add(hit2);
		hits2.add(hit2);
		hits2.add(hit2);
		hits2.add(hit2);
		result2.setHits(hits2);
		GeneService geneServiceSpy = Mockito.spy(geneService);
		Mockito.doReturn(result1).when(geneServiceSpy).query(GeneService.GET_MY_GENE_INFO_QUERY_SYMBOL, "query");
		Mockito.doReturn(result2).when(geneServiceSpy).query(GeneService.GET_MY_GENE_INFO_QUERY_ALIAS, "query");

		List<MyGeneInfoHit> results = geneServiceSpy.querySymbolAndAlias("query");

		assertEquals(results.get(0), hit1a);
		assertEquals(results.get(1), hit1b);
		assertEquals(results.get(5), hit1c);
		assertEquals(results.get(6), hit2);
		assertEquals(10, results.size());
	}

	@Test
	public void testQuery_200response() throws Exception {
		GeneService geneServicespy = Mockito.spy(geneService);
		doReturn(connection).when(geneServicespy).getUrlConnection(any());
		doReturn(200).when(connection).getResponseCode();
		InputStream stubInputStream = IOUtils.toInputStream(
				"{\"took\": 2, \"total\": 1, \"max_score\": 98.02321, \"hits\": [{\"_id\": \"8542\", \"_score\": 98.02321, \"alias\": "
						+ "[\"APO-L\", \"APOL\", \"APOL-I\", \"FSGS4\"], \"entrezgene\": \"8542\", \"name\": \"apolipoprotein L1\","
						+ " \"symbol\": \"APOL1\", \"taxid\": 9606}]}",
				"UTF-8");
		doReturn(stubInputStream).when(connection).getInputStream();

		MyGeneInfoResult result = geneServicespy.query("http://google.com", "queryString");

		assertEquals(1, result.getTotal());
		List<MyGeneInfoHit> actualHits = result.getHits();
		assertEquals(1, actualHits.size());
		assertEquals(Arrays.asList("APO-L", "APOL", "APOL-I", "FSGS4"), actualHits.get(0).getAlias());
		assertEquals("APOL1", actualHits.get(0).getSymbol());
		verify(connection).disconnect();
	}

	@Test
	public void testQuery_400Response() throws Exception {
		GeneService geneServicespy = Mockito.spy(geneService);
		doReturn(connection).when(geneServicespy).getUrlConnection(any());
		doReturn(400).when(connection).getResponseCode();

		MyGeneInfoResult result = geneServicespy.query("http://google.com", "queryString");

		assertEquals(0, result.getTotal());

		verify(connection).disconnect();
		verify(connection, times(0)).getInputStream();
	}

	@Test
	public void testQuery_500Response() throws Exception {
		GeneService geneServicespy = Mockito.spy(geneService);
		doReturn(connection).when(geneServicespy).getUrlConnection(any());
		doReturn(500).when(connection).getResponseCode();

		try {
			geneServicespy.query("http://google.com", "queryString");
			fail("Should have thrown exception");
		} catch (Exception expected) {
			assertEquals("Unable to connect to mygene.info: 500", expected.getMessage());
			verify(connection).disconnect();
			verify(connection, times(0)).getInputStream();
		}
	}
}
