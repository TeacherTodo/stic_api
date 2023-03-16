package edu.nau.stic_api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import edu.nau.stic_api.DataRepos.DocumentRepository;
import edu.nau.stic_api.DataRepos.RequirementInstanceRepository;
import edu.nau.stic_api.DataRepos.StudentRepository;
import edu.nau.stic_api.DataStructures.Document;
import edu.nau.stic_api.DataStructures.RequirementInstance;
import edu.nau.stic_api.DataStructures.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api", produces = "application/json")
public class ApiController
{
    @Autowired
    private StudentRepository student_repo;

    @Autowired
    private RequirementInstanceRepository instance_repo;

    @Autowired
    private DocumentRepository doc_repo;

    @RequestMapping(path = "/student/{uid}", method = RequestMethod.GET)
    public String getStudent(@PathVariable String uid) throws JsonProcessingException
    {
        Student student = student_repo.findByUid(uid);

        if(student != null)
        {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(student);
        }

        return "{\"error\": \"Unable to locate user with UID of " + uid + ".\"}";
    }

    @RequestMapping(path = "/student/requirements/{uid}")
    public String getStudentRequirements(@PathVariable String uid) throws JsonProcessingException
    {
        List<RequirementInstance> requirements = instance_repo.findByStudent(uid);

        if(requirements != null)
        {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            return mapper.writeValueAsString(requirements.toArray());
        }

        return "{\"error\": \"Unable to locate user with UID of " + uid + ".\"}";
    }

    @RequestMapping(path = "/students", method = RequestMethod.GET)
    public String getAllStudents() throws JsonProcessingException
    {
        Iterable<Student> studentIterator = student_repo.findAll();
        List<Student> students = new ArrayList<Student>();
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        for(Student student : studentIterator)
        {
            students.add(student);
        }

        return mapper.writeValueAsString(students.toArray());
    }

    @RequestMapping(path = "/requirement-instance/{id}/{uid}")
    public String getRequirementInstance(@PathVariable int id, @PathVariable String uid) throws JsonProcessingException
    {
        RequirementInstance instance = instance_repo.findById(id);

        if(instance == null)
        {
            return "{\"error\": \"Unable to find requirement instance with ID of " + id + ".\"}";
        }
        else if(!instance.getStudentUID().equals(uid))
        {
            return "{\"error\": \"UID listed on requirement does not match " + uid + ".\"}";
        }

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(instance);
    }

    @RequestMapping(path = "/document/{guid}/{uid}")
    public String getDocument(@PathVariable String guid, @PathVariable String uid) throws JsonProcessingException
    {
        Document doc = doc_repo.findByGuid(guid);

        if(doc == null)
        {
            return "{\"error\": \"Unable to find document instance with GUID of " + guid + ".\"}";
        }
        else if(!doc.getStudentUID().equals(uid))
        {
            return "{\"error\": \"UID listed on document does not match " + uid + ".\"}";
        }

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(doc);
    }
}
