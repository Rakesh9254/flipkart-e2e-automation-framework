package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtility {

    private static Workbook workbook;
    private static Sheet sheet;
    private static final String FILE_PATH = "src/test/resources/testdata.xlsx";

    public static void loadSheet(String sheetName) {
        try {
            workbook = new XSSFWorkbook(new FileInputStream(FILE_PATH));
            sheet = workbook.getSheet(sheetName);
        } catch (IOException e) {
            LoggerConfig.error("Failed to load Excel: " + FILE_PATH, e);
        }
    }

    public static String getCellData(int row, int col) {
        Row r = sheet.getRow(row);
        if (r != null && r.getCell(col) != null) {
            return new DataFormatter().formatCellValue(r.getCell(col));
        }
        return "";
    }

    public static int getRowCount() {
        return sheet != null ? sheet.getLastRowNum() : 0;
    }

    public static void setCellData(int row, int col, String value) {
        try {
            Row r = sheet.getRow(row);
            if (r == null)
                r = sheet.createRow(row);
            r.createCell(col).setCellValue(value);

            FileOutputStream fos = new FileOutputStream(FILE_PATH);
            workbook.write(fos);
            fos.close();
        } catch (IOException e) {
            LoggerConfig.error("Failed to write to Excel.", e);
        }
    }

    public static void close() {
        try {
            if (workbook != null)
                workbook.close();
        } catch (IOException ignored) {
        }
    }
}
