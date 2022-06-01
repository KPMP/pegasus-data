package org.kpmp.geneExpressionSummary;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.kpmp.datasetSummary.DatasetSummary;
import org.kpmp.datasetSummary.DatasetSummaryService;
import org.kpmp.datasetSummary.DatasetSummaryRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.kpmp.DataTypeEnum;
import org.kpmp.FullDataTypeEnum;
import org.kpmp.TissueTypeEnum;

public class DatasetSummaryTest {
	private DatasetSummaryService datasetSummaryService;
	@Mock
	private DatasetSummaryRepository datasetSummaryRepository;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		datasetSummaryService = new DatasetSummaryService(datasetSummaryRepository);
	}

	@After
	public void tearDown() throws Exception {
		datasetSummaryService = null;
	}

	@Test
	public void testGetGeneDatasetInformation() throws Exception {
		when(datasetSummaryRepository.getDataSummaryCount(TissueTypeEnum.AKI.getParticipantTissueType(), FullDataTypeEnum.SPATIAL_TRANSCRIPTOMICS_FULL.getFull())).thenReturn(Long.valueOf(5));
		when(datasetSummaryRepository.getDataSummaryCount(TissueTypeEnum.CKD.getParticipantTissueType(), FullDataTypeEnum.SPATIAL_TRANSCRIPTOMICS_FULL.getFull())).thenReturn(Long.valueOf(15));
		when(datasetSummaryRepository.getDataSummaryCount(TissueTypeEnum.HEALTHY_REFERENCE.getParticipantTissueType(), FullDataTypeEnum.SPATIAL_TRANSCRIPTOMICS_FULL.getFull())).thenReturn(Long.valueOf(0));
		when(datasetSummaryRepository.getParticipantSummaryCount(FullDataTypeEnum.SPATIAL_TRANSCRIPTOMICS_FULL.getFull())).thenReturn(Long.valueOf(20));

		when(datasetSummaryRepository.getDataSummaryCount(TissueTypeEnum.AKI.getParticipantTissueType(), FullDataTypeEnum.TISSUE_IMAGING_AND_CYTOMETRY_3D_FULL.getFull())).thenReturn(Long.valueOf(5));
		when(datasetSummaryRepository.getDataSummaryCount(TissueTypeEnum.CKD.getParticipantTissueType(), FullDataTypeEnum.TISSUE_IMAGING_AND_CYTOMETRY_3D_FULL.getFull())).thenReturn(Long.valueOf(15));
		when(datasetSummaryRepository.getDataSummaryCount(TissueTypeEnum.HEALTHY_REFERENCE.getParticipantTissueType(), FullDataTypeEnum.TISSUE_IMAGING_AND_CYTOMETRY_3D_FULL.getFull())).thenReturn(Long.valueOf(5));
		when(datasetSummaryRepository.getParticipantSummaryCount(FullDataTypeEnum.TISSUE_IMAGING_AND_CYTOMETRY_3D_FULL.getFull())).thenReturn(Long.valueOf(25));

		when(datasetSummaryRepository.getDataSummaryCount(TissueTypeEnum.AKI.getParticipantTissueType(), FullDataTypeEnum.LIGHT_MICROSCOPIC_WHOLE_SLIDE_IMAGES_FULL.getFull())).thenReturn(Long.valueOf(5));
		when(datasetSummaryRepository.getDataSummaryCount(TissueTypeEnum.CKD.getParticipantTissueType(), FullDataTypeEnum.LIGHT_MICROSCOPIC_WHOLE_SLIDE_IMAGES_FULL.getFull())).thenReturn(Long.valueOf(15));
		when(datasetSummaryRepository.getDataSummaryCount(TissueTypeEnum.HEALTHY_REFERENCE.getParticipantTissueType(), FullDataTypeEnum.LIGHT_MICROSCOPIC_WHOLE_SLIDE_IMAGES_FULL.getFull())).thenReturn(Long.valueOf(2));
		when(datasetSummaryRepository.getParticipantSummaryCount(FullDataTypeEnum.LIGHT_MICROSCOPIC_WHOLE_SLIDE_IMAGES_FULL.getFull())).thenReturn(Long.valueOf(22));

		List<DatasetSummary> result = datasetSummaryService.getSummaryData();

		assertEquals(FullDataTypeEnum.SPATIAL_TRANSCRIPTOMICS_FULL.getFull(), result.get(0).getDataType());
		assertEquals(DataTypeEnum.SPATIAL_TRANSCRIPTOMICS.getAbbreviation(), result.get(0).getDataTypeShort());
		assertEquals(Long.valueOf(5), result.get(0).getAkiCount());
		assertEquals(Long.valueOf(15), result.get(0).getCkdCount());
		assertEquals(Long.valueOf(0), result.get(0).getHrtCount());
		assertEquals(Long.valueOf(20), result.get(0).getParticipantCount());
		
		assertEquals(FullDataTypeEnum.TISSUE_IMAGING_AND_CYTOMETRY_3D_FULL.getFull(), result.get(1).getDataType());
		assertEquals(DataTypeEnum.SPATIAL_TRANSCRIPTOMICS.getAbbreviation(), result.get(0).getDataTypeShort());
		assertEquals(Long.valueOf(5), result.get(1).getAkiCount());
		assertEquals(Long.valueOf(15), result.get(1).getCkdCount());
		assertEquals(Long.valueOf(5), result.get(1).getHrtCount());
		assertEquals(Long.valueOf(25), result.get(1).getParticipantCount());

		assertEquals(FullDataTypeEnum.LIGHT_MICROSCOPIC_WHOLE_SLIDE_IMAGES_FULL.getFull(), result.get(2).getDataType());
		assertEquals(DataTypeEnum.SPATIAL_TRANSCRIPTOMICS.getAbbreviation(), result.get(0).getDataTypeShort());
		assertEquals(Long.valueOf(5), result.get(2).getAkiCount());
		assertEquals(Long.valueOf(15), result.get(2).getCkdCount());
		assertEquals(Long.valueOf(2), result.get(2).getHrtCount());
		assertEquals(Long.valueOf(22), result.get(2).getParticipantCount());
	}
}
