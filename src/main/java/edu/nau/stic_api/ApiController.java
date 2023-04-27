package edu.nau.stic_api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.collect.Lists;
import edu.nau.stic_api.DataRepos.*;
import edu.nau.stic_api.DataStructures.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    private AdminUserRepository admin_repo;

    @Autowired
    private MajorRepository major_repo;

    @Autowired
    private TermRepository term_repo;

    @Autowired
    private RequirementStatusRepository request_status_repo;

    @Autowired
    private ApprovalStatusRepository approval_status_repo;

    @Autowired
    private RequirementRepository requirement_repo;

    private S3_Helper helper = new S3_Helper();

    /* Database Access Methods: GET */

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
        List<Student> students = Lists.newArrayList(studentIterator);
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        System.out.println("getAllStudents(): ObjectMapper(): " + mapper.writeValueAsString(students.get(0)));

//        System.out.println("getAllStudents(): toString()");
//        students.forEach(System.out::println);
//        System.out.println("getAllStudents() ObjectMapper(): " + mapper.writeValueAsString(students.toArray()));

        return mapper.writeValueAsString(students.toArray());
    }

    @RequestMapping(path = "/admins", method = RequestMethod.GET)
    public String getAllAdmins() throws JsonProcessingException
    {
        Iterable<AdminUser> adminUserIterable = admin_repo.findAll();
        List<AdminUser> admins = new ArrayList<AdminUser>();
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        for(AdminUser admin : adminUserIterable)
        {
            admins.add(admin);
        }

        return mapper.writeValueAsString(admins);
    }

    @RequestMapping(path = "/requirement-instance/{id}/{uid}", method = RequestMethod.GET)
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

    @RequestMapping(path = "/document/{guid}/{uid}", method = RequestMethod.GET)
    public String getDocument(@PathVariable String guid, @PathVariable String uid) throws Exception
    {
        Document doc = doc_repo.findByGuid(guid);

        if(doc == null)
        {
            return  "{\"error\": \"Unable to find document instance with GUID of " + guid + ".\"}";
        }
        else if(!doc.getStudentGuid().equals(uid))
        {
            return  "{\"error\": \"UID listed on document does not match " + uid + ".\"}";
        }

        /*
        ByteArrayResource resource = new ByteArrayResource(S3_Helper.downloadFile(doc.getGUID(), doc.getFileExtension()));
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(resource.contentLength())
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment()
                                .filename("document")
                                .build().toString())
                .body(resource);
         */

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(doc);
    }

    @RequestMapping(path = "/document-content/{guid}/{fileExtension}", method = RequestMethod.GET)
    public ResponseEntity<Resource> getFileContent(@PathVariable String guid, @PathVariable String fileExtension) throws IOException
    {
        byte[] content = helper.downloadFile(guid, fileExtension);
        ByteArrayResource resource = new ByteArrayResource(content);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(resource.contentLength())
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment()
                                .filename(guid + "." + fileExtension)
                                .build().toString())
                .body(resource);
    }

    @RequestMapping(path = "/document-content/{guid}/{fileExtension}", method = RequestMethod.POST)
    public String postFileContent(@PathVariable String guid, @PathVariable String fileExtension, @RequestBody byte data[]) throws IOException
    {
        helper.uploadFile(guid, fileExtension, data);
        return "{\"message\": \"Successfully uploaded file content to S3.\"}";
    }

    @RequestMapping(path = "/majors", method = RequestMethod.GET)
    public String getAllMajors() throws JsonProcessingException
    {
        Iterable<Major> majorIterable = major_repo.findAll();
        List<Major> majors = new ArrayList<Major>();
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        for(Major major : majorIterable)
        {
            majors.add(major);
        }

        return mapper.writeValueAsString(majors.toArray());
    }

    @RequestMapping(path = "/terms", method = RequestMethod.GET)
    public String getAllTerms() throws JsonProcessingException
    {
        Iterable<Term> termIterable = term_repo.findAll();
        List<Term> terms = new ArrayList<Term>();
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        for(Term term : termIterable)
        {
            terms.add(term);
        }

        return mapper.writeValueAsString(terms.toArray());
    }

    @RequestMapping(path = "/requirement-status", method = RequestMethod.GET)
    public String getAllRequirementStatuses() throws JsonProcessingException
    {
        Iterable<RequirementStatus> statusIterable = request_status_repo.findAll();
        List<RequirementStatus> statuses = new ArrayList<RequirementStatus>();
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        for(RequirementStatus status : statusIterable)
        {
            statuses.add(status);
        }

        return mapper.writeValueAsString(statuses.toArray());
    }

    @RequestMapping(path = "/approval-status", method = RequestMethod.GET)
    public String getAllApprovalStatuses() throws JsonProcessingException
    {
        Iterable<ApprovalStatus> statusIterable = approval_status_repo.findAll();
        List<ApprovalStatus> statuses = new ArrayList<ApprovalStatus>();
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        for(ApprovalStatus status : statusIterable)
        {
            statuses.add(status);
        }

        return mapper.writeValueAsString(statuses.toArray());
    }

    @RequestMapping(path = "/requirements", method = RequestMethod.GET)
    public String getAllRequirements() throws JsonProcessingException
    {
        Iterable<Requirement> requirementIterable = requirement_repo.findAll();
        List<Requirement> requirements = new ArrayList<Requirement>();
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        for(Requirement req : requirementIterable)
        {
            requirements.add(req);
        }

        return mapper.writeValueAsString(requirements.toArray());
    }

    @RequestMapping(path = "/requirement/{id}", method = RequestMethod.GET)
    public String getRequirement(@PathVariable int id) throws JsonProcessingException
    {
        Requirement req = requirement_repo.findById(id);

        if(req != null)
        {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(req);
        }

        return "{\"error\": \"Unable to locate requirement with ID of " + id + ".\"}";
    }

    @RequestMapping(path = "/major/{name}", method = RequestMethod.GET)
    public String getMajor(@PathVariable String name) throws JsonProcessingException
    {
        Major major = major_repo.findByMajor(name);

        if(major != null)
        {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(major);
        }

        return "{\"error\": \"Unable to locate major with name of " + name + ".\"}";
    }

    @RequestMapping(path = "/term/{name}", method = RequestMethod.GET)
    public String getTerm(@PathVariable String name) throws JsonProcessingException
    {
        Term term = term_repo.findByTerm(name);

        if(term != null)
        {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(term);
        }

        return "{\"error\": \"Unable to locate term with name of " + name + ".\"}";
    }

    @RequestMapping(path = "/docs-all", method = RequestMethod.GET)
    public String allDocs() throws JsonProcessingException
    {
        Iterable<Document> documentIterable = doc_repo.findAll();
        List<Document> docs = new ArrayList<Document>();
        ObjectMapper mapper = new ObjectMapper();
        for(Document doc : documentIterable)
        {
            docs.add(doc);
        }

        return mapper.writeValueAsString(docs.toArray());
    }

    @RequestMapping(path = "/docs-pending", method = RequestMethod.GET)
    public String pendingDocs() throws JsonProcessingException
    {
        List<Document> docs = doc_repo.findByApprovalStatus("Pending Approval");
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(docs.toArray());
    }

    /* Database Manipulation Methods: POST */

    @RequestMapping(path = "/admins/{uid}/{name}", method = RequestMethod.POST)
    public String createAdminUser(@PathVariable String uid, @PathVariable String name) throws JsonProcessingException
    {
        admin_repo.save(new AdminUser(uid, name));
        return "{\"message\": \"Created new administrator user with UID " + uid + ".\"}";
    }

    @RequestMapping(path = "/students", method = RequestMethod.POST, consumes = "application/json")
    public String createStudent(@RequestBody String jsonString) throws JsonProcessingException
    {
        ObjectMapper mapper = new ObjectMapper();
        CreateStudentRequest req = mapper.readValue(jsonString, CreateStudentRequest.class);
        student_repo.save(new Student(req.uid, req.major, req.grad_term, req.grad_year));
        return "{\"message\": \"Created new student with UID " + req.uid + ".\"}";
    }

    @RequestMapping(path = "/documents", method = RequestMethod.POST, consumes = "application/json")
    public String createDocument(@RequestBody String jsonString) throws JsonProcessingException
    {
        System.out.println("createDocument(@RequestBody String jsonString):");

        ObjectMapper mapper = new ObjectMapper();
        CreateDocumentRequest req = mapper.readValue(jsonString, CreateDocumentRequest.class);

        System.out.println("createDocument(@RequestBody String jsonString): " + req);

//        String guid = UUID.randomUUID().toString(); //TODO: is it a good idea for the client to generate the GUID?

        doc_repo.save(new Document(req.fileGuid, req.fileExtension, "Pending Approval", req.requirementInstanceId,
                req.studentGuid, req.studentName, new Date()));

        // TODO: add the document to the requirement instance
        RequirementInstance requirement = instance_repo.findById(req.requirementInstanceId);
        requirement.setDocGUID(req.fileGuid);
        instance_repo.save(requirement);

        System.out.println("createDocument(@RequestBody String jsonString) requirementInstance: " + requirement);

        return "{\"message\": \"Created new document with GUID " + req.fileGuid + ".\"}";
    }

    @RequestMapping(path = "/requirements", method = RequestMethod.POST, consumes = "application/json")
    public String createRequirement(@RequestBody String jsonString) throws JsonProcessingException
    {
        ObjectMapper mapper = new ObjectMapper();
        CreateRequirementRequest req = mapper.readValue(jsonString, CreateRequirementRequest.class);
        Requirement newReq = new Requirement(req.major, req.title, req.description, req.documentation_required);
        requirement_repo.save(newReq);
        return "{\"message\": \"Created new requirement with ID " + newReq.getID() + ".\"}";
    }

    @RequestMapping(path = "/requirement-instance", method = RequestMethod.POST, consumes = "application/json")
    public String createRequirementInstance(@RequestBody String jsonString) throws JsonProcessingException
    {
        ObjectMapper mapper = new ObjectMapper();
        CreateRequirementInstanceRequest req = mapper.readValue(jsonString, CreateRequirementInstanceRequest.class);
        RequirementInstance instance;

        //Both doc_guid and retake_date null
        if(req.doc_guid == null && req.retake_date == null)
        {
            instance = new RequirementInstance(req.requirement_id, req.student_uid, req.status);
        }
        //doc_guid provided, retake_date null
        else if(req.retake_date == null)
        {
            instance = new RequirementInstance(req.requirement_id, req.student_uid, req.status, req.doc_guid);
        }
        //doc_guid null, retake_date provided
        else if(req.doc_guid == null)
        {
            instance = new RequirementInstance(req.requirement_id, req.student_uid, req.status, req.retake_date);
        }
        //Both doc_guid and retake_date are set
        else
        {
            instance = new RequirementInstance(req.requirement_id, req.student_uid, req.status, req.doc_guid, req.retake_date);
        }

        instance_repo.save(instance);
        return "{\"message\": \"Created new requirement instance with ID " + instance.getID() + ".\"}";
    }

    @RequestMapping(path = "/majors/{name}", method = RequestMethod.POST)
    public String createMajor(@PathVariable String name) throws JsonProcessingException
    {
        major_repo.save(new Major(name));
        return "{\"message\": \"Created new major " + name + ".\"}";
    }

    @RequestMapping(path = "/requirement", method = RequestMethod.POST)
    public String editRequirement(@RequestBody String jsonString) throws JsonProcessingException
    {
        ObjectMapper mapper = new ObjectMapper();
        Requirement req = mapper.readValue(jsonString, Requirement.class);
        requirement_repo.save(req);
        return "{\"message\": \"Updated requirement with ID " + req.getID() + ".\"}";
    }

    @RequestMapping(path = "/requirement-instance", method = RequestMethod.POST)
    public String editRequirementInstance(@RequestBody String jsonString) throws JsonProcessingException
    {
        ObjectMapper mapper = new ObjectMapper();
        RequirementInstance instance = mapper.readValue(jsonString, RequirementInstance.class);
        instance_repo.save(instance);
        return "{\"message\": \"Updated requirement instance with ID " + instance.getID() + ".\"}";
    }

    @RequestMapping(path = "/document", method = RequestMethod.POST)
    public String editDocument(@RequestBody String jsonString) throws JsonProcessingException
    {
        ObjectMapper mapper = new ObjectMapper();
        Document doc = mapper.readValue(jsonString, Document.class);
        doc_repo.save(doc);
        return "{\"message\": \"Updated document with GUID " + doc.getGuid() + ".\"}";
    }

    @RequestMapping(path = "/student", method = RequestMethod.POST)
    public String editStudent(@RequestBody String jsonString) throws JsonProcessingException
    {
        ObjectMapper mapper = new ObjectMapper();
        Student student = mapper.readValue(jsonString, Student.class);
        student_repo.save(student);
        return "{\"message\": \"Updated student with UID " + student.getUid() + ".\"}";
    }

    @RequestMapping(path = "/requirement/{id}", method = RequestMethod.DELETE)
    public String deleteRequirement(@PathVariable int id)
    {
        requirement_repo.deleteById(id);
        return "{\"message\": \"Deleted requirement with ID " + id + ".\"}";
    }

    @RequestMapping(path = "/requirement-instance/{id}", method = RequestMethod.DELETE)
    public String deleteRequirementInstance(@PathVariable int id)
    {
        instance_repo.deleteById(id);
        return "{\"message\": \"Deleted requirement instance with ID " + id + ".\"}";
    }

    @RequestMapping(path = "/document/{guid}", method = RequestMethod.DELETE)
    public String deleteDocument(@PathVariable String guid)
    {
        doc_repo.deleteById(guid);
        return "{\"message\": \"Deleted document with GUID " + guid + ".\"}";
    }

    @RequestMapping(path = "/major/{name}", method = RequestMethod.DELETE)
    public String deleteMajor(@PathVariable String name)
    {
        major_repo.deleteById(name);
        return "{\"message\": \"Deleted major with name " + name + ".\"}";
    }

    @RequestMapping(path = "/admin/{uid}", method = RequestMethod.DELETE)
    public String deleteAdmin(@PathVariable String uid)
    {
        admin_repo.deleteById(uid);
        return "{\"message\": \"Deleted admin with UID " + uid + ".\"}";
    }

    @RequestMapping(path = "/admin/approve-document/{guid}", method = RequestMethod.GET)
    public String approveDocument(@PathVariable String guid)
    {
        Document doc = doc_repo.findByGuid(guid);
        doc.setApprovalStatus("Approved");
        doc_repo.save(doc);
        return "{\"message\": \"Updated document status to approved.\"}";
    }

    @RequestMapping(path = "/admin/deny-document/{guid}", method = RequestMethod.GET)
    public String denyDocument(@PathVariable String guid)
    {
        Document doc = doc_repo.findByGuid(guid);
        doc.setApprovalStatus("Denied");
        doc_repo.save(doc);
        return "{\"message\": \"Updated document status to denied.\"}";
    }
}
