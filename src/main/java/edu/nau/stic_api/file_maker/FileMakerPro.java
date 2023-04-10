package edu.nau.stic_api.file_maker;


import edu.nau.stic_api.DataStructures.Student;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FileMakerPro {

    public static class InvalidFormatException extends Exception {
        public InvalidFormatException() {
            super();
        }

        public InvalidFormatException(String message) {
            super(message);
        }
    }

    public static List<Student> getStudents(Sheet sheet) throws InvalidFormatException {
        List<Student> students = new ArrayList<>();

        if (isValidSheetFormat(sheet) == false) {
            throw new InvalidFormatException("Invalid sheet format. Please check the sheet format and try again.");
        }

        Iterator<Row> studentRows = getStudentRows(sheet);

        // TODO: start of testing
//        int count = 0;
//        while (studentRows.hasNext()) {
//            count++;
//            System.out.println("Count: " + count);
//            Student student = getStudent(sheet, studentRows.next());
//        }
        // TODO: end of testing

        try {
            studentRows.forEachRemaining(row -> {
                try {
                    students.add(getStudent(sheet, row));
                } catch (InvalidFormatException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (RuntimeException e) {
            if (e.getCause() instanceof InvalidFormatException) {
                throw (InvalidFormatException) e.getCause();
            } else {
                throw e;
            }
        }

        return students;
    }

    private static boolean isValidSheetFormat(Sheet sheet) {
        Row headerRow = sheet.getRow(0);
        return headerRow.getCell(0).getStringCellValue().equals("ID #")
                && headerRow.getCell(1).getStringCellValue().equals("PEU Master::FIRST NAME")
                && headerRow.getCell(2).getStringCellValue().equals("PEU Master::LAST NAME")
                && headerRow.getCell(3).getStringCellValue().equals("PEU Master::E-mail")
                && headerRow.getCell(4).getStringCellValue().equals("PEU Master::ST Plan");
    }

    private static Iterator<Row> getStudentRows(Sheet sheet) {
        Iterator<Row> students = sheet.rowIterator();

        // TODO: start of testing
//        int count = 0;
//        while(students.hasNext()) {
//            count++;
//            students.next();
//        }
//        System.out.println("getStudentRows(): students.rowIterator.count: " + count);
//
//        students = sheet.rowIterator();
//        students.next();
//        count = 0;
//        while(students.hasNext()) {
//            count++;
//            students.next();
//        }
//        System.out.println("getStudentRows(): students.rowIterator.count: " + count);
//
//        students = sheet.rowIterator();
        // TODO: end of testing

        students.next(); // Skip header row

        return students;
    }

    private static Student getStudent(Sheet sheet, Row row) throws InvalidFormatException {
        // TODO: start of testing
//        System.out.println("--- getStudent()");
//        row.cellIterator().forEachRemaining(System.out::println);
//        System.out.println("--- END getStudent()\n");
        // TODO: end of testing

        Student student = new Student();
        student.setUid(getStudentEmail(row));
        student.setMajor(getStudentPlan(row));
        student.setGrad_term(getStudentGradTerm(sheet));
        student.setGrad_year(getStudentGradYear(sheet));

        return student;
    }

    private static String getStudentFirstName(Row row) {
        return row.getCell(1).getStringCellValue();
    }

    private static String getStudentLastName(Row row) {
        return row.getCell(2).getStringCellValue();
    }

    private static String getStudentEmail(Row row) {
        return row.getCell(3).getStringCellValue();
    }

    private static String getStudentPlan(Row row) {
        return row.getCell(4).getStringCellValue();
    }

    private static String getStudentGradTerm(Sheet sheet) {
        return sheet.getSheetName().split(" ")[0];
    }

    private static int getStudentGradYear(Sheet sheet) throws InvalidFormatException {
        int baseYear = 2000; // NOTE: we will run into the 2100 problem as form only encodes 2 digits for year
        int parsedYear;
        String sheetYear = sheet.getSheetName().split(" ")[1];

        try {
            parsedYear = Integer.parseInt(sheetYear);
        } catch (NumberFormatException e) {
            throw new InvalidFormatException("Unable to parse year from sheet name.");
        }

        return baseYear + parsedYear;
    }


}
