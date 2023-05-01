package edu.nau.stic_api.DataRepos;

import edu.nau.stic_api.DataStructures.Major;
import org.springframework.data.repository.CrudRepository;

public interface MajorRepository extends CrudRepository<Major, String> {
    Major findByMajor(String major);
}
