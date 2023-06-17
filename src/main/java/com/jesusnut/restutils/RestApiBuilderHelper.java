package com.jesusnut.restutils;


import com.jesusnut.constants.FrameworkConstants;
import com.jesusnut.exceptions.FrameworkException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RestApiBuilderHelper {

    static String getEnvDataJSONFilePath() {

        //use "-Denv=qa" or "-Denv=dev" from maven

        String env = System.getProperty("env") == null ? "qa" : System.getProperty("env");

        String EnvDataJSONFilePath;

        if (env.equalsIgnoreCase("qa")) {

            EnvDataJSONFilePath = FrameworkConstants.QA_ENV_JSONDATA_FILEPATH;

        } else if (env.equalsIgnoreCase("dev")) {

            EnvDataJSONFilePath = FrameworkConstants.DEV_ENV_JSONDATA_FILEPATH;

        } else {

            throw new FrameworkException(FrameworkConstants.FRAMEWORK_EXCEPTION_GENERIC_MESSAGE + "==> Please provide a valid environment value 'qa'/'dev' only !!!");
        }

        return EnvDataJSONFilePath;
    }


}
