package org.spring.boot.aop.cglib;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * CGLIB实现动态代理 拦截器
 * 
 * @author zhangzhigang
 *
 */
public class CGLIBProxy implements MethodInterceptor {

	private static final Logger log = LoggerFactory.getLogger(CGLIBProxy.class);

	/**
	 * 所有方法都会被这个方法拦截， intercept(CGLIBUserService$$EnhancerByCGLIBProxy$$1465604435,
	 * CGLIB$sayHello$0$Method, new Object[] { paramString },
	 * CGLIB$sayHello$0$Proxy);
	 * 
	 * @param obj    目标类代理子类实例
	 * @param method 目标类方法的反射对象
	 * @param args   方法的动态入参
	 * @param proxy  代理类实例
	 */
	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		log.info("cglib实现的前置代理1");
		// 通过代理类调用父类中的方法
		// CGLIBUserService$$EnhancerByCGLIBProxy$$1465604435.CGLIB$sayHello$0()
		Object result = proxy.invokeSuper(obj, args);
		// 如果这样写，会循环调用，导致内存泄漏
		// CGLIBUserService.sayHello();
//			Object result = proxy.invoke(obj, args);
		log.info("cglib实现的后置代理1");
		return "hello:" + result;
	}

}
