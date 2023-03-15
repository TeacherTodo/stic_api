package edu.nau.stic_api.DataStructures;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Major
{
    @Id
    private String major;

    protected Major() {}

    public Major(String major)
    {
        this.major = major;
    }

    public String getMajor()
    {
        return this.major;
    }

    public void setMajor(String major)
    {
        this.major = major;
    }
}
