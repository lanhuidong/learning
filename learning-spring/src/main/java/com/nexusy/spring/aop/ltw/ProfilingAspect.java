package com.nexusy.spring.aop.ltw;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.util.StopWatch;

/**
 * @author lan
 * @since 2016-04-27
 */
@Aspect
public class ProfilingAspect {

    @Around("methodToBeProfile()")
    public Object profile(ProceedingJoinPoint pjp) throws Throwable {
        StopWatch watch = new StopWatch(getClass().getSimpleName());
        try {
            watch.start(pjp.getSignature().getName());
            return pjp.proceed();
        } finally {
            watch.stop();
            System.out.println(watch.prettyPrint());
        }
    }

    @Pointcut("execution(public * com.nexusy.spring.aop.ltw.MyService.*(..))")
    public void methodToBeProfile() {

    }
}
