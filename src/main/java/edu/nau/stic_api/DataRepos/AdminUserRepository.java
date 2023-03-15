package edu.nau.stic_api.DataRepos;

import edu.nau.stic_api.DataStructures.AdminUser;
import org.springframework.data.repository.CrudRepository;

public interface AdminUserRepository extends CrudRepository<AdminUser, String>
{
    AdminUser findByUid(String uid);
}
