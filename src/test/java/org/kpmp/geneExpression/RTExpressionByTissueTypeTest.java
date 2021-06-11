package org.kpmp.geneExpression;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RTExpressionByTissueTypeTest {

    private RTExpressionByTissueType rtExpressionByTissueType;

    @Before
    public void setUp() throws Exception {
        rtExpressionByTissueType = new RTExpressionByTissueType();
    }

    @After
    public void tearDown() throws Exception {
        rtExpressionByTissueType = null;
    }

    @Test
    void setAll() {
        RTExpressionDataAllSegments allAkiData = new RTExpressionDataAllSegments();
        List<? extends RTExpressionData> allAkiDataList = Arrays.asList(allAkiData);
        rtExpressionByTissueType.setAll(allAkiDataList);
        List<? extends RTExpressionData> actual = rtExpressionByTissueType.getAll();
        assertEquals(allAkiDataList, actual);
    }

    @Test
    void setAki() {
        RTExpressionDataAllSegments allAkiData = new RTExpressionDataAllSegments();
        List<? extends RTExpressionData> allAkiDataList = Arrays.asList(allAkiData);
        rtExpressionByTissueType.setHrt(allAkiDataList);
        List<? extends RTExpressionData> actual = rtExpressionByTissueType.getHrt();
        assertEquals(allAkiDataList, actual);
    }

    @Test
    void setCkd() {
        RTExpressionDataAllSegments allAkiData = new RTExpressionDataAllSegments();
        List<? extends RTExpressionData> allAkiDataList = Arrays.asList(allAkiData);
        rtExpressionByTissueType.setAki(allAkiDataList);
        List<? extends RTExpressionData> actual = rtExpressionByTissueType.getAki();
        assertEquals(allAkiDataList, actual);
    }

    @Test
    void setHrt() {
        RTExpressionDataAllSegments allAkiData = new RTExpressionDataAllSegments();
        List<? extends RTExpressionData> allAkiDataList = Arrays.asList(allAkiData);
        rtExpressionByTissueType.setCkd(allAkiDataList);
        List<? extends RTExpressionData> actual = rtExpressionByTissueType.getCkd();
        assertEquals(allAkiDataList, actual);
    }
}