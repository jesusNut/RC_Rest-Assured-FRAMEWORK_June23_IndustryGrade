package com.jesusnut.restutils;


import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.jesusnut.constants.FrameworkConstants;
import com.jesusnut.extentreports.ExtentLogger;
import io.restassured.response.Response;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AssertionsUtils {

    public static boolean assertResponseBodyAndGetResult(Response response, Map<String, Object> expectedValuesMap) {

        List<AssertionKeys> actualValuesList = new ArrayList<>();
        // Table headers
        actualValuesList.add(new AssertionKeys("JSON_PATH", "EXPECTED_VALUE", "ACTUAL_VALUE", "RESULT"));
        boolean allMatched = true;
        // Iterate to extract value from response using jsonpath
        Set<String> jsonPaths = expectedValuesMap.keySet();
        for (String jsonPath : jsonPaths) {
            Optional<Object> actualValue = Optional.ofNullable(response.jsonPath().get(jsonPath));
            if (actualValue.isPresent()) {
                Object value = actualValue.get();

                // Assert actual and expected values
                if (value.equals(expectedValuesMap.get(jsonPath)))
                    // if value is matched then add details
                    actualValuesList.add(new AssertionKeys(jsonPath, expectedValuesMap.get(jsonPath), value, "MATCHED"+ FrameworkConstants.ICON_SMILEY_MATCHED));
                else {
                    // if single assertion is failed then to update final result as failure
                    allMatched = false;
                    actualValuesList.add(new AssertionKeys(jsonPath, expectedValuesMap.get(jsonPath), value, "NOT_MATCHED"+FrameworkConstants.ICON_SMILEY_NOTMATCHED));
                }
            }
            // if jsonpath does not exist in the response
            else {
                allMatched = false;
                actualValuesList.add(new AssertionKeys(jsonPath, expectedValuesMap.get(jsonPath), "<code style=color:yellow><i>VALUE_NOT_FOUND &#129326;</i></code>", "NOT_MATCHED"+FrameworkConstants.ICON_SMILEY_NOTMATCHED));
            }
        }

        // Log actual result fetched as 'actualValuesList' as table in extent report

        printAssertionsDetailsInExtentReport(actualValuesList);

        return allMatched;

    }

    //to log as table in extent report

    private static void printAssertionsDetailsInExtentReport(List<AssertionKeys> actualValuesList) {

        String[][] temp = new String[actualValuesList.size()][4];

        for (int i = 0; i < actualValuesList.size(); i++) {

            AssertionKeys assertionKeys = actualValuesList.get(i);

            String jsonPath = assertionKeys.getJsonPath();
            Object expectedValue = assertionKeys.getExpectedValue();
            Object actualValue = assertionKeys.getActualValue();
            String result = assertionKeys.getResult();

            temp[i][0] = jsonPath;
            temp[i][1] = String.valueOf(expectedValue);
            temp[i][2] = String.valueOf(actualValue);
            temp[i][3] = result;

        }
        ExtentLogger.info(MarkupHelper.createTable(temp));
    }


}

