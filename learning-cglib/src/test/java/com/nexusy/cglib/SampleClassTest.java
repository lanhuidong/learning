package com.nexusy.cglib;

import net.sf.cglib.proxy.*;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @author lanhuidong
 * @since 2016-04-23
 */
public class SampleClassTest {

    @Test
    public void testFixedValue() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SampleClass.class);
        enhancer.setCallback((FixedValue) () -> "Hello cglib!");
        SampleClass proxy = (SampleClass) enhancer.create();
        //所有的返回类型为String或其父类的方法都返回"Hello cglib!"
        Assert.assertEquals("Hello cglib!", proxy.test(null));
        Assert.assertEquals("Hello cglib!", proxy.toString());
        //返回类型不为String或其父类的方法抛出ClassCastException
        //Assert.assertEquals("Hello cglib!", proxy.hashCode());
    }

    @Test(expected = RuntimeException.class)
    public void testInvocationHandler() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SampleClass.class);
        enhancer.setCallback((InvocationHandler) (o, method, objects) -> {
            if (method.getDeclaringClass() != Object.class && method.getReturnType() == String.class) {
                return "Hello cglib!";
            }
            throw new RuntimeException("Don't know what to do!");
        });
        SampleClass proxy = (SampleClass) enhancer.create();
        Assert.assertEquals("Hello cglib!", proxy.test(null));
        Assert.assertNotEquals("Hello cglib!", proxy.toString());
    }

    @Test
    public void testMethodInterceptor() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SampleClass.class);
        enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> {
            if (method.getDeclaringClass() != Object.class && method.getReturnType() == String.class) {
                return "Hello cglib!";
            }
            return methodProxy.invokeSuper(o, objects);
        });
        SampleClass proxy = (SampleClass) enhancer.create();
        Assert.assertEquals("Hello cglib!", proxy.test(null));
        Assert.assertNotEquals("Hello cglib!", proxy.toString());
    }

    @Test
    public void testCallbackFilter() throws Exception {
        Enhancer enhancer = new Enhancer();
        CallbackHelper callbackHelper = new CallbackHelper(SampleClass.class, new Class[0]) {
            @Override
            protected Object getCallback(Method method) {
                if (method.getDeclaringClass() != Object.class && method.getReturnType() == String.class) {
                    return new FixedValue() {
                        @Override
                        public Object loadObject() throws Exception {
                            return "Hello cglib!";
                        }
                    };
                } else {
                    return NoOp.INSTANCE; // A singleton provided by NoOp.
                }
            }
        };
        enhancer.setSuperclass(SampleClass.class);
        enhancer.setCallbackFilter(callbackHelper);
        enhancer.setCallbacks(callbackHelper.getCallbacks());
        SampleClass proxy = (SampleClass) enhancer.create();
        Assert.assertEquals("Hello cglib!", proxy.test(null));
        Assert.assertNotEquals("Hello cglib!", proxy.toString());
        proxy.hashCode(); // Does not throw an exception or result in an endless loop.
    }

}
