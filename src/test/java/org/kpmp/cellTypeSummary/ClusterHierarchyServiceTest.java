package org.kpmp.cellTypeSummary;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ClusterHierarchyServiceTest {

	@Mock
	private ClusterHiearchyRepository clusterHierarchyRepo;
	private ClusterHierarchyService service;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		service = new ClusterHierarchyService(clusterHierarchyRepo);
	}

	@After
	public void tearDown() throws Exception {
		service = null;
	}

	@Test
	public void testFindClustersByCellType() {
		List<ClusterHierarchy> hierarchies = Arrays.asList(new ClusterHierarchy());
		when(clusterHierarchyRepo.findByCellType("cell type")).thenReturn(hierarchies);

		assertEquals(hierarchies, service.findClustersByCellType("cell type"));
	}

}
