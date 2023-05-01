package edu.nau.stic_api;

import com.google.common.collect.Lists;
import edu.nau.stic_api.DataStructures.Major;
import edu.nau.stic_api.DataStructures.Requirement;
import edu.nau.stic_api.DataStructures.Student;
import edu.nau.stic_api.file_maker.FileMakerPro;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

public class FileMakerProInitilizeDatabase {

    public static List<Major> createMajors() {
        return Lists.newArrayList(new Major("Plan 1"), new Major("Plan 2"), new Major("Plan 3"));
    }

    public static List<Requirement> createRequirements() {
        return Lists.newArrayList(
                new Requirement(UUID.randomUUID().hashCode(), "Plan 1", "Plan 1 Requirement 1", "Requirement 1 Description", false),
                new Requirement(UUID.randomUUID().hashCode(), "Plan 1", "Plan 1 Requirement 2", "Requirement 2 Description", false),
                new Requirement(UUID.randomUUID().hashCode(), "Plan 1", "Plan 1 Requirement 3", "Requirement 3 Description", false),
                new Requirement(UUID.randomUUID().hashCode(), "Plan 2", "Plan 2 Requirement 1", "Requirement 1 Description", false),
                new Requirement(UUID.randomUUID().hashCode(), "Plan 2", "Plan 2 Requirement 2", "Requirement 2 Description", false),
                new Requirement(UUID.randomUUID().hashCode(), "Plan 3", "Plan 3 Requirement 1", "Requirement 1 Description", false),
                new Requirement(UUID.randomUUID().hashCode(), "Plan 3", "Plan 3 Requirement 2", "Requirement 2 Description", false),
                new Requirement(UUID.randomUUID().hashCode(), "Plan 3", "Plan 3 Requirement 3", "Requirement 3 Description", false),
                new Requirement(UUID.randomUUID().hashCode(), "Plan 3", "Plan 3 Requirement 4", "Requirement 4 Description", false)
        );
    }

    public static List<Student> createStudents() {
//        try (InputStream is = FileMakerProInitilizeDatabase.class.getClass().getResourceAsStream("generated-data.xlsx");) {
        try (InputStream is = new FileInputStream("src/main/resources/generated-data.xlsx")) {
            Workbook wb = new XSSFWorkbook(is);
            Sheet sheet = wb.getSheetAt(0);
            List<Student> students = FileMakerPro.getStudents(sheet);

            // NOTE: start of debugging
            System.out.println("createStudents(): ");
            students.forEach(System.out::println);
            // NOTE: end of debugging

            return students;
        } catch (IOException e) {
            System.out.println("IOException");
        } catch (FileMakerPro.InvalidFormatException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Exception");
        }

        return null;
    }


//    3.2.4
}
