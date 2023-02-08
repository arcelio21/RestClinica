package com.example.util;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LogAspect {


    @Around("execution(* com.example.service.*.*(..)) || execution(* com.example.controller.*.*.*(..))")
    public Object timeExecutedService(ProceedingJoinPoint joinPoint) throws Throwable{

        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long elasedTime = System.currentTimeMillis() - start;

        log.info("Method {} executed in {} ms", joinPoint.getSignature(), elasedTime);

        return result;
    }

}
