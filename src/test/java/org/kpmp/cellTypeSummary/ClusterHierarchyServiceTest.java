package org.kpmp.cellTypeSummary;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kpmp.DataTypeEnum;
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

	@Test
	public void testFindDataTypesByClusterNameWhenBothY() throws Exception {
		ClusterHierarchy clusterHierarchy = new ClusterHierarchy();
		clusterHierarchy.setIsSingleCellCluster("Y");
		clusterHierarchy.setIsSingleNucCluster("Y");
		clusterHierarchy.setIsRegionalTranscriptomics("N");
		when(clusterHierarchyRepo.findFirstByClusterOrRegion("cluster")).thenReturn(clusterHierarchy);

		List<String> dataTypes = service.findDataTypesByClusterName("cluster");

		assertEquals(2, dataTypes.size());
		assertEquals(Arrays.asList(DataTypeEnum.SINGLE_CELL.getAbbreviation(),
				DataTypeEnum.SINGLE_NUCLEUS.getAbbreviation()), dataTypes);
		verify(clusterHierarchyRepo).findFirstByClusterOrRegion("cluster");
	}

	@Test
	public void testFindDataTypesByClusterNameWhenSingleCellY() throws Exception {
		ClusterHierarchy clusterHierarchy = new ClusterHierarchy();
		clusterHierarchy.setIsSingleCellCluster("Y");
		clusterHierarchy.setIsSingleNucCluster("N");
		clusterHierarchy.setIsRegionalTranscriptomics("N");

		when(clusterHierarchyRepo.findFirstByClusterOrRegion("cluster")).thenReturn(clusterHierarchy);

		List<String> dataTypes = service.findDataTypesByClusterName("cluster");

		assertEquals(1, dataTypes.size());
		assertEquals(Arrays.asList(DataTypeEnum.SINGLE_CELL.getAbbreviation()), dataTypes);
		verify(clusterHierarchyRepo).findFirstByClusterOrRegion("cluster");
	}

	@Test
	public void testFindDataTypesByClusterNameWhenSingleNucY() throws Exception {
		ClusterHierarchy clusterHierarchy = new ClusterHierarchy();
		clusterHierarchy.setIsSingleCellCluster("N");
		clusterHierarchy.setIsSingleNucCluster("y");
		clusterHierarchy.setIsRegionalTranscriptomics("N");
		when(clusterHierarchyRepo.findFirstByClusterOrRegion("cluster")).thenReturn(clusterHierarchy);

		List<String> dataTypes = service.findDataTypesByClusterName("cluster");

		assertEquals(1, dataTypes.size());
		assertEquals(Arrays.asList(DataTypeEnum.SINGLE_NUCLEUS.getAbbreviation()), dataTypes);
		verify(clusterHierarchyRepo).findFirstByClusterOrRegion("cluster");
	}

	@Test
	public void testFindDataTypesByClusterNameWhenNeitherY() throws Exception {
		ClusterHierarchy clusterHierarchy = new ClusterHierarchy();
		clusterHierarchy.setIsSingleCellCluster("N");
		clusterHierarchy.setIsSingleNucCluster("N");
		clusterHierarchy.setIsRegionalTranscriptomics("N");
		when(clusterHierarchyRepo.findFirstByClusterOrRegion("cluster")).thenReturn(clusterHierarchy);

		List<String> dataTypes = service.findDataTypesByClusterName("cluster");

		assertEquals(0, dataTypes.size());
		verify(clusterHierarchyRepo).findFirstByClusterOrRegion("cluster");
	}
}
