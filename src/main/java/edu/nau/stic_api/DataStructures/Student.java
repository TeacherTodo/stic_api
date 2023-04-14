package edu.nau.stic_api.DataStructures;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.hash.Hashing;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.nio.charset.StandardCharsets;

@Entity
public class Student {
    @Id
    private String uid;
    private String first_name;
    private String last_name;
    private String major;
    private String grad_term; // might want to change this to term
    private int grad_year; // might want to change this to year

    public Student() {
    }

    public Student(String uid, String major, String grad_term, int grad_year) {
        this.uid = uid;
        this.major = major;
        this.grad_term = grad_term;
        this.grad_year = grad_year;
    }

    public Student(String uid, String first_name, String last_name, String major, String grad_term, int grad_year) {
        this.uid = uid;
        this.first_name = first_name;
        this.last_name = last_name;
        this.major = major;
        this.grad_term = grad_term;
        this.grad_year = grad_year;
    }

    @JsonProperty("uid")
    public String getUid() {
        return uid;
    }

    @JsonProperty("uid")
    public void setUid(String email) {
        String hashedUid = Hashing.sha256()
                                  .hashString(email, StandardCharsets.UTF_8)
                                  .toString();

        this.uid = hashedUid;
    }

    @JsonProperty("firstName")
    public String getFirst_name() {
        return first_name;
    }

    @JsonProperty("firstName")
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    @JsonProperty("lastName")
    public String getLast_name() {
        return last_name;
    }

    @JsonProperty("lastName")
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    @JsonProperty("major")
    public String getMajor() {
        return major;
    }

    @JsonProperty("major")
    public void setMajor(String major) {
        this.major = major;
    }

    @JsonProperty("gradTerm")
    public String getGrad_term() {
        return grad_term;
    }

    @JsonProperty("gradTerm")
    public void setGrad_term(String grad_term) {
        this.grad_term = grad_term;
    }

    @JsonProperty("gradYear")
    public int getGrad_year() {
        return grad_year;
    }

    @JsonProperty("gradYear")
    public void setGrad_year(int grad_year) {
        this.grad_year = grad_year;
    }

    @Override
    public String toString() {
        return "Student{" +
                "uid='" + uid + '\'' +
                ", firsName='" + first_name + '\'' +
                ", lastName='" + last_name + '\'' +
                ", major='" + major + '\'' +
                ", gradTerm='" + grad_term + '\'' +
                ", gradYear=" + grad_year +
                '}';
    }
}