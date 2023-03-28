package org.kpmp.cluster;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ClusterServiceTest {

	@Mock
	private ClusterRepository clusterRepo;
	private ClusterService service;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		service = new ClusterService(clusterRepo);
	}

	@After
	public void tearDown() throws Exception {
		MockitoAnnotations.openMocks(this).close();
		service = null;
	}

	@Test
	public void testGetClusterToColorMapping() {
		Cluster cluster1 = new Cluster();
		cluster1.setAbbreviation("abbr1");
		cluster1.setClusterColor("blue");
		Cluster cluster2 = new Cluster();
		cluster2.setAbbreviation("abbr2");
		cluster2.setClusterColor("red");
		Cluster cluster3 = new Cluster();
		cluster3.setAbbreviation("abbr3");
		cluster3.setClusterColor("yellow");
		Cluster cluster4 = new Cluster();
		cluster4.setAbbreviation("abbr4");
		cluster4.setClusterColor("green");
		when(clusterRepo.findAll()).thenReturn(Arrays.asList(cluster1, cluster2, cluster3, cluster4));

		Map<String, String> clusterToColorMapping = service.getClusterToColorMapping();

		assertEquals(4, clusterToColorMapping.size());
		assertEquals("blue", clusterToColorMapping.get("abbr1"));
		assertEquals("red", clusterToColorMapping.get("abbr2"));
		assertEquals("yellow", clusterToColorMapping.get("abbr3"));
		assertEquals("green", clusterToColorMapping.get("abbr4"));
	}

}
