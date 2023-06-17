package com.jesusnut.tests;

import com.jesusnut.Annotations.FrameworkAnnotation;
import com.jesusnut.constants.FrameworkConstants;
import com.jesusnut.dputils.AirlineDPUtils;
import com.jesusnut.enums.Author;
import com.jesusnut.enums.Category;
import com.jesusnut.payloads.createAirlinePayloads;
import com.jesusnut.pojos.CreateAirlines;
import com.jesusnut.pojos.CreateAirlinesAllScenarios;
import com.jesusnut.pojos.ErrorPojo;
import com.jesusnut.restutils.AssertionsUtils;
import com.jesusnut.testApiBuilders.AirlinesApiBuilder;
import com.jesusnut.utils.DateTimeUtils;
import com.jesusnut.utils.FileReadWriteUtils;
import com.jesusnut.utils.JaversPojoCompareUtils;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

public class CreateAirlineTests {

    private static final String RESPONSEBODY_ASSERTION_FAILED_CUSTOM_MESSAGE = FrameworkConstants.BOLD_START + "RESPONSE BODY ASSERTIONS FAILED !!!" + FrameworkConstants.BOLD_END;

    @Test(description = "Create an Airline using payload coming from an external file & verify response & store response body in external file - 1")
    @FrameworkAnnotation(author = Author.ANKUSH, category = {Category.REGRESSION, Category.SMOKE})
    public void createAirlineAndVerify_ExternalFiles(Method method) {

        String payload = FileReadWriteUtils.readJSONFileAndGetAsString("ExternalAirlinePayload.json");

        String randomizedPayload = createAirlinePayloads.getCreateAirlinePayload_RandomizeExternalStringPayload(payload);

        Response response = AirlinesApiBuilder.createAirline(randomizedPayload);

        System.out.println("---------------------------");

        //Printing response body on console

        response.prettyPrint();

        //Asserting response attributes

        Assertions.assertThat(response.getStatusCode()).isEqualTo(200);

        //store response body in external file

        FileReadWriteUtils.storeResponseAsJSONFile(method.getName().toLowerCase()+"_"+ DateTimeUtils.getLocalDateTime()+".json", response);

    }

    @Test(description = "Create an Airline using payload coming from an external file & verify response & store response body in external file - 2")
    @FrameworkAnnotation(author = Author.ANKUSH, category = {Category.REGRESSION, Category.SMOKE})
    public void createAirlineAndVerify_ExternalFiles2(Method method) {

        String payload = FileReadWriteUtils.readJSONFileAndGetAsString("ExternalAirlinePayload.json");

        String randomizedPayload = createAirlinePayloads.getCreateAirlinePayload_RandomizeExternalStringPayload(payload);

        Response response = AirlinesApiBuilder.createAirline(randomizedPayload);

        System.out.println("---------------------------");

        //Printing response body on console

        response.prettyPrint();

        //Asserting response attributes

        Assertions.assertThat(response.getStatusCode()).isEqualTo(200);

        //store response body in external file

        FileReadWriteUtils.storeResponseAsJSONFile(method.getName().toLowerCase()+"_"+ DateTimeUtils.getLocalDateTime()+".json", response);

    }


