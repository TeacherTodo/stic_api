package edu.nau.stic_api.DataStructures;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ApprovalStatus
{
    @Id
    private String status;

    protected ApprovalStatus() {}

    public ApprovalStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return this.status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }
}
