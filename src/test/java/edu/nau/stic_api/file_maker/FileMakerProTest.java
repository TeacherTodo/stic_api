package edu.nau.stic_api.file_maker;

import edu.nau.stic_api.DataStructures.Student;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

class FileMakerProTest {

    @Test
    void getStudents() {
        File studentData = new File("src/main/resources/generated-data.xlsx");

        try (InputStream is = new FileInputStream(studentData)) {
            Workbook wb = new XSSFWorkbook(is);
            Sheet sheet = wb.getSheetAt(0);
            List<Student> students = FileMakerPro.getStudents(sheet);
        } catch (FileNotFoundException e) {
            fail("FileNotFoundException");
        } catch (IOException e) {
            fail("IOException");
        } catch (FileMakerPro.InvalidFormatException e) {
            fail(e.getMessage());
        }
    }
}