    @Test(description = "Create an Airline using payload as Map & verify response checking JSON path of response body")
    @FrameworkAnnotation(author = Author.ANKUSH, category = {Category.REGRESSION, Category.SMOKE})
    public void createAirlineAndVerify_Map() {

        Map<String, Object> payload = createAirlinePayloads.getCreateAirlinePayloadAsMap();

        Response response = AirlinesApiBuilder.createAirline(payload);

        System.out.println("---------------------------");

        //Printing response body on console

        response.prettyPrint();

        //Asserting response attributes

        Assertions.assertThat(response.getStatusCode()).isEqualTo(200);

        // Asserting response body (not as a POJO but fetching response body values using JSONpath and comparing with expected values{sent via 'expectedValueMap'})
        // Kinldy Put in expectedValueMap as (<JSON_PATH> from response body, values sent in request payload(expected values) )

        Map<String, Object> expectedValueMap = new LinkedHashMap<>();
        expectedValueMap.put("id", payload.get("id"));
        expectedValueMap.put("name", payload.get("name"));
        expectedValueMap.put("country", payload.get("country"));
        expectedValueMap.put("logo", payload.get("logo"));
        expectedValueMap.put("slogan", payload.get("slogan"));
        expectedValueMap.put("head_quaters", payload.get("head_quaters"));
        expectedValueMap.put("website", payload.get("website"));
        expectedValueMap.put("established", payload.get("established"));
        boolean responseBodyAssertionStatus = AssertionsUtils.assertResponseBodyAndGetResult(response, expectedValueMap);

        Assertions.assertThat(responseBodyAssertionStatus).as(RESPONSEBODY_ASSERTION_FAILED_CUSTOM_MESSAGE).isTrue();
    }


    //pojo used : com.jesusnut.pojos.CreateAirlines
    @Test(description = "Create an Airline using payload as POJO object & verify response")
    @FrameworkAnnotation(author = Author.ANKUSH, category = {Category.REGRESSION, Category.SMOKE})
    public void createAirlineAndVerify_POJO() {


        CreateAirlines payload = createAirlinePayloads.getCreateAirlinePayloadFromPOJO();

        Response response = AirlinesApiBuilder.createAirline(payload);

        System.out.println("---------------------------");

        //Printing response body on console

        response.prettyPrint();

        //Asserting response attributes

        Assertions.assertThat(response.getStatusCode()).isEqualTo(200);

        //Asserting response body.Kinldy Put in expectedValueMap as (<JSON_PATH> from response body, actual value sent in request payload )

        Map<String, Object> expectedValueMap = new LinkedHashMap<>();
        expectedValueMap.put("id", payload.getId());
        expectedValueMap.put("name", payload.getName());
        expectedValueMap.put("country", payload.getCountry());
        expectedValueMap.put("logo", payload.getLogo());
        expectedValueMap.put("slogan", payload.getSlogan());
        expectedValueMap.put("head_quaters", payload.getHeadQuaters());
        expectedValueMap.put("website", payload.getWebsite());
        expectedValueMap.put("established", payload.getEstablished());
        boolean responseBodyAssertionStatus = AssertionsUtils.assertResponseBodyAndGetResult(response, expectedValueMap);

        Assertions.assertThat(responseBodyAssertionStatus).as(RESPONSEBODY_ASSERTION_FAILED_CUSTOM_MESSAGE).isTrue();
    }

    //pojo used : com.jesusnut.pojos.CreateAirlines
    @Test(description = "Create an Airline using payload as POJO object (default values used for POJO variables) & verify response")
    @FrameworkAnnotation(author = Author.ANKUSH, category = {Category.REGRESSION, Category.SMOKE})
    public void createAirlineAndVerify_POJO2() {

        //https://www.youtube.com/watch?v=CBRNmi1NAKE&list=PL-a9eJ2NZlbRFnZbN8glYFGaEudds86-k&index=20

        CreateAirlines payload = new CreateAirlines().toBuilder().build();

        Response response = AirlinesApiBuilder.createAirline(payload);

        System.out.println("---------------------------");

        //Printing response body on console

        response.prettyPrint();

        //Asserting response attributes

        Assertions.assertThat(response.getStatusCode()).isEqualTo(200);

        //Asserting response body.Kinldy Put in expectedValueMap as (<JSON_PATH> from response body, actual value sent in request payload )

        Map<String, Object> expectedValueMap = new LinkedHashMap<>();
        expectedValueMap.put("id", payload.getId());
        expectedValueMap.put("name", payload.getName());
        expectedValueMap.put("country", payload.getCountry());
        expectedValueMap.put("logo", payload.getLogo());
        expectedValueMap.put("slogan", payload.getSlogan());
        expectedValueMap.put("head_quaters", payload.getHeadQuaters());
        expectedValueMap.put("website", payload.getWebsite());
        expectedValueMap.put("established", payload.getEstablished());
        boolean responseBodyAssertionStatus = AssertionsUtils.assertResponseBodyAndGetResult(response, expectedValueMap);

        Assertions.assertThat(responseBodyAssertionStatus).as(RESPONSEBODY_ASSERTION_FAILED_CUSTOM_MESSAGE).isTrue();
    }

