//package edu.nau.stic_api.file_maker;
//
//import edu.nau.stic_api.DataStructures.RequirementInstance;
//import edu.nau.stic_api.DataStructures.Student;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.List;
//
//public class ExcelExport {
//    public static void generate(Sheet students) throws IOException {
//        /*
//         * We will export the students to an Excel file. Each program will be
//         * its own sheet as it makes managing the columns easier. The header of
//         * the sheet will include the requirements, and the detail of what each
//         * requirement is.
//         */
//
//        // Create a workbook
//        Workbook wb = new XSSFWorkbook();
//        Sheet sheet = wb.createSheet("Major 1");
//
//        sheet.createRow(0).createCell(0).setCellValue("This is a test to see if a really long column will be sized");
//        Row row = sheet.getRow(0);
//        Cell cell = row.getCell(0);
//
//        sheet.autoSizeColumn(0);
//
//
//        File file = new File("src/main/resources/excel-files/export.xlsx");
//        file.createNewFile();
//        FileOutputStream fos = new FileOutputStream(file);
//        wb.write(fos);
//    }
//
//    /*
//     * We will export the students to an Excel file. Each program will be
//     * its own sheet as it makes managing the columns easier. The header of
//     * the sheet will include the requirements, and the detail of what each
//     * requirement is.
//     */
//    // we should either make the argument a FileMakerProExport object, or keep it as a workbook, and throw an exception of InvalidFormatException
//    public static Workbook generateWorkbook(Workbook fileMakerProExport) {
//        Workbook wb = new XSSFWorkbook();
//        Sheet sheet = wb.createSheet("Major 1");
//
//        MultipartFile file;
////        file.
//
//        sheet.createRow(0).createCell(0).setCellValue("This is a test to see if a really long column will be sized");
//        Row row = sheet.getRow(0);
//        Cell cell = row.getCell(0);
//
//        sheet.autoSizeColumn(0);
//
//        return wb;
//    }
//
//    private static Row createHeaderRow(Sheet sheet, List<String> headers) {
//        Row row = sheet.createRow(0);
//        for (int i = 0; i < headers.size(); i++) {
//            row.createCell(i).setCellValue(headers.get(i));
//        }
//        return row;
//    }
//
//    private static Row createStudentRow(Student student, List<RequirementInstance> req) {
//
//    }
//    // get the students from the fileMakerPro file.
//    // get the students from our database, this includes the requirements and the requirements description
//    // group the students by major
//    // create a new sheet per major
//    // the sheet will have the requirements as the header, and the columns as the status per student
//
//
//
//
//}
