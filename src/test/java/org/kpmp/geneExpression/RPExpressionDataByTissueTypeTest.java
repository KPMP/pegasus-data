package org.kpmp.geneExpression;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RPExpressionDataByTissueTypeTest {

    private RPExpressionByTissueType rpExpressionByTissueType;

    @Before
    public void setUp() throws Exception {
        rpExpressionByTissueType = new RPExpressionByTissueType();
    }

    @After
    public void tearDown() throws Exception {
        rpExpressionByTissueType = null;
    }

    @Test
    public void setAll() {
        RPExpressionData allData = new RPExpressionData();
        List<? extends RPExpressionData> allataList = Arrays.asList(allData);
        rpExpressionByTissueType.setAll(allataList);
        List<? extends RPExpressionData> actual = rpExpressionByTissueType.getAll();
        assertEquals(allataList, actual);
    }

    @Test
    public void setAki() {
        RPExpressionData allAkiData = new RPExpressionData();
        List<? extends RPExpressionData> allAkiDataList = Arrays.asList(allAkiData);
        rpExpressionByTissueType.setAki(allAkiDataList);
        List<? extends RPExpressionData> actual = rpExpressionByTissueType.getAki();
        assertEquals(allAkiDataList, actual);
    }

    @Test
    public void setCkd() {
        RPExpressionData allCkdData = new RPExpressionData();
        List<? extends RPExpressionData> allCkdDataList = Arrays.asList(allCkdData);
        rpExpressionByTissueType.setCkd(allCkdDataList);
        List<? extends RPExpressionData> actual = rpExpressionByTissueType.getCkd();
        assertEquals(allCkdDataList, actual);
    }

    @Test
    public void setHrt() {
        RPExpressionData allHrtData = new RPExpressionData();
        List<? extends RPExpressionData> allHrtDataList = Arrays.asList(allHrtData);
        rpExpressionByTissueType.setHrt(allHrtDataList);
        List<? extends RPExpressionData> actual = rpExpressionByTissueType.getHrt();
        assertEquals(allHrtDataList, actual);
    }

    @Test
    public void setDmr() {
        RPExpressionData allDmrData = new RPExpressionData();
        List<? extends RPExpressionData> allDmrDataList = Arrays.asList(allDmrData);
        rpExpressionByTissueType.setDmr(allDmrDataList);
        List<? extends RPExpressionData> actual = rpExpressionByTissueType.getDmr();
        assertEquals(allDmrDataList, actual);
    }

}
