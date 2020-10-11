package com.core.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class OrderProxyFactory implements InvocationHandler {

	private Object target;
	
	
	public OrderProxyFactory(Object target) {
		super();
		this.target = target;
	}

	/**
	 * 
	 * @return $Proxy0对象
	 */
	@SuppressWarnings("unchecked")
	public <T> T getProxy() {
		return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
	}
	
	
	/**
	 * proxy $Proxy0对象
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("order handler before...");
		Object result = method.invoke(target, args);
		System.out.println("order handler after...");
		return result;
	}

}
