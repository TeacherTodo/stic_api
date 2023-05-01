package edu.nau.stic_api.DataStructures;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class RequirementInstance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int requirement_id;
    //@JsonProperty("student_uid")
    private String student;
    private String status;
    @Nullable
    //@JsonProperty("doc_guid")
    private String docGuid;
    @Nullable
    //@JsonProperty("retake_date")
    private Date retake_date;

    protected RequirementInstance() {
    }

    public RequirementInstance(int requirement_id, String student, String status) {
        this.requirement_id = requirement_id;
        this.student = student;
        this.status = status;
    }

    public RequirementInstance(int id, int requirement_id, String student, String status, String docGuid) {
        this.id = id;
        this.requirement_id = requirement_id;
        this.student = student;
        this.status = status;
        this.docGuid = docGuid;
        this.retake_date = null;
    }

    public RequirementInstance(int id, int requirement_id, String student, String status, Date retake_date) {
        this.id = id;
        this.requirement_id = requirement_id;
        this.student = student;
        this.status = status;
        this.docGuid = null;
        this.retake_date = retake_date;
    }

    public RequirementInstance(int id, int requirement_id, String student, String status, String docGuid, Date retake_date) {
        this.id = id;
        this.requirement_id = requirement_id;
        this.student = student;
        this.status = status;
        this.docGuid = docGuid;
        this.retake_date = retake_date;
    }

    public RequirementInstance(int requirement_id, String student, String status, String docGuid) {
        this.requirement_id = requirement_id;
        this.student = student;
        this.status = status;
        this.docGuid = docGuid;
        this.retake_date = null;
    }

    public RequirementInstance(int requirement_id, String student, String status, Date retake_date) {
        this.requirement_id = requirement_id;
        this.student = student;
        this.status = status;
        this.docGuid = null;
        this.retake_date = retake_date;
    }

    public RequirementInstance(int requirement_id, String student, String status, String docGuid, Date retake_date) {
        this.requirement_id = requirement_id;
        this.student = student;
        this.status = status;
        this.docGuid = docGuid;
        this.retake_date = retake_date;
    }

    public int getID() {
        return this.id;
    }

    @JsonProperty("requirement_id")
    public int getRequirementID() {
        return this.requirement_id;
    }

    @JsonProperty("student_uid")
    public String getStudentUID() {
        return this.student;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("doc_guid")
    public String getDocGUID() {
        return this.docGuid;
    }

    public void setDocGUID(String doc_guid) {
        this.docGuid = doc_guid;
    }

    @JsonProperty("retake_date")
    public Date getRetakeDate() {
        return this.retake_date;
    }

    public void setRetakeDate(Date retake_date) {
        this.retake_date = retake_date;
    }

    @Override
    public String toString() {
        return "RequirementInstance{" +
                "id=" + id +
                ", requirement_id=" + requirement_id +
                ", student='" + student + '\'' +
                ", status='" + status + '\'' +
                ", doc_guid='" + docGuid + '\'' +
                ", retake_date=" + retake_date +
                '}';
    }
}
