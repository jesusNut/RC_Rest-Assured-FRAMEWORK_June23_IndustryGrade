package com.jesusnut.Annotations;


import com.jesusnut.enums.Author;
import com.jesusnut.enums.Category;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;

@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Target({METHOD})
@Documented
public @interface FrameworkAnnotation {

    /**
     * Stores ONLY a single author who created the test; from a specified set of
     * Authors in {@link com.jesusnut.enums.Author }; .<br>
     *
     * <pre>
     * NOTE : Only a single author can be assigned to a test case/test method in JesusNut Framework.
     * </pre>
     *
     * Default value is set, for the scenario if no author is provided for a test
     * method while annotating it with FrameworkAnnotation.<br>
     * Include new authors/team memebrs in {@link com.jesusnut.enums.Author}.
     *
     * @return Author
     */

    Author author() default Author.ABHISHEK;

    /**
     * Strores an array of category to which a test case belongs to; from a
     * specified set of Category in {@link com.jesusnut.enums.Category}; .<br>
     * Default value is set, for the scenario if no category is provided for a test
     * method while annotating it with FrameworkAnnotation.<br>
     * Include new test categories in {@link com.jesusnut.enums.Category}.
     *
     * @return array of Category
     */

    Category[] category() default Category.REGRESSION;

}
