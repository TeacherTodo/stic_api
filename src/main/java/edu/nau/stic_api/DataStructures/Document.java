package edu.nau.stic_api.DataStructures;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.Date;

@Entity
public class Document
{
    @Id
    private String guid;
    private String file_extension;
    @JsonProperty("approval_status")
    private String status;
    private int requirement_instance_id;
    @JsonProperty("student_uid")
    private String student;

    private String student_name;
    private Date upload_timestamp;

    protected Document() {}

    public Document(String guid, String file_extension, String status, int requirement_instance_id, String student, String student_name, Date upload_timestamp)
    {
        this.guid = guid;
        this.file_extension = file_extension;
        this.status = status;
        this.requirement_instance_id = requirement_instance_id;
        this.student = student;
        this.student_name = student_name;
        this.upload_timestamp = upload_timestamp;
    }

    public String getGUID()
    {
        return this.guid;
    }

    public String getFileExtension()
    {
        return this.file_extension;
    }

    public String getApprovalStatus()
    {
        return this.status;
    }

    public int getRequirementInstanceID()
    {
        return this.requirement_instance_id;
    }
    public String getStudentUID()
    {
        return this.student;
    }

    public String getStudentName() {return this.student_name;}

    public Date getUploadTimestamp()
    {
        return this.upload_timestamp;
    }

    public void setFileExtension(String file_extension)
    {
        this.file_extension = file_extension;
    }

    public void setApprovalStatus(String status)
    {
        this.status = status;
    }
}
