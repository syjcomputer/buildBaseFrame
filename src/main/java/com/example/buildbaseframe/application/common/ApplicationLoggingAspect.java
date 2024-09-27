package com.example.buildbaseframe.application.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <b>服务层日志切面</b>
 *
 * @author lq
 * @version 1.0
 */
@Component
@Aspect
@Slf4j
public class ApplicationLoggingAspect {

    @Autowired
    private ObjectMapper objectMapper;

    @Pointcut("execution(* com.example.buildbaseframe.application.*.service.impl.*.*(..))")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object printLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("进入服务: {}.{}, 入参 {}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                objectMapper.writeValueAsString(joinPoint.getArgs()));
        Object result = joinPoint.proceed();
        log.info("离开服务: {}.{}, 出参 {}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                objectMapper.writeValueAsString(result));
        return result;
    }

}
