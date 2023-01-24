package org.kpmp.dataSummary;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kpmp.DataTypeEnum;
import org.kpmp.FullDataTypeEnum;
import org.kpmp.TissueTypeEnum;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class DataSummaryServiceTest {
	private DataSummaryService dataSummaryService;
	@Mock
	private DataSummaryRepository dataSummaryRepository;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		dataSummaryService = new DataSummaryService(dataSummaryRepository);
	}

	@After
	public void tearDown() throws Exception {
		MockitoAnnotations.openMocks(this).close();
		dataSummaryService = null;
	}

	@Test
	public void testGetGeneDatasetInformation() throws Exception {
		when(dataSummaryRepository.getDataSummaryCount(TissueTypeEnum.AKI.getParticipantTissueType(),
				FullDataTypeEnum.SPATIAL_TRANSCRIPTOMICS_FULL.getFullName())).thenReturn(Long.valueOf(5));
		when(dataSummaryRepository.getDataSummaryCount(TissueTypeEnum.CKD.getParticipantTissueType(),
				FullDataTypeEnum.SPATIAL_TRANSCRIPTOMICS_FULL.getFullName())).thenReturn(Long.valueOf(15));
		when(dataSummaryRepository.getDataSummaryCount(TissueTypeEnum.HEALTHY_REFERENCE.getParticipantTissueType(),
				FullDataTypeEnum.SPATIAL_TRANSCRIPTOMICS_FULL.getFullName())).thenReturn(Long.valueOf(0));
		when(dataSummaryRepository
				.getParticipantSummaryCount(FullDataTypeEnum.SPATIAL_TRANSCRIPTOMICS_FULL.getFullName()))
						.thenReturn(Long.valueOf(20));

		when(dataSummaryRepository.getDataSummaryCount(TissueTypeEnum.AKI.getParticipantTissueType(),
				FullDataTypeEnum.TISSUE_IMAGING_AND_CYTOMETRY_3D_FULL.getFullName())).thenReturn(Long.valueOf(5));
		when(dataSummaryRepository.getDataSummaryCount(TissueTypeEnum.CKD.getParticipantTissueType(),
				FullDataTypeEnum.TISSUE_IMAGING_AND_CYTOMETRY_3D_FULL.getFullName())).thenReturn(Long.valueOf(15));
		when(dataSummaryRepository.getDataSummaryCount(TissueTypeEnum.HEALTHY_REFERENCE.getParticipantTissueType(),
				FullDataTypeEnum.TISSUE_IMAGING_AND_CYTOMETRY_3D_FULL.getFullName())).thenReturn(Long.valueOf(5));
		when(dataSummaryRepository
				.getParticipantSummaryCount(FullDataTypeEnum.TISSUE_IMAGING_AND_CYTOMETRY_3D_FULL.getFullName()))
						.thenReturn(Long.valueOf(25));

		when(dataSummaryRepository.getDataSummaryCount(TissueTypeEnum.AKI.getParticipantTissueType(),
				FullDataTypeEnum.LIGHT_MICROSCOPIC_WHOLE_SLIDE_IMAGES_FULL.getFullName())).thenReturn(Long.valueOf(5));
		when(dataSummaryRepository.getDataSummaryCount(TissueTypeEnum.CKD.getParticipantTissueType(),
				FullDataTypeEnum.LIGHT_MICROSCOPIC_WHOLE_SLIDE_IMAGES_FULL.getFullName())).thenReturn(Long.valueOf(15));
		when(dataSummaryRepository.getDataSummaryCount(TissueTypeEnum.HEALTHY_REFERENCE.getParticipantTissueType(),
				FullDataTypeEnum.LIGHT_MICROSCOPIC_WHOLE_SLIDE_IMAGES_FULL.getFullName())).thenReturn(Long.valueOf(2));
		when(dataSummaryRepository
				.getParticipantSummaryCount(FullDataTypeEnum.LIGHT_MICROSCOPIC_WHOLE_SLIDE_IMAGES_FULL.getFullName()))
						.thenReturn(Long.valueOf(22));

		when(dataSummaryRepository.getDataSummaryCount(TissueTypeEnum.AKI.getParticipantTissueType(),
				FullDataTypeEnum.CODEX_FULL.getFullName())).thenReturn(Long.valueOf(5));
		when(dataSummaryRepository.getDataSummaryCount(TissueTypeEnum.CKD.getParticipantTissueType(),
				FullDataTypeEnum.CODEX_FULL.getFullName())).thenReturn(Long.valueOf(15));
		when(dataSummaryRepository.getDataSummaryCount(TissueTypeEnum.HEALTHY_REFERENCE.getParticipantTissueType(),
				FullDataTypeEnum.CODEX_FULL.getFullName())).thenReturn(Long.valueOf(2));
		when(dataSummaryRepository.getParticipantSummaryCount(FullDataTypeEnum.CODEX_FULL.getFullName()))
				.thenReturn(Long.valueOf(22));

		when(dataSummaryRepository.getDataSummaryLinkCount(TissueTypeEnum.AKI.getParticipantTissueType(),
				FullDataTypeEnum.SPATIAL_LIPIDOMICS_FULL.getFullName())).thenReturn(Long.valueOf(5));
		when(dataSummaryRepository.getDataSummaryLinkCount(TissueTypeEnum.CKD.getParticipantTissueType(),
				FullDataTypeEnum.SPATIAL_LIPIDOMICS_FULL.getFullName())).thenReturn(Long.valueOf(15));
		when(dataSummaryRepository.getDataSummaryLinkCount(TissueTypeEnum.HEALTHY_REFERENCE.getParticipantTissueType(),
				FullDataTypeEnum.SPATIAL_LIPIDOMICS_FULL.getFullName())).thenReturn(Long.valueOf(2));
		when(dataSummaryRepository
				.getParticipantSummaryLinkCount(FullDataTypeEnum.SPATIAL_LIPIDOMICS_FULL.getFullName()))
						.thenReturn(Long.valueOf(22));

		when(dataSummaryRepository.getDataSummaryLinkCount(TissueTypeEnum.AKI.getParticipantTissueType(),
				FullDataTypeEnum.SPATIAL_METABOLOMICS_FULL.getFullName())).thenReturn(Long.valueOf(5));
		when(dataSummaryRepository.getDataSummaryLinkCount(TissueTypeEnum.CKD.getParticipantTissueType(),
				FullDataTypeEnum.SPATIAL_METABOLOMICS_FULL.getFullName())).thenReturn(Long.valueOf(15));
		when(dataSummaryRepository.getDataSummaryLinkCount(TissueTypeEnum.HEALTHY_REFERENCE.getParticipantTissueType(),
				FullDataTypeEnum.SPATIAL_METABOLOMICS_FULL.getFullName())).thenReturn(Long.valueOf(2));
		when(dataSummaryRepository
				.getParticipantSummaryLinkCount(FullDataTypeEnum.SPATIAL_METABOLOMICS_FULL.getFullName()))
						.thenReturn(Long.valueOf(22));

		when(dataSummaryRepository.getDataSummaryLinkCount(TissueTypeEnum.AKI.getParticipantTissueType(),
				FullDataTypeEnum.SPATIAL_NGLYCOMICS_FULL.getFullName())).thenReturn(Long.valueOf(5));
		when(dataSummaryRepository.getDataSummaryLinkCount(TissueTypeEnum.CKD.getParticipantTissueType(),
				FullDataTypeEnum.SPATIAL_NGLYCOMICS_FULL.getFullName())).thenReturn(Long.valueOf(15));
		when(dataSummaryRepository.getDataSummaryLinkCount(TissueTypeEnum.HEALTHY_REFERENCE.getParticipantTissueType(),
				FullDataTypeEnum.SPATIAL_NGLYCOMICS_FULL.getFullName())).thenReturn(Long.valueOf(2));
		when(dataSummaryRepository
				.getParticipantSummaryLinkCount(FullDataTypeEnum.SPATIAL_NGLYCOMICS_FULL.getFullName()))
						.thenReturn(Long.valueOf(22));

		List<DataTypeSummary> result = dataSummaryService.getSummaryData();

		assertEquals(FullDataTypeEnum.SPATIAL_TRANSCRIPTOMICS_FULL.getFullName(), result.get(0).getDataType());
		assertEquals(DataTypeEnum.SPATIAL_TRANSCRIPTOMICS.getAbbreviation(), result.get(0).getDataTypeShort());
		assertEquals(Long.valueOf(5), result.get(0).getAkiCount());
		assertEquals(Long.valueOf(15), result.get(0).getCkdCount());
		assertEquals(Long.valueOf(0), result.get(0).getHrtCount());
		assertEquals(Long.valueOf(20), result.get(0).getParticipantCount());

		assertEquals(FullDataTypeEnum.TISSUE_IMAGING_AND_CYTOMETRY_3D_FULL.getFullName(), result.get(1).getDataType());
		assertEquals(DataTypeEnum.SPATIAL_TRANSCRIPTOMICS.getAbbreviation(), result.get(0).getDataTypeShort());
		assertEquals(Long.valueOf(5), result.get(1).getAkiCount());
		assertEquals(Long.valueOf(15), result.get(1).getCkdCount());
		assertEquals(Long.valueOf(5), result.get(1).getHrtCount());
		assertEquals(Long.valueOf(25), result.get(1).getParticipantCount());

		assertEquals(FullDataTypeEnum.LIGHT_MICROSCOPIC_WHOLE_SLIDE_IMAGES_FULL.getFullName(),
				result.get(2).getDataType());
		assertEquals(DataTypeEnum.SPATIAL_TRANSCRIPTOMICS.getAbbreviation(), result.get(0).getDataTypeShort());
		assertEquals(Long.valueOf(5), result.get(2).getAkiCount());
		assertEquals(Long.valueOf(15), result.get(2).getCkdCount());
		assertEquals(Long.valueOf(2), result.get(2).getHrtCount());
		assertEquals(Long.valueOf(22), result.get(2).getParticipantCount());

		assertEquals(FullDataTypeEnum.CODEX_FULL.getFullName(), result.get(3).getDataType());
		assertEquals(DataTypeEnum.SPATIAL_TRANSCRIPTOMICS.getAbbreviation(), result.get(0).getDataTypeShort());
		assertEquals(Long.valueOf(5), result.get(3).getAkiCount());
		assertEquals(Long.valueOf(15), result.get(3).getCkdCount());
		assertEquals(Long.valueOf(2), result.get(3).getHrtCount());
		assertEquals(Long.valueOf(22), result.get(3).getParticipantCount());

		assertEquals(FullDataTypeEnum.SPATIAL_LIPIDOMICS_FULL.getFullName(), result.get(4).getDataType());
		assertEquals(DataTypeEnum.SPATIAL_TRANSCRIPTOMICS.getAbbreviation(), result.get(0).getDataTypeShort());
		assertEquals(Long.valueOf(5), result.get(4).getAkiCount());
		assertEquals(Long.valueOf(15), result.get(4).getCkdCount());
		assertEquals(Long.valueOf(2), result.get(4).getHrtCount());
		assertEquals(Long.valueOf(22), result.get(4).getParticipantCount());

		assertEquals(FullDataTypeEnum.SPATIAL_METABOLOMICS_FULL.getFullName(), result.get(5).getDataType());
		assertEquals(DataTypeEnum.SPATIAL_TRANSCRIPTOMICS.getAbbreviation(), result.get(0).getDataTypeShort());
		assertEquals(Long.valueOf(5), result.get(5).getAkiCount());
		assertEquals(Long.valueOf(15), result.get(5).getCkdCount());
		assertEquals(Long.valueOf(2), result.get(5).getHrtCount());
		assertEquals(Long.valueOf(22), result.get(5).getParticipantCount());

		assertEquals(FullDataTypeEnum.SPATIAL_NGLYCOMICS_FULL.getFullName(), result.get(6).getDataType());
		assertEquals(DataTypeEnum.SPATIAL_TRANSCRIPTOMICS.getAbbreviation(), result.get(0).getDataTypeShort());
		assertEquals(Long.valueOf(5), result.get(6).getAkiCount());
		assertEquals(Long.valueOf(15), result.get(6).getCkdCount());
		assertEquals(Long.valueOf(2), result.get(6).getHrtCount());
		assertEquals(Long.valueOf(22), result.get(6).getParticipantCount());
	}
}
