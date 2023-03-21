package edu.nau.stic_api;

import edu.nau.stic_api.DataStructures.Major;
import edu.nau.stic_api.DataStructures.Requirement;
import edu.nau.stic_api.DataStructures.RequirementInstance;
import edu.nau.stic_api.DataStructures.Student;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InitilizeDatabase {

    public static List<Major> createMajors() {
        List<Major> majors = new ArrayList<>();
        List<String> majorNames = List.of("Computer Science", "Computer Engineering", "Electrical Engineering", "Mechanical Engineering");

        for (String majorName : majorNames) {
            majors.add(new Major(majorName));
        }

        return majors;
    }

    public static List<Requirement> createRequirements(List<Major> majors) {
        List<Requirement> requirements = new ArrayList<>();
        List<String> requirementsNames = List.of("Requirement 1", "Requirement 2", "Requirement 3");

        for (Major major : majors) {
            for (String requirementName : requirementsNames) {
                String reqTitle = major.getMajor() + " " + requirementName + " Title";
                String reqDescription = major.getMajor() + " " + requirementName + " Description";
                int reqID = UUID.fromString(reqTitle)
                                .hashCode();
                Boolean reqDocumentRequired = false;

                Requirement requirement = new Requirement(reqID, major.getMajor(), reqTitle, reqDescription, reqDocumentRequired);
                requirements.add(requirement);

                System.out.println("createRequirements() requirement: " + requirement.toString());
            }
        }

        return requirements;
    }

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
                int reqInstanceId = UUID.fromString(student.getUID() + requirement.getID())
                                        .hashCode();

                RequirementInstance requirementInstance = new RequirementInstance(reqInstanceId, requirement.getID(), student.getUID(), "In Progress", null, null);

                requirementInstances.add(requirementInstance);
            }
        }

        return requirementInstances;
    }

    public static void initilizeDatabase() {
        List<Student> students = new ArrayList<>();
        List<String> emails = new ArrayList<>();
        List<String> terms = List.of("Fall", "Spring", "Summer", "Winter");
        List<Integer> years = List.of(2023, 2024);

        List<Major> majors = createMajors();

        for (int index = 0; index < 30; index++) {
            Student student;
            String email = "abc" + String.format("%2d", index) + "@nau.edu";
            String uuid = Hashing.sha256()
                                 .hashString(email, StandardCharsets.UTF_8)
                                 .toString();
            String major = majors.get((int) (Math.random() * majors.size())).getMajor();
            String term = terms.get((int) (Math.random() * terms.size()));

            student = new Student(uuid, major, term, years.get((int) (Math.random() * years.size())));
            System.out.println("initilizeStudents() email: " + email);
            System.out.println("initilizeStudents() student: " + student.toString());
        }
    }
}
