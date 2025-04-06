package com.example.P05PrintExcel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class PrintExcelTest {
    @Test
    public void testFilterRowsBasedOnPrice() throws IOException {
        PrintExcel.processExcel();

        try (FileInputStream inputStream = new FileInputStream(PrintExcel.OUTPUT_FILE_PATH);
             Workbook workbook = new XSSFWorkbook(inputStream)) {

            Sheet sheet = workbook.getSheetAt(0);
            int rowCount = sheet.getPhysicalNumberOfRows();

            assertTrue(rowCount > 1, "Failed to create an output file (missing header)");

            for (int currentRow = 1; currentRow < rowCount; currentRow++) {
                Row row = sheet.getRow(currentRow);
                Cell priceCell = row.getCell(0);
                assertNotNull(priceCell, "Price cell should not be null");
                assertTrue(priceCell.getNumericCellValue() >= 100, "A row contains a price less than 100");
            }
        }
    }

    @Test
    public void testAveragePriceCalculation() throws IOException {
        PrintExcel.processExcel();
        try (FileInputStream fis = new FileInputStream(PrintExcel.OUTPUT_FILE_PATH);
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);
            Cell avgValueCell = sheet.getRow(1).getCell(1);
            double averagePrice = avgValueCell.getNumericCellValue();

            assertTrue(averagePrice > 0, "Average price should be greater than 0");
            assertEquals(221.56, averagePrice, 0.01, "The average price should be 221.56");
        }
    }
}