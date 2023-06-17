package com.jesusnut.dputils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jesusnut.pojos.CreateAirlines;
import com.jesusnut.utils.ExcelUtils;
import com.poiji.bind.Poiji;
import org.testng.annotations.DataProvider;

import com.jesusnut.constants.FrameworkConstants;

public final class AirlineDPUtils {

    // DATA PROVIDER with return type as a MAP.
    // This approach can be used in 2 ways:
    //Pre-requisite: Use different sheets to get test data for different test methods.
    //For example : sheet named 'createAirlines' will be used for a test method named 'createAirlineAndVerify_DataProvider'
    //similarly, a different sheet named 'blahblah' will be used for a test method named 'blahblahTester'.
    // 1. {BEST WAY} Write different '@DataProvider' methods for different test methods which will fetch different test data based on sheetName provided.
    // 2. Customize the same '@DataProvider' method for different test cases (using-if-else) which will fetch different test data sets based on sheetName provided.
    // I have used demoed apprach 1 (usage of 2 different DP) & 2 (in method named : getDataForCreateAirlines ) below (Commented out).

    @DataProvider(name = "getDataForCreateAirlines")
    public  Object[] getDataForCreateAirlines(Method method) throws IOException {

        //getting data from ExcelUtils class method name :: getExcelData()

        List<Map<String, String>> allDatalist;

        //CONFIGURATION

//        if (method.getName().equalsIgnoreCase("createAirlineAndVerify_DataProvider")) {
//
//            allDatalist =
//                    ExcelUtils.getExcelData("createAirlines", FrameworkConstants.DATAEXCEL_WORKBOOK_NAME);
//
//        } else if (method.getName().equalsIgnoreCase("blahblahTester")) {
//
//            allDatalist =
//                    ExcelUtils.getExcelData("blahblah", FrameworkConstants.DATAEXCEL_WORKBOOK_NAME);
//        }
//
//        else{
//
//            throw new FrameworkException(FrameworkConstants.FRAMEWORK_EXCEPTION_GENERIC_MESSAGE+" ABORT !!! : test method <b><i>'"+method.getName()+"'</i></b> is not configured in Data Provider method 'getData' in com.jesusnut.dputils.DataProviderUtils /n OR check spelling of <b><i>'"+method.getName()+"'</i></b> test method in 'CONFIGURATION' section of com.jesusnut.dputils.DataProviderUtils#getData()");
//        }

        allDatalist =
                ExcelUtils.getExcelData("createAirline", FrameworkConstants.DATAEXCEL_WORKBOOK_NAME);

        // filter the data based on column name 'execute' =yes/no.

        List<Map<String, String>> iterationsTobeRunList = new ArrayList<Map<String, String>>();

        for (int i = 0; i < allDatalist.size(); i++) {

            if (isTestCaseDataExecutable(allDatalist, i)) {

                iterationsTobeRunList.add(allDatalist.get(i));
            }

        }

        return iterationsTobeRunList.toArray();
    }

    @DataProvider(name = "getDataForCreateAirlines_Poiji")
    public  Object[] getDataForCreateAirlines_Poiji(Method method) {

        List<CreateAirlines> createAirlinesDataFromExcel =
                Poiji.fromExcel(new File(FrameworkConstants.DATAEXCEL_LOCATION + FrameworkConstants.DATAEXCEL_WORKBOOK_NAME + ".xlsx"), CreateAirlines.class);

        return createAirlinesDataFromExcel.toArray();

    }

    @DataProvider(name = "getDataForCreateAirlines_AllScenarios")
    public  Object[] getDataForCreateAirlines_AllScenarios(Method method) {

        List<Map<String, String>> allDatalist = ExcelUtils.getExcelData("createAirlineAllScenarios", FrameworkConstants.DATAEXCEL_WORKBOOK_NAME);

        List<Map<String, String>> iterationsTobeRunList = new ArrayList<Map<String, String>>();

        for (int i = 0; i < allDatalist.size(); i++) {

            if (isTestCaseDataExecutable(allDatalist, i)) {

                iterationsTobeRunList.add(allDatalist.get(i));
            }

        }

        return iterationsTobeRunList.toArray();


    }

    //method to check if data for the test case that needs to be executed is marked as yes/no in excel

    private static boolean isTestCaseDataExecutable(List<Map<String, String>> allDatalist, int i) {
        return allDatalist.get(i).get("execute").equalsIgnoreCase("yes");
    }

}
