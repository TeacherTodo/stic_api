package edu.nau.stic_api.DataStructures;

import jakarta.annotation.Nullable;

import java.util.Date;

public class CreateRequirementInstanceRequest
{
    public int requirement_id;
    public String student_uid;
    public String status;
    @Nullable
    public String doc_guid;
    @Nullable
    public Date retake_date;
}
