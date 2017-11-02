package com.nexusy.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
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
public class SystemArchitecture {

    @Pointcut("execution(public * com.nexusy.spring.aop.*.*(..))")
    public void anyPublicMethod() {
    }

    @Pointcut("within(com.nexusy.spring.aop.*)")
    public void xxx() {
    }

    @Around("xxx()")
    public Object profile(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("before " + pjp.getSignature().getName());
        Object[] args = pjp.getArgs();
        Object result = pjp.proceed(args);
        System.out.println("after " + pjp.getSignature().getName());
        return result;
    }

}
