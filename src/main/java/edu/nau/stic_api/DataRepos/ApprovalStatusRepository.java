package edu.nau.stic_api.DataRepos;

import edu.nau.stic_api.DataStructures.ApprovalStatus;
import org.springframework.data.repository.CrudRepository;

public interface ApprovalStatusRepository extends CrudRepository<ApprovalStatus, String>
{
    ApprovalStatus findByStatus(String status);
}
