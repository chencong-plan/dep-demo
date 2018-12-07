package com.jytpay.depdemo.annotation;

import java.lang.annotation.*;

/**
 * @author www.ccoder.cc
 */
@Documented
@Inherited
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Validate {

}
