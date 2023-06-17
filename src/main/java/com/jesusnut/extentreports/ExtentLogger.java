package com.jesusnut.extentreports;

import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.jesusnut.constants.FrameworkConstants;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ExtentLogger {

    // log Pass

    public static void pass(String details) {

        ExtentTestManager.getExtentTest().pass(MarkupHelper.createLabel(details, ExtentColor.GREEN));

    }

    // log Fail

    public static void fail(String details) {

        ExtentTestManager.getExtentTest().fail(MarkupHelper.createLabel(details, ExtentColor.RED));

    }

    // log Skip

    public static void skip(String details) {

        ExtentTestManager.getExtentTest().skip(MarkupHelper.createLabel(details, ExtentColor.AMBER));

    }

    // log Warning

    public static void warning(String details) {

        ExtentTestManager.getExtentTest().warning(MarkupHelper.createLabel(details, ExtentColor.BROWN));

    }

    // log Info

    public static void info(String details) {

        ExtentTestManager.getExtentTest().info(MarkupHelper.createLabel(details, ExtentColor.BLACK));

    }

    // log JSON

    public static void info(Markup markup) {

        ExtentTestManager.getExtentTest().info(markup);

    }

    // log exception in formatted way

    public static void logStackTraceInfoInExtentReport(String stackTrace, String exceptionMessage) {

        String formattedText = "<pre>" + stackTrace.replace(",", "<br>") + "</pre>";
        String formattedTrace = "<details>\n" +
                "    <summary style=\"font-family:'Courier New';color:red\"><b><i>Click Here To See Exception Logs</i></b></summary>\n" +
                "    " + formattedText + "\n" +
                "</details>\n";
        ExtentTestManager.getExtentTest().fail(MarkupHelper.createLabel(FrameworkConstants.BOLD_START + "EXCEPTION : " + exceptionMessage + FrameworkConstants.BOLD_END, ExtentColor.RED));
        ExtentTestManager.getExtentTest().fail(formattedTrace);
    }

    // to log request including request body

    public static void logRequest(RequestSpecification requestSpecification) {

        QueryableRequestSpecification query = SpecificationQuerier.query(requestSpecification);
        info(FrameworkConstants.ICON_SMILEY_SENDINGREQUEST + "  Request URI is : " + FrameworkConstants.BOLD_START + query.getURI() + FrameworkConstants.BOLD_END);
        info(FrameworkConstants.ICON_SMILEY_SENDINGREQUEST + "  Request METHOD is : " + FrameworkConstants.BOLD_START + query.getMethod() + FrameworkConstants.BOLD_END);
        info(FrameworkConstants.ICON_SMILEY_SENDINGREQUEST + "  Request HEADER(s) is/are : ");
        printHeaders(query.getHeaders().asList());
        info(FrameworkConstants.ICON_SMILEY_SENDINGREQUEST + "  Request BODY is  : ");
        info(MarkupHelper.createCodeBlock(query.getBody(), CodeLanguage.JSON));

    }


    // to log response

    public static void logResponse(Response response) {

        info(FrameworkConstants.ICON_SMILEY_GOTRESPONSE + "  Response STATUS is : " + response.getStatusCode() + " - " + response.getStatusLine());
        info(FrameworkConstants.ICON_SMILEY_GOTRESPONSE + "  Response HEADER(s) is/are : ");
        printHeaders(response.getHeaders().asList());
        info(FrameworkConstants.ICON_SMILEY_GOTRESPONSE + "  Response BODY is  : ");
        info(MarkupHelper.createCodeBlock(response.asPrettyString(), CodeLanguage.JSON));


    }

    //to log as table in extent report

    private static void printHeaders(List<Header> headers) {

        String[][] temp = new String[headers.size()][2];

        for (int i = 0; i < headers.size(); i++) {

            Header header = headers.get(i);

            String nameOfHeader = header.getName();
            String valueOfHeader = header.getValue();

            temp[i][0] = nameOfHeader;
            temp[i][1] = valueOfHeader;

        }
        info(MarkupHelper.createTable(temp));
    }


}
