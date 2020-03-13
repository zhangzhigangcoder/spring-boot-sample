package org.spring.boot.aop.cglib;

import net.sf.cglib.core.DebuggingClassWriter;

/**
 * CGLIB实现动态代理 测试类
 * 
 * @author zhangzhigang
 *
 */
public class CGLIBProxyTest {
	
	public static void main(String[] args) {
		// 定义代理类字节码保存路径
		System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:/logs");
		// 生成代理类
		Object bean = CGLIBProxyFactory.getProxy(CGLIBUserService.class);
//		if (bean instanceof CGLIBUserService) {
//			CGLIBUserService userService = (CGLIBUserService) CGLIBProxyFactory.getProxy(CGLIBUserService.class);
//			String result = null;
//			result = userService.sayHello("jack"); // "hello:" + "jack"
//			result = userService.sayBye("jack"); // "Good Bye"
//			result = userService.eat("jack"); // "jack"
//			System.out.println(userService.getClass());
//		}
		
		if (bean instanceof CGLIBInterface) {
			CGLIBInterface inter = (CGLIBInterface) bean;
			System.out.println(inter.getName("peter"));
		}
	}
}
