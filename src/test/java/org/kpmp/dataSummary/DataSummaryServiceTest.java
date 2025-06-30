package org.kpmp.dataSummary;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kpmp.FullDataTypeEnum;
import org.kpmp.EnrollmentCategoryEnum;
import org.kpmp.file.ARFileInfoService;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

public class DataSummaryServiceTest {
	private DataSummaryService dataSummaryService;
	@Mock
	private DataSummaryRepository dataSummaryRepository;
	@Mock
	private AtlasRepoSummaryRepository atlasRepoSummaryRepository;
	@Mock
	private ARFileInfoService fileInfoService;

	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		dataSummaryService = new DataSummaryService(dataSummaryRepository, atlasRepoSummaryRepository, fileInfoService);
		ReflectionTestUtils.setField(dataSummaryService, "CLINICAL_STUDY_DATA", "Clinical Study Data");
        ReflectionTestUtils.setField(dataSummaryService, "BIOMARKERS", "Biomarkers");
		ReflectionTestUtils.setField(dataSummaryService, "BIOMARKER", "Biomarker");
	}

	@AfterEach
	public void tearDown() throws Exception {
		MockitoAnnotations.openMocks(this).close();
		dataSummaryService = null;
	}

	@Test
	public void testGetAtlasRepoSummary() throws Exception {
		List<ExperimentalStrategyValue> strategies = new ArrayList<>(
			Arrays.asList(
				new ExperimentalStrategyValue(),
				new ExperimentalStrategyValue(),
				new ExperimentalStrategyValue(),
				new ExperimentalStrategyValue()
			)
		);

		strategies.get(0).setExperimentalStrategy("abcd");
		strategies.get(0).setDataCategory("Biomarker");
		strategies.get(0).setDataType("datatype");

		strategies.get(1).setExperimentalStrategy("");
		strategies.get(1).setDataCategory("category");
		strategies.get(1).setDataType("Clinical Study Data");
		
		strategies.get(2).setExperimentalStrategy("strategy1");
		strategies.get(2).setDataCategory("category1");
		strategies.get(2).setDataType("datatype");
		
		strategies.get(3).setExperimentalStrategy("strategy2");
		strategies.get(3).setDataCategory("category2");
		strategies.get(3).setDataType("datatype");

		when(atlasRepoSummaryRepository.findAll()).thenReturn(strategies);
		when(dataSummaryRepository.getRepoBiomarkerSummaryCount(EnrollmentCategoryEnum.AKI.getParticipantEnrollmentCategory())).thenReturn(Long.valueOf(1));
		when(dataSummaryRepository.getRepoBiomarkerSummaryCount(EnrollmentCategoryEnum.CKD.getParticipantEnrollmentCategory())).thenReturn(Long.valueOf(2));
		when(dataSummaryRepository.getRepoBiomarkerSummaryCount(EnrollmentCategoryEnum.HEALTHY_REFERENCE.getParticipantEnrollmentCategory())).thenReturn(Long.valueOf(3));
		when(dataSummaryRepository.getRepoBiomarkerSummaryCount(EnrollmentCategoryEnum.DMR.getParticipantEnrollmentCategory())).thenReturn(4l);

		when(dataSummaryRepository.getRepoDataSummaryCount(EnrollmentCategoryEnum.AKI.getParticipantEnrollmentCategory(),
				"")).thenReturn(Long.valueOf(5));
		when(dataSummaryRepository.getRepoDataSummaryCount(EnrollmentCategoryEnum.CKD.getParticipantEnrollmentCategory(),
				"")).thenReturn(Long.valueOf(6));
		when(dataSummaryRepository.getRepoDataSummaryCount(EnrollmentCategoryEnum.HEALTHY_REFERENCE.getParticipantEnrollmentCategory(),
				"")).thenReturn(Long.valueOf(7));
		when(dataSummaryRepository.getRepoDataSummaryCount(EnrollmentCategoryEnum.DMR.getParticipantEnrollmentCategory(),
				"")).thenReturn(8l);

		when(dataSummaryRepository.getRepoDataSummaryCount(EnrollmentCategoryEnum.AKI.getParticipantEnrollmentCategory(),
				"strategy1")).thenReturn(Long.valueOf(9));
		when(dataSummaryRepository.getRepoDataSummaryCount(EnrollmentCategoryEnum.CKD.getParticipantEnrollmentCategory(),
				"strategy1")).thenReturn(Long.valueOf(10));
		when(dataSummaryRepository.getRepoDataSummaryCount(EnrollmentCategoryEnum.HEALTHY_REFERENCE.getParticipantEnrollmentCategory(),
				"strategy1")).thenReturn(Long.valueOf(11));
		when(dataSummaryRepository.getRepoDataSummaryCount(EnrollmentCategoryEnum.DMR.getParticipantEnrollmentCategory(),
				"strategy1")).thenReturn(12l);

		when(dataSummaryRepository.getRepoDataSummaryCount(EnrollmentCategoryEnum.AKI.getParticipantEnrollmentCategory(),
				"strategy2")).thenReturn(Long.valueOf(13));
		when(dataSummaryRepository.getRepoDataSummaryCount(EnrollmentCategoryEnum.CKD.getParticipantEnrollmentCategory(),
				"strategy2")).thenReturn(Long.valueOf(14));
		when(dataSummaryRepository.getRepoDataSummaryCount(EnrollmentCategoryEnum.HEALTHY_REFERENCE.getParticipantEnrollmentCategory(),
				"strategy2")).thenReturn(Long.valueOf(15));
		when(dataSummaryRepository.getRepoDataSummaryCount(EnrollmentCategoryEnum.DMR.getParticipantEnrollmentCategory(),
				"strategy2")).thenReturn(16l);

		List<AtlasRepoSummaryRow> result = dataSummaryService.getAtlasRepoSummary().getSummaryRows();

		assertEquals("Biomarkers", result.get(0).getOmicsType());
		assertEquals(Long.valueOf(1), result.get(0).getAkiCount());
		assertEquals(Long.valueOf(2), result.get(0).getCkdCount());
		assertEquals(Long.valueOf(3), result.get(0).getHrtCount());
		assertEquals(Long.valueOf(4), result.get(0).getDmrCount());

		assertEquals("Clinical Study Data", result.get(1).getOmicsType());
		assertEquals(Long.valueOf(5), result.get(1).getAkiCount());
		assertEquals(Long.valueOf(6), result.get(1).getCkdCount());
		assertEquals(Long.valueOf(7), result.get(1).getHrtCount());
		assertEquals(Long.valueOf(8), result.get(1).getDmrCount());

		assertEquals("strategy1", result.get(2).getOmicsType());
		assertEquals(Long.valueOf(9), result.get(2).getAkiCount());
		assertEquals(Long.valueOf(10), result.get(2).getCkdCount());
		assertEquals(Long.valueOf(11), result.get(2).getHrtCount());
		assertEquals(Long.valueOf(12), result.get(2).getDmrCount());

		assertEquals("strategy2", result.get(3).getOmicsType());
		assertEquals(Long.valueOf(13), result.get(3).getAkiCount());
		assertEquals(Long.valueOf(14), result.get(3).getCkdCount());
		assertEquals(Long.valueOf(15), result.get(3).getHrtCount());
		assertEquals(Long.valueOf(16), result.get(3).getDmrCount());
	}

	@Test
	public void testGetGeneDatasetInformation() throws Exception {
		when(dataSummaryRepository.getDataSummaryCount(EnrollmentCategoryEnum.AKI.getParticipantEnrollmentCategory(),
				FullDataTypeEnum.SPATIAL_TRANSCRIPTOMICS.getFullName())).thenReturn(Long.valueOf(5));
		when(dataSummaryRepository.getDataSummaryCount(EnrollmentCategoryEnum.CKD.getParticipantEnrollmentCategory(),
				FullDataTypeEnum.SPATIAL_TRANSCRIPTOMICS.getFullName())).thenReturn(Long.valueOf(15));
		when(dataSummaryRepository.getDataSummaryCount(EnrollmentCategoryEnum.HEALTHY_REFERENCE.getParticipantEnrollmentCategory(),
				FullDataTypeEnum.SPATIAL_TRANSCRIPTOMICS.getFullName())).thenReturn(Long.valueOf(0));
		when(dataSummaryRepository.getDataSummaryCount(EnrollmentCategoryEnum.DMR.getParticipantEnrollmentCategory(),
				FullDataTypeEnum.SPATIAL_TRANSCRIPTOMICS.getFullName())).thenReturn(88l);
		when(dataSummaryRepository
				.getParticipantSummaryCount(FullDataTypeEnum.SPATIAL_TRANSCRIPTOMICS.getFullName()))
						.thenReturn(Long.valueOf(20));

		when(dataSummaryRepository.getDataSummaryCount(EnrollmentCategoryEnum.AKI.getParticipantEnrollmentCategory(),
				FullDataTypeEnum.TISSUE_IMAGING_AND_CYTOMETRY_3D.getFullName())).thenReturn(Long.valueOf(5));
		when(dataSummaryRepository.getDataSummaryCount(EnrollmentCategoryEnum.CKD.getParticipantEnrollmentCategory(),
				FullDataTypeEnum.TISSUE_IMAGING_AND_CYTOMETRY_3D.getFullName())).thenReturn(Long.valueOf(15));
		when(dataSummaryRepository.getDataSummaryCount(EnrollmentCategoryEnum.HEALTHY_REFERENCE.getParticipantEnrollmentCategory(),
				FullDataTypeEnum.TISSUE_IMAGING_AND_CYTOMETRY_3D.getFullName())).thenReturn(Long.valueOf(5));
		when(dataSummaryRepository.getDataSummaryCount(EnrollmentCategoryEnum.DMR.getParticipantEnrollmentCategory(),
				FullDataTypeEnum.TISSUE_IMAGING_AND_CYTOMETRY_3D.getFullName())).thenReturn(88l);
		when(dataSummaryRepository
				.getParticipantSummaryCount(FullDataTypeEnum.TISSUE_IMAGING_AND_CYTOMETRY_3D.getFullName()))
						.thenReturn(Long.valueOf(25));

		when(dataSummaryRepository.getDataSummaryCount(EnrollmentCategoryEnum.AKI.getParticipantEnrollmentCategory(),
				FullDataTypeEnum.LIGHT_MICROSCOPIC_WHOLE_SLIDE_IMAGES.getFullName())).thenReturn(Long.valueOf(5));
		when(dataSummaryRepository.getDataSummaryCount(EnrollmentCategoryEnum.CKD.getParticipantEnrollmentCategory(),
				FullDataTypeEnum.LIGHT_MICROSCOPIC_WHOLE_SLIDE_IMAGES.getFullName())).thenReturn(Long.valueOf(15));
		when(dataSummaryRepository.getDataSummaryCount(EnrollmentCategoryEnum.HEALTHY_REFERENCE.getParticipantEnrollmentCategory(),
				FullDataTypeEnum.LIGHT_MICROSCOPIC_WHOLE_SLIDE_IMAGES.getFullName())).thenReturn(Long.valueOf(2));
		when(dataSummaryRepository.getDataSummaryCount(EnrollmentCategoryEnum.DMR.getParticipantEnrollmentCategory(),
				FullDataTypeEnum.LIGHT_MICROSCOPIC_WHOLE_SLIDE_IMAGES.getFullName())).thenReturn(88l);
		when(dataSummaryRepository
				.getParticipantSummaryCount(FullDataTypeEnum.LIGHT_MICROSCOPIC_WHOLE_SLIDE_IMAGES.getFullName()))
						.thenReturn(Long.valueOf(22));

		when(dataSummaryRepository.getDataSummaryCount(EnrollmentCategoryEnum.AKI.getParticipantEnrollmentCategory(),
				FullDataTypeEnum.CODEX.getFullName())).thenReturn(Long.valueOf(5));
		when(dataSummaryRepository.getDataSummaryCount(EnrollmentCategoryEnum.CKD.getParticipantEnrollmentCategory(),
				FullDataTypeEnum.CODEX.getFullName())).thenReturn(Long.valueOf(15));
		when(dataSummaryRepository.getDataSummaryCount(EnrollmentCategoryEnum.HEALTHY_REFERENCE.getParticipantEnrollmentCategory(),
				FullDataTypeEnum.CODEX.getFullName())).thenReturn(Long.valueOf(2));
		when(dataSummaryRepository.getDataSummaryCount(EnrollmentCategoryEnum.DMR.getParticipantEnrollmentCategory(),
				FullDataTypeEnum.CODEX.getFullName())).thenReturn(88l);
		when(dataSummaryRepository.getParticipantSummaryCount(FullDataTypeEnum.CODEX.getFullName()))
				.thenReturn(Long.valueOf(22));

		when(dataSummaryRepository.getDataSummaryLinkCount(EnrollmentCategoryEnum.AKI.getParticipantEnrollmentCategory(),
				FullDataTypeEnum.SPATIAL_LIPIDOMICS.getFullName())).thenReturn(Long.valueOf(5));
		when(dataSummaryRepository.getDataSummaryLinkCount(EnrollmentCategoryEnum.CKD.getParticipantEnrollmentCategory(),
				FullDataTypeEnum.SPATIAL_LIPIDOMICS.getFullName())).thenReturn(Long.valueOf(15));
		when(dataSummaryRepository.getDataSummaryLinkCount(EnrollmentCategoryEnum.HEALTHY_REFERENCE.getParticipantEnrollmentCategory(),
				FullDataTypeEnum.SPATIAL_LIPIDOMICS.getFullName())).thenReturn(Long.valueOf(2));
		when(dataSummaryRepository.getDataSummaryLinkCount(EnrollmentCategoryEnum.DMR.getParticipantEnrollmentCategory(),
				FullDataTypeEnum.SPATIAL_LIPIDOMICS.getFullName())).thenReturn(Long.valueOf(88));
		when(dataSummaryRepository
				.getParticipantSummaryLinkCount(FullDataTypeEnum.SPATIAL_LIPIDOMICS.getFullName()))
						.thenReturn(Long.valueOf(22));

		when(dataSummaryRepository.getDataSummaryLinkCount(EnrollmentCategoryEnum.AKI.getParticipantEnrollmentCategory(),
				FullDataTypeEnum.SPATIAL_METABOLOMICS.getFullName())).thenReturn(Long.valueOf(5));
		when(dataSummaryRepository.getDataSummaryLinkCount(EnrollmentCategoryEnum.CKD.getParticipantEnrollmentCategory(),
				FullDataTypeEnum.SPATIAL_METABOLOMICS.getFullName())).thenReturn(Long.valueOf(15));
		when(dataSummaryRepository.getDataSummaryLinkCount(EnrollmentCategoryEnum.HEALTHY_REFERENCE.getParticipantEnrollmentCategory(),
				FullDataTypeEnum.SPATIAL_METABOLOMICS.getFullName())).thenReturn(Long.valueOf(2));
		when(dataSummaryRepository.getDataSummaryLinkCount(EnrollmentCategoryEnum.DMR.getParticipantEnrollmentCategory(),
				FullDataTypeEnum.SPATIAL_METABOLOMICS.getFullName())).thenReturn(Long.valueOf(88));
		when(dataSummaryRepository
				.getParticipantSummaryLinkCount(FullDataTypeEnum.SPATIAL_METABOLOMICS.getFullName()))
						.thenReturn(Long.valueOf(22));

		when(dataSummaryRepository.getDataSummaryLinkCount(EnrollmentCategoryEnum.AKI.getParticipantEnrollmentCategory(),
				FullDataTypeEnum.SPATIAL_NGLYCOMICS.getFullName())).thenReturn(Long.valueOf(5));
		when(dataSummaryRepository.getDataSummaryLinkCount(EnrollmentCategoryEnum.CKD.getParticipantEnrollmentCategory(),
				FullDataTypeEnum.SPATIAL_NGLYCOMICS.getFullName())).thenReturn(Long.valueOf(15));
		when(dataSummaryRepository.getDataSummaryLinkCount(EnrollmentCategoryEnum.HEALTHY_REFERENCE.getParticipantEnrollmentCategory(),
				FullDataTypeEnum.SPATIAL_NGLYCOMICS.getFullName())).thenReturn(Long.valueOf(2));
		when(dataSummaryRepository.getDataSummaryLinkCount(EnrollmentCategoryEnum.DMR.getParticipantEnrollmentCategory(),
				FullDataTypeEnum.SPATIAL_NGLYCOMICS.getFullName())).thenReturn(Long.valueOf(88));
		when(dataSummaryRepository
				.getParticipantSummaryLinkCount(FullDataTypeEnum.SPATIAL_NGLYCOMICS.getFullName()))
						.thenReturn(Long.valueOf(22));

		List<DataTypeSummary> result = dataSummaryService.getSummaryData();

		assertEquals(FullDataTypeEnum.SPATIAL_TRANSCRIPTOMICS.getFullName(), result.get(0).getDataType());
		assertEquals(FullDataTypeEnum.SPATIAL_TRANSCRIPTOMICS.getAbbreviation(), result.get(0).getDataTypeShort());
		assertEquals(Long.valueOf(5), result.get(0).getAkiCount());
		assertEquals(Long.valueOf(15), result.get(0).getCkdCount());
		assertEquals(Long.valueOf(0), result.get(0).getHrtCount());
		assertEquals(Long.valueOf(88), result.get(0).getDmrCount());
		assertEquals(Long.valueOf(20), result.get(0).getParticipantCount());

		assertEquals(FullDataTypeEnum.TISSUE_IMAGING_AND_CYTOMETRY_3D.getFullName(), result.get(1).getDataType());
		assertEquals(FullDataTypeEnum.SPATIAL_TRANSCRIPTOMICS.getAbbreviation(), result.get(0).getDataTypeShort());
		assertEquals(Long.valueOf(5), result.get(1).getAkiCount());
		assertEquals(Long.valueOf(15), result.get(1).getCkdCount());
		assertEquals(Long.valueOf(5), result.get(1).getHrtCount());
		assertEquals(Long.valueOf(88), result.get(1).getDmrCount());
		assertEquals(Long.valueOf(25), result.get(1).getParticipantCount());

		assertEquals(FullDataTypeEnum.LIGHT_MICROSCOPIC_WHOLE_SLIDE_IMAGES.getFullName(),
				result.get(2).getDataType());
		assertEquals(FullDataTypeEnum.SPATIAL_TRANSCRIPTOMICS.getAbbreviation(), result.get(0).getDataTypeShort());
		assertEquals(Long.valueOf(5), result.get(2).getAkiCount());
		assertEquals(Long.valueOf(15), result.get(2).getCkdCount());
		assertEquals(Long.valueOf(2), result.get(2).getHrtCount());
		assertEquals(Long.valueOf(88), result.get(2).getDmrCount());
		assertEquals(Long.valueOf(22), result.get(2).getParticipantCount());

		assertEquals(FullDataTypeEnum.CODEX.getFullName(), result.get(3).getDataType());
		assertEquals(FullDataTypeEnum.SPATIAL_TRANSCRIPTOMICS.getAbbreviation(), result.get(0).getDataTypeShort());
		assertEquals(Long.valueOf(5), result.get(3).getAkiCount());
		assertEquals(Long.valueOf(15), result.get(3).getCkdCount());
		assertEquals(Long.valueOf(2), result.get(3).getHrtCount());
		assertEquals(Long.valueOf(88), result.get(3).getDmrCount());
		assertEquals(Long.valueOf(22), result.get(3).getParticipantCount());

		assertEquals(FullDataTypeEnum.SPATIAL_LIPIDOMICS.getFullName(), result.get(4).getDataType());
		assertEquals(FullDataTypeEnum.SPATIAL_TRANSCRIPTOMICS.getAbbreviation(), result.get(0).getDataTypeShort());
		assertEquals(Long.valueOf(5), result.get(4).getAkiCount());
		assertEquals(Long.valueOf(15), result.get(4).getCkdCount());
		assertEquals(Long.valueOf(2), result.get(4).getHrtCount());
		assertEquals(Long.valueOf(88), result.get(4).getDmrCount());
		assertEquals(Long.valueOf(22), result.get(4).getParticipantCount());

		assertEquals(FullDataTypeEnum.SPATIAL_METABOLOMICS.getFullName(), result.get(5).getDataType());
		assertEquals(FullDataTypeEnum.SPATIAL_TRANSCRIPTOMICS.getAbbreviation(), result.get(0).getDataTypeShort());
		assertEquals(Long.valueOf(5), result.get(5).getAkiCount());
		assertEquals(Long.valueOf(15), result.get(5).getCkdCount());
		assertEquals(Long.valueOf(2), result.get(5).getHrtCount());
		assertEquals(Long.valueOf(88), result.get(5).getDmrCount());
		assertEquals(Long.valueOf(22), result.get(5).getParticipantCount());

		assertEquals(FullDataTypeEnum.SPATIAL_NGLYCOMICS.getFullName(), result.get(6).getDataType());
		assertEquals(FullDataTypeEnum.SPATIAL_TRANSCRIPTOMICS.getAbbreviation(), result.get(0).getDataTypeShort());
		assertEquals(Long.valueOf(5), result.get(6).getAkiCount());
		assertEquals(Long.valueOf(15), result.get(6).getCkdCount());
		assertEquals(Long.valueOf(2), result.get(6).getHrtCount());
		assertEquals(Long.valueOf(88), result.get(6).getDmrCount());
		assertEquals(Long.valueOf(22), result.get(6).getParticipantCount());
	}
}
