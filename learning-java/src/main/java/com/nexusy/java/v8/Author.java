package com.nexusy.java.v8;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author lanhuidong
 * @since 2016-10-24
 */
@Repeatable(Authors.class)
@Retention(RetentionPolicy.RUNTIME)
@interface Author {
    String name();
}

@Retention(RetentionPolicy.RUNTIME)
@interface Authors {
    Author[] value();
}
