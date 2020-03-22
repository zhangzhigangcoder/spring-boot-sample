package com.core.proxy.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class OrderMethodInterceptor implements MethodInterceptor {

	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		System.out.println("cglib method before...");
		Object result = proxy.invokeSuper(obj, args);
		System.out.println("cglib method after...");
		return result;
	}
}
