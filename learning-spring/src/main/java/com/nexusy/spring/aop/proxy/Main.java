package com.nexusy.spring.aop.proxy;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;

/**
 * @author lan
 * @since 2016-04-20
 */
public class Main {

    public static void main(String[] args) {
        ProxyFactory factory = new ProxyFactory(new SimplePojo());
        factory.addInterface(Pojo.class);
        factory.addAdvice(new MethodInterceptor() {
            @Override
            public Object invoke(MethodInvocation methodInvocation) throws Throwable {
                Object[] params = methodInvocation.getArguments();
                Method method = methodInvocation.getMethod();
                Object target = methodInvocation.getThis();
                System.out.println("before " + method.getName() + " called");
                Object retVal = method.invoke(target, params);
                System.out.println("after " + method.getName() + " called");
                return retVal;
            }
        });
        factory.setExposeProxy(true);

        Pojo pojo = (Pojo) factory.getProxy();

        // this is a method call on the proxy!
        pojo.foo();
    }
}
