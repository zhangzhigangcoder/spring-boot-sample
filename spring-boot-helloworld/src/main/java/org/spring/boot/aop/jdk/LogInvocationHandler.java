package org.spring.boot.aop.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * jdk实现动态代理 代理类
 * 
 * @author zhangzhigang
 *
 */
public class LogInvocationHandler implements InvocationHandler {

	private Object target;
	
	public LogInvocationHandler(Object target) {
		this.target = target;
	}
	
	/**
	 * @param proxy 代理类(关联InvocationHandler实例)
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		String result = (String) method.invoke(target, args);
		return "hello:" + result;
	}

	
}
