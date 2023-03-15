package edu.nau.stic_api.DataRepos;

import edu.nau.stic_api.DataStructures.RequirementInstance;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface RequirementInstanceRepository extends CrudRepository<RequirementInstance, Integer>
{
    List<RequirementInstance> findByStudent(String student_uid);
    RequirementInstance findById(int id);
}
