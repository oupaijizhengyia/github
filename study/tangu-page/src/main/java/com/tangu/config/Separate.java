package com.tangu.config;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Separate {
    FromSide value() default FromSide.FIRST;
}
