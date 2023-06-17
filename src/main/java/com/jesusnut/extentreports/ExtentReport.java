package com.jesusnut.extentreports;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.jesusnut.constants.FrameworkConstants;
import com.jesusnut.enums.Author;
import com.jesusnut.enums.Category;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.awt.*;
import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ExtentReport {

    private static ExtentReports extentReports;

     static void initReports() {

        if (Objects.isNull(extentReports)){
            extentReports = new ExtentReports();
        }
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(FrameworkConstants.EXTENTREPORT_FILEPATH);
        extentSparkReporter.config().setReportName(FrameworkConstants.FRAMEWORK_EXTENTREPORT_NAME);
        extentSparkReporter.config().setDocumentTitle(FrameworkConstants.FRAMEWORK_EXTENTREPORT_DOCUMENT_TITLE);
        extentSparkReporter.config().setTheme(Theme.DARK);
        extentSparkReporter.config().setEncoding("utf-8");
        extentSparkReporter.config().setTimelineEnabled(false);
         extentSparkReporter.config().setJs(FrameworkConstants.FRAMEWORK_EXTENTREPORT_CUSTOM_JS);
        extentSparkReporter.viewConfigurer().viewOrder().as(FrameworkConstants.FRAMEWORK_EXTENTREPORT_VIEWING_ORDER).apply();
        extentReports.attachReporter(extentSparkReporter);
        extentReports.setReportUsesManualConfiguration(true);
    }

    @SneakyThrows
     static void tearDownReports() {

        if (Objects.nonNull(extentReports)) {
            extentReports.flush();
        }
        Desktop.getDesktop().browse(new File(FrameworkConstants.EXTENTREPORT_FILEPATH).toURI());

    }

     static void createTest(String testCaseName, String description) {

        ExtentTest test = extentReports.createTest(testCaseName, description);

        ExtentTestManager.setExtentTest(test);

    }

     static void assignAuthor(Author author) {

        ExtentTestManager.getExtentTest().assignAuthor(author.toString());

    }


     static void assignCategory(Category[] category) {

        for (Category temp_category : category) {

            ExtentTestManager.getExtentTest().assignCategory(temp_category.toString());
        }
    }

     static void setStartTime(long time) {

        ExtentTestManager.getExtentTest().getModel().setStartTime(getTime(time));

    }

     static void setEndTime(long time) {

        ExtentTestManager.getExtentTest().getModel().setEndTime(getTime(time));
    }

    /**
     * Returns a Date object representing this Calendar's time value.<br>
     *
     * @param millis - time in long format
     * @return java.util.Date
     */

    private static Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }


}
