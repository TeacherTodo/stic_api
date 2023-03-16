package edu.nau.stic_api.DataStructures;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Requirement
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String major;
    private String description;
    private boolean documentation_required;

    protected Requirement() {}

    public Requirement(int id, String major, String description, boolean documentation_required)
    {
        this.id = id;
        this.major = major;
        this.description = description;
        this.documentation_required = documentation_required;
    }

    public Requirement(String major, String description, boolean documentation_required)
    {
        this.major = major;
        this.description = description;
        this.documentation_required = documentation_required;
    }

    public int getID()
    {
        return this.id;
    }

    public String getMajor()
    {
        return this.major;
    }

    public String getDescription()
    {
        return this.description;
    }

    public boolean isDocumentationRequired()
    {
        return this.documentation_required;
    }

    public void setMajor(String Major)
    {
        this.major = major;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public void setDocumentationRequired(boolean documentation_required)
    {
        this.documentation_required = documentation_required;
    }
}
