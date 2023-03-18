package edu.nau.stic_api.DataStructures;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.Date;

@Entity
public class Document
{
    @Id
    private String guid;
    private String file_extension;
    private String approval_status;
    private int requirement_instance_id;
    private String student_uid;

    private String student_name;
    private Date upload_timestamp;

    protected Document() {}

    public Document(String guid, String file_extension, String approval_status, int requirement_instance_id, String student_uid, String student_name, Date upload_timestamp)
    {
        this.guid = guid;
        this.file_extension = file_extension;
        this.approval_status = approval_status;
        this.requirement_instance_id = requirement_instance_id;
        this.student_uid = student_uid;
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
        return this.approval_status;
    }

    public int getRequirementInstanceID()
    {
        return this.requirement_instance_id;
    }
    public String getStudentUID()
    {
        return this.student_uid;
    }

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
        this.approval_status = approval_status;
    }
}
