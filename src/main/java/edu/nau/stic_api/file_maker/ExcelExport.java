package edu.nau.stic_api.file_maker;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelExport {
    public static void generate(Sheet students) throws IOException {
        /*
         * We will export the students to an Excel file. Each program will be
         * its own sheet as it makes managing the columns easier. The header of
         * the sheet will include the requirements, and the detail of what each
         * requirement is.
         */

        // Create a workbook
        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("Major 1");

        sheet.createRow(0).createCell(0).setCellValue("This is a test to see if a really long column will be sized");
        Row row = sheet.getRow(0);
        Cell cell = row.getCell(0);

        sheet.autoSizeColumn(0);


        File file = new File("src/main/resources/excel-files/export.xlsx");
        file.createNewFile();
        FileOutputStream fos = new FileOutputStream(file);
        wb.write(fos);
    }




}
