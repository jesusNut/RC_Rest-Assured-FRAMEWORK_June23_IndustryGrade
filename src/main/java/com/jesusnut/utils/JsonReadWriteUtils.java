package com.jesusnut.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jesusnut.constants.FrameworkConstants;
import com.jesusnut.exceptions.FrameworkException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JsonReadWriteUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static Map<String, Object> getJsonDataAsMap(String filepath) {

        Map<String, Object> data;

        try {

            data = objectMapper.readValue(new File(filepath), new TypeReference<>() {
            });

        } catch (IOException e) {

            throw new FrameworkException(FrameworkConstants.FRAMEWORK_EXCEPTION_GENERIC_MESSAGE + "==> Issue while reading Environment JSON data files", e);

        }

        return data;
    }


}
