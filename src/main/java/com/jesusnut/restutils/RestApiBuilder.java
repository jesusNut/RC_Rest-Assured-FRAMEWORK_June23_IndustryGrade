package com.jesusnut.restutils;


import com.jesusnut.extentreports.ExtentLogger;
import com.jesusnut.utils.JsonReadWriteUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RestApiBuilder {


    private static RequestSpecification getRequestSpecification(String basePath, Object requestPayload, Map<String, String> headers) {

        String baseURI = (String) JsonReadWriteUtils.getJsonDataAsMap(RestApiBuilderHelper.getEnvDataJSONFilePath()).get("createAirLineBaseURI");

        return RestAssured.given().baseUri(baseURI)
                .basePath(basePath)
                .contentType(ContentType.JSON)
                .headers(headers)
                .body(requestPayload);

    }

    public static Response performPost(String basePath, Object requestPayload, Map<String, String> headers) {

        RequestSpecification requestSpecification = getRequestSpecification(basePath, requestPayload, headers);

        Response response = requestSpecification.log().all().post();

        ExtentLogger.logRequest(requestSpecification);
        ExtentLogger.logResponse(response);

        return response;


    }


}
