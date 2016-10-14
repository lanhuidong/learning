package com.nexusy.java.v8;

/**
 * @author lan
 * @since 2016-10-10
 */
public class LambdaTest {

    /**
     * 匿名类和lambda表达式的几个区别
     */
    public void difference() {
        int a = 10;
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                int a = 2;  //可以隐藏外围变量
                System.out.println(a);
                System.out.println(this.getClass().getName()); //this执行Runnable的一个实例
            }
        };

        Runnable r2 = () -> {
            //int a = 2; 编译错误，lambda表达式不能隐藏外围变量
            System.out.println(a);
            System.out.println(this.getClass().getName()); //this指向LambdaTest的一个实例
        };

        r1.run();
        r2.run();

    }

    public static void main(String[] args) {
        new LambdaTest().difference();
    }
}
