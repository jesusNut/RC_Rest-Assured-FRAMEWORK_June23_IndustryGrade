package com.jesusnut.utils;


import com.jesusnut.constants.FrameworkConstants;
import com.jesusnut.exceptions.FrameworkException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Diff;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JaversPojoCompareUtils {

    //this method is used to compare two POJO objects while assertion of a test case.
    //customize this method as per your need.

    public static void comparePOJOObjects(Object obj1, Object obj2) {

        nullCheckForPojoObjects(obj1, obj2);

        pojoObjectsInstanceCheck(obj1, obj2);

        System.out.println();

        Javers javers = JaversBuilder.javers().build();

        Diff diff = javers.compare(obj1, obj2);

        if (diff.hasChanges()) {

            throw new FrameworkException(FrameworkConstants.FRAMEWORK_EXCEPTION_GENERIC_MESSAGE + "==> Test case failed as there are DIFFERENCES IN THE VALUES OF REQUEST AND RESPONSE POJO OBJECTS !!");

        }
    }

    private static void pojoObjectsInstanceCheck(Object obj1, Object obj2) {
        if (!obj1.getClass().equals(obj2.getClass())) {

            throw new FrameworkException(FrameworkConstants.FRAMEWORK_EXCEPTION_GENERIC_MESSAGE + "==> Test case failed as REQUEST AND RESPONSE POJO OBJECTS ARE NOT OBJECTS FROM SAME POJO CLASS !!!");

        }
    }

    private static void nullCheckForPojoObjects(Object obj1, Object obj2) {
        if (Objects.isNull(obj1) || Objects.isNull(obj2)) {

            throw new FrameworkException(FrameworkConstants.FRAMEWORK_EXCEPTION_GENERIC_MESSAGE + "==> Either of  REQUEST OR RESPONSE POJO IS NULL. Please Check and Fix !!!");

        }
    }

}


