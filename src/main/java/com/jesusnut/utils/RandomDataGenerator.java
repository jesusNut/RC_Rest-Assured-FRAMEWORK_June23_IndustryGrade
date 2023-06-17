package com.jesusnut.utils;


import com.jesusnut.enums.RandomDataTypeNames;
import net.datafaker.Faker;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Objects;

public final class  RandomDataGenerator {

    private RandomDataGenerator() {

    }

    // implementing Faker Utils via singleton

    private static Faker instance = null;

    public synchronized static Faker getFakerInstance() {

        if (Objects.isNull(instance)) {

            instance = new Faker();
        }

        return instance;
    }

    public static String getRandomDataFor(RandomDataTypeNames dataTypesNames) {
        switch (dataTypesNames) {
            case FIRSTNAME:
                return getFakerInstance().name().firstName();
            case LASTNAME:
                return getFakerInstance().name().lastName();
            case FULLNAME:
                return getFakerInstance().name().fullName();
            case COUNTRY:
                return getFakerInstance().address().country();
            case CITYNAME:
                return getFakerInstance().address().cityName();
            default:
                return "Data type name not available";
        }
    }

    public static int getRandomNumber(int count) {
        return Integer.parseInt(getFakerInstance().number().digits(count));
    }

    public static int getRandomNumberInRange(int min, int max) {
        return getFakerInstance().number().numberBetween(min, max);
    }

    public static String getRandomAlphabets(int count) {
        return RandomStringUtils.randomAlphabetic(count);
    }

    public static String getRandomWebsiteName() {
        return "https://" + getRandomAlphabets(10) + ".com";
    }


}
