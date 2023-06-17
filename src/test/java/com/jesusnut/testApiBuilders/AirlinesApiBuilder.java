package com.jesusnut.testApiBuilders;

import com.jesusnut.restutils.RestApiBuilder;
import io.restassured.response.Response;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AirlinesApiBuilder {

    public static Response createAirline(Object payload) {

        return RestApiBuilder.performPost("/v1/airlines", payload, new HashMap<>());

    }

    public static Response createAirline(Object payload, Map<String, String> headers) {

        return RestApiBuilder.performPost("/v1/airlines", payload, headers);

    }

}
