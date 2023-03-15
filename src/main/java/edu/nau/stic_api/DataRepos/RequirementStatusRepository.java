package edu.nau.stic_api.DataRepos;

import edu.nau.stic_api.DataStructures.RequirementStatus;
import org.springframework.data.repository.CrudRepository;

public interface RequirementStatusRepository extends CrudRepository<RequirementStatus, String>
{
    RequirementStatus findByStatus(String status);
}
