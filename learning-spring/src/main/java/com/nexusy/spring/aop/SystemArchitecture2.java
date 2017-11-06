package com.nexusy.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * within, widthcode的匹配速度很快
 * <p>
 * 来自spring文档：a good pointcut should always include one if possible
 *
 * @author lanhuidong
 * @since 2017-11-02
 */
@Component
@Aspect
@Order(200)
public class SystemArchitecture2 {

    @Pointcut("execution(public * com.nexusy.spring.aop.*.*(..))")
    public void anyPublicMethod() {
    }

    @Pointcut("within(com.nexusy.spring.aop.*)")
    public void xxx() {
    }

    @Around("xxx()")
    public Object profile(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("before 2 " + pjp.getSignature().getName());
        Object result = pjp.proceed();
        System.out.println("after 2 " + pjp.getSignature().getName());
        return result;
    }

}
