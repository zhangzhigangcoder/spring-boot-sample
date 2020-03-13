package org.spring.boot.aop;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 测试AOP
 * 
 * @see 配置类: AopAutoConfiguration   
 * @see @Aspect注解类解析 
 * 	org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator.postProcessBeforeInstantiation()
 * @see 检测bean是否需要代理 
 * 	org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator.postProcessAfterInitialization()
 * @see org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor
 * 多层代理可通过@Order(num)进行排序,num越小，优先级越高
 * @author zhangzhigang
 *
 */
@Aspect
@Order(9)
@Configuration
public class WebLogAspect {
	
	private static final Logger log = LoggerFactory.getLogger(WebLogAspect.class);
	
	// 也可以使用@Around进行统计
	ThreadLocal<Long> startTime = new ThreadLocal<>();
	
	@Pointcut("execution(public * org.spring.boot.controller..*.*(..))")
	public void webLog() {}
	
	@Before("webLog()")
	public void doBefore(JoinPoint joinPoint) throws Throwable {
		startTime.set(System.currentTimeMillis());
		
		// RequestContextHolder获取HTTPServletRequest请求
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		
		// 记录一下请求内容
		log.info("url : " + request.getRequestURI().toString());
		log.info("http method : " + request.getMethod());
		log.info("ip : " + request.getRemoteAddr());
		log.info("class method : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
		log.info("args : " + Arrays.toString(joinPoint.getArgs()));
	}
	
	@AfterReturning(returning = "ret", pointcut = "webLog()")
	public void doAfterReturning(Object ret) throws Throwable {
		//处理完请求，返回内容
		log.info("reponse : " + ret);
		log.info("spend time : " + (System.currentTimeMillis() - startTime.get()));
	}

}