    //pojo used : com.jesusnut.pojos.CreateAirlines
    @Test(description = "Create an Airline using payload as POJO object & verify response AS $$$ POJO $$$")
    @FrameworkAnnotation(author = Author.ANKUSH, category = {Category.REGRESSION, Category.SMOKE})
    public void createAirlineAndVerify_POJO3() {


        CreateAirlines payload = createAirlinePayloads.getCreateAirlinePayloadFromPOJO();

        Response response = AirlinesApiBuilder.createAirline(payload);

        System.out.println("---------------------------");

        //Printing response body on console

        response.prettyPrint();

        //Asserting response attributes

        Assertions.assertThat(response.getStatusCode()).isEqualTo(200);

        // Assertion of response body as a POJO.

        //Creating response POJO [pojo used : com.jesusnut.pojos.CreateAirlines]
        //['@JsonIgnoreProperties(ignoreUnknown = true)'] used in 'com.jesusnut.pojos.CreateAirlines' to make sure extra fields in response
        //i.e '_id' and '_v' are not deserealized. In this way, we can DIRECTLY COMPARE REQUEST POJO and RESPONSE POJO.

        CreateAirlines responseAsCreateAirlinesObject = response.as(CreateAirlines.class);

        JaversPojoCompareUtils.comparePOJOObjects(payload, responseAsCreateAirlinesObject);


    }

    //pojo used : com.jesusnut.pojos.CreateAirlines
    @Test(description = "Data Driven Testing using DP and POJO", dataProvider = "getDataForCreateAirlines", dataProviderClass = AirlineDPUtils.class)
    @FrameworkAnnotation(author = Author.ANKUSH, category = {Category.REGRESSION, Category.SMOKE})
    public void createAirlineAndVerify_DP_POJO(Map<String, String> data) {

        CreateAirlines payload = createAirlinePayloads.getCreateAirlinePayload_POJO_DP(data);

        Response response = AirlinesApiBuilder.createAirline(payload);

        System.out.println("---------------------------");

        //Printing response body on console

        response.prettyPrint();

        //Asserting response attributes

        Assertions.assertThat(response.getStatusCode()).isEqualTo(Integer.parseInt(payload.getExpectedStatusCode()));

        //Asserting response body.Kinldy Put in expectedValueMap as (<JSON_PATH> from response body, actual value sent in request payload )

        Map<String, Object> expectedValueMap = new LinkedHashMap<>();
        expectedValueMap.put("id", payload.getId());
        expectedValueMap.put("name", payload.getName());
        expectedValueMap.put("country", payload.getCountry());
        expectedValueMap.put("logo", payload.getLogo());
        expectedValueMap.put("slogan", payload.getSlogan());
        expectedValueMap.put("head_quaters", payload.getHeadQuaters());
        expectedValueMap.put("website", payload.getWebsite());
        expectedValueMap.put("established", payload.getEstablished());
        boolean responseBodyAssertionStatus = AssertionsUtils.assertResponseBodyAndGetResult(response, expectedValueMap);

        Assertions.assertThat(responseBodyAssertionStatus).as(RESPONSEBODY_ASSERTION_FAILED_CUSTOM_MESSAGE).isTrue();


    }

