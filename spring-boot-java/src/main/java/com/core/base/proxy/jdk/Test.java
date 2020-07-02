package com.core.base.proxy.jdk;

public class Test {

	public static void main(String[] args) {
		// 代理类的字节码内容保存在了项目根目录下
		System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
		
		OrderProxyFactory orderProxyFactory = new OrderProxyFactory(new OrderServiceImpl());
		OrderService orderServiceProxy = orderProxyFactory.getProxy();
		System.out.println(orderServiceProxy.getClass());
		orderServiceProxy.add(new Order("111", "手机"));
//		orderServiceProxy.delete("111");
	}
}
