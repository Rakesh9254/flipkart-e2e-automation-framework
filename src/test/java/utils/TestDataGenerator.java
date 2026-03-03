package utils;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;

public class TestDataGenerator {
    public static void main(String[] args) throws Exception {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Products");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Product");
        header.createCell(1).setCellValue("Status");

        Row row1 = sheet.createRow(1);
        row1.createCell(0).setCellValue("headphones");

        Row row2 = sheet.createRow(2);
        row2.createCell(0).setCellValue("mouse");

        File file = new File("src/test/resources/testdata.xlsx");
        file.getParentFile().mkdirs();
        FileOutputStream fos = new FileOutputStream(file);
        workbook.write(fos);
        fos.close();
        workbook.close();
        System.out.println("Excel file created successfully!");
    }
}
