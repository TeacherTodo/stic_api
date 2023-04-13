package edu.nau.stic_api.DataStructures;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Student
{
    @Id
    private String uid;
    private String major;
    private String grad_term; // might want to change this to term
    private int grad_year; // might want to change this to year
    private String first_name;
    private String last_name;

    public Student() {}

    public Student(String uid, String major, String grad_term, int grad_year)
    {
        this.uid = uid;
        this.major = major;
        this.grad_term = grad_term;
        this.grad_year = grad_year;
    }

//    public String getUid() {
//        return uid;
//    }
//
//    public void setUid(String uid) {
//        this.uid = uid;
//    }
//
//    public void setMajor(String major) {
//        this.major = major;
//    }
//
//    public String getGrad_term() {
//        return grad_term;
//    }
//
//    public void setGrad_term(String grad_term) {
//        this.grad_term = grad_term;
//    }
//
//    public int getGrad_year() {
//        return grad_year;
//    }
//
//    public void setGrad_year(int grad_year) {
//        this.grad_year = grad_year;
//    }

    // od code, will refactor later

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

    public String getFirst_name() { return first_name; }

    public String getLastName() { return last_name; }

    public void setGradTerm(String grad_term)
    {
        this.grad_term = grad_term;
    }

    public void setGradYear(int grad_year)
    {
        this.grad_year = grad_year;
    }

    @Override
    public String toString() {
        return "Student{" +
                "uid='" + uid + '\'' +
                ", major='" + major + '\'' +
                ", grad_term='" + grad_term + '\'' +
                ", grad_year=" + grad_year +
                '}';
    }
}
