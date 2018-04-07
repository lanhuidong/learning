package com.nexusy.java.v8;

/**
 * @author lanhuidong
 * @since 2018-04-04
 */
public class DefaultMethodDemo {

    interface A {

        default void say() {
            System.out.println("Hello from A!");
        }

    }

    interface B extends A {

        default void say() {
            System.out.println("Hello from B!");
        }

    }

    interface C {
        default void say() {
            System.out.println("Hello from C!");
        }
    }

    static class F {
        public void say() {
            System.out.println("Hello from F!");
        }
    }

    /**
     * 1.当父类的方法和接口中的方法冲突时，优先选择父类中的方法
     */
    static class G extends F implements A, B {

    }

    /**
     * 2.更具体的类型的方法将被选择，
     */
    static class D implements B {

    }

    /**
     * 3.当上述两条规则不适用时，需明确指定调用哪个接口的方法，否则编译会失败
     */
    static class E implements A, C {
        @Override
        public void say() {
            C.super.say();
        }

    }

    static class H implements A {

    }

    static class I extends D implements B {

    }

    public static void main(String[] args) {
        new D().say();
        new E().say();
        new G().say();
        new I().say();
    }

}
