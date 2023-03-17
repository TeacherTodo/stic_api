package edu.nau.stic_api.DataStructures;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class AdminUser
{
    @Id
    private String uid;
    private String name;

    protected AdminUser() {}

    public AdminUser(String uid, String name)
    {
        this.uid = uid;
        this.name = name;
    }

    public String getUID()
    {
        return this.uid;
    }

    public String getName() {return this.name;}

    public void setUID(String uid)
    {
        this.uid = uid;
    }
}