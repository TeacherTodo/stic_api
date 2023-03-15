package edu.nau.stic_api.DataRepos;

import edu.nau.stic_api.DataStructures.Student;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface StudentRepository extends CrudRepository<Student, String>
{
    Student findByUid(String uid);
}
