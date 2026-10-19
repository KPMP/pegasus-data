package org.kpmp.cellTypeSummary;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kpmp.FullDataTypeEnum;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ClusterHierarchyServiceTest {

	@Mock
	private ClusterHiearchyRepository clusterHierarchyRepo;
	private ClusterHierarchyService service;

	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		service = new ClusterHierarchyService(clusterHierarchyRepo);
	}

	@AfterEach
	public void tearDown() throws Exception {
		MockitoAnnotations.openMocks(this).close();
		service = null;
	}

    @Test
    public void testFindClustersByCellType2025() {

        ClusterHierarchy clusterHierarchy1 = new ClusterHierarchy();
        clusterHierarchy1.setCellType("celltype");
        clusterHierarchy1.setClusterName(null);
        clusterHierarchy1.setCellTypeOrder(3.2);
        ClusterHierarchy clusterHierarchy2 = new ClusterHierarchy();
        clusterHierarchy2.setCellType("celltype2");
        clusterHierarchy2.setClusterName(null);
        clusterHierarchy2.setCellTypeOrder(2.0);
        ClusterHierarchy clusterHierarchy3 = new ClusterHierarchy();
        clusterHierarchy3.setCellType("celltype1");
        clusterHierarchy3.setClusterName(null);
        clusterHierarchy3.setCellTypeOrder(1.0);

        List<ClusterHierarchy> hierarchiesRTRP = new ArrayList<>(Arrays.asList(clusterHierarchy1));
        List<ClusterHierarchy> hierarchiesRNA =  new ArrayList<>(Arrays.asList(clusterHierarchy2));
        List<ClusterHierarchy> hierarchiesParent =  new ArrayList<>(Arrays.asList(clusterHierarchy3));

        when(clusterHierarchyRepo.findRTRPByCellTypeOrRegion("cell type")).thenReturn(hierarchiesRTRP);
        when(clusterHierarchyRepo.findRnaSeqByCellTypeOrRegion("cell type")).thenReturn(hierarchiesRNA);
        when(clusterHierarchyRepo.findRTRPParentRegions("cell type")).thenReturn(hierarchiesParent);

        assertEquals(3, service.findClustersByCellType2025("cell type").size());
    }

    @Test
    public void testFindClustersByCellType_remvoesDups2025() {
        ClusterHierarchy clusterHierarchy1 = new ClusterHierarchy();
        clusterHierarchy1.setCellType("celltype");
        clusterHierarchy1.setClusterName("cluster");
        clusterHierarchy1.setCellTypeOrder(3.2);
        ClusterHierarchy clusterHierarchy2 = new ClusterHierarchy();
        clusterHierarchy2.setCellType("celltype");
        clusterHierarchy2.setClusterName("cluster");
        clusterHierarchy2.setCellTypeOrder(2.0);
        ClusterHierarchy clusterHierarchy3 = new ClusterHierarchy();
        clusterHierarchy3.setCellType("cluster");
        clusterHierarchy3.setClusterName("cluster");
        clusterHierarchy3.setCellTypeOrder(1.0);

        List<ClusterHierarchy> hierarchiesRTRP = new ArrayList<>(Arrays.asList(clusterHierarchy1));
        List<ClusterHierarchy> hierarchiesRNA =  new ArrayList<>(Arrays.asList(clusterHierarchy2));
        List<ClusterHierarchy> hierarchiesParent =  new ArrayList<>(Arrays.asList(clusterHierarchy3));

        when(clusterHierarchyRepo.findRTRPByCellTypeOrRegion("cell type")).thenReturn(hierarchiesRTRP);
        when(clusterHierarchyRepo.findRnaSeqByCellTypeOrRegion("cell type")).thenReturn(hierarchiesRNA);
        when(clusterHierarchyRepo.findRTRPParentRegions("cell type")).thenReturn(hierarchiesParent);

        assertEquals(Arrays.asList(clusterHierarchy3), service.findClustersByCellType2025("cell type"));
    }

    @Test
    public void testFindClustersByCellType_remvoesDupsUnlessNull2025() {
        ClusterHierarchy clusterHierarchy1 = new ClusterHierarchy();
        clusterHierarchy1.setCellType("celltype");
        clusterHierarchy1.setClusterName(null);
        clusterHierarchy1.setCellTypeOrder(3.2);
        ClusterHierarchy clusterHierarchy2 = new ClusterHierarchy();
        clusterHierarchy2.setCellType("celltype");
        clusterHierarchy2.setClusterName(null);
        clusterHierarchy2.setCellTypeOrder(2.0);
        ClusterHierarchy clusterHierarchy3 = new ClusterHierarchy();
        clusterHierarchy3.setCellType("cluster");
        clusterHierarchy3.setClusterName(null);
        clusterHierarchy3.setCellTypeOrder(1.0);

        List<ClusterHierarchy> hierarchiesRTRP = new ArrayList<>(Arrays.asList(clusterHierarchy1));
        List<ClusterHierarchy> hierarchiesRNA =  new ArrayList<>(Arrays.asList(clusterHierarchy2));
        List<ClusterHierarchy> hierarchiesParent =  new ArrayList<>(Arrays.asList(clusterHierarchy3));

        when(clusterHierarchyRepo.findRTRPByCellTypeOrRegion("cell type")).thenReturn(hierarchiesRTRP);
        when(clusterHierarchyRepo.findRnaSeqByCellTypeOrRegion("cell type")).thenReturn(hierarchiesRNA);
        when(clusterHierarchyRepo.findRTRPParentRegions("cell type")).thenReturn(hierarchiesParent);


        List<ClusterHierarchy> expected = Arrays.asList(clusterHierarchy1, clusterHierarchy2, clusterHierarchy3);

        List<ClusterHierarchy> result = service.findClustersByCellType2025("cell type");

        assertEquals(expected.size(), result.size());
        assertEquals(true, result.containsAll(expected));
    }

    @Test
    public void testFindClustersByCellType_sorts2025() {
        ClusterHierarchy clusterHierarchy1 = new ClusterHierarchy();
        clusterHierarchy1.setCellType("celltype");
        clusterHierarchy1.setClusterName(null);
        clusterHierarchy1.setCellTypeOrder(3.2);
        ClusterHierarchy clusterHierarchy2 = new ClusterHierarchy();
        clusterHierarchy2.setCellType("celltype");
        clusterHierarchy2.setClusterName(null);
        clusterHierarchy2.setCellTypeOrder(2.0);
        ClusterHierarchy clusterHierarchy3 = new ClusterHierarchy();
        clusterHierarchy3.setCellType("cluster");
        clusterHierarchy3.setClusterName(null);
        clusterHierarchy3.setCellTypeOrder(1.0);

        List<ClusterHierarchy> hierarchiesRTRP = new ArrayList<>(Arrays.asList(clusterHierarchy1));
        List<ClusterHierarchy> hierarchiesRNA =  new ArrayList<>(Arrays.asList(clusterHierarchy2));
        List<ClusterHierarchy> hierarchiesParent =  new ArrayList<>(Arrays.asList(clusterHierarchy3));

        when(clusterHierarchyRepo.findRTRPByCellTypeOrRegion("cell type")).thenReturn(hierarchiesRTRP);
        when(clusterHierarchyRepo.findRnaSeqByCellTypeOrRegion("cell type")).thenReturn(hierarchiesRNA);
        when(clusterHierarchyRepo.findRTRPParentRegions("cell type")).thenReturn(hierarchiesParent);

        assertEquals(Arrays.asList(clusterHierarchy3, clusterHierarchy2, clusterHierarchy1), service.findClustersByCellType2025("cell type"));
    }

    @Test
    public void testFindClustersByCellType_preservesRnaSeqOrder2025() {
        ClusterHierarchy clusterHierarchy1 = new ClusterHierarchy();
        clusterHierarchy1.setClusterName("Podocyte (<i>degenerative<sup>3</sup></i>)");
        clusterHierarchy1.setCellTypeOrder(1.0);
        ClusterHierarchy clusterHierarchy2 = new ClusterHierarchy();
        clusterHierarchy2.setClusterName("Podocyte");
        clusterHierarchy2.setCellTypeOrder(1.0);

        List<ClusterHierarchy> hierarchiesRNA = new ArrayList<>(Arrays.asList(clusterHierarchy1, clusterHierarchy2));
        List<ClusterHierarchy> hierarchiesRTRP = new ArrayList<>();
        List<ClusterHierarchy> hierarchiesParent = new ArrayList<>();

        when(clusterHierarchyRepo.findRTRPByCellTypeOrRegion("cell type")).thenReturn(hierarchiesRTRP);
        when(clusterHierarchyRepo.findRnaSeqByCellTypeOrRegion("cell type")).thenReturn(hierarchiesRNA);
        when(clusterHierarchyRepo.findRTRPParentRegions("cell type")).thenReturn(hierarchiesParent);

        assertEquals(Arrays.asList(clusterHierarchy1, clusterHierarchy2), service.findClustersByCellType2025("cell type"));
    }

    @Test
    public void testFindDataTypesByClusterNameWhenBothY2025() throws Exception {
        ClusterHierarchy clusterHierarchy = new ClusterHierarchy();
        clusterHierarchy.setIsSingleCellCluster("Y");
        clusterHierarchy.setIsSingleNucCluster("Y");
        clusterHierarchy.setIsRegionalTranscriptomics("N");
        clusterHierarchy.setIsRegionalProteomics("N");
        when(clusterHierarchyRepo.findFirstByClusterOrRegion2025("cluster")).thenReturn(clusterHierarchy);

        List<String> dataTypes = service.findDataTypesByClusterName2025("cluster");

        assertEquals(2, dataTypes.size());
        assertEquals(Arrays.asList(FullDataTypeEnum.SINGLE_CELL.getAbbreviation(),
                FullDataTypeEnum.SINGLE_NUCLEUS.getAbbreviation()), dataTypes);
        verify(clusterHierarchyRepo).findFirstByClusterOrRegion2025("cluster");
    }

    @Test
    public void testFindDataTypesByClusterNameWhenSingleCellY2025() throws Exception {
        ClusterHierarchy clusterHierarchy = new ClusterHierarchy();
        clusterHierarchy.setIsSingleCellCluster("Y");
        clusterHierarchy.setIsSingleNucCluster("N");
        clusterHierarchy.setIsRegionalTranscriptomics("N");
        clusterHierarchy.setIsRegionalProteomics("N");
        when(clusterHierarchyRepo.findFirstByClusterOrRegion2025("cluster")).thenReturn(clusterHierarchy);

        List<String> dataTypes = service.findDataTypesByClusterName2025("cluster");

        assertEquals(1, dataTypes.size());
        assertEquals(Arrays.asList(FullDataTypeEnum.SINGLE_CELL.getAbbreviation()), dataTypes);
        verify(clusterHierarchyRepo).findFirstByClusterOrRegion2025("cluster");
    }

    @Test
    public void testFindDataTypesByClusterNameWhenSingleNucY2025() throws Exception {
        ClusterHierarchy clusterHierarchy = new ClusterHierarchy();
        clusterHierarchy.setIsSingleCellCluster("N");
        clusterHierarchy.setIsSingleNucCluster("y");
        clusterHierarchy.setIsRegionalTranscriptomics("N");
        clusterHierarchy.setIsRegionalProteomics("N");
        when(clusterHierarchyRepo.findFirstByClusterOrRegion2025("cluster")).thenReturn(clusterHierarchy);

        List<String> dataTypes = service.findDataTypesByClusterName2025("cluster");

        assertEquals(1, dataTypes.size());
        assertEquals(Arrays.asList(FullDataTypeEnum.SINGLE_NUCLEUS.getAbbreviation()), dataTypes);
        verify(clusterHierarchyRepo).findFirstByClusterOrRegion2025("cluster");
    }

    @Test
    public void testFindDataTypesByClusterNameWhenNeitherY2025() throws Exception {
        ClusterHierarchy clusterHierarchy = new ClusterHierarchy();
        clusterHierarchy.setIsSingleCellCluster("N");
        clusterHierarchy.setIsSingleNucCluster("N");
        clusterHierarchy.setIsRegionalTranscriptomics("N");
        clusterHierarchy.setIsRegionalProteomics("N");
        when(clusterHierarchyRepo.findFirstByClusterOrRegion2025("cluster")).thenReturn(clusterHierarchy);

        List<String> dataTypes = service.findDataTypesByClusterName2025("cluster");

        assertEquals(0, dataTypes.size());
        verify(clusterHierarchyRepo).findFirstByClusterOrRegion2025("cluster");
    }

    @Test
    public void testFindDataTypesByClusterNameWhenRTY2025() throws Exception {
        ClusterHierarchy clusterHierarchy = new ClusterHierarchy();
        clusterHierarchy.setIsSingleCellCluster("N");
        clusterHierarchy.setIsSingleNucCluster("N");
        clusterHierarchy.setIsRegionalTranscriptomics("Y");
        clusterHierarchy.setIsRegionalProteomics("N");
        when(clusterHierarchyRepo.findFirstByClusterOrRegion2025("cluster")).thenReturn(clusterHierarchy);

        List<String> dataTypes = service.findDataTypesByClusterName2025("cluster");

        assertEquals(1, dataTypes.size());
        verify(clusterHierarchyRepo).findFirstByClusterOrRegion2025("cluster");
    }


    @Test
    public void testFindDataTypesByClusterNameWhenRPY2025() throws Exception {
        ClusterHierarchy clusterHierarchy = new ClusterHierarchy();
        clusterHierarchy.setIsSingleCellCluster("N");
        clusterHierarchy.setIsSingleNucCluster("N");
        clusterHierarchy.setIsRegionalTranscriptomics("N");
        clusterHierarchy.setIsRegionalProteomics("Y");
        when(clusterHierarchyRepo.findFirstByClusterOrRegion2025("cluster")).thenReturn(clusterHierarchy);

        List<String> dataTypes = service.findDataTypesByClusterName2025("cluster");

        assertEquals(1, dataTypes.size());
        assertEquals(Arrays.asList("rp"), dataTypes);
        verify(clusterHierarchyRepo).findFirstByClusterOrRegion2025("cluster");
    }

    @Test
    public void testFindClustersByCellTypeTubulesOrInterstitium2025() throws Exception {
        List<ClusterHierarchy> clusterHierarchies = new ArrayList<>();
        when(clusterHierarchyRepo.findRTRPByCellTypeOrRegion("cell type")).thenReturn(clusterHierarchies);
        when(clusterHierarchyRepo.findRnaSeqByCellTypeOrRegion("cell type")).thenReturn(clusterHierarchies);
        when(clusterHierarchyRepo.findRTRPParentRegions("cell type")).thenReturn(clusterHierarchies);
        List<ClusterHierarchy> clusters = service.findClustersByCellType2025("Tubules");
        assertEquals("Tubulo-interstitium", clusters.get(0).getStructureRegion());
        List<ClusterHierarchy> clusters2 = service.findClustersByCellType2025("Interstitium");
        assertEquals("Tubulo-interstitium", clusters2.get(0).getStructureRegion());
    }

    @Test
    public void testfindClustersByCellType2025RemovesDupes() throws Exception {
        List<ClusterHierarchy> clusterHierarchies = new ArrayList<>();

    }

    @Test
    public void testFindDataTypesByClusterNameTi2025() throws Exception {
        List<String> dataTypes = service.findDataTypesByClusterName2025("Tubulo-interstitium");
        assertTrue(dataTypes.contains(FullDataTypeEnum.REGIONAL_PROTEOMICS.getAbbreviation()));
        assertTrue(dataTypes.contains(FullDataTypeEnum.REGIONAL_TRANSCRIPTOMICS.getAbbreviation()));
        assertFalse(dataTypes.contains(FullDataTypeEnum.SINGLE_CELL.getAbbreviation()));
        assertFalse(dataTypes.contains(FullDataTypeEnum.SINGLE_NUCLEUS.getAbbreviation()));
    }

}
