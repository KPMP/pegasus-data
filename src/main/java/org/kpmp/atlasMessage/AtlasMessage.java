package org.kpmp.atlasMessage;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "atlas_messages")
public class AtlasMessage{
    @Id
    private int atlasMessageId;
    private String message;
    private String application;
    private Date startDate;
    private Date endDate;

    public int getAtlasMessageId() {
        return this.atlasMessageId;
    }

    public void setAtlasMessageId(int atlasMessageId) {
        this.atlasMessageId = atlasMessageId;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getApplication() {
        return this.application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

}
