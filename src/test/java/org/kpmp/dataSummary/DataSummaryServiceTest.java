package org.kpmp.dataSummary;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kpmp.DataTypeEnum;
import org.kpmp.FullDataTypeEnum;
import org.kpmp.TissueTypeEnum;
import org.kpmp.file.ARFileInfoService;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class DataSummaryServiceTest {
	private DataSummaryService dataSummaryService;
	@Mock
	private DataSummaryRepository dataSummaryRepository;
	@Mock
	private AtlasRepoSummaryRepository atlasRepoSummaryRepository;
	@Mock
	private ARFileInfoService fileInfoService;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		dataSummaryService = new DataSummaryService(dataSummaryRepository, atlasRepoSummaryRepository, fileInfoService);
	}

	@After
	public void tearDown() throws Exception {
		MockitoAnnotations.openMocks(this).close();
		dataSummaryService = null;
	}

	@Test
	public void testGetAtlasRepoSummary() throws Exception {
		ExperimentalStrategyValue clinicalData = new ExperimentalStrategyValue();
		clinicalData.setDataCategory("stuff");
		clinicalData.setExperimentalStrategy("");
		clinicalData.setDataType("Clinical Study Data");
		clinicalData.setCount(1);
		clinicalData.setAccess("open");
		ExperimentalStrategyValue biomarker1 = new ExperimentalStrategyValue();
		biomarker1.setDataCategory("Biomarker");
		biomarker1.setExperimentalStrategy("something");
		biomarker1.setDataType("stuff");
		biomarker1.setDataCategory("Biomarker");
		biomarker1.setCount(5);
		biomarker1.setAccess("open");
		ExperimentalStrategyValue biomarker2 = new ExperimentalStrategyValue();
		biomarker2.setDataCategory("Biomarker");
		biomarker2.setDataType("more stuff");
		biomarker2.setExperimentalStrategy("something else");
		biomarker2.setDataCategory("Biomarker");
		biomarker2.setCount(10);
		biomarker2.setAccess("open");
		ExperimentalStrategyValue other1 = new ExperimentalStrategyValue();
		other1.setDataCategory("data category");
		other1.setDataType("different stuff");
		other1.setExperimentalStrategy("strategy1");
		other1.setCount(10);
		other1.setAccess("open");
		ExperimentalStrategyValue other2 = new ExperimentalStrategyValue();
		other2.setDataCategory("data category2");
		other2.setDataType("even more different stuff");
		other2.setExperimentalStrategy("strategy1");
		other2.setCount(10);
		other2.setAccess("controlled");
		List<ExperimentalStrategyValue> strategyValues = Arrays.asList(clinicalData, biomarker1, biomarker2, other1,
				other2);
		when(atlasRepoSummaryRepository.findAll()).thenReturn(strategyValues);
		when(fileInfoService.getRepositoryTotalFileCount()).thenReturn(36l);

		AtlasRepoSummaryResult result = dataSummaryService.getAtlasRepoSummary();
		List<AtlasRepoSummaryRow> summaryRows = result.getSummaryRows();
		assertEquals(3, summaryRows.size());
		assertEquals("Biomarkers", summaryRows.get(0).getOmicsType());
		assertEquals(0, summaryRows.get(0).getControlledCount());
		assertEquals(15, summaryRows.get(0).getOpenCount());
		assertEquals(new AtlasRepositoryLinkInformation("data_category", "Biomarker"),
				summaryRows.get(0).getLinkInformation());
		assertEquals("Clinical Study Data", summaryRows.get(1).getOmicsType());
		assertEquals(0, summaryRows.get(1).getControlledCount());
		assertEquals(1, summaryRows.get(1).getOpenCount());
		assertEquals(new AtlasRepositoryLinkInformation("data_category", "stuff"),
				summaryRows.get(1).getLinkInformation());
		assertEquals("strategy1", summaryRows.get(2).getOmicsType());
		assertEquals(10, summaryRows.get(2).getControlledCount());
		assertEquals(10, summaryRows.get(2).getOpenCount());
		assertEquals(new AtlasRepositoryLinkInformation("experimental_strategy", "strategy1"),
				summaryRows.get(2).getLinkInformation());
		assertEquals(Long.valueOf(36), result.getTotalFiles());

	}

	@Test
	public void testGetGeneDatasetInformation() throws Exception {
		when(dataSummaryRepository.getDataSummaryCount(TissueTypeEnum.AKI.getParticipantTissueType(),
				FullDataTypeEnum.SPATIAL_TRANSCRIPTOMICS_FULL.getFullName())).thenReturn(Long.valueOf(5));
		when(dataSummaryRepository.getDataSummaryCount(TissueTypeEnum.CKD.getParticipantTissueType(),
				FullDataTypeEnum.SPATIAL_TRANSCRIPTOMICS_FULL.getFullName())).thenReturn(Long.valueOf(15));
		when(dataSummaryRepository.getDataSummaryCount(TissueTypeEnum.HEALTHY_REFERENCE.getParticipantTissueType(),
				FullDataTypeEnum.SPATIAL_TRANSCRIPTOMICS_FULL.getFullName())).thenReturn(Long.valueOf(0));
		when(dataSummaryRepository.getDataSummaryCount(TissueTypeEnum.DMR.getParticipantTissueType(),
				FullDataTypeEnum.SPATIAL_TRANSCRIPTOMICS_FULL.getFullName())).thenReturn(88l);
		when(dataSummaryRepository
				.getParticipantSummaryCount(FullDataTypeEnum.SPATIAL_TRANSCRIPTOMICS_FULL.getFullName()))
						.thenReturn(Long.valueOf(20));

		when(dataSummaryRepository.getDataSummaryCount(TissueTypeEnum.AKI.getParticipantTissueType(),
				FullDataTypeEnum.TISSUE_IMAGING_AND_CYTOMETRY_3D_FULL.getFullName())).thenReturn(Long.valueOf(5));
		when(dataSummaryRepository.getDataSummaryCount(TissueTypeEnum.CKD.getParticipantTissueType(),
				FullDataTypeEnum.TISSUE_IMAGING_AND_CYTOMETRY_3D_FULL.getFullName())).thenReturn(Long.valueOf(15));
		when(dataSummaryRepository.getDataSummaryCount(TissueTypeEnum.HEALTHY_REFERENCE.getParticipantTissueType(),
				FullDataTypeEnum.TISSUE_IMAGING_AND_CYTOMETRY_3D_FULL.getFullName())).thenReturn(Long.valueOf(5));
		when(dataSummaryRepository.getDataSummaryCount(TissueTypeEnum.DMR.getParticipantTissueType(),
				FullDataTypeEnum.TISSUE_IMAGING_AND_CYTOMETRY_3D_FULL.getFullName())).thenReturn(88l);
		when(dataSummaryRepository
				.getParticipantSummaryCount(FullDataTypeEnum.TISSUE_IMAGING_AND_CYTOMETRY_3D_FULL.getFullName()))
						.thenReturn(Long.valueOf(25));

		when(dataSummaryRepository.getDataSummaryCount(TissueTypeEnum.AKI.getParticipantTissueType(),
				FullDataTypeEnum.LIGHT_MICROSCOPIC_WHOLE_SLIDE_IMAGES_FULL.getFullName())).thenReturn(Long.valueOf(5));
		when(dataSummaryRepository.getDataSummaryCount(TissueTypeEnum.CKD.getParticipantTissueType(),
				FullDataTypeEnum.LIGHT_MICROSCOPIC_WHOLE_SLIDE_IMAGES_FULL.getFullName())).thenReturn(Long.valueOf(15));
		when(dataSummaryRepository.getDataSummaryCount(TissueTypeEnum.HEALTHY_REFERENCE.getParticipantTissueType(),
				FullDataTypeEnum.LIGHT_MICROSCOPIC_WHOLE_SLIDE_IMAGES_FULL.getFullName())).thenReturn(Long.valueOf(2));
		when(dataSummaryRepository.getDataSummaryCount(TissueTypeEnum.DMR.getParticipantTissueType(),
				FullDataTypeEnum.LIGHT_MICROSCOPIC_WHOLE_SLIDE_IMAGES_FULL.getFullName())).thenReturn(88l);
		when(dataSummaryRepository
				.getParticipantSummaryCount(FullDataTypeEnum.LIGHT_MICROSCOPIC_WHOLE_SLIDE_IMAGES_FULL.getFullName()))
						.thenReturn(Long.valueOf(22));

		when(dataSummaryRepository.getDataSummaryCount(TissueTypeEnum.AKI.getParticipantTissueType(),
				FullDataTypeEnum.CODEX_FULL.getFullName())).thenReturn(Long.valueOf(5));
		when(dataSummaryRepository.getDataSummaryCount(TissueTypeEnum.CKD.getParticipantTissueType(),
				FullDataTypeEnum.CODEX_FULL.getFullName())).thenReturn(Long.valueOf(15));
		when(dataSummaryRepository.getDataSummaryCount(TissueTypeEnum.HEALTHY_REFERENCE.getParticipantTissueType(),
				FullDataTypeEnum.CODEX_FULL.getFullName())).thenReturn(Long.valueOf(2));
		when(dataSummaryRepository.getDataSummaryCount(TissueTypeEnum.DMR.getParticipantTissueType(),
				FullDataTypeEnum.CODEX_FULL.getFullName())).thenReturn(88l);
		when(dataSummaryRepository.getParticipantSummaryCount(FullDataTypeEnum.CODEX_FULL.getFullName()))
				.thenReturn(Long.valueOf(22));

		when(dataSummaryRepository.getDataSummaryLinkCount(TissueTypeEnum.AKI.getParticipantTissueType(),
				FullDataTypeEnum.SPATIAL_LIPIDOMICS_FULL.getFullName())).thenReturn(Long.valueOf(5));
		when(dataSummaryRepository.getDataSummaryLinkCount(TissueTypeEnum.CKD.getParticipantTissueType(),
				FullDataTypeEnum.SPATIAL_LIPIDOMICS_FULL.getFullName())).thenReturn(Long.valueOf(15));
		when(dataSummaryRepository.getDataSummaryLinkCount(TissueTypeEnum.HEALTHY_REFERENCE.getParticipantTissueType(),
				FullDataTypeEnum.SPATIAL_LIPIDOMICS_FULL.getFullName())).thenReturn(Long.valueOf(2));
		when(dataSummaryRepository.getDataSummaryLinkCount(TissueTypeEnum.DMR.getParticipantTissueType(),
				FullDataTypeEnum.SPATIAL_LIPIDOMICS_FULL.getFullName())).thenReturn(Long.valueOf(88));
		when(dataSummaryRepository
				.getParticipantSummaryLinkCount(FullDataTypeEnum.SPATIAL_LIPIDOMICS_FULL.getFullName()))
						.thenReturn(Long.valueOf(22));

		when(dataSummaryRepository.getDataSummaryLinkCount(TissueTypeEnum.AKI.getParticipantTissueType(),
				FullDataTypeEnum.SPATIAL_METABOLOMICS_FULL.getFullName())).thenReturn(Long.valueOf(5));
		when(dataSummaryRepository.getDataSummaryLinkCount(TissueTypeEnum.CKD.getParticipantTissueType(),
				FullDataTypeEnum.SPATIAL_METABOLOMICS_FULL.getFullName())).thenReturn(Long.valueOf(15));
		when(dataSummaryRepository.getDataSummaryLinkCount(TissueTypeEnum.HEALTHY_REFERENCE.getParticipantTissueType(),
				FullDataTypeEnum.SPATIAL_METABOLOMICS_FULL.getFullName())).thenReturn(Long.valueOf(2));
		when(dataSummaryRepository.getDataSummaryLinkCount(TissueTypeEnum.DMR.getParticipantTissueType(),
				FullDataTypeEnum.SPATIAL_METABOLOMICS_FULL.getFullName())).thenReturn(Long.valueOf(88));
		when(dataSummaryRepository
				.getParticipantSummaryLinkCount(FullDataTypeEnum.SPATIAL_METABOLOMICS_FULL.getFullName()))
						.thenReturn(Long.valueOf(22));

		when(dataSummaryRepository.getDataSummaryLinkCount(TissueTypeEnum.AKI.getParticipantTissueType(),
				FullDataTypeEnum.SPATIAL_NGLYCOMICS_FULL.getFullName())).thenReturn(Long.valueOf(5));
		when(dataSummaryRepository.getDataSummaryLinkCount(TissueTypeEnum.CKD.getParticipantTissueType(),
				FullDataTypeEnum.SPATIAL_NGLYCOMICS_FULL.getFullName())).thenReturn(Long.valueOf(15));
		when(dataSummaryRepository.getDataSummaryLinkCount(TissueTypeEnum.HEALTHY_REFERENCE.getParticipantTissueType(),
				FullDataTypeEnum.SPATIAL_NGLYCOMICS_FULL.getFullName())).thenReturn(Long.valueOf(2));
		when(dataSummaryRepository.getDataSummaryLinkCount(TissueTypeEnum.DMR.getParticipantTissueType(),
				FullDataTypeEnum.SPATIAL_NGLYCOMICS_FULL.getFullName())).thenReturn(Long.valueOf(88));
		when(dataSummaryRepository
				.getParticipantSummaryLinkCount(FullDataTypeEnum.SPATIAL_NGLYCOMICS_FULL.getFullName()))
						.thenReturn(Long.valueOf(22));

		List<DataTypeSummary> result = dataSummaryService.getSummaryData();

		assertEquals(FullDataTypeEnum.SPATIAL_TRANSCRIPTOMICS_FULL.getFullName(), result.get(0).getDataType());
		assertEquals(DataTypeEnum.SPATIAL_TRANSCRIPTOMICS.getAbbreviation(), result.get(0).getDataTypeShort());
		assertEquals(Long.valueOf(5), result.get(0).getAkiCount());
		assertEquals(Long.valueOf(15), result.get(0).getCkdCount());
		assertEquals(Long.valueOf(0), result.get(0).getHrtCount());
		assertEquals(Long.valueOf(88), result.get(0).getDmrCount());
		assertEquals(Long.valueOf(20), result.get(0).getParticipantCount());

		assertEquals(FullDataTypeEnum.TISSUE_IMAGING_AND_CYTOMETRY_3D_FULL.getFullName(), result.get(1).getDataType());
		assertEquals(DataTypeEnum.SPATIAL_TRANSCRIPTOMICS.getAbbreviation(), result.get(0).getDataTypeShort());
		assertEquals(Long.valueOf(5), result.get(1).getAkiCount());
		assertEquals(Long.valueOf(15), result.get(1).getCkdCount());
		assertEquals(Long.valueOf(5), result.get(1).getHrtCount());
		assertEquals(Long.valueOf(88), result.get(1).getDmrCount());
		assertEquals(Long.valueOf(25), result.get(1).getParticipantCount());

		assertEquals(FullDataTypeEnum.LIGHT_MICROSCOPIC_WHOLE_SLIDE_IMAGES_FULL.getFullName(),
				result.get(2).getDataType());
		assertEquals(DataTypeEnum.SPATIAL_TRANSCRIPTOMICS.getAbbreviation(), result.get(0).getDataTypeShort());
		assertEquals(Long.valueOf(5), result.get(2).getAkiCount());
		assertEquals(Long.valueOf(15), result.get(2).getCkdCount());
		assertEquals(Long.valueOf(2), result.get(2).getHrtCount());
		assertEquals(Long.valueOf(88), result.get(2).getDmrCount());
		assertEquals(Long.valueOf(22), result.get(2).getParticipantCount());

		assertEquals(FullDataTypeEnum.CODEX_FULL.getFullName(), result.get(3).getDataType());
		assertEquals(DataTypeEnum.SPATIAL_TRANSCRIPTOMICS.getAbbreviation(), result.get(0).getDataTypeShort());
		assertEquals(Long.valueOf(5), result.get(3).getAkiCount());
		assertEquals(Long.valueOf(15), result.get(3).getCkdCount());
		assertEquals(Long.valueOf(2), result.get(3).getHrtCount());
		assertEquals(Long.valueOf(88), result.get(3).getDmrCount());
		assertEquals(Long.valueOf(22), result.get(3).getParticipantCount());

		assertEquals(FullDataTypeEnum.SPATIAL_LIPIDOMICS_FULL.getFullName(), result.get(4).getDataType());
		assertEquals(DataTypeEnum.SPATIAL_TRANSCRIPTOMICS.getAbbreviation(), result.get(0).getDataTypeShort());
		assertEquals(Long.valueOf(5), result.get(4).getAkiCount());
		assertEquals(Long.valueOf(15), result.get(4).getCkdCount());
		assertEquals(Long.valueOf(2), result.get(4).getHrtCount());
		assertEquals(Long.valueOf(88), result.get(4).getDmrCount());
		assertEquals(Long.valueOf(22), result.get(4).getParticipantCount());

		assertEquals(FullDataTypeEnum.SPATIAL_METABOLOMICS_FULL.getFullName(), result.get(5).getDataType());
		assertEquals(DataTypeEnum.SPATIAL_TRANSCRIPTOMICS.getAbbreviation(), result.get(0).getDataTypeShort());
		assertEquals(Long.valueOf(5), result.get(5).getAkiCount());
		assertEquals(Long.valueOf(15), result.get(5).getCkdCount());
		assertEquals(Long.valueOf(2), result.get(5).getHrtCount());
		assertEquals(Long.valueOf(88), result.get(5).getDmrCount());
		assertEquals(Long.valueOf(22), result.get(5).getParticipantCount());

		assertEquals(FullDataTypeEnum.SPATIAL_NGLYCOMICS_FULL.getFullName(), result.get(6).getDataType());
		assertEquals(DataTypeEnum.SPATIAL_TRANSCRIPTOMICS.getAbbreviation(), result.get(0).getDataTypeShort());
		assertEquals(Long.valueOf(5), result.get(6).getAkiCount());
		assertEquals(Long.valueOf(15), result.get(6).getCkdCount());
		assertEquals(Long.valueOf(2), result.get(6).getHrtCount());
		assertEquals(Long.valueOf(88), result.get(6).getDmrCount());
		assertEquals(Long.valueOf(22), result.get(6).getParticipantCount());
	}
}
