package com.app.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	
/*	@Around("logMessagePointCut()")
	public void logBeforeCallingMethod() {
		System.out.println(" before calling the method ");
	}
	@After("logMessagePointCut()")
	public void logAfterCallingMethod() {
		System.out.println(" after calling the method ");
	}*/
	@Before("logMessagePointCut()")
	public void logAroundCallingMethod(JoinPoint joinPoint) throws Throwable {
		System.out.println("Inside Before aspect, calling the method: "+joinPoint.getSignature().toShortString());
		
	}
	@Pointcut("within(com.app.controller.*)")
	public void logMessagePointCut() {}
}
