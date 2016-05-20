package com.nexusy.spring.aop.demo;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * @author lan
 * @since 2016-04-28
 */
@Aspect
public class FaceSDKProfiling {

    @Around("execution(* com.nexusy.spring.aop.demo.FaceSDK.*(..))")
    public Object profile(ProceedingJoinPoint pjp) throws Throwable {
        long t1 = System.currentTimeMillis();
        String methodName = pjp.getSignature().getName();
        try {
            return pjp.proceed();
        } finally {
            long t2 = System.currentTimeMillis();
            System.out.println(methodName + ":" + (t2 - t1));
        }
    }
}
