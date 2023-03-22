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
    private String title;
    private String description;
    private boolean documentation_required;

    protected Requirement() {}

    public Requirement(int id, String major, String title, String description, boolean documentation_required)
    {
        this.id = id;
        this.major = major; // TODO a requirement doesnt need to be mapped to a major, we should use a join table
        this.title = title;
        this.description = description;
        this.documentation_required = documentation_required;
    }

    public Requirement(String major, String title, String description, boolean documentation_required)
    {
        this.major = major;
        this.title = title;
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

    public String getTitle() {return  this.title;}

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

    public void setTitle(String title) {this.title = title;}

    public void setDescription(String description)
    {
        this.description = description;
    }

    public void setDocumentationRequired(boolean documentation_required)
    {
        this.documentation_required = documentation_required;
    }

    @Override
    public String toString() {
        return "Requirement{" +
                "id=" + id +
                ", major='" + major + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", documentation_required=" + documentation_required +
                '}';
    }
}
