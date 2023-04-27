package edu.nau.stic_api.DataRepos;

import edu.nau.stic_api.DataStructures.Document;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface DocumentRepository extends CrudRepository<Document, String>
{
    List<Document> findByStudentGuid(String student_uid);
    List<Document> findByApprovalStatus(String status);
    Document findByGuid(String guid);
}
