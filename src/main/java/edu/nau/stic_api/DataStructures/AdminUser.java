package edu.nau.stic_api.DataStructures;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class AdminUser
{
    @Id
    private String uid;

    protected AdminUser() {}

    public AdminUser(String uid)
    {
        this.uid = uid;
    }

    public String getUID()
    {
        return this.uid;
    }

    public void setUID(String uid)
    {
        this.uid = uid;
    }
}