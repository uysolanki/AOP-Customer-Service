package com.excelr.cms.config;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

import com.excelr.cms.controller.CustomerController;

@Configuration
@Aspect
public class AspectConfig {

	private static final Logger logger=Logger.getLogger(CustomerController.class);
	
	@Before("execution(public * com.excelr.cms.controller.*.*(..))")
	public void beforeMethod(JoinPoint joinpoint)
	{
		logger.info("Before "+ joinpoint.getSignature().getName() + " Invoked");
	}
	
	@After("execution(public * com.excelr.cms.controller.*.*(..))")
	public void afterMethod(JoinPoint joinpoint)
	{
		logger.info("After "+ joinpoint.getSignature().getName() + " Invoked");
	}
}
