package com.jesusnut.extentreports;

import com.aventstack.extentreports.ExtentTest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

//Implementation of thread local class for Extent Test Object's thread Safety during parallel execution


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ExtentTestManager {

    private static ThreadLocal<ExtentTest> exTest = new ThreadLocal<ExtentTest>();

    static ExtentTest getExtentTest() {

        return exTest.get();

    }

    static void setExtentTest(ExtentTest test) {

        exTest.set(test);
    }

}
