package com.abhishekvermaa10.aspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * @author abhishekvermaa10
 */
@Slf4j
@Aspect
@Component
public class LogAspect {

	@Pointcut("execution(* com.abhishekvermaa10.service.impl.*.*(..))")
	public void serviceMethodExpression() {

	}

	@Before("serviceMethodExpression()")
	public void logBeforeAdvice(JoinPoint joinPoint) {
		log.info("Entered {} with arguments as {}", joinPoint.getSignature(), Arrays.toString(joinPoint.getArgs()));
	}

	@AfterReturning(pointcut = "serviceMethodExpression()", returning = "result")
	public void logAfterReturningAdvice(JoinPoint joinPoint, Object result) {
		log.info("Completed {} with result as {}", joinPoint.getSignature(), result);
	}

	@AfterThrowing(pointcut = "serviceMethodExpression()", throwing = "exception")
	public void logAfterThrowingAdvice(JoinPoint joinPoint, Exception exception) {
		log.error("Completed {} with exception as {}", joinPoint.getSignature(), exception.getMessage());
	}
	
}
