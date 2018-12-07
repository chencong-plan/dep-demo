package com.jytpay.depdemo.annotation;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

/**
 * @author www.ccoder.cc
 */
@Documented
@Inherited
@Target({FIELD, METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Validate {

}
