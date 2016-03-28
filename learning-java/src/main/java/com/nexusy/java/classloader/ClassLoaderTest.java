package com.nexusy.java.classloader;

/**
 * @author lanhuidong
 * @since 2016-02-20
 */
public class ClassLoaderTest {

    public static void main(String[] args) {
        //原始类型没有ClassLoader, 因此getClassLoader()方法返回null
        ClassLoader intClassLoader = int.class.getClassLoader();
        System.out.println("int's ClassLoader: " + intClassLoader);

        ClassLoader integerClassLoader = ClassLoaderTest.class.getClassLoader();
        System.out.println("ClassLoaderTest's ClassLoader: " + integerClassLoader);

        //数组类型的getClassLoader()方法返回的ClassLoader和数组的元素类型的getClassLoader()的返回结果一致
        int[] array1 = new int[1];
        ClassLoader intArrayClassLoader = array1.getClass().getClassLoader();
        System.out.println("int array's ClassLoader: " + intArrayClassLoader);

        ClassLoaderTest[] array2 = new ClassLoaderTest[1];
        System.out.println("ClassLoaderTest array's ClassLoader: " + array2.getClass().getClassLoader());

        //如果ClassLoader是bootstrap class loader, 则实现getClassLoader()方法时可返回null
        System.out.println(String.class.getClassLoader());

    }
}
