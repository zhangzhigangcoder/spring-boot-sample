package org.spring.boot.aop.jdk;

import java.lang.reflect.Proxy;

/**
 * jdk实现动态代理 测试类
 * 
 * @author zhangzhigang
 *
 */
public class JDKProxyTest {
	
	public static void main(String[] args) {
		Target target = new Target();
		TargetInterface targetInterface = (TargetInterface) Proxy.newProxyInstance(Target.class.getClassLoader(), new Class[] {TargetInterface.class}, new LogInvocationHandler(target));
		System.out.println(targetInterface.getClass());
		System.out.println(targetInterface.sayHello("zhangsan"));
	}
}
