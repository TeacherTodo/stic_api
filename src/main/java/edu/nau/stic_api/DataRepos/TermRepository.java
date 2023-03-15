package edu.nau.stic_api.DataRepos;

import edu.nau.stic_api.DataStructures.Term;
import org.springframework.data.repository.CrudRepository;

public interface TermRepository extends CrudRepository<Term, String>
{
    Term getByTerm(String term);
}
