package edu.nau.stic_api.DataStructures;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Student
{
    @Id
    private String uid;
    private String major;
    private String grad_term;
    private int grad_year;

    protected Student() {}

    public Student(String uid, String major, String grad_term, int grad_year)
    {
        this.uid = uid;
        this.major = major;
        this.grad_term = grad_term;
        this.grad_year = grad_year;
    }

    public String getUID()
    {
        return this.uid;
    }

    public String getMajor()
    {
        return this.major;
    }

    public String getGradTerm()
    {
        return this.grad_term;
    }

    public int getGradYear()
    {
        return this.grad_year;
    }

    public void setGradTerm(String grad_term)
    {
        this.grad_term = grad_term;
    }

    public void setGradYear(int grad_year)
    {
        this.grad_year = grad_year;
    }
}
