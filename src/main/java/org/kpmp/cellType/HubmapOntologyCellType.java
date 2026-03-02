package org.kpmp.cellType;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "hubmap_ontology_celltype_v")
public class HubmapOntologyCellType implements Serializable{

    @Id
    @Column(name = "hubmap_ontology_id", nullable = false, insertable = false, updatable = false)
    private String hubmapOntologyId;
    private String cellType;

    public String getHubmapOntologyId() {
        return hubmapOntologyId;
    }

    public void setHubmapOntologyId(String hubmapOntologyId) {
        this.hubmapOntologyId = hubmapOntologyId;
    }

    public String getCellType() {
        return cellType;
    }

    public void setCellType(String cell_type) {
        this.cellType = cell_type;
    }

}
