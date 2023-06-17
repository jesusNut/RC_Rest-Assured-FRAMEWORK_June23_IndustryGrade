package com.jesusnut.constants;


import com.aventstack.extentreports.reporter.configuration.ViewName;
import com.jesusnut.utils.DateTimeUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FrameworkConstants {

    // Constants related to *** ACROSS FRAMEWORK ***

    private static final String WORKING_DIRECTORY_PATH = System.getProperty("user.dir");
    public static final String FRAMEWORK_EXCEPTION_GENERIC_MESSAGE = "JESUSNUT REST ASSURED API TESTING FRAMEWORK [Powered by Retarget Common] :";

    // Constants related to *** FOLDER TO KEEP REQUEST JSONs & to STORE RESPONSE
    // JSONs ***

    public static final String REQUEST_JSON_FOLDER_PATH = WORKING_DIRECTORY_PATH + "/src/test/resources/RequestJsonsInput/";
    public static final String RESPONSE_JSON_FOLDER_PATH = WORKING_DIRECTORY_PATH + "/ResponseJsonOutput/";


    // Constants related to *** EXCEL DATA SHEET for DATA DRIVEN TESTING ***

    public static final String DATAEXCEL_LOCATION = WORKING_DIRECTORY_PATH + "/src/test/resources/excel/";
    public static final String DATAEXCEL_WORKBOOK_NAME = "testdata";
    public static final String DATAEXCEL_RUNMANAGERSHEET_NAME="runmanager";

    // Constants related to *** Environment Related JSON files ***

    public static final String QA_ENV_JSONDATA_FILEPATH = WORKING_DIRECTORY_PATH + "/src/test/resources/EnvJSONData/airlinesQaEnvData.json";
    public static final String DEV_ENV_JSONDATA_FILEPATH = WORKING_DIRECTORY_PATH + "/src/test/resources/EnvJSONData/airlinesDevEnvData.json";

    // Constants related to *** ICONS ***

    public static final String BOLD_START = "<b>";
    public static final String BOLD_END = "</b>";

    public static final String ICON_SMILEY_PASS = "&#128525;";
    public static final String ICON_SMILEY_SKIP = "&#128562;";
    public static final String ICON_SMILEY_FAIL = "&#129324;";
    public static final String ICON_SMILEY_MATCHED = "&#9989;";
    public static final String ICON_SMILEY_NOTMATCHED = "&#9940;";
    public static final String ICON_SMILEY_SENDINGREQUEST = "<i  class=\"fa fa-cloud-upload\" aria-hidden=\"true\" style=\"font-size:20px;color:#F5EA08\"></i>";
    public static final String ICON_SMILEY_GOTRESPONSE = "<i class=\"fa fa-download\" aria-hidden=\"true\" style=\"font-size:20px;color:#F5EA08\"></i>";


    // Constants related to *** EXTENT REPORT ***

    public static final String EXTENTREPORT_FILEPATH = getExtentReportFilePath();
    public static final String FRAMEWORK_EXTENTREPORT_DOCUMENT_TITLE = "JesusNut RestAssured Framework Result";
    public static final String FRAMEWORK_EXTENTREPORT_NAME = "JesusNut RestAssured Framework Results";
    public static final ViewName[] FRAMEWORK_EXTENTREPORT_VIEWING_ORDER = new ViewName[]{ViewName.DASHBOARD,
            ViewName.TEST, ViewName.CATEGORY, ViewName.AUTHOR, ViewName.DEVICE, ViewName.LOG};
    public static final String FRAMEWORK_EXTENTREPORT_LOGO_PATH = "url('file:///D:/INTELLI J WORKSPACE/RetargetCommon_Rest-Assured-FRAMEWORK_June23/src/main/resources/extentCustomizationResources/myCat.png')";
    public static final String FRAMEWORK_EXTENTREPORT_CUSTOM_JS = getCustomJSForExtentReporting();


    //Creates Extent Report Path

    private static String getExtentReportFilePath() {

        String finalDateTimeFormat = DateTimeUtils.getLocalDateTime();
        return WORKING_DIRECTORY_PATH + "/" + "extent-test-output" + "/" + finalDateTimeFormat + "_" + "index.html";


    }

    private static String getCustomJSForExtentReporting() {

        return "var els = document.getElementsByClassName(\"m-t-10 m-l-5\");\r\n" + "\r\n"
                + "        for (var i=0; i<els.length; i++) {\r\n" + "            els[i].style.fontStyle=\"italic\"\r\n"
                + "            els[i].style.fontSize=\"14px\"\r\n" + "\r\n" + "}\r\n" + "\r\n"
                + "var end_time = document.querySelectorAll('span.badge-danger:not(.log)');\r\n" + "\r\n" + "\r\n"
                + "for (var i=0; i<end_time.length; i++) {\r\n"
                + "            end_time[i].style.backgroundColor=\"#6c757d\"\r\n" + "             \r\n" + " }\r\n"
                + " \r\n" + " var end_time = document.querySelectorAll('span.badge-success:not(.log)');\r\n" + "\r\n"
                + "\r\n" + "for (var i=0; i<end_time.length; i++) {\r\n"
                + "            end_time[i].style.backgroundColor=\"#6c757d\"\r\n" + "             \r\n" + " }\r\n"
                + "\r\n" + "var methodName = document.querySelectorAll('h5.test-status');\r\n" + "\r\n"
                + "for (var i=0; i<methodName.length; i++) {\r\n"
                + "            methodName[i].style.fontSize=\"18px\"\r\n"
                + "            methodName[i].style.fontFamily=\"Arial Black\"\r\n" + "\r\n" + "             \r\n"
                + " }\r\n" + "\r\n"
                + "var start_time = document.querySelectorAll('span.badge-default:not(.badge-pill):not(.uri-anchor)');\r\n"
                + "\r\n" + "\r\n" + "for (var i=0; i<start_time.length; i++) {\r\n"
                + "            start_time[i].style.backgroundColor=\"#343a40\"\r\n" + "             \r\n" + " }\r\n"
                + "\r\n" + "\r\n" + "document.getElementsByClassName('logo')[0].style.backgroundImage = \"url('')\"\r\n"
                + "\r\n" + "document.getElementsByClassName('nav-logo')[0].style.backgroundImage = \""
                + FrameworkConstants.FRAMEWORK_EXTENTREPORT_LOGO_PATH + "\"\r\n" + "\r\n"
                + "document.getElementsByClassName('nav-logo')[0].style.width=\"70px\"\r\n" + "\r\n"
                + "document.getElementsByClassName('nav-logo')[0].style.height=\"70px\"";
    }


}
