package com.core.base.proxy.cglib;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;

/**
 * Enhancer不能拦截final的类和方法
 * @author zhangzhigang
 *
 */
public class Test {

	public static void main(String[] args) {
		
		System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "/Users/zhangzhigang/Documents/baiduNetdisk/java-sample");
		
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(OrderService.class);
		// 自定义MethodInterceptor
//		enhancer.setCallback(new OrderMethodInterceptor());
		
		// 对拦截的所有方法都返回相同返回值
//		enhancer.setCallback(new FixedValueInterceptor());
		
		// 自定义InvocationHandler
//		enhancer.setCallback(new OrderInvocationHandler());
		
		// 根据不用方法采取不同的处理
		OrderCallbackHelper callbackHelper = new OrderCallbackHelper(OrderService.class, OrderService.class.getInterfaces());
		enhancer.setCallbackFilter(callbackHelper);
		enhancer.setCallbacks(callbackHelper.getCallbacks());
		
		// OrderService$$EnhancerByCGLIB$$47a95d3e
		OrderService orderService = (OrderService) enhancer.create();
		System.out.println(orderService.add("111"));
		System.out.println(orderService.toString());
	}
}
