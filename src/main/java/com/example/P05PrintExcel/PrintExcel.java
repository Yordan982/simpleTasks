package com.example.P05PrintExcel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

public class PrintExcel {
    public static final String INPUT_FILE_PATH = "input.xlsx";
    public static final String OUTPUT_FILE_PATH = "filtered_data.xlsx";

    public static void processExcel() throws IOException {

        try (FileInputStream inputStream = new FileInputStream(INPUT_FILE_PATH);
             Workbook workbook = new XSSFWorkbook(inputStream)) {
            Sheet sheet = workbook.getSheetAt(0);

            List<Row> filteredRows = new ArrayList<>();
            double totalPrice = 0;
            int count = 0;

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue;
                Cell priceCell = row.getCell(0);

                if (priceCell != null && priceCell.getCellType() == CellType.NUMERIC) {
                    double price = priceCell.getNumericCellValue();
                    if (price >= 100) {
                        filteredRows.add(row);
                        totalPrice += price;
                        count++;
                    }
                }
            }

            double averagePrice = (count > 0) ? totalPrice / count : 0;

            Workbook newWorkbook = new XSSFWorkbook();
            Sheet newSheet = newWorkbook.createSheet("Filtered data");

            Row headerRow = sheet.getRow(0);
            Row newHeaderRow = newSheet.createRow(0);
            for (int i = 0; i < headerRow.getPhysicalNumberOfCells(); i++) {
                Cell headerCell = newHeaderRow.createCell(i);
                headerCell.setCellValue(headerRow.getCell(i).getStringCellValue());
            }

            int rowNum = 1;
            for (Row row : filteredRows) {
                Row newRow = newSheet.createRow(rowNum++);
                for (int i = 0; i < row.getPhysicalNumberOfCells(); i++) {
                    Cell oldCell = row.getCell(i);
                    if (oldCell != null) {
                        Cell newCell = newRow.createCell(i);
                        switch (oldCell.getCellType()) {
                            case NUMERIC:
                                newCell.setCellValue(oldCell.getNumericCellValue());
                                break;
                            case STRING:
                                newCell.setCellValue(oldCell.getStringCellValue());
                                break;
                            default:
                                break;
                        }
                    }
                }
            }

            Row firstRow = newSheet.getRow(0);
            if (firstRow == null) {
                firstRow = newSheet.createRow(0);
            }
            Cell averageLabelCell = firstRow.createCell(1);
            averageLabelCell.setCellValue("Average price");

            Row secondRow = newSheet.getRow(1);
            if (secondRow == null) {
                secondRow = newSheet.createRow(1);
            }
            Cell averageValueCell = secondRow.createCell(1);
            averageValueCell.setCellValue(averagePrice);

            try (FileOutputStream fileOut = new FileOutputStream(OUTPUT_FILE_PATH)) {
                newWorkbook.write(fileOut);
            }

            newWorkbook.close();
            System.out.println("The filtered data has been saved at: " + OUTPUT_FILE_PATH);
        }
    }
}