package edu.nau.stic_api;

import edu.nau.stic_api.DataRepos.MajorRepository;
import edu.nau.stic_api.DataRepos.RequirementInstanceRepository;
import edu.nau.stic_api.DataRepos.RequirementRepository;
import edu.nau.stic_api.DataRepos.StudentRepository;
import edu.nau.stic_api.DataStructures.Major;
import edu.nau.stic_api.DataStructures.Requirement;
import edu.nau.stic_api.DataStructures.RequirementInstance;
import edu.nau.stic_api.DataStructures.Student;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class InitilizeDatabase {

    /*
     * Currently 4 majors
     * Total: 4 majors
     */
    public static List<Major> createMajors() {
        List<Major> majors = new ArrayList<>();
        List<String> majorNames = List.of("Computer Science", "Computer Engineering", "Electrical Engineering", "Mechanical Engineering");

        for (String majorName : majorNames) {
            majors.add(new Major(majorName));
        }

        return majors;
    }

    /*
     * Currently 3 requirements per major
     * Total: 3 requirements * 4 majors = 12 requirements
     */
    public static List<Requirement> createRequirements(List<Major> majors) {
        List<Requirement> requirements = new ArrayList<>();
        List<String> requirementsNames = List.of("Requirement 1", "Requirement 2", "Requirement 3");

        for (Major major : majors) {
            for (String requirementName : requirementsNames) {
                String reqTitle = major.getMajor() + " " + requirementName + " Title";
                String reqDescription = major.getMajor() + " " + requirementName + " Description";
                int reqID = reqTitle.hashCode();
                Boolean reqDocumentRequired = false;

                Requirement requirement = new Requirement(reqID, major.getMajor(), reqTitle, reqDescription, reqDocumentRequired);
                requirements.add(requirement);

                System.out.println("createRequirements() requirement: " + requirement.toString());
            }
        }

        return requirements;
    }

    /*
     * Currently 4 students per major, per semester, per year
     * Total: 4 students per major * 2 semesters * 2 years *4 majors = 64 students
     */
    public static List<Student> createStudents(List<Major> majors) {
        List<Student> students = new ArrayList<>();
//        List<String> terms = List.of("Fall", "Spring", "Summer", "Winter");
        List<String> terms = List.of("Fall", "Spring");
        List<Integer> years = List.of(2023, 2024);

        List<String> firstNames = List.of("John", "Paul", "George", "Ringo", "Pete", "Stu", "Mick", "Keith", "Brian", "Charlie", "Eric", "Bill", "David", "Mike", "Tom", "Chris", "Bob", "Bruce", "Roger", "John", "Paul", "George", "Ringo", "Pete", "Stu", "Mick", "Keith", "Brian", "Charlie", "Eric", "Bill", "David", "Mike", "Tom", "Chris", "Bob", "Bruce", "Roger", "John", "Paul", "George", "Ringo", "Pete", "Stu", "Mick", "Keith", "Brian", "Charlie", "Eric", "Bill", "David", "Mike", "Tom", "Chris", "Bob", "Bruce", "Roger", "John", "Paul", "George", "Ringo", "Pete", "Stu", "Mick", "Keith", "Brian", "Charlie", "Eric", "Bill", "David", "Mike", "Tom", "Chris", "Bob", "Bruce", "Roger", "John", "Paul", "George", "Ringo", "Pete", "Stu", "Mick", "Keith", "Brian", "Charlie", "Eric", "Bill", "David", "Mike", "Tom", "Chris", "Bob", "Bruce", "Roger", "John", "Paul", "George", "Ringo", "Pete", "Stu", "Mick", "Keith", "Brian", "Charlie", "Eric", "Bill", "David", "Mike", "Tom", "Chris", "Bob", "Bruce", "Roger", "John", "Paul", "George", "Ringo", "Pete", "Stu", "Mick", "Keith", "Brian", "Charlie", "Eric", "Bill", "David", "Mike", "Tom", "Chris", "Bob", "Bruce", "Roger", "John", "Paul", "George", "Ringo", "Pete", "Stu", "Mick", "Keith", "Brian", "Charlie", "Eric", "Bill", "David", "Mike", "Tom", "Chris", "Bob", "Bruce", "Roger");
        List<String> lastNames = List.of("Lennon", "McCartney", "Harrison", "Starr", "Townshend", "Sutcliffe", "Jagger", "Richards", "Jones", "Watts", "Clapton", "Wyman", "Gilmour", "Waters", "Jagger", "Richards", "Jones", "Watts", "Clapton", "Wyman", "Gilmour", "Waters", "Lennon", "McCartney", "Harrison", "Starr", "Townshend", "Sutcliffe", "Jagger", "Richards", "Jones", "Watts", "Clapton", "Wyman", "Gilmour", "Waters", "Jagger", "Richards", "Jones", "Watts", "Clapton", "Wyman", "Gilmour", "Waters", "Lennon", "McCartney", "Harrison", "Starr", "Townshend", "Sutcliffe", "Jagger", "Richards", "Jones", "Watts", "Clapton", "Wyman", "Gilmour", "Waters", "Jagger", "Richards", "Jones", "Watts", "Clapton", "Wyman", "Gilmour", "Waters", "Lennon", "McCartney", "Harrison", "Starr", "Townshend", "Sutcliffe", "Jagger", "Richards", "Jones", "Watts", "Clapton", "Wyman", "Gilmour", "Waters", "Jagger", "Richards", "Jones", "Watts", "Clapton", "Wyman", "Gilmour", "Waters", "Lennon", "McCartney", "Harrison", "Starr", "Townshend", "Sutcliffe", "Jagger", "Richards", "Jones", "Watts", "Clapton", "Wyman", "Gilmour", "Waters", "Jagger", "Richards", "Jones", "Watts", "Clapton", "Wyman", "Gilmour", "Waters", "Lennon", "McCartney", "Harrison", "Starr", "Townshend", "Sutcliffe", "Jagger", "Richards");

        int nameIndex = 0;

        for (Major major : majors) {
            for (int year : years) {
                for (String term : terms) {
                    for (int index = 0; index < 4; index++) {
                        String email = major.getMajor() + "_" + String.valueOf(year) + "_" + term + "_" + String.format("%02d", index) + "@nau.edu";
                        String uuid = Hashing.sha256()
                                .hashString(email, StandardCharsets.UTF_8)
                                .toString();

                        Student student = new Student(uuid, firstNames.get(nameIndex), lastNames.get(nameIndex), major.getMajor(), term, year);
                        students.add(student);

                        nameIndex++;

                        System.out.println("initilizeStudents() email: " + email);
                        System.out.println("initilizeStudents() student: " + student.toString());
                    }
                }
            }

        }

        return students;
    }

    /*
     * Currently 3 requirements per major, there are 64 students
     * Total: 3 requirements * 64 students = 192 requirement instances
     */
    public static List<RequirementInstance> createRequirementInstances(List<Requirement> requirements, List<Student> students) {
        List<RequirementInstance> requirementInstances = new ArrayList<>();

        for (Student student : students) {
            List<Requirement> studentRequirements = requirements.stream()
                    .filter((req) -> {
                        return req.getMajor()
                                .equals(student.getMajor());
                    })
                    .toList();

            List<RequirementInstance> studentRequirementInstances = new ArrayList<>();

            for (Requirement requirement : studentRequirements) {
                int reqInstanceId = (student.getUid() + requirement.getID()).hashCode();

                RequirementInstance requirementInstance = new RequirementInstance(reqInstanceId, requirement.getID(), student.getUid(), "In Progress", null, null);

                requirementInstances.add(requirementInstance);
                studentRequirementInstances.add(requirementInstance); //TODO: remove this line, using it for debug
            }


            //TODO: start of debug
            System.out.println("createRequirementInstances() student: " + student.toString());
            System.out.println("createRequirementInstances() studentRequirements: " + studentRequirements.toString());
            System.out.println("createRequirementInstances() studentRequirementInstances: " + studentRequirementInstances.toString());
            //TODO: end of debug
        }

        return requirementInstances;
    }

    public static void initilizeDatabase(RequirementRepository requirement_repo, RequirementInstanceRepository instance_repo, StudentRepository student_repo, MajorRepository major_repo) {
        List<Major> majors = createMajors();
        List<Requirement> requirements = createRequirements(majors);
        List<Student> students = createStudents(majors);
        List<RequirementInstance> requirementInstances = createRequirementInstances(requirements, students);

        System.out.println("initilizeDatabase() majors.size() (expecting 4): actual " + majors.size());
        System.out.println("initilizeDatabase() requirements.size() (expecting 12): actual " + requirements.size());
        System.out.println("initilizeDatabase() students.size() (expecting 64): actual " + students.size());
        System.out.println("initilizeDatabase() requirementInstances.size() (expecting 192): actual " + requirementInstances.size());

        major_repo.saveAll(majors);
        requirement_repo.saveAll(requirements);
        student_repo.saveAll(students);
        instance_repo.saveAll(requirementInstances);
    }
}
