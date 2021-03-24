package org.kpmp.cellType;

import java.io.Serializable;

public class CellTypeSynonymId implements Serializable {
    private int cellTypeId;
    private String cellTypeSynonym;

    public CellTypeSynonymId() {
    }

    public CellTypeSynonymId(int cellTypeId, String cellTypeSynonym) {
        this.cellTypeId = cellTypeId;
        this.cellTypeSynonym = cellTypeSynonym;
    }

    public int getCellTypeId() {
        return cellTypeId;
    }

    public void setCellTypeId(int cellTypeId) {
        this.cellTypeId = cellTypeId;
    }

    public String getCellTypeSynonym() {
        return cellTypeSynonym;
    }

    public void setCellTypeSynonym(String cellTypeSynonym) {
        this.cellTypeSynonym = cellTypeSynonym;
    }
}
