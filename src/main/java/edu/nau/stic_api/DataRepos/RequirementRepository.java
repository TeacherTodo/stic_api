package edu.nau.stic_api.DataRepos;

import edu.nau.stic_api.DataStructures.Requirement;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RequirementRepository extends CrudRepository<Requirement, Integer> {
    List<Requirement> findByMajor(String major);

    Requirement findById(int id);
}