    //pojo used : com.jesusnut.pojos.CreateAirlines
    @Test(enabled = true, description = "Data Driven Testing using DP Method (using POIJI) & POJO", dataProvider = "getDataForCreateAirlines_Poiji", dataProviderClass = AirlineDPUtils.class)
    @FrameworkAnnotation(author = Author.ANKUSH, category = {Category.REGRESSION, Category.SMOKE})
    public void createAirlineAndVerify_DP_POJO_POIJI(CreateAirlines data) {

        CreateAirlines payload = createAirlinePayloads.getCreateAirlinePayload_POJO_DP_POIJI(data);

        Response response = AirlinesApiBuilder.createAirline(payload);

        System.out.println("---------------------------");

        //Printing response body on console

        response.prettyPrint();

        //Asserting response attributes

        Assertions.assertThat(response.getStatusCode()).isEqualTo(Integer.parseInt(data.getExpectedStatusCode()));

        //response.as(CreateAirlines.class);

        //Asserting response body.Kinldy Put in expectedValueMap as (<JSON_PATH> from response body, actual value sent in request payload )

        Map<String, Object> expectedValueMap = new LinkedHashMap<>();
        expectedValueMap.put("id", payload.getId());
        expectedValueMap.put("name", payload.getName());
        expectedValueMap.put("country", payload.getCountry());
        expectedValueMap.put("logo", payload.getLogo());
        expectedValueMap.put("slogan", payload.getSlogan());
        expectedValueMap.put("head_quaters", payload.getHeadQuaters());
        expectedValueMap.put("website", payload.getWebsite());
        expectedValueMap.put("established", payload.getEstablished());
        boolean responseBodyAssertionStatus = AssertionsUtils.assertResponseBodyAndGetResult(response, expectedValueMap);

        Assertions.assertThat(responseBodyAssertionStatus).as(RESPONSEBODY_ASSERTION_FAILED_CUSTOM_MESSAGE).isTrue();
    }

    //pojo used : com.jesusnut.pojos.CreateAirlinesAllScenarios
    @Test(enabled = true, description = "Test all positive and negative sceanrios of Create Airlines", dataProvider = "getDataForCreateAirlines_AllScenarios", dataProviderClass = AirlineDPUtils.class)
    @FrameworkAnnotation(author = Author.ANKUSH, category = {Category.REGRESSION, Category.SMOKE})
    public void createAirlineAndVerify_AllScenarios(Map<String, String> data) {

        CreateAirlinesAllScenarios payload = createAirlinePayloads.getCreateAirlinePayload_AllSceanrios(data);

        Response response = AirlinesApiBuilder.createAirline(payload);

        //writing test case based om the data provided in excel sheetname : 'createAirlineAllScenarios'.

        if (!data.get("expectedStatusCode").equalsIgnoreCase("200")) {

            //in case of scenario description named 'CreateAirline_DuplicateID', need to pass same payload twice for intentional duplication.

            if (data.get("scenarioDesc").equalsIgnoreCase("CreateAirline_DuplicateID")) {

                response = AirlinesApiBuilder.createAirline(payload);

            }

            ErrorPojo responseAsErrorPojoObject = response.as(ErrorPojo.class);
            Assertions.assertThat(response.getStatusCode()).isEqualTo(Integer.parseInt(payload.getExpectedStatusCode()));
            Assertions.assertThat(responseAsErrorPojoObject.getMessage()).isEqualTo(payload.getExectedErrorMessage());

        } else {

            Assertions.assertThat(response.getStatusCode()).isEqualTo(Integer.parseInt(payload.getExpectedStatusCode()));

            Map<String, Object> expectedValueMap = new LinkedHashMap<>();
            expectedValueMap.put("name", payload.getName());
            expectedValueMap.put("country", payload.getCountry());
            expectedValueMap.put("logo", payload.getLogo());
            expectedValueMap.put("slogan", payload.getSlogan());
            expectedValueMap.put("head_quaters", payload.getHeadQuaters());
            expectedValueMap.put("website", payload.getWebsite());
            expectedValueMap.put("established", payload.getEstablished());
            boolean responseBodyAssertionStatus = AssertionsUtils.assertResponseBodyAndGetResult(response, expectedValueMap);
            Assertions.assertThat(responseBodyAssertionStatus).as(RESPONSEBODY_ASSERTION_FAILED_CUSTOM_MESSAGE).isTrue();

        }

    }
}
