package com.jesusnut.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.jesusnut.constants.FrameworkConstants;
import com.jesusnut.exceptions.FrameworkException;

public final class ExcelUtils {

    private ExcelUtils() {
    }

    // to get entire excel data in form of List of Maps

    public static List<Map<String, String>> getExcelData(String sheetName, String workBookName) {

        String excel_sheet_path = FrameworkConstants.DATAEXCEL_LOCATION + workBookName + ".xlsx";

        FileInputStream fis;
        try {
            fis = new FileInputStream(excel_sheet_path);
        } catch (FileNotFoundException e) {

            throw new FrameworkException(
                    FrameworkConstants.FRAMEWORK_EXCEPTION_GENERIC_MESSAGE + " Excel Data sheet not found. Check path of Excel Data sheet");

        }

        Workbook workbook = null;

        try {
            workbook = new XSSFWorkbook(fis);
        } catch (IOException e) {

            throw new FrameworkException(FrameworkConstants.FRAMEWORK_EXCEPTION_GENERIC_MESSAGE
                    + " Exception while reading Excel Data sheet.");

        }

        Sheet sheet = workbook.getSheet(sheetName);

        // get last row number

        int total_rows = sheet.getLastRowNum();

        // get last column number

        Row row = sheet.getRow(0);

        int total_cols = row.getLastCellNum();

        List<Map<String, String>> list = new ArrayList<Map<String, String>>();

        Map<String, String> datamap = null;

        for (int i = 1; i <= total_rows; i++) {

            datamap = new HashMap<String, String>();

            for (int j = 0; j < total_cols; j++) {

                String key = sheet.getRow(0).getCell(j).getStringCellValue();

                String value = sheet.getRow(i).getCell(j).getStringCellValue();

                //In excel sheet, if a cell value contains, the string 'Random/random', then,
                //we decide to generate numeral data (but, as Data Type-String).
                // Write like a String -> 'Random_4' in excel sheet for a random numeral data of size 4.
                // Write like a String -> 'Random' in excel sheet for a random numeral data of size 6 (default size).
                // if size of numeral data desired is not provided, then default size set = 6.
                //you can change the default size of the randomly generated numeral data if it is not explicity provided in
                //"checkAndTransformDataAsPerRules(value,<SIZE>)" method.

                // Resultant random numeral data is sent as a String from here to DP. So convert to Integer when used in intended methods.

                value = checkAndTransformDataAsPerRules(value,6);

                datamap.put(key, value);

            }

            list.add(datamap);

        }

        return list;
    }

    //This method checks if any value of the cell contains the word 'Random/random', and then generate and returns random numeral data.

    private static String checkAndTransformDataAsPerRules(String value, int defaultSize) {

        int size = defaultSize;



        if (value.contains("Random") || value.contains("random")) {

            if (value.contains("_")) {

                String[] temp = value.split("_");
                size = Integer.parseInt(temp[1]);
            }

            value = String.valueOf(RandomDataGenerator.getRandomNumber(size));
        }
        return value;
    }
}
