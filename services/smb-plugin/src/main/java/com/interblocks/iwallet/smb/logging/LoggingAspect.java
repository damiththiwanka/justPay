package com.interblocks.iwallet.smb.logging;


import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
@Log4j2
public class LoggingAspect {

    @Before("execution(* com.interblocks.iwallet.smb.servicefacade.*.*(..))")
    public void before(JoinPoint joinPoint) {
        log.info("Invoked {}.{}",
                joinPoint.getTarget().getClass().getSimpleName(),
                joinPoint.getSignature().getName());
        log.debug("Invoked {}.{} with {}",
                joinPoint.getTarget().getClass().getSimpleName(),
                joinPoint.getSignature().getName(), joinPoint.getArgs());
    }

    @AfterReturning(value = "execution(* com.interblocks.iwallet.smb.servicefacade.*.*(..))", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        log.info("{}.{}  returned",
                joinPoint.getTarget().getClass().getSimpleName(),
                joinPoint.getSignature().getName());
        log.debug("{}.{}  returned data: {}",
                joinPoint.getTarget().getClass().getSimpleName(),
                joinPoint.getSignature().getName(), result);
    }
}
