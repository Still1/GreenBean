package com.oc.greenbean.spring.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {
    @Pointcut("within(com.oc.greenbean..*)")
    private void globalPointCut() {
    }

    @AfterThrowing(pointcut = "globalPointCut()", throwing = "e")
    private void printExceptionInfo(JoinPoint joinPoint, Throwable e) {
        Logger logger = LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringType());
        logger.error(e.getMessage(), e);
    }
}
