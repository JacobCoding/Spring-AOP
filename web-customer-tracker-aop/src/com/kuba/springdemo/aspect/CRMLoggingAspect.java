package com.kuba.springdemo.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class CRMLoggingAspect {

	// setup logger
	private Logger myLogger = Logger.getLogger(getClass().getName());
	
	
	//setup pointcut declarations for controller
	@Pointcut("execution(* com.kuba.springdemo.controller.*.*(..))")
	private void forControllerPackage(){}
	
	//service
	@Pointcut("execution(* com.kuba.springdemo.service.*.*(..))")
	private void forServicePackage(){}
	
	//dao
	@Pointcut("execution(* com.kuba.springdemo.dao.*.*(..))")
	private void forDaoPackage(){}
	
	@Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()" )
	private void forAppFlow(){}
	
	
	// add @Before advice
	@Before("forAppFlow()")
	public void before(JoinPoint joinPoint){
		// display method we are calling 
		String theMethod = joinPoint.getSignature().toShortString();
		myLogger.info("====> @Before: calling method: " + theMethod );
		
		// display the arguments to the method
		
		// get the arguments 
		Object[] args = joinPoint.getArgs();
		
		
		// loop thru and display args
		for(Object tempArg: args){
			myLogger.info("=====>> argument: " + tempArg);
			
		}
		
		
	}
	
	
	
	//add @AfterReturning advice
	@AfterReturning(
			pointcut = "forAppFlow()",
			returning = "result"
			)
	public void afterReturning(JoinPoint joinPoint, Object result){
		
		// display method we are returning from
		String theMethod = joinPoint.getSignature().toShortString();
		myLogger.info("====> @AfterReturning: calling method: " + theMethod );
		
		// display data returned
		myLogger.info("====>> result:" + result);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
