package org.spring.boot.aop.cglib;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * CGLIB实现动态代理 被代理类
 * 
 * @author zhangzhigang
 *
 */
public class CGLIBUserService {
	
	private static final Logger log = LoggerFactory.getLogger(CGLIBUserService.class);
	
	public String sayHello(String str) {
		log.info("CGLIBUserService:sayHello() is called");
		return str;
	}
	
	public String sayBye(String str) {
		log.info("CGLIBUserService:sayBye() is called");
		return str;
	}
	
	public String eat(String str) {
		log.info("CGLIBUserService:eat() is called");
		return str;
	}
	
}
