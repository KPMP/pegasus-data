package org.kpmp.cellType;

import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "celltype_synonym")
@IdClass(CellTypeSynonym.class)
public class CellTypeSynonym {

    @Id
    @Column(name="cell_type_id")
    private int cellTypeId;

    @Id
    @Column(name="cell_type_synonym")
    private String cellTypeSynonym;

    @ManyToOne
    @JoinColumn(name="cell_type_id", nullable=false)
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
