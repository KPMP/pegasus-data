package org.kpmp.geneExpression;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kpmp.EnrollmentCategoryEnum;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

public class RPExpressionDataServiceTest {

    @Mock
    RPExpressionDataRepository rpExpressionDataRepository;

    RPExpressionDataService rpExpressionDataService;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        rpExpressionDataService = new RPExpressionDataService(rpExpressionDataRepository);
    }

    @AfterEach
    public void tearDown() throws Exception {
        MockitoAnnotations.openMocks(this).close();
        rpExpressionDataService = null;
    }

    @Test
    public void testGetByGeneSymbolPerTissue() throws Exception {

        List<String> accessions = new ArrayList<>();
        accessions.add("a1");
        accessions.add("a2");
        when(rpExpressionDataRepository.findAccessionByGeneSymbol("gene")).thenReturn(accessions);

        List<RPExpressionData>  rpExpressionDataAll = Arrays.asList(new RPExpressionData[]{new RPExpressionData()});

        List<RPExpressionData>  rpExpressionDataAll2 = Arrays.asList(new RPExpressionData[]{new RPExpressionData()});

        when(rpExpressionDataRepository.findByGeneSymbolAndEnrollmentCategoryAndProteinWithCounts("gene", EnrollmentCategoryEnum.ALL.getParticipantEnrollmentCategory(), "a1")).
                thenReturn(rpExpressionDataAll);

        when(rpExpressionDataRepository.findByGeneSymbolAndEnrollmentCategoryAndProteinWithCounts("gene", EnrollmentCategoryEnum.ALL.getParticipantEnrollmentCategory(), "a2")).
                thenReturn(rpExpressionDataAll2);

        List<RPAccessionGroup> rpAccessionGroups = rpExpressionDataService.getByGeneSymbolPerTissue("gene");

        assertEquals("a1", rpAccessionGroups.get(0).getAccession());
        assertEquals("a2", rpAccessionGroups.get(1).getAccession());
        RPExpressionByEnrollmentCategory rpExpressionByEnrollmentCategory1 = rpAccessionGroups.get(0).getRpExpressionByEnrollmentCategory();
        RPExpressionByEnrollmentCategory rpExpressionByEnrollmentCategory2 = rpAccessionGroups.get(1).getRpExpressionByEnrollmentCategory();


        assertEquals(rpExpressionByEnrollmentCategory1.getAll(), rpExpressionDataAll);
        assertEquals(rpExpressionByEnrollmentCategory2.getAll(), rpExpressionDataAll2);
        
    }

    @Test
    public void testGetByGeneSymbolAndProteinPerTissue() throws Exception {
        List<RPExpressionData>  rpExpressionDataAll = Arrays.asList(new RPExpressionData[]{new RPExpressionData()});
       
        when(rpExpressionDataRepository.findByGeneSymbolAndEnrollmentCategoryAndProteinWithCounts("gene", EnrollmentCategoryEnum.ALL.getParticipantEnrollmentCategory(), "protein")).
                thenReturn(rpExpressionDataAll);

        RPExpressionByEnrollmentCategory rpExpressionByEnrollmentCategory = rpExpressionDataService.getByGeneSymbolAndProteinPerTissue("gene", "protein");

        assertEquals(rpExpressionByEnrollmentCategory.getAll(), rpExpressionDataAll);
    }

    @Test
    public void testGetByStructure() throws Exception {
        List<RPExpressionData> rpExpressionDataList = new ArrayList<>();
        when(rpExpressionDataRepository.findByStructure("structure")).thenReturn(rpExpressionDataList);
        assertEquals(rpExpressionDataList, rpExpressionDataService.getByStructure("structure"));
    }
}
