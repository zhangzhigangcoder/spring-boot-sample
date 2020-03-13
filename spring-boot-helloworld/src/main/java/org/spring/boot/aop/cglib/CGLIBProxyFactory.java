package org.spring.boot.aop.cglib;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

/**
 * CGLIB实现动态代理 代理类工厂
 * 
 * @author zhangzhigang
 *
 */
public class CGLIBProxyFactory {
	
	private static final Logger log = LoggerFactory.getLogger(CGLIBProxyFactory.class);

	public static Object getProxy(Class<?> clazz) {
		Enhancer enhancer = new Enhancer();
		// 可选 设置加载器
//		ClassLoader loader = Thread.currentThread().getContextClassLoader();
//		enhancer.setClassLoader(loader);
		// 设置被代理类
		enhancer.setSuperclass(clazz);
		// 设置Callbacks
		Callback[] callbacks = new Callback[4];
		callbacks[0] = NoOp.INSTANCE;
		callbacks[1] = new CGLIBProxy();
		callbacks[2] = new CGLIBProxy2();
		callbacks[3] = new CGLIBProxy3(new CGLIBInterfaceSupport());
		enhancer.setCallbacks(callbacks);
		// 设置CallbackFilter
		// interface
		enhancer.setInterfaces(new Class[] {CGLIBInterface.class});
		// 当设置多个Callback时，要设置CallbackFilter进行路由
		enhancer.setCallbackFilter(new CGLIBFilter());
		// 命名策略
//		enhancer.setNamingPolicy(CGLIBNamingPolicy.getInstance());
		// 通过字节码动态创建子类实例
		return enhancer.create();
	}
	
}
