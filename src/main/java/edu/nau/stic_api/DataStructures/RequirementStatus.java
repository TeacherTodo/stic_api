package edu.nau.stic_api.DataStructures;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class RequirementStatus {
    @Id
    private String status;

    protected RequirementStatus() {
    }

    public RequirementStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
