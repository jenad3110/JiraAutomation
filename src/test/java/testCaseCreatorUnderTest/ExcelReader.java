package testCaseCreatorUnderTest;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader {

    public static List<TestCase> readTestCases(String filePath) {
        List<TestCase> testCases = new ArrayList<>();

        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fileInputStream)) {

            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    continue; // Skip header row
                }

                String summary = row.getCell(2).getStringCellValue();
                String testStatus = row.getCell(9).getStringCellValue();
                String actualResults = row.getCell(8).getStringCellValue();
                String expectedResults = row.getCell(7).getStringCellValue();
                String testData = row.getCell(5).getStringCellValue();
                String testSteps = row.getCell(4).getStringCellValue();

                testCases.add(new TestCase(summary, testStatus, actualResults, expectedResults, testData, testSteps));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return testCases;
    }
}
