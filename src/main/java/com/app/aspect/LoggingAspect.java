package com.app.aspect;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	
	@Before("logMessagePointCut()")
	public void logBefofeCallingMethod() {
		System.out.println(" before calling the method ");
	}
	@After("logMessagePointCut()")
	public void logAfterCallingMethod() {
		System.out.println(" after calling the method ");
	}
	@Around("logMessagePointCut()")
	public void logAroundCallingMethod(JoinPoint joinPoint) {
		System.out.println(" around calling the method "+joinPoint.getSignature().getName());
	}
	@Pointcut("within(com.app.controller.*)")
	public void logMessagePointCut() {}
}
