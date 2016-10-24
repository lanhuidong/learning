package com.nexusy.java.v8;

import java.util.Arrays;

/**
 * Java 8开始可以重复使用同一类型注解
 *
 * @author lanhuidong
 * @since 2016-10-24
 */
@Author(name = "lan")
@Author(name = "xx")
@Author(name = "alan")
public class Book {

    public static void main(String[] args) {
        Author author = Book.class.getAnnotation(Author.class);
        System.out.println(author);
        //Output: null

        Authors authors = Book.class.getAnnotation(Authors.class);
        System.out.println(authors);
        //Output: @com.nexusy.java.v8.Authors(value=[@com.nexusy.java.v8.Author(name=lan), @com.nexusy.java.v8.Author(name=xx), @com.nexusy.java.v8.Author(name=alan)])

        Author[] authroList = Book.class.getAnnotationsByType(Author.class);
        Arrays.asList(authroList).forEach(System.out::println);
        /* Output:
            @com.nexusy.java.v8.Author(name=lan)
            @com.nexusy.java.v8.Author(name=xx)
            @com.nexusy.java.v8.Author(name=alan)
         */
    }
}
