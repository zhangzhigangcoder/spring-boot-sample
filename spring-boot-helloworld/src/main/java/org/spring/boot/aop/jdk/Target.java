package org.spring.boot.aop.jdk;

/**
 * jdk实现动态代理 实现类
 * 
 * @author zhangzhigang
 *
 */
public class Target implements TargetInterface {

	@Override
	public String sayHello(String name) {
		return name;
	}
	
}
