package org.spring.boot.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

@Aspect
@Order(6)
//@Configuration
public class WebLogAspect2 {
	
	private static final Logger log = LoggerFactory.getLogger(WebLogAspect2.class);
	
	@Pointcut("execution(public * org.spring.boot.controller..*.*(..))")
	public void webLog() {}
	
	@Before("webLog()")
	public void doBefore(JoinPoint joinPoint) throws Throwable {
		log.info("----------doBefore-----------");
	}
	
	@AfterReturning(returning = "ret", pointcut = "webLog()")
	public void doAfterReturning(Object ret) throws Throwable {
		log.info("----------doAfterReturning-----------");
	}

}
