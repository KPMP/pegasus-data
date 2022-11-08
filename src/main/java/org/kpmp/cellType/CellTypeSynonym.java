package org.kpmp.cellType;

import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "celltype_synonym")
@IdClass(CellTypeSynonymId.class)
public class CellTypeSynonym implements Serializable {

    @Id
    @Column(name="cell_type_id")
    private int cellTypeId;

    @Id
    @Column(name="cell_type_synonym")
    private String cellTypeSynonym;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="cell_type_id", nullable = false, insertable = false, updatable = false)
    private CellType cellType;

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
