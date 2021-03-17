package org.kpmp.umap;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kpmp.cluster.ClusterService;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UmapDataServiceTest {

	private static final double DELTA_FOR_DOUBLE_ASSERTIONS = 0.000001;
	@Mock
	private SingleCellUmapPointRepository singleCellRepo;
	@Mock
	private SingleNucleusUmapPointRepository singleNucRepo;
	@Mock
	private ClusterService clusterService;
	private UmapDataService service;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		service = new UmapDataService(singleCellRepo, singleNucRepo, clusterService);
	}

	@After
	public void tearDown() throws Exception {
		service = null;
	}

	@Test
	public void testgetSingleCellUmapPoints() {
		HashMap<String, String> clusterToColorMapping = new HashMap<>();
		clusterToColorMapping.put("abbr1", "red");
		clusterToColorMapping.put("abbr2", "green");
		SingleCellUmapPoint scPoint1 = new SingleCellUmapPoint();
		scPoint1.setCellTypeSubClassL2("abbr1");
		scPoint1.setUmapX(12d);
		scPoint1.setUmapY(24d);
		SingleCellUmapPoint scPoint2 = new SingleCellUmapPoint();
		scPoint2.setCellTypeSubClassL2("abbr2");
		scPoint2.setUmapX(36d);
		scPoint2.setUmapY(48d);
		List<SingleCellUmapPoint> scPoints = Arrays.asList(scPoint1, scPoint2);
		when(singleCellRepo.findAll()).thenReturn(scPoints);
		when(clusterService.getClusterToColorMapping()).thenReturn(clusterToColorMapping);

		List<UmapPoint> umapPoints = service.getSingleCellUmapPoints();

		assertEquals(2, umapPoints.size());
		for (UmapPoint umapPoint : umapPoints) {
			if (umapPoint.getCluster() == "abbr1") {
				assertEquals("red", umapPoint.getColor());
				assertEquals(12d, umapPoint.getxCoordinate(), DELTA_FOR_DOUBLE_ASSERTIONS);
				assertEquals(24d, umapPoint.getyCoordinate(), DELTA_FOR_DOUBLE_ASSERTIONS);
			} else if (umapPoint.getCluster() == "abbr2") {
				assertEquals("green", umapPoint.getColor());
				assertEquals(36d, umapPoint.getxCoordinate(), DELTA_FOR_DOUBLE_ASSERTIONS);
				assertEquals(48d, umapPoint.getyCoordinate(), DELTA_FOR_DOUBLE_ASSERTIONS);
			} else {
				fail("Unknown additional cluster: " + umapPoint.getCluster());
			}
		}
	}

	@Test
	public void testgetUmapPointsWhenSingleNuc() {
		HashMap<String, String> clusterToColorMapping = new HashMap<>();
		clusterToColorMapping.put("abbr1", "red");
		clusterToColorMapping.put("abbr2", "green");
		SingleNucleusUmapPoint scPoint1 = new SingleNucleusUmapPoint();
		scPoint1.setCellTypeSubClassL2("abbr1");
		scPoint1.setUmapX(12d);
		scPoint1.setUmapY(24d);
		SingleNucleusUmapPoint scPoint2 = new SingleNucleusUmapPoint();
		scPoint2.setCellTypeSubClassL2("abbr2");
		scPoint2.setUmapX(36d);
		scPoint2.setUmapY(48d);
		List<SingleNucleusUmapPoint> scPoints = Arrays.asList(scPoint1, scPoint2);
		when(singleNucRepo.findAll()).thenReturn(scPoints);
		when(clusterService.getClusterToColorMapping()).thenReturn(clusterToColorMapping);

		List<UmapPoint> umapPoints = service.getSingleNucUmapPoints();

		assertEquals(2, umapPoints.size());
		for (UmapPoint umapPoint : umapPoints) {
			if (umapPoint.getCluster() == "abbr1") {
				assertEquals("red", umapPoint.getColor());
				assertEquals(12d, umapPoint.getxCoordinate(), DELTA_FOR_DOUBLE_ASSERTIONS);
				assertEquals(24d, umapPoint.getyCoordinate(), DELTA_FOR_DOUBLE_ASSERTIONS);
			} else if (umapPoint.getCluster() == "abbr2") {
				assertEquals("green", umapPoint.getColor());
				assertEquals(36d, umapPoint.getxCoordinate(), DELTA_FOR_DOUBLE_ASSERTIONS);
				assertEquals(48d, umapPoint.getyCoordinate(), DELTA_FOR_DOUBLE_ASSERTIONS);
			} else {
				fail("Unknown additional cluster: " + umapPoint.getCluster());
			}
		}
	}

}
