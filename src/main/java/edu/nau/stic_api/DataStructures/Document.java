package edu.nau.stic_api.DataStructures;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Document {
    @Id
    @Column(name = "guid")
    private String guid;
    @Column(name = "file_extension")
    private String fileExtension;
    @Column(name = "approval_status")
    private String approvalStatus;
    @Column(name = "requirement_instance_id")
    private int requirementInstanceId;
    @Column(name = "student_guid")
    private String studentGuid;
    @Column(name = "student_name")
    private String studentName;
    @Column(name = "upload_timestamp")
    @JsonIgnore
    private Date uploadTimestamp;

    protected Document() {
    }

    public Document(String guid, String fileExtension, String approvalStatus, int requirementInstanceId, String studentGuid, String studentName, Date uploadTimestamp) {
        this.guid = guid;
        this.fileExtension = fileExtension;
        this.approvalStatus = approvalStatus;
        this.requirementInstanceId = requirementInstanceId;
        this.studentGuid = studentGuid;
        this.studentName = studentName;
        this.uploadTimestamp = uploadTimestamp;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String file_extension) {
        this.fileExtension = file_extension;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approval_status) {
        this.approvalStatus = approval_status;
    }

    public int getRequirementInstanceId() {
        return requirementInstanceId;
    }

    public void setRequirementInstanceId(int requirement_instance_id) {
        this.requirementInstanceId = requirement_instance_id;
    }

    public String getStudentGuid() {
        return studentGuid;
    }

    public void setStudentGuid(String student_guid) {
        this.studentGuid = student_guid;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String student_name) {
        this.studentName = student_name;
    }

    @JsonIgnore
    public Date getUploadTimestamp() {
        return uploadTimestamp;
    }

    @JsonIgnore
    public void setUploadTimestamp(Date upload_timestamp) {
        this.uploadTimestamp = upload_timestamp;
    }

    @Override
    public String toString() {
        return "Document{" +
                "guid='" + guid + '\'' +
                ", fileExtension='" + fileExtension + '\'' +
                ", approvalStatus='" + approvalStatus + '\'' +
                ", requirementInstanceId=" + requirementInstanceId +
                ", studentGuid='" + studentGuid + '\'' +
                ", studentName='" + studentName + '\'' +
                ", uploadTimestamp=" + uploadTimestamp +
                '}';
    }
}
