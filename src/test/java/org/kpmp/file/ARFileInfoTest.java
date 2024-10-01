package org.kpmp.file;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ARFileInfoTest {
    
    private ARFileInfo fileInfo;

    @BeforeEach
    public void setUp() throws Exception {
        fileInfo = new ARFileInfo();
    }

    @AfterEach
    public void tearDown() throws Exception {
        fileInfo = null;
    }

    @Test
    public void testSetArFileInfoId() {
        fileInfo.setArFileInfoId(4);
        assertEquals(4, fileInfo.getArFileInfoId());
    }

    @Test
    public void testSetFileId() {
        fileInfo.setFileId(12);
        assertEquals(12, fileInfo.getFileId());
    }

    @Test
    public void testSetReleaseVersion() {
        fileInfo.setReleaseVersion(4);
        assertEquals(4, fileInfo.getReleaseVersion());
    }

    @Test
    public void testSetReleaseSunsetVersion() {
        fileInfo.setReleaseSunsetVersion(45);
        assertEquals(45, fileInfo.getReleaseSunsetVersion());
    }

    @Test
    public void testSetMetadataTypeId() {
        fileInfo.setMetadataTypeId(66);
        assertEquals(66, fileInfo.getMetadataTypeId());
    }
}


