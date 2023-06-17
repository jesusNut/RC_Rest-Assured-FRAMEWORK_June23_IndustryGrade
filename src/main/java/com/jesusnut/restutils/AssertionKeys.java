package com.jesusnut.restutils;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
 final class AssertionKeys {

    private String jsonPath;
    private Object expectedValue;
    private Object actualValue;
    private String result;
}
