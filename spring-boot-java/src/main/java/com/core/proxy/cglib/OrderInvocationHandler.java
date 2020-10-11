package com.core.proxy.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.InvocationHandler;


/**
 * @author zhangzhigang
 *
 */
public class OrderInvocationHandler implements InvocationHandler {

	/**
	 * proxy: OrderService$$EnhancerByCGLIB$$47a95d3e
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println(proxy.getClass());
		if (method.getDeclaringClass() != Object.class && method.getReturnType() == String.class) {
			return "hello cglib";
		} else {
			throw new RuntimeException("Do not know what to do.");
		}
	}


}
