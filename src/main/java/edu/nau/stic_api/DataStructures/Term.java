package edu.nau.stic_api.DataStructures;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Term
{
    @Id
    private String term;

    protected Term() {}

    public Term(String term)
    {
        this.term = term;
    }

    public String getTerm()
    {
        return this.term;
    }

    public void setTerm(String term)
    {
        this.term = term;
    }
}
