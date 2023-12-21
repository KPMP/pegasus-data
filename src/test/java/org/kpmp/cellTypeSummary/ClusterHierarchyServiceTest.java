package org.kpmp.cellTypeSummary;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
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
		MockitoAnnotations.openMocks(this);
		service = new ClusterHierarchyService(clusterHierarchyRepo);
	}

	@After
	public void tearDown() throws Exception {
		MockitoAnnotations.openMocks(this).close();
		service = null;
	}

	@Test
	public void testFindClustersByCellType() {
		List<ClusterHierarchy> hierarchies = Arrays.asList(new ClusterHierarchy());
		when(clusterHierarchyRepo.findByCellType("cell type")).thenReturn(hierarchies);

		assertEquals(hierarchies, service.findClustersByCellType("cell type"));
	}

	@Test
	public void testFindClustersByCellType_remvoesDups() {
		ClusterHierarchy clusterHierarchy1 = new ClusterHierarchy();
		clusterHierarchy1.setCellType("celltype");
		clusterHierarchy1.setClusterName("cluster");
		ClusterHierarchy clusterHierarchy2 = new ClusterHierarchy();
		clusterHierarchy2.setCellType("cluster");
		clusterHierarchy2.setClusterName("cluster");
		List<ClusterHierarchy> hierarchies = Arrays.asList(clusterHierarchy1, clusterHierarchy2);

		when(clusterHierarchyRepo.findByCellType("cell type")).thenReturn(hierarchies);

		assertEquals(Arrays.asList(clusterHierarchy2), service.findClustersByCellType("cell type"));
	}

	@Test
	public void testFindClustersByCellType_remvoesDupsUnlessNull() {
		ClusterHierarchy clusterHierarchy1 = new ClusterHierarchy();
		clusterHierarchy1.setCellType("celltype");
		clusterHierarchy1.setClusterName(null);
		clusterHierarchy1.setCellTypeOrder(3.2);
		ClusterHierarchy clusterHierarchy2 = new ClusterHierarchy();
		clusterHierarchy2.setCellType("cluster");
		clusterHierarchy2.setClusterName(null);
		clusterHierarchy2.setCellTypeOrder(2.0);
		List<ClusterHierarchy> hierarchies = Arrays.asList(clusterHierarchy1, clusterHierarchy2);
		when(clusterHierarchyRepo.findByCellType("cell type")).thenReturn(hierarchies);
		List<ClusterHierarchy> expected = Arrays.asList(clusterHierarchy1, clusterHierarchy2);

		List<ClusterHierarchy> result = service.findClustersByCellType("cell type");

		assertEquals(expected.size(), result.size());
		assertEquals(true, result.containsAll(expected));
	}

	@Test
	public void testFindClustersByCellType_sorts() {
		ClusterHierarchy clusterHierarchy1 = new ClusterHierarchy();
		clusterHierarchy1.setCellType("celltype");
		clusterHierarchy1.setStructureSubregion("second region");
		clusterHierarchy1.setClusterName("cluster");
		clusterHierarchy1.setCellTypeOrder(5.0);
		ClusterHierarchy clusterHierarchy2 = new ClusterHierarchy();
		clusterHierarchy2.setCellType("cluster");
		clusterHierarchy2.setClusterName("another cluster");
		clusterHierarchy2.setStructureSubregion("first region");
		clusterHierarchy2.setCellTypeOrder(1.0);

		List<ClusterHierarchy> hierarchies = Arrays.asList(clusterHierarchy1, clusterHierarchy2);

		when(clusterHierarchyRepo.findByCellType("cell type")).thenReturn(hierarchies);

		assertEquals(Arrays.asList(clusterHierarchy2, clusterHierarchy1), service.findClustersByCellType("cell type"));
	}

	@Test
	public void testFindDataTypesByClusterNameWhenBothY() throws Exception {
		ClusterHierarchy clusterHierarchy = new ClusterHierarchy();
		clusterHierarchy.setIsSingleCellCluster("Y");
		clusterHierarchy.setIsSingleNucCluster("Y");
		clusterHierarchy.setIsRegionalTranscriptomics("N");
		clusterHierarchy.setIsRegionalProteomics("N");
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
		clusterHierarchy.setIsRegionalProteomics("N");
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
		clusterHierarchy.setIsRegionalProteomics("N");
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
		clusterHierarchy.setIsRegionalProteomics("N");
		when(clusterHierarchyRepo.findFirstByClusterOrRegion("cluster")).thenReturn(clusterHierarchy);

		List<String> dataTypes = service.findDataTypesByClusterName("cluster");

		assertEquals(0, dataTypes.size());
		verify(clusterHierarchyRepo).findFirstByClusterOrRegion("cluster");
	}

	@Test
	public void testFindDataTypesByClusterNameWhenRTY() throws Exception {
		ClusterHierarchy clusterHierarchy = new ClusterHierarchy();
		clusterHierarchy.setIsSingleCellCluster("N");
		clusterHierarchy.setIsSingleNucCluster("N");
		clusterHierarchy.setIsRegionalTranscriptomics("Y");
		clusterHierarchy.setIsRegionalProteomics("N");
		when(clusterHierarchyRepo.findFirstByClusterOrRegion("cluster")).thenReturn(clusterHierarchy);

		List<String> dataTypes = service.findDataTypesByClusterName("cluster");

		assertEquals(1, dataTypes.size());
		verify(clusterHierarchyRepo).findFirstByClusterOrRegion("cluster");
	}


	@Test
	public void testFindDataTypesByClusterNameWhenRPY() throws Exception {
		ClusterHierarchy clusterHierarchy = new ClusterHierarchy();
		clusterHierarchy.setIsSingleCellCluster("N");
		clusterHierarchy.setIsSingleNucCluster("N");
		clusterHierarchy.setIsRegionalTranscriptomics("N");
		clusterHierarchy.setIsRegionalProteomics("Y");
		when(clusterHierarchyRepo.findFirstByClusterOrRegion("cluster")).thenReturn(clusterHierarchy);

		List<String> dataTypes = service.findDataTypesByClusterName("cluster");

		assertEquals(1, dataTypes.size());
		assertEquals(Arrays.asList("rp"), dataTypes);
		verify(clusterHierarchyRepo).findFirstByClusterOrRegion("cluster");
	}

	@Test
	public void testFindClustersByCellTypeTubulesOrInterstitium() throws Exception {
		List<ClusterHierarchy> clusterHierarchies = new ArrayList<>();
		when(clusterHierarchyRepo.findByCellType("Tubules")).thenReturn(clusterHierarchies);
		when(clusterHierarchyRepo.findByCellType("Interstitium")).thenReturn(clusterHierarchies);
		List<ClusterHierarchy> clusters = service.findClustersByCellType("Tubules");
		assertEquals("Tubulo-interstitium", clusters.get(0).getStructureRegion());
		List<ClusterHierarchy> clusters2 = service.findClustersByCellType("Interstitium");
		assertEquals("Tubulo-interstitium", clusters2.get(0).getStructureRegion());
	}

	@Test
	public void testFindDataTypesByClusterNameTi() throws Exception {
		List<String> dataTypes = service.findDataTypesByClusterName("Tubulo-interstitium");
		assertTrue(dataTypes.contains(DataTypeEnum.REGIONAL_PROTEOMICS.getAbbreviation()));
		assertTrue(dataTypes.contains(DataTypeEnum.REGIONAL_TRANSCRIPTOMICS.getAbbreviation()));
		assertFalse(dataTypes.contains(DataTypeEnum.SINGLE_CELL.getAbbreviation()));
		assertFalse(dataTypes.contains(DataTypeEnum.SINGLE_NUCLEUS.getAbbreviation()));
	}
}
