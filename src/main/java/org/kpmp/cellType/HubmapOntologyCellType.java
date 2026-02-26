package org.kpmp.cellType;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "humbapOntology_celltype_v")
public class HubmapOntologyCellType {

    @Id
    private String hubmapOntologyId;
    private String cell_type;

    public String getHubmapOntologyId() {
        return hubmapOntologyId;
    }

    public void setHubmapOntologyId(String hubmapOntologyId) {
        this.hubmapOntologyId = hubmapOntologyId;
    }

    public String getCell_type() {
        return cell_type;
    }

    public void setCell_type(String cell_type) {
        this.cell_type = cell_type;
    }

}
