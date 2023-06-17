package com.jesusnut.payloads;

import com.jesusnut.enums.RandomDataTypeNames;
import com.jesusnut.pojos.CreateAirlines;
import com.jesusnut.pojos.CreateAirlinesAllScenarios;
import com.jesusnut.utils.DateTimeUtils;
import com.jesusnut.utils.RandomDataGenerator;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.LinkedHashMap;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class createAirlinePayloads {

    public static String getCreateAirlinePayloadAsString(int id, String name, String country, String logo, String slogan, String head_quaters, String website, String established) {

        return "{\n" + "        \"id\": " + id + ",\n" + "        \"name\": \"" + name + "\",\n" + "        \"country\": \"" + country + "\",\n" + "        \"logo\": \"" + logo + "\",\n" + "        \"slogan\": \"" + slogan + "\",\n" + "        \"head_quaters\": \"" + head_quaters + "\",\n" + "        \"website\": \"" + website + "\",\n" + "        \"established\": \"" + established + "\"\n" + "}";


    }

    public static String getCreateAirlinePayload_RandomizeExternalStringPayload( String fixedPayloadFromExternalFile) {

        return fixedPayloadFromExternalFile
                .replace("1234", Integer.toString(RandomDataGenerator.getRandomNumber(8)))
                .replace("SOMEAIRLINES", RandomDataGenerator.getRandomDataFor(RandomDataTypeNames.FIRSTNAME)+" Airlines")
                .replace("SOMECOUNTRY", RandomDataGenerator.getRandomDataFor(RandomDataTypeNames.COUNTRY))
                .replace("SOMELOGO", RandomDataGenerator.getRandomAlphabets(22))
                .replace("SOMESLOGAN", RandomDataGenerator.getRandomAlphabets(20))
                .replace("SOMEHQ", RandomDataGenerator.getRandomDataFor(RandomDataTypeNames.CITYNAME))
                .replace("SOMEWEBSITE", RandomDataGenerator.getRandomWebsiteName())
                .replace("SOMEEST", String.valueOf(RandomDataGenerator.getRandomNumberInRange(1900, DateTimeUtils.getCurrentYear())));
    }

    public static Map<String, Object> getCreateAirlinePayloadAsMap() {

        Map<String, Object> payload = new LinkedHashMap<>();
        payload.put("id", RandomDataGenerator.getRandomNumber(9));
        payload.put("name", RandomDataGenerator.getRandomDataFor(RandomDataTypeNames.FIRSTNAME) + " Airlines");
        payload.put("country", RandomDataGenerator.getRandomDataFor(RandomDataTypeNames.COUNTRY));
        payload.put("logo", RandomDataGenerator.getRandomAlphabets(22));
        payload.put("slogan", RandomDataGenerator.getRandomAlphabets(20));
        payload.put("head_quaters", RandomDataGenerator.getRandomDataFor(RandomDataTypeNames.CITYNAME));
        payload.put("website", RandomDataGenerator.getRandomWebsiteName());
        payload.put("established", String.valueOf(RandomDataGenerator.getRandomNumberInRange(1900, DateTimeUtils.getCurrentYear())));
        return payload;

    }

    public static CreateAirlines getCreateAirlinePayloadFromPOJO() {

        return CreateAirlines.builder()
                .setId(RandomDataGenerator.getRandomNumber(9))
                .setName(RandomDataGenerator.getRandomDataFor(RandomDataTypeNames.FIRSTNAME) + " Airlines")
                .setCountry(RandomDataGenerator.getRandomDataFor(RandomDataTypeNames.COUNTRY))
                .setLogo(RandomDataGenerator.getRandomAlphabets(22))
                .setSlogan(RandomDataGenerator.getRandomAlphabets(20))
                .setHeadQuaters(RandomDataGenerator.getRandomDataFor(RandomDataTypeNames.CITYNAME))
                .setWebsite(RandomDataGenerator.getRandomWebsiteName())
                .setEstablished(String.valueOf(RandomDataGenerator.getRandomNumberInRange(1900, DateTimeUtils.getCurrentYear())))
                .build();

    }

    public static CreateAirlines getCreateAirlinePayload_POJO_DP(Map<String, String> dataProviderData) {

        return CreateAirlines.builder()
                .setId(Integer.parseInt(dataProviderData.get("id")))
                .setName(dataProviderData.get("name"))
                .setCountry(dataProviderData.get("country"))
                .setLogo(dataProviderData.get("logo"))
                .setSlogan(dataProviderData.get("slogan"))
                .setHeadQuaters(dataProviderData.get("headquaters"))
                .setWebsite(dataProviderData.get("website"))
                .setEstablished(dataProviderData.get("established"))
                .setExpectedStatusCode(dataProviderData.get("expectedStatusCode")).build();

    }


    public static CreateAirlines getCreateAirlinePayload_POJO_DP_POIJI(CreateAirlines createAirlines) {

        String idValInStringFromExcel = createAirlines.getIdStringValue();

        createAirlines.setId(Integer.parseInt(modifyIdValAsPerRules(idValInStringFromExcel)));

        return createAirlines;
    }


    public static CreateAirlinesAllScenarios getCreateAirlinePayload_AllSceanrios(Map<String, String> dataProviderData) {

        return getCustomizedAirlinesData(dataProviderData);


    }

    private static String modifyIdValAsPerRules(String idValInStringFromExcel) {

        // code to generate random numeral data with size described in excel sheet.
        // if size of numeral data desired is not provided, then default size set = 5
        // Write like a String -> 'Random_4' in excel sheet for a random numeral data of size 4.
        // Write like a String -> 'Random' in excel sheet for a random numeral data of size 6 (default size).
        // Resultant random numeral data is sent as a String from here. So convert to Integer when used in intended methods.


        int size = 6;

        if (idValInStringFromExcel.contains("Random") || idValInStringFromExcel.contains("random")) {

            if (idValInStringFromExcel.contains("_")) {

                String[] temp = idValInStringFromExcel.split("_");
                size = Integer.parseInt(temp[1]);
            }

            idValInStringFromExcel = String.valueOf(RandomDataGenerator.getRandomNumber(size));
        }

        return idValInStringFromExcel;
    }


    private static CreateAirlinesAllScenarios getCustomizedAirlinesData(Map<String, String> data) {

        CreateAirlinesAllScenarios createAirlinesAllScenarios = new CreateAirlinesAllScenarios();

        //not used in actual test here, but may be leveraged as per need.
        createAirlinesAllScenarios.setScenarioDesc(data.get("scenarioDesc"));
        //used to assert in actual test.
        if (!(data.get("exectedErrorMessage").trim().equalsIgnoreCase("NO_DATA")))
            createAirlinesAllScenarios.setExectedErrorMessage(data.get("exectedErrorMessage"));
        //used to assert in actual test.
        if (!(data.get("expectedStatusCode").trim().equalsIgnoreCase("NO_DATA")))
            createAirlinesAllScenarios.setExpectedStatusCode(data.get("expectedStatusCode"));

        if (!(data.get("id").trim().equalsIgnoreCase("NO_DATA")))
            createAirlinesAllScenarios.setId(Integer.parseInt(data.get("id")));
        if (!(data.get("name").trim().equalsIgnoreCase("NO_DATA")))
            createAirlinesAllScenarios.setName(data.get("name"));
        if (!(data.get("country").trim().equalsIgnoreCase("NO_DATA")))
            createAirlinesAllScenarios.setCountry(data.get("country"));
        if (!(data.get("logo").trim().equalsIgnoreCase("NO_DATA")))
            createAirlinesAllScenarios.setLogo(data.get("logo"));
        if (!(data.get("slogan").trim().equalsIgnoreCase("NO_DATA")))
            createAirlinesAllScenarios.setSlogan(data.get("slogan"));
        if (!(data.get("headquaters").trim().equalsIgnoreCase("NO_DATA")))
            createAirlinesAllScenarios.setHeadQuaters(data.get("headquaters"));
        if (!(data.get("website").trim().equalsIgnoreCase("NO_DATA")))
            createAirlinesAllScenarios.setWebsite(data.get("website"));
        if (!(data.get("established").trim().equalsIgnoreCase("NO_DATA")))
            createAirlinesAllScenarios.setEstablished(data.get("established"));


        return createAirlinesAllScenarios;

    }


